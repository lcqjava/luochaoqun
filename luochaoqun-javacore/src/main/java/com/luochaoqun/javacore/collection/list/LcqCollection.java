package com.luochaoqun.javacore.collection.list;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月6日 上午9:44:07 
 * @today: 
 */
public interface LcqCollection<T> extends LcqIterator<T>{
	
	public int size();
	
	public boolean isEmpty();
	
	public boolean add(T t);
	
	public boolean addAll(LcqCollection<T> collection);
	
	public boolean remove(T t);
	
	public boolean contains(T t);
	
	
}
