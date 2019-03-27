package com.luochaoqun.test.concurrent;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description: 线程顺序执行:后一个线程持有前一个线程的实例，并调用上一个线程的join
 * @author: 小艾亲亲
 * @date: 2019年3月7日 上午12:07:45
 * @today:
 */
public class ThreadSerializeExecuteByJoin {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Work(null));
		Thread thread1 = new Thread(new Work(thread));
		Thread thread2 = new Thread(new Work(thread1));
		
		thread.start();
		thread1.start();
		thread2.start();
	}
}

class Work implements Runnable {
	private Thread beforeThread;

	public Work(Thread beforeThread) {
		this.beforeThread = beforeThread;
	}

	@Override
	public void run() {
		if (beforeThread != null) {
			try {
				beforeThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("thread start:" + Thread.currentThread().getName());
	}

}