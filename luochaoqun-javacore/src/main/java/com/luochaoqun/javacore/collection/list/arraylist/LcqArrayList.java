package com.luochaoqun.javacore.collection.list.arraylist;

import java.util.Arrays;

import com.luochaoqun.javacore.collection.list.LcqCollection;
import com.luochaoqun.javacore.collection.list.LcqList;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2018年12月6日 上午9:48:50
 * @today:
 */
public class LcqArrayList<T> implements LcqList<T> {

	// 元素素组
	private Object[] elementData = null;
	// 当前元素数组存了多少元素
	private int size = 0;
	// 有些虚拟机会header words 放在数组里
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	public LcqArrayList() {
		elementData = new Object[6];
	}

	public LcqArrayList(int allocate) {
		elementData = new Object[allocate];
	}

	@Override
	public boolean isEmpty() {
		return elementData == null && size <= 0;
	}

	@Override
	public boolean add(T t) {

		ensureElementCapacity(size + 1);

		elementData[size] = t;
		size++;
		return true;
	}

	/**
	 * 
	 * @param minCapacity
	 *            容器最小容量
	 */
	public void ensureElementCapacity(int minCapacity) {
		// 需要扩容
		if (minCapacity > elementData.length) {
			int oldCapacity = elementData.length;
			// 扩容当前容量的一半
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			// 加一半后还是不能满足新增数据的容量
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}

			if (newCapacity > LcqArrayList.MAX_ARRAY_SIZE) {
				throw new IndexOutOfBoundsException();
			}

			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	@Override
	public boolean addAll(LcqCollection<T> collection) {
		if (collection.isEmpty()) {
			return false;
		}

		int minCapacity = size + collection.size();
		ensureElementCapacity(minCapacity);

		int addSize = collection.size();
		System.arraycopy(collection, 0, elementData, size - 1, collection.size());

		size = size + addSize;

		return addSize > 0 ? true : false;
	}

	@Override
	public boolean remove(T t) {

		if (isEmpty()) {
			return false;
		}

		if (!contains(t)) {
			return false;
		}

		if (t == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (t.equals(elementData[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}

	private void fastRemove(int index) {
		// 被删除元素所在下标后的元素个数
		int movedLength = size - index - 1;

		// 如果删除的元素不在最后
		if (movedLength > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, movedLength);
		}

		// 最后一个元素清空
		elementData[--size] = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (isEmpty()) {
			return null;
		}

		if (index > elementData.length || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return (T) elementData[index];
	}

	@Override
	public boolean contains(T t) {
		for(Object ele:elementData){
			if(t==null){
				
			}
			if(t.equals(ele)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

}
