package com.luochaoqun.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 线程顺序执行，CountDownLatch每调用一次countDown将会减1，直到
 *               计数器值为0就代表条件成熟，所以调用await方法的线程会被唤醒
 * @author: 小艾亲亲     
 * @date:   2019年3月7日 上午12:16:36 
 * @today: 
 */
public class ThreadSerializeExecuteByCountDownLatch {

	public static void main(String[] args) {
		CountDownLatch countDownLatch0 = new CountDownLatch(0);
		CountDownLatch countDownLatch1 = new CountDownLatch(1);
		CountDownLatch countDownLatch2 = new CountDownLatch(1);
		
		Thread thread = new Thread(new Worker(countDownLatch0, countDownLatch1));
		Thread thread1 = new Thread(new Worker(countDownLatch1, countDownLatch2));
		Thread thread2 = new Thread(new Worker(countDownLatch2, countDownLatch2));
		
		thread.start();
		thread1.start();
		thread2.start();
		
		
	}
	
}


class Worker implements Runnable{

	private CountDownLatch c1;
	private CountDownLatch c2;
	
	public Worker(CountDownLatch c1,CountDownLatch c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public void run() {
		try {
			c1.await();
			System.out.println("thread start:"+Thread.currentThread().getName());
			c2.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}