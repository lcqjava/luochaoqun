package com.luochaoqun.javacore.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月6日 下午4:36:02 
 * @today: 
 */
public class LcqQueue {

	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayBlockingQueue<>(2);
		/*
		for(int i = 0;i<2;i++){
			boolean result = queue.offer(i);
			System.out.println(result);
		}
		*/
		//queue.remove();
		Integer result = queue.poll();
		//System.out.println(result);
	}
	
}
