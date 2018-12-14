package com.luochaoqun.javacore.util;

import com.luochaoqun.javacore.collection.list.LcqCollection;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月7日 下午3:23:44 
 * @today: 
 */
public interface LcqQueue<T> extends LcqCollection<T>{

	/**
	 * 如果队列有空间，立即返回；如果没有空间，抛异常
	 * @return
	 */
	public boolean add();
	
	/**
	 * 队列有空间，立即返回true；如果没有空间，立即返回false
	 * @return
	 */
	public boolean offer();
	
	public T remove();
	
	public T pool();
	
	public T element();
	
	public T peek();
	
}
