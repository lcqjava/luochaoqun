package com.luochaoqun.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年3月9日 下午7:25:15
 * @today:
 */
public class ElasticsearchDemo {

	private final static String host = "47.94.170.165";
	private static TransportClient transportClient = null;;

	public static GetResponse get(String index, String type, String id) {
		GetResponse getResponse = transportClient.prepareGet(index, type, id).get();
		return getResponse;
	}

	public static IndexResponse prepareIndex(String index, String type, Byte[] jsonByte) {
		IndexResponse indexResponse = transportClient.prepareIndex(index, type).setSource(jsonByte, XContentType.JSON)
				.get();
		return indexResponse;
	}

	public static DeleteResponse delete(String index, String type, String id) {
		DeleteResponse deleteResponse = transportClient.prepareDelete(index, type, id).get();
		return deleteResponse;
	}

	public static void deleteByQuery() {
		DeleteByQueryAction.INSTANCE.newRequestBuilder(transportClient).filter(QueryBuilders.matchQuery("user", "luo"))
				.source("twitter").execute(new ActionListener<BulkByScrollResponse>() {

					@Override
					public void onResponse(BulkByScrollResponse arg0) {
						long deleteId = arg0.getDeleted();
						System.out.println("deletedId=" + deleteId);
					}

					@Override
					public void onFailure(Exception arg0) {
						System.out.println("delete failed");
					}
				});
	}

	public static XContentBuilder getGeoPointJson(double lat, double lon, String text) throws IOException {
		return XContentFactory.jsonBuilder().startObject().startObject("pin").startObject("location").field("lat", lat)
				.field("lon", lon).endObject().field("name", text).endObject().endObject();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Settings settings = Settings.builder().put("cluster.name", "lcq-es").build();
			transportClient = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9300))
					.addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9301))
					.addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9302));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", "luochaoqun");
			map.put("postDate", new Date());
			map.put("message", "trying out es");
			ObjectMapper mapper = new ObjectMapper();
			byte[] json = mapper.writeValueAsBytes(map);

			// IndexResponse response =
			// transportClient.prepareIndex("twitter","tweet")
			// .setSource(json, XContentType.JSON)
			// .get();
			// System.out.println(response.toString());

			// GetResponse getResponse = transportClient.prepareGet("twitter",
			// "tweet","N5KJYmkB-s7wKC0EFJj-").get();

			// GetResponse getResponse =
			// transportClient.prepareGet().setIndex("twitter").setType("tweet").get();
			// System.out.println(getResponse.toString());

			/*
			 * BulkRequestBuilder prepareBulk = transportClient.prepareBulk();
			 * String index = "charger"; String type = "gun"; String text =
			 * "深圳航盛大厦地下停车场充电桩"; for(int i = 0;i<50;i++){ try { XContentBuilder
			 * source = getGeoPointJson(22+i, 90+i,text);
			 * prepareBulk.add(transportClient.prepareIndex(index,
			 * type).setSource(source)); } catch (IOException e) {
			 * e.printStackTrace(); } }
			 * 
			 * BulkResponse response = prepareBulk.execute().actionGet();
			 * System.out.println(response.toString());
			 */

			GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location").point(40, 116.5)
					.distance(20, DistanceUnit.KILOMETERS)
					// .optimizeBbox("memory")
					.geoDistance(GeoDistance.ARC);
			SearchResponse response = transportClient.prepareSearch("charger")
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(queryBuilder).execute().actionGet();
			System.out.println(response);
			System.err.println(response.getHits().totalHits);

		} catch (UnknownHostException | JsonProcessingException e) {
			e.printStackTrace();
		} finally {
			if (transportClient != null) {
				transportClient.close();
			}
		}

	}

}
