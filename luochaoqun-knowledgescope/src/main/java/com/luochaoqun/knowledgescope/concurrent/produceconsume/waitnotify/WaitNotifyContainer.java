package com.luochaoqun.knowledgescope.concurrent.produceconsume.waitnotify;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月6日 下午1:52:40 
 * @today: 
 */
public class WaitNotifyContainer {
	
	private AtomicInteger index = new AtomicInteger(0);
	private Vector<Integer> foodList = new Vector<Integer>();
	private Integer maxSize = 50;
	
	public synchronized void increase() throws InterruptedException{
		while(foodList.size() == maxSize){
			wait();
			System.out.println("阻塞了，当前容器中有元素："+foodList.size());
		}
		Integer newFood = index.getAndIncrement();
		foodList.add(newFood);
		System.out.println(Thread.currentThread().getName()+"生成了元素："+newFood);
		notify();
	}
	
	public synchronized void decrease() throws InterruptedException{
		while(foodList.size() == 0){
			wait();
		}
		Integer food = foodList.remove(0);
		System.out.println(Thread.currentThread().getName()+"消费了元素："+food);
		Thread.sleep(1000);
		notify();
	}
	
	public static void main(String[] args) {
		WaitNotifyContainer waitNotifyContainer = new WaitNotifyContainer();
		Producer producer = new Producer(waitNotifyContainer);
		Consumer consumer = new Consumer(waitNotifyContainer);
		
		for(int i = 0;i<5;i++){
			Thread producerThread = new Thread(producer);
			producerThread.setName("生产者-"+i);
			producerThread.start();
		}
		
		for(int i = 0;i<2;i++){
			Thread consumerThread = new Thread(consumer);
			consumerThread.setName("消费者-"+i);
		
			consumerThread.start();
		}
	}
}

class Producer implements Runnable{
	private WaitNotifyContainer container;
	
	public Producer(WaitNotifyContainer waitNotifyContainer){
		this.container = waitNotifyContainer;
	}
	
	public void run(){
		while(true){
			try {
				container.increase();
			
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class Consumer implements Runnable{
	private WaitNotifyContainer container;
	
	public Consumer(WaitNotifyContainer container){
		this.container = container;
	}
	
	public void run(){
		while(true){
			try {
				container.decrease();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}


