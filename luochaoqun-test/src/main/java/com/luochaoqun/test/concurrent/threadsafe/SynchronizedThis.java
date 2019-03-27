package com.luochaoqun.test.concurrent.threadsafe;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: synchronized(this)
 *               1.对其他synchronized同步方法和synchronized(this)同步代码块会造成阻塞
 *               2.同一时间只有一个线程执行synchronized代码块中的代码
 *               
 * @author: 小艾亲亲     
 * @date:   2019年3月27日 下午11:22:45 
 * @today: 
 */
public class SynchronizedThis {

	public static void main(String[] args) {
		SynchronizedService synchronizedService = new SynchronizedService();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronizedService.method1();
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronizedService.method2();
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronizedService.method3();
			}
		});
		
		thread.start();
		thread2.start();
		thread3.start();
	}
	
}

class SynchronizedService{
	
	public void method1(){
		synchronized (this) {
			System.out.println("同步方法1开始");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("同步方法1结束");
		}
	}
	
	public synchronized void method2(){
		System.out.println("同步方法2开始");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("同步方法2结束");
	}
	
	public void method3(){
		System.out.println("非同步方法3开始");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("非同步方法3结束");
	}
}
