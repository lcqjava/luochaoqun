package com.luochaoqun.test.concurrent.threadsafe;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月28日 上午12:09:39 
 * @today: 
 */
public class SynchronizedObject {

	public static void main(String[] args) {
		Service service = new Service();
		String object = "a";
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				service.method(object);
			}
		});
		Thread thread05 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				service.method("b");
			}
		});
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				service.method1("b");
			}
		});
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				service.method2("c");
			}
		});
		
		thread.start();
		thread05.start();
		//thread1.start();
		thread2.start();
		
	}
	
}

class Service{
	
	public void method(String object){
		synchronized (object) {
			System.out.println(Thread.currentThread().getName()+"method 开始:object="+object);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"method 结束 :object="+object);
		}
	}
	
	public void method1(String object){
			System.out.println("method1 开始:object="+object);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("method1 结束 :object="+object);
	}
	
	public synchronized void method2(String object){
		System.out.println("method2 开始:object="+object);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("method2 结束 :object="+object);
}
}

