package com.luochaoqun.javacore.concurrent.threadlocal;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月5日 下午12:41:33 
 * @today: 
 */
public class ThreadLocalTest {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	static{
		threadLocal.set("asdf");
	}
	
	public static void main(String[] args) {
		String threadLocalName = threadLocal.get();
		System.out.println(threadLocalName);
	}
	
}
