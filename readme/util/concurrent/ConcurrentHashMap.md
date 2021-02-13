# ConcurrentHashMap

- [ConcurrentHashMap源码](../../../src/main/java/xyz/zzyitj/source/java/util/concurrent/ConcurrentHashMap.java)
- [测试用例](../../../src/test/java/xyz/zzyitj/java/util/concurrent/ConcurrentHashMapTest.java)

## 初始化

ConcurrentHashMap无参数的构造方法并没有初始化，在put的时候才初始化。
```java
/**
 * {@link ConcurrentHashMapTest#testInit()}
 */
public class Test{
}
```

1个参数的构造方法只是设置了sizeCtl的大小，这里涉及到一个tableSizeFor的方法，原理是把int的二进制的0设置为1
```java
/**
 * {@link ConcurrentHashMapTest#testTableSizeFor()}
 */
public class Test{
}
```

2个构造参数的方法和1个构造参数的方法类似，只是增加了一个负载因子，这个负载因子和HashMap的负载因子不同，
HashMap的负载因子在扩容的时候也使用到了，而ConcurrentHashMap使用了sizeCtl控制table的大小，
负载因子在这里只是在构造函数里面参与了sizeCtl大小的计算而已。

## put

1. 首先，判断key和value是否为null，其中一个为null，则抛出NullPointerException()。
    
    注：ConcurrentHashMap的key和Value都不能为null

2. 计算哈希值：spread(key.hashCode());

3. 根据哈希值计算放在table中的位置

4. 通过哈希值执行插入或替换操作

      - 如果这个位置没有值，直接将键值对放进去，不需要加锁
      
      - 如果要插入的位置是一个forwordingNode节点，表示正在扩容，那么当前线程帮助扩容3.3 加锁。以下操作都需要加锁
    
      - 如果要插入的节点在链表中，遍历链表中的所有节点，如果某一节点的key哈希值和key与参数相等，替换节点的value，记录被替换的值；如果遍历到了最后一个节点，还没找到key对应的节点，根据参数新建节点，插入链表尾部
      
      - 如果要插入的节点在树中，则按照树的方式插入或替换节点。如果是替换操作，记录被替换的值
      
5. 判断如果节点数量是大于8，就将链表转化成红黑树（binCount >= TREEIFY_THRESHOLD）

6. 如果操作3中执行的是替换操作，返回被替换的value，然后程序结束

7. 如果能执行到这一步，说明节点不是被替换的，是被插入的，所以要将map的元素数量加1