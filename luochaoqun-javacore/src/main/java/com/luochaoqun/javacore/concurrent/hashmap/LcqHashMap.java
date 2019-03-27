package com.luochaoqun.javacore.concurrent.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.aspectj.weaver.ast.Var;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月7日 下午10:11:57 
 * @today: 
 */
public class LcqHashMap<K,V> {

	static final int MAXIMUM_CAPACITY = 1<<30;
	
	transient Node<K, V> table;

	//加载因子
	float loadFactor;
	//阀值
	int threshold ;
	
	public LcqHashMap(int initialCapacity,float loadFactor){
		if(initialCapacity<0){
			throw new IllegalArgumentException();
		}
		
		if(initialCapacity > MAXIMUM_CAPACITY){
			initialCapacity = MAXIMUM_CAPACITY;
		}
		
		if(loadFactor <= 0|| Float.isNaN(loadFactor)){
			throw new IllegalArgumentException();
		}
		
		this.loadFactor = loadFactor;
		this.threshold = tableSizeFor(initialCapacity);
	}
	
	static final int tableSizeFor(int cap){
		
		
		
		return 0;
	}
	public V put(K key,V value){
		
		
		return null;
	}
	
	
	static class Node<K,V> implements Map.Entry<K, V>{
		
		final int hash;
		final K key;
		V value;
		Node<K,V> next;
		
		public Node(int hash,K key,V val,Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = val;
			this.next = next;
		}
		
		public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }
        
        public final int hashCode(){
        		return Objects.hashCode(key);
        }
        
		@Override
		public V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
		
		public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
	}
	
	public static void main(String[] args) {
		System.out.println(MAXIMUM_CAPACITY);
		System.out.println(Integer.MAX_VALUE);
		HashMap hashMap = new HashMap<>();
		//hashMap.put(key, value);
	}
}
