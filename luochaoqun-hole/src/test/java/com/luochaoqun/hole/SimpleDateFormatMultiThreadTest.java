package com.luochaoqun.hole;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;


/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2018年12月16日 上午9:09:31
 * @today:
 */
public class SimpleDateFormatMultiThreadTest {

	public static class TestSimpleDateFormatMultiThread extends Thread {

		private AtomicInteger index = new AtomicInteger(0);

		@Override
		public void run() {
//			System.out.println(Thread.currentThread().getName() + "-" + index.getAndIncrement());
			while (true) {
				try {
//					System.out.println("before:" + Thread.currentThread().getName() + "-" + index.get());
					this.join(200);
//					System.out.println("after:" + Thread.currentThread().getName() + "-" + index.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					//存在并发问题
//					SimpleDateFormatMultiThread.parse("2018-12-16 09:12:28");
//					SimpleDateFormatMultiThread2.parse("2018-12-16 09:12:28");
					System.out.println(SimpleDateFormatMultiThread3.parse("2018-12-16 09:12:28"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new TestSimpleDateFormatMultiThread().start();
		}
	}
	@Test
	public void testMultiEnv() {
		for (int i = 0; i < 3; i++) {
			new TestSimpleDateFormatMultiThread().start();
		}
	}
	
	@Test
	public void testSingleThreadEnv(){
		try {
			Date date = SimpleDateFormatMultiThread.parse("2018-12-16 09:12:28");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test(){
		String str = "27d0519f06ab4381a7cd67f620cc0362";
		System.out.println(str.hashCode());
	}
	

}
