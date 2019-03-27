package com.luochaoqun.knowledgescope.concurrent.produceconsume.queueImpl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月6日 下午2:49:39 
 * @today: 
 */
public class QueueContainer {

	public static void main(String[] args) {
		BlockingQueue<Integer> container = new LinkedBlockingQueue<Integer>(5);
		
		for(int i = 0;i<5;i++){
			Thread producer = new Thread(new ProducerQueue(container));
			producer.setName("生产者-01");
			producer.start();
		}
	
		for(int k = 0;k<2;k++){
			Thread con = new Thread(new ConsumerQueue(container));
			con.setName("消费者-01");
			con.start();
		}
	}
	
}

class ProducerQueue implements Runnable{

	private final BlockingQueue<Integer> proQueue;
	private AtomicInteger index = new AtomicInteger(0);
	
	public ProducerQueue(BlockingQueue<Integer> blockingQueue) {
		this.proQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		
		while(true){
			try {
				Integer ele = index.getAndIncrement();
				proQueue.put(index.getAndIncrement());
				System.out.println(Thread.currentThread().getName()+"-生产了-"+ele);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class ConsumerQueue implements Runnable{
	private BlockingQueue<Integer> consumeQueue;
	
	public ConsumerQueue(BlockingQueue<Integer> consumeQueue) {
		this.consumeQueue = consumeQueue;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Integer ele = consumeQueue.take();
				System.out.println(Thread.currentThread().getName()+"-消费了："+ele);
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
