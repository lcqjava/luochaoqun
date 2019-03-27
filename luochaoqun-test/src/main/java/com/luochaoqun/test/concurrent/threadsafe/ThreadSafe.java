package com.luochaoqun.test.concurrent.threadsafe;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月27日 下午9:27:32 
 * @today: 
 */
public class ThreadSafe implements Runnable{

	
	private FlushService flushService;
	private RequestBo requestBo;
	public ThreadSafe(FlushService flushService,RequestBo requestBo) {
		this.flushService = flushService;
		this.requestBo = requestBo;
	}
	
	@Override
	public void run() {
		flushService.flushCache(requestBo);
	}

	public static void main(String[] args) {
		FlushService flushService = new FlushService();
		RequestBo requestBo = new RequestBo();
		Set<String> customerIds = new HashSet<>();
		customerIds.add("a");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		requestBo.setBegin(timestamp);
		requestBo.setEnd(timestamp);
		requestBo.setCustomerIds(customerIds);
		
		Thread thread = new Thread(new ThreadSafe(flushService, requestBo));
		thread.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		RequestBo requestBo2 = new RequestBo();
//		Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
//		requestBo2.setBegin(timestamp2);
//		requestBo2.setEnd(timestamp2);
//		Set<String> customerIds2 = new HashSet<>();
//		customerIds2.add("A");
//		requestBo2.setCustomerIds(customerIds2);
//		Thread thread2 = new Thread(new ThreadSafe(flushService, requestBo2));
//		
//		thread2.start();
		
		ThreadSafeVistMethod threadSafeVistMethod = new ThreadSafeVistMethod(flushService);
		Thread thread3 = new Thread(threadSafeVistMethod);
		thread3.start();
	}
	
}

class ThreadSafeVistMethod implements Runnable{

	
	private FlushService flushService;
	public ThreadSafeVistMethod(FlushService flushService) {
		this.flushService = flushService;
	}
	
	@Override
	public void run() {
		flushService.testSynMethod();
	}

}


class FlushService {
	
	public void testNotSynMethod(){
		System.out.println("测试访问非同步方法");
		
	}
	public synchronized void testSynMethod(){
		System.out.println("测试访问同步方法");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("testSynMethod释放");
	}
	
	public synchronized void flushCache(RequestBo requestBo){
		System.out.println(JSONObject.toJSONString(requestBo));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i = 0;i<20;i++){
			System.out.println("for:"+JSONObject.toJSONString(requestBo));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(JSONObject.toJSONString(requestBo));
	}
}

class RequestBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7718293926875757307L;
	
	private Timestamp begin;
	private Timestamp end;
	private Set<String> customerIds;
	public Timestamp getBegin() {
		return begin;
	}
	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public Set<String> getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(Set<String> customerIds) {
		this.customerIds = customerIds;
	}
	
}
