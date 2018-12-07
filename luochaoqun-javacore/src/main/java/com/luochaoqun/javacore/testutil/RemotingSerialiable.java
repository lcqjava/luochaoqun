package com.luochaoqun.javacore.testutil;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2018年8月2日 下午11:52:15
 * @today:
 */
public abstract class RemotingSerialiable {
	private final static Charset CHARSET_UTF8 = Charset.forName("UTF-8");

	/**
	 * json转对象
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(final String json, Class<T> classOfT) {
		return JSON.parseObject(json, classOfT);
	}

	/**
	 * 对象转json
	 * 
	 * @param obj
	 * @param prettyFormat
	 * @return
	 */
	public static String toJson(final Object obj, boolean prettyFormat) {
		return JSON.toJSONString(obj, prettyFormat);
	}
	
	public static String toJson(final Object obj) {
		return JSON.toJSONString(obj, false);
	}

	public  String toJson(final boolean prettyFormat) {
		return toJson(this, prettyFormat);
	}
	
	/**
	 * 解码
	 * @param data
	 * @param classOfT
	 * @return
	 */
	public static <T> T decode(final byte[] data, Class<T> classOfT) {
		String json = new String(data, CHARSET_UTF8);
		return fromJson(json, classOfT);
	}

	/**
	 * 把对象转成字节码
	 * 
	 * @return
	 */
	public static byte[] encode(final Object obj) {
		final String json = toJson(obj, true);
		if (json != null) {
			return json.getBytes(CHARSET_UTF8);
		}
		return null;
	}

}
