package com.luochaoqun.test.concurrent.threadsafe;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description: synchronized(class) 锁住的是类，如果new出来的对象和class和synchronized方法用到不是同一把锁
 *               
 * @author: 小艾亲亲
 * @date: 2019年3月28日 上午12:33:01
 * @today:
 */
public class SynchronizedClass {

	public static void main(String[] args) {
		ServiceSynchronizedClass serviceSynchronizedClass = new ServiceSynchronizedClass();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ServiceSynchronizedClass serviceSynchronizedClass1 = new ServiceSynchronizedClass();
				serviceSynchronizedClass1.method();
			}
		});
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread thread1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				ServiceSynchronizedClass serviceSynchronizedClass1 = new ServiceSynchronizedClass();
				serviceSynchronizedClass1.method1();
			}
		});
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread thread2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				serviceSynchronizedClass.method2();
			}
		});
		Thread thread3= new Thread(new Runnable() {
			
			@Override
			public void run() {
				serviceSynchronizedClass.method3();
			}
		});
		
		Thread thread4= new Thread(new Runnable() {
			
			@Override
			public void run() {
				serviceSynchronizedClass.method2();
			}
		});
		Thread thread5= new Thread(new Runnable() {
			
			@Override
			public void run() {
				serviceSynchronizedClass.method3();
			}
		});
		thread.setName("thread0");
		thread.start();
		
		thread1.setName("thread1");
		thread1.start();
		
		thread2.setName("thread2");
		thread2.start();
		
		thread3.setName("thread3");
		thread3.start();
		thread4.setName("thread4");
		thread4.start();
		thread5.setName("thread5");
		thread5.start();
	}
	
}

class ServiceSynchronizedClass {

	public void method() {
		synchronized (ServiceSynchronizedClass.class) {
			System.out.println(Thread.currentThread().getName() + "method");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "method");
		}
	}

	public void method1() {
		synchronized (ServiceSynchronizedClass.class) {
			System.out.println(Thread.currentThread().getName() + "method1");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "method1");
		}
	}

	public synchronized void method2() {
		System.out.println(Thread.currentThread().getName() + "method2");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "method2");
	}

	public synchronized void method3() {
		System.out.println(Thread.currentThread().getName() + "method3");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "method3");
	}
}
