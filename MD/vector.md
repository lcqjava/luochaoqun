# Vector

线程安全的列表结构，方法都用synchronized修饰了,跟ArrayList一样，实现List。

## vector & ArrayList相同点：

1.初始化elementData都是10个元素

2.都实现了List接口

```java
/*
 * @author  Lee Boynton
 * @author  Jonathan Payne
 * @see Collection
 * @see LinkedList
 * @since   JDK1.0
 */
public class Vector<E>
    extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    protected Object[] elementData;
    
    public Vector() {
        this(10);
    }
}
```



## Vectory & ArrayList不同点：

1.vector 属性elementData没有用transient修饰

2.扩容机制不一样，arrayList为0.5倍，vector为如果增量increamentCapacity不为0则扩容increamentCapacity个元素，如果为0则增加一倍。

3.作者不一样（有点像废话，不排除扩容不一样跟不同程序猿编写的有关系）

4.vectory没有readObject和writeObject方法



### vectory扩容：

```java
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                         capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```

### ArrayList扩容：

```java
private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```

问题：1、为什么扩容不一样？

===》1）扩容一半在频繁新增情况下，由于vector线程安全，数组锁的时间长，在高并发情况下性能不高

​          2）不同的程序猿编写的

