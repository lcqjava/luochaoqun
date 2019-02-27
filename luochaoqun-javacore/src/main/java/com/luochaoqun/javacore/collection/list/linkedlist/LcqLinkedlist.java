package com.luochaoqun.javacore.collection.list.linkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.luochaoqun.javacore.collection.list.LcqCollection;
import com.luochaoqun.javacore.collection.list.LcqList;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月7日 下午3:14:29 
 * @today: 
 */
public class LcqLinkedlist<E> implements LcqList<E> {
	
	transient LcqNode<E> first;
	transient LcqNode<E> last;
	transient int size;
	
	private void linkedFirst(E e){
		LcqNode<E> oldFirst = first;
		LcqNode<E> newNode = new LcqNode<E>(null, e, oldFirst);
		first = newNode;
		if(oldFirst == null){
			last = newNode;
		}else{
			oldFirst.prev = newNode;
		}
		size ++;
		
	}
	
	public void addFirst(E e){
		linkedFirst(e);
	}
	
	public void linkLast(E e){
		LcqNode<E> oldLast = last;
		LcqNode<E> newNode = new LcqNode<E>(oldLast, e, null);
		last = newNode;
		
		if(last == null){
			first = newNode;
		}else{
			last.next = newNode;
		}
		size ++;
	}
	
	public E getFirst(){
		final LcqNode<E> f = first;
		if(f == null){
			throw new NoSuchElementException();
		}
		return f.item;
	}
	
	public E getLast(){
		final LcqNode<E> l = last;
		if(l == null){
			throw new NoSuchElementException();
		}
		return l.item;
	}
	
	 private E unlinkLast(LcqNode<E> l) {
	        // assert l == last && l != null;
	        final E element = l.item;
	        final LcqNode<E> prev = l.prev;
	        l.item = null;
	        l.prev = null; // help GC
	        last = prev;
	        if (prev == null)
	            first = null;
	        else
	            prev.next = null;
	        size--;
	        return element;
	    }
	
	 private E unlinkFirst(LcqNode<E> f) {
	        // assert f == first && f != null;
	        final E element = f.item;
	        final LcqNode<E> next = f.next;
	        f.item = null;
	        f.next = null; // help GC
	        first = next;
	        if (next == null)
	            last = null;
	        else
	            next.prev = null;
	        size--;
	        return element;
	    }
	 
	public E removeFirst() {
        final LcqNode<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }
	
	public E removeLast() {
        final LcqNode<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }
	
	public int indexof(Object o){
		
		int index = 0;
		if(o == null){
			for(LcqNode<E> x = first;x!=null;x=x.next){
				if(x.item == null){
					return index;
				}
				index++;
			}
		}else{
			for (LcqNode<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
		}
		
		return -1;
	}
	
	public boolean contains(Object o){
		return indexof(o)!=-1;
	}
	
	
	
	public boolean add(E e){
		linkLast(e);
		return true;
	}
	
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
	}
	
	private static class LcqNode<E>{
		E item;
		LcqNode<E> next;
		LcqNode<E> prev;
		
		public LcqNode(LcqNode<E> prev, E element, LcqNode<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
		
	}

	@Override
	public boolean isEmpty() {
		return size<=0;
	}

	@Override
	public boolean addAll(LcqCollection<E> collection) {
		return false;
	}

	@Override
	public boolean remove(E t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}




	

}
