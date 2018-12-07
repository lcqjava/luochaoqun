#ArrayList底层实现

依次实现List、Collection、Iterator接口，有两个重要属性：

1.elementData:数组对象，存放Arraylist集合元素，默认大小10

2.size:当前存放元素个数，最大Integer.maxValue-8（有的虚拟机会把header word放在数组里）



## 方法列表：

###add:

新增元素

```java
public boolean add(T t) {

		ensureElementCapacity(size + 1);

		elementData[size] = t;
		size++;
		return true;
	}
```

```java
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
```

说明：先对elementData数组扩容，默认当前长度>>1扩容一半，数组最大长度是整型最大值-8，备注上说的是有的虚拟机会把头信息放入数组中。并使用Arrays.copyOf拷贝到新数组中。

结论：arrayList的消耗主要是数组内存的扩容，使用尽量估算容量以免频繁扩容。



### remove:

删除元素

```java
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
```

```java
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
```

说明：删除时用的是equals比较，所以泛型是自定义对象要重写equals方法，否则删不掉。删除逻辑为：遍历数组，获取删除对象的下标index,然后拷贝index+1至末尾的数据。

### clear:

```java
  public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }
```

清空所有元素，把elementData数组内所有的元素置空，size设置为0



## 序列化和反序列化

问题：1、很奇怪，都是私有的并且没有被调用过？

==》ArrayList是可以序列化的，但是要用ObjectInputStream或ObjectOutputStream

```java
private void writeObject(java.io.ObjectOutputStream s)throws java.io.IOException

private void readObject(java.io.ObjectInputStream s)throws java.io.IOException, ClassNotFoundException
```

ObjectInputstream和ObjectOutputstream的用法：

```java
@Test
	public void testObjectStreamSerialize() throws IOException{
		
		String filePath = "./array.obj";
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setName("adf");
		elementEntity.setNum(123);
		
		ObjectOutputStream outputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
			outputStream.writeObject(elementEntity);
			
			objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
			ElementEntity elementEntity2 = (ElementEntity)objectInputStream.readObject();
			System.out.println(elementEntity2.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null){
				outputStream.close();
			}
			if(objectInputStream!=null){
				objectInputStream.close();
			}
		}
		
	}
```



问题：2、既然ArrayList支持序列化，为何要将elementData设置为transient？

==》elementData会初始化一个数组，默认所有元素开始都是null，size记录了数组中存放了多少元素，序列化只序列化elementData数组中有值的部分，也就是size个元素

```java
 private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
```

```java
private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }
```

