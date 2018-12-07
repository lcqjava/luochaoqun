package com.luochaoqun.javacore.collection.list;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月6日 上午9:18:11 
 * @today: 
 */
public interface LcqList<T> extends LcqCollection<T>{
	
	public T get(int index);
	
	public boolean contains(T t);
	
	public int size();
	
	
}
