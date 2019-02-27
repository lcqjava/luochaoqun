package com.luochaoqun.util.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月24日 下午12:39:32 
 * @today: 
 */
public class RedisClusterUtil {

	private static JedisCluster jedisCluster = null;
	
	private static JedisCluster getJedisCluster(){
		if(jedisCluster == null){
			synchronized (jedisCluster) {
				if(jedisCluster == null){
					jedisCluster = initJedisCluster();
				}
			}
		}
		
		return jedisCluster;
	}
	
	private  static JedisCluster initJedisCluster() {
	    JedisPoolConfig config = new JedisPoolConfig();
	    config.setMaxTotal(1);
	   
	    // 集群模式
	    Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
	    String host = "47.94.170.165";
	    int[] port = {7000,7001,7002,7003,7004,7005};
	    for(int i = 0;i<port.length;i++){
	    		HostAndPort hostAndPort = new HostAndPort(host, port[i]);
	    		jedisClusterNode.add(hostAndPort);
	    }

	    int connectionTimeout = 6000;
	    //返回值的超市时间
	    int soTimeout = 3000;
	    int maxAttempts = 3;
	    String password = "passwd123";
	    
	    JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 
	    		connectionTimeout, 
	    		soTimeout, 
	    		maxAttempts, 
	    		password, 
	    		new GenericObjectPoolConfig());
	    
	    return jedisCluster;
	}
	
	/*** string结构 ***/
	/**
	 * 赋值并设置过期时间
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void setex(String key,String value,int seconds){
		jedisCluster.setex(key, seconds, value);
	}
	
	/**
	 * 赋值并设置过期时间
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void setex(byte[] key,byte[] value,int seconds){
		jedisCluster.setex(key, seconds, value);
	}
	
	/**
	 * 根据key获取数据
	 * @param key
	 * @return
	 */
	public String get(String key){
		return jedisCluster.get(key);
	}
	
	/**
	 * 根据key获取数据
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		return jedisCluster.get(key);
	}
	
	/*** List数据结构 ***/
	/**
	 * @param key 键
	 * @param listPosition after 或者 before
	 * @param pivot 参照的值
	 * @param value 值
	 * @return
	 */
	public Long linsert(String key,BinaryClient.LIST_POSITION listPosition,String pivot,String value){
		return jedisCluster.linsert(key, BinaryClient.LIST_POSITION.AFTER, pivot, value);
	}
	
	/**
	 * list 的长度
	 * @param key
	 * @return
	 */
	public Long len(String key){
		return jedisCluster.llen(key);
	}
	
	/**
	 * list根据下标获取对应元素值
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key,Integer index){
		return jedisCluster.lindex(key, index);
	}
	
	/**
	 * list 修改指定下标值
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public String lset(String key,Integer index,String value){
		return jedisCluster.lset(key, index, value);
	}
	
	/**
	 * 移除并返回头元素，如果key不存在返回null
	 * @param key
	 * @return
	 */
	public String lpop(String key){
		return jedisCluster.lpop(key);
	}
	
	/**
	 * 在表头增加元素
	 *   1.如果key不存在，新建一个队列
	 * @return
	 */
	public Long lpush(String key,String value){
		return jedisCluster.lpushx(key, value);
	}
	
	/**
	 * 在表头增加元素
	 *   1.如果key不存在，不会新建队列
	 * @param key
	 * @param value
	 * @return
	 */
	public Long lpushx(String key,String value){
		return jedisCluster.lpushx(key, value);
	}
	
	/**
	 * 获取列表中指定下标值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key,Integer start,Integer end){
		return jedisCluster.lrange(key, start, end);
	}
	
	/**
	 * 删除列表中元素
	 *  备注：  
	 *   1.count > 0 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
	 *   2.count < 0 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值
	 *   3.count = 0 移除表中所有与 value 相等的值。
	 * @param key
	 * @param count
	 * @param value
	 */
	public Long lrem(String key,Integer count,String value){
		return jedisCluster.lrem(key, count, value);
	}
	
	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
	 *  备注：
	 *    1.以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
	 *	  2.可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public String ltrim(String key,Integer start,Integer end){
		return jedisCluster.ltrim(key, start, end);
	}
	
	/**
	 * 移除并返回队列尾元素
	 * @param key
	 * @return
	 */
	public String rpop(String key){
		return jedisCluster.rpop(key);
	}
	
	/**
	 * 将队列srckey的末尾元素弹出，并插入队列dstkey的头部
	 */
	public String rpoppush(String srckey,String dstkey){
		return jedisCluster.rpoplpush(srckey, dstkey);
	}
	
	/**
	 * 从队列末尾开始插入
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rpush(String key,String value){
		return jedisCluster.rpush(key, value);
	}
	
	/**
	 * 队列末尾开始插入，如果存在该队列则插入，不存在则不执行插入
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rpushx(String key,String value){
		return jedisCluster.rpushx(key, value);
	}
	
	/**
	 * 阻塞是从队列头部获取，如果当前队列有值则直接返回，如果没有则等待指定时间直到队列中有数据
	 * @param key
	 * @param timeout
	 * @return
	 */
	public List<String> blpop(String key,Integer timeout){
		return jedisCluster.blpop(timeout, key);
	}
	
	/**
	 * 阻塞是从队列尾部获取，如果当前队列有值则直接返回，如果没有则等待指定时间直到队列中有数据
	 * @param key
	 * @param timeout
	 * @return
	 */
	public List<String> brpop(String key,Integer timeout){
		return jedisCluster.brpop(timeout, key);
	}
	
	/**
	 * 阻塞式，从队列source中获取尾部元素，如果有数据和poplpush一样，如果没有值会等待指定时间直到获取数值插入队列destionation头部
	 * @param key
	 * @param timeout
	 * @return
	 */
	public String brpoplpush(String source,String destination,Integer timeout){
		return jedisCluster.brpoplpush(source, destination, timeout);
	}
	
	/*** 
	 * hash数据结构 
	 *   描述：
	 *     1.数据不可以重复
	 *     2.数据结构为:key field value
	 ***/
	
	/**
	 * 新增hash队列，如果不存在该队列新增一个;
	 * 如果hash队列中已经存在了，则覆盖之前的
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hset(String key,String field,String value){
		return jedisCluster.hset(key, field, value);
	}
	
	/**
	 * 如果field已经存在了，该操作无效
	 * 如果key不存在，则新建一个hash
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hsetnx(String key,String field,String value){
		return jedisCluster.hsetnx(key, field, value);
	}
	
	/**
	 * 返回hash所有的field
	 * @param key
	 * @return
	 */
	public Set<String> hkeys(String key){
		return jedisCluster.hkeys(key);
	}
	
	/**
	 * 返回hash所有的value
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key){
		return jedisCluster.hvals(key);
	}
	
	/**
	 * 返回hash总行数
	 * @param key
	 * @return
	 */
	public Long hlen(String key){
		return jedisCluster.hlen(key);
	}
	
	/**
	 * 可以一次性查多个field对应的值
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(String key,String...fields){
		return jedisCluster.hmget(key, fields);
	}
	
	/**
	 * 如果key不存在，会新建hash队列并执行hmset
	 * 成功返回ok(返回boolean或抛异常岂不是更好)
	 * @param key
	 * @param fieldValueMap
	 * @return
	 */
	public String hmset(String key,Map<String, String> fieldValueMap){
		return jedisCluster.hmset(key, fieldValueMap);
	}
	
	/**
	 * 查看key-field对应的值是否存在
	 * @param key
	 * @param field
	 * @return
	 */
	public Boolean hexists(String key,String field){
		return jedisCluster.hexists(key, field);
	}
	
	/**
	 * 根据key-field获取对应值
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key,String field){
		return jedisCluster.hget(key, field);
	}
	
	/**
	 * 获取hash表中所有的field和value
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetall(String key){
		return jedisCluster.hgetAll(key);
	}
	
	/**
	 * 对value值加1，如果不存在field则新建并初始化值为0
	 * 如果field之前就存在，value不是integer抛异常
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hincrby(String key,String field,Integer value){
		return jedisCluster.hincrBy(key, field, value);
	}
	
	/**
	 * 按指定增量递增
	 * @param key
	 * @param field
	 * @param increment
	 * @return
	 */
	public Double hincrbyfloat(String key,String field,float increment){
		return jedisCluster.hincrByFloat(key, field, increment);
	}
	
	/***** set数据结构：1）无序；2）不可重复*******/
	public Long sadd(String key,String member){
		return jedisCluster.sadd(key, member);
	}
	
	/**
	 * 返回集合元素个数
	 * @param key
	 * @return
	 */
	public Long scard(String key){
		return jedisCluster.scard(key);
	}
	
	/**
	 * 返回集合的所有成员
	 * @param key
	 * @return
	 */
	public Set<String> sdiff(String key){
		return jedisCluster.sdiff(key);
	}
	
	/**
	 * 如果只有一个参数，返回的
	 * @param key
	 * @return
	 */
	public Set<String> sinter(String... keys){
		return jedisCluster.sinter(keys);
	}
	
	/**
	 * set集合中是否存在key
	 * @param key
	 * @param member
	 * @return
	 */
	public boolean sIsMember(String key,String member){
		return jedisCluster.sismember(key, member);
	}
	
	/**
	 * set中所有的值
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key){
		return jedisCluster.smembers(key);
	}
	
	/**
	 * 返回存储字符串的长度
	 * @param key
	 * @return
	 */
	public Long strlen(String key){
		return jedisCluster.strlen(key);
	}
	
	
	/*** 公共部分 ***/
	/**
	 * seconds秒后过期，自动删除
	 * @param key
	 * @param seconds
	 */
	public void expire(String key,Integer seconds){
		jedisCluster.expire(key, seconds);
	}
	
	/**
	 * 设置过期时间，以毫秒为单位
	 * @param key
	 * @param milliseconds
	 * @return
	 */
	public Long pexpire(String key,Long milliseconds){
		return jedisCluster.pexpire(key, milliseconds);
	}

	public Long delete(String key){
		return jedisCluster.del(key);
	}

	public boolean exists(String key){
		return jedisCluster.exists(key);
	}
	
	/**
	 * 设置截止有效期，单位：毫秒时间戳
	 * @param key
	 * @param millisecondsTimestamp
	 * @return
	 */
	public Long pexpireat(String key,long millisecondsTimestamp){
		return jedisCluster.pexpireAt(key, millisecondsTimestamp);
	}
	
	/**
	 * 在特定的时间过期
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireat(String key,Long unixTime){
		return jedisCluster.expireAt(key, unixTime);
	}
	
	/**
	 * 设置为永不过期
	 * @param key
	 * @return
	 */
	public Long persist(String key){
		return jedisCluster.persist(key);
	}
	
	/**
	 * 返回有效时间，单位：毫秒
	 * key不存在返回-2
	 * key存在但没有生存时间返回-1
	 * @param key
	 * @return
	 */
	public Long pttl(String key){
		return jedisCluster.pttl(key);
	}
	
	/**
	 * 重命名key,如果key不存在会返回一个错误
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public String rename(String oldkey,String newkey){
		return jedisCluster.rename(oldkey, newkey);
	}
	
	/**
	 * newkey不存在的时候才会执行
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public Long renamenx(String oldkey,String newkey){
		return jedisCluster.renamenx(oldkey, newkey);
	}
	
//	public Long sort(String key){
//		return jedisCluster.sort(key, sortingParameters, dstkey)
//	}
	
	/**
	 * 返回key的过期时间，单位：秒
	 * @param key
	 * @return
	 */
	public Long ttl(String key){
		return jedisCluster.ttl(key);
	}
	
	/**
	 * 返回key对应的数据类型：none (key不存在)
		string (字符串)
		list (列表)
		set (集合)
		zset (有序集)
		hash (哈希表)
	 * @param key
	 * @return
	 */
	public String type(String key){
		return jedisCluster.type(key);
	}
	
	/**
	 * sortedSet
	 * 有序的集合
	 */
	/**
	 * 如果key不存在，则创建一个空的有序集合并执行zadd操作
	 * 如果key存在，但不是一个有序集合返回一个错误
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public Long zadd(String key,Double score,String member){
		return jedisCluster.zadd(key, score, member);
	}

	public Long zcount(String key,long start,long end){
		return jedisCluster.zcount(key, start, end);
	}
	
	public Set<String> zrange(String key,long start,Long end){
		return jedisCluster.zrange(key, start, end);
	}
	
	public Set<String> zrangebyscore(String key,Double min,Double max){
		return jedisCluster.zrangeByScore(key, min, max);
	}
	
	/**
	 * 返回成员的排名
	 * @param key
	 * @param member
	 */
	public Long zranke(String key,String member){
		return jedisCluster.zrank(key, member);
	}
	
	/**
	 * 移除元素
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrem(String key,String member){
		return jedisCluster.zrem(key, member);
	}
	
	/**
	 * 移除指定排名的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zremrangeByRank(String key,Long start,Long end){
		return jedisCluster.zremrangeByRank(key, start, end);
	}
	
	/**
	 * 移除指定分数的成员
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zremRangeByScore(String key,Double start,Double end){
		return jedisCluster.zremrangeByScore(key, start, end);
	}
	
	/**
	 * 返回指定区间的成员
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrevRange(String key,Long start,Long end){
		return jedisCluster.zrevrange(key, start, end);
	}
	
	/**
	 * 返回指定取件的成员，从大到小排列
	 * @return
	 */
	public Set<String> zrevRangeByScore(String key,Double max,Double min){
		return jedisCluster.zrevrangeByScore(key, max, min);
	}
	
	/**
	 * 返回member对应的score
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zrevRang(String key,String member){
		return jedisCluster.zscore(key, member);
	}
	
	
	 /*************** 订阅发布 ***************/
	
	
}
