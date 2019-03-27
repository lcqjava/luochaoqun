package com.luochaoqun.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月7日 上午12:31:01 
 * @today: 
 */
public class DeadLock {

	public static void main(String[] args) {
		ReentrantLock reentrantLock = new ReentrantLock();
		Condition keyboard = reentrantLock.newCondition();
		Condition printer = reentrantLock.newCondition();
		
		Thread thread1 = new Thread(new PeopleA(keyboard,printer));
		Thread thread2 = new Thread(new PeopleB(keyboard,printer));
		
		thread1.start();
		thread2.start();
	}
	
}

class PeopleA implements Runnable{
	private Condition keyboard;
	private Condition printer;
	
	public PeopleA(Condition keyboard,Condition printer){
		this.keyboard = keyboard;
		this.printer = printer;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		synchronized (keyboard) {
			System.out.println(threadName+"-键盘在手，键盘给我保护伞");
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadName+"-我还要打印机");
			synchronized (printer) {
				System.out.println(threadName+"-获取打印机");
			}
		}
	}
}

class PeopleB implements Runnable{
	private Condition printer;
	private Condition keyboard;
	
	public PeopleB(Condition keyboard,Condition printer){
		this.keyboard = keyboard;
		this.printer = printer;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		synchronized (printer) {
			System.out.println(threadName+"-我持有打印机");
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadName+"-我还要键盘拯救世界");
			synchronized (keyboard) {
				System.out.println(threadName+"-我获取了键盘");
			}
		}
	}
}
