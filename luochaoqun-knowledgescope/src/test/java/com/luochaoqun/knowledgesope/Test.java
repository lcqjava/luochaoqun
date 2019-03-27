package com.luochaoqun.knowledgesope;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月10日 下午8:25:49 
 * @today: 
 */
public class Test {

	@org.junit.Test
	public void test(){
		// 源代码，value=null直接抛异常了,key没有判断是否为空就直接hash()，如果key为空也会抛异常
		Hashtable<Object, Object> hashtable = new Hashtable<>();
		hashtable.put(null, null);
		
		//hash()方法里有判断key为null则返回0
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put(null, null);
		
		//这两个类还是相同的三个人写的，hashTable是1.0引入的，hashMap是1.2引入的，可能一开始觉得
		//没有情况下会null作为key，实际上也很少以null作为主键
		
	}
	
	@org.junit.Test
	public void testCopyOnWriteArrayList(){
	}
}
