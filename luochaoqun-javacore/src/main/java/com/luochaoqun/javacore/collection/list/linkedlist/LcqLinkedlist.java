//package com.luochaoqun.javacore.collection.list.linkedlist;
//
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.NoSuchElementException;
//
//import com.luochaoqun.javacore.collection.list.LcqList;
//
///**  
// * All rights Reserved, Designed By www.xiaoaiqinqin.com   
// * @Description: 
// * @author: 小艾亲亲     
// * @date:   2018年12月7日 下午3:14:29 
// * @today: 
// */
//public class LcqLinkedlist<E> implements LcqList<E> {
//	
//	LcqNode<E> first;
//	LcqNode<E> last;
//	int size = 0;
//	
//	private void linkFirst(E e){
//		LcqNode<E> f = first;
//		LcqNode<E> newNode = new LcqNode<E>(null, e, f);
//		first = newNode;
//		if(f == null){
//			last = newNode;
//		}else{
//			f.prev = newNode;
//		}
//		
//		size ++;
//		
//	}
//	
//	public void linkLast(E e){
//		LcqNode<E> l = last;
//		
//		LcqNode<E> newNode = new LcqNode<E>(l, e, null);
//		last = newNode;
//		
//		if(l == null){
//			first = newNode;
//		}else{
//			l.next = newNode;
//		}
//		size ++;
//	}
//	
//	public E getFirst(){
//		LcqNode<E> f = first;
//		if(f == null){
//			throw new NoSuchElementException();
//		}
//		return f.item;
//	}
//	
//	public 
//	
//	
//	private static class LcqNode<E>{
//		E item;
//		LcqNode<E> next;
//		LcqNode<E> prev;
//		public LcqNode(LcqNode<E> prev, E element, LcqNode<E> next) {
//			this.item = element;
//			this.next = next;
//			this.prev = prev;
//		}
//		
//	}
//
//
//
//
//	
//
//}
