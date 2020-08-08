# 资料

- [HashMap源码](../../src/main/java/xyz/zzyitj/source/java/util/HashMap.java)
- [测试用例](../../src/test/java/xyz/zzyitj/java/util/HashMapTest.java)
- [插入流程图](../../其他/util/HashMap/插入流程图.png)
  
# 问题

HashMap默认初始化大小为什么是`1 << 4(16)`?

    如果太小，4或者8，扩容比较频繁；如果太大，32或者64甚至太大，又占用内存空间。
    打个比喻，假设你开了个情侣咖啡厅，平时一般都是7,8对情侣来喝咖啡，高峰也就10对。
    那么，你是不是设置8个桌子就好啦，如果人来得多再考虑加桌子。
    如果设置4桌，那么就经常座位不够要加桌子，如果设置10桌或者更多，那么肯定占地方嘛。

默认初始化大小为什么定义为2的幂？

    我们知道HashMap的底层数据结构是数组+链表/数组+红黑树，由以上方法，可以发现数组下标索引的定位公式是：
    i = (n - 1) & hash，当初始化大小n是2的倍数时，(n - 1) & hash等价于n % hash。比如：
    n = 16，hash = 1010，则(n - 1) & hash = 1111 & 1010 = 1010 = 6
    n = 16，hash = 1010，则n % hash = 1111 % 1010 = 1010 = 6
    定位下标一般用取余法，为什么这里不用取余呢？
    因为，与运算（&）比取余（%）运算效率高
    求余运算： a % b就相当与a-(a / b)*b 的运算。
    与运算： 一个指令就搞定
    因此，默认初始化大定义为2的幂，就是为了使用更高效的与运算。
    
默认加载因子为什么是0.75?

    加载因子表示哈希表的填满程度，跟扩容息息相关。为什么不是0.5或者1呢？
    如果是0.5，就是说哈希表填到一半就开始扩容了，这样会导致扩容频繁，并且空间利用率比较低。
    如果是1，就是说哈希表完全填满才开始扩容，这样虽然空间利用提高了，但是哈希冲突机会却大了。可以看一下源码文档的解释：

```java
 /*
 * <p>As a general rule, the default load factor (.75) offers a good
 * tradeoff between time and space costs.  Higher values decrease the
 * space overhead but increase the lookup cost (reflected in most of
 * the operations of the <tt>HashMap</tt> class, including
 * <tt>get</tt> and <tt>put</tt>).  The expected number of entries in
 * the map and its load factor should be taken into account when
 * setting its initial capacity, so as to minimize the number of
 * rehash operations.  If the initial capacity is greater than the
 * maximum number of entries divided by the load factor, no rehash
 * operations will ever occur.
 */
```
    翻译大概意思是：
    作为一般规则，默认负载因子（0.75）在时间和空间成本上提供了良好的权衡。
    负载因子数值越大，空间开销越低，但是会提高查找成本（体现在大多数的HashMap类的操作，包括get和put）。
    设置初始大小时，应该考虑预计的entry数在map及其负载系数，并且尽量减少rehash操作的次数。如果初始容量大于最大条目数除以负载因子，rehash操作将不会发生。
    简言之， 负载因子0.75 就是冲突的机会 与空间利用率权衡的最后体现，也是一个程序员实验的经验值。
StackOverFlow有个回答这个问题的： [What is the significance of load factor in HashMap?](https://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap)

    这个回答解释：一个bucket空和非空的概率为0.5，通过牛顿二项式等数学计算，得到这个loadfactor的值为log（2），约等于0.693。
    最后选择选择0.75，可能0.75是接近0.693的四舍五入数中，比较好理解的一个，并且默认容量大小16*0.75=12，为一个整数。

链表转换红黑树的阀值为什么是8?

    JDK8及以后的版本中，HashMap底层数据结构引入了红黑树。
    当添加元素的时候，如果桶中链表元素超过8，会自动转为红黑树。那么阀值为什么是8呢？请看HashMap的源码这段注释：
```java
    /*
    * Ideally, under random hashCodes, the frequency of
    * nodes in bins follows a Poisson distribution
    * (http://en.wikipedia.org/wiki/Poisson_distribution) with a
    * parameter of about 0.5 on average for the default resizing
    * threshold of 0.75, although with a large variance because of
    * resizing granularity. Ignoring variance, the expected
    * occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
    * factorial(k)). The first values are:
    *
    * 0:    0.60653066
    * 1:    0.30326533
    * 2:    0.07581633
    * 3:    0.01263606
    * 4:    0.00157952
    * 5:    0.00015795
    * 6:    0.00001316
    * 7:    0.00000094
    * 8:    0.00000006
    * more: less than 1 in ten million
    */
```
    理想状态中，在随机哈希码情况下，对于默认0.75的加载因子，桶中节点的分布频率服从参数为0.5的泊松分布，即使粒度调整会产生较大方差。
    由对照表，可以看到链表中元素个数为8时的概率非常非常小了，所以链表转换红黑树的阀值选择了8。
    
一个树的链表还原阈值为什么是6?

    上一小节分析，可以知道，链表树化阀值是8，那么树还原为链表为什么是6而不是7呢？
    这是为了防止链表和树之间频繁的转换。如果是7的话，假设一个HashMap不停的插入、删除元素，
    链表个数一直在8左右徘徊，就会频繁树转链表、链表转树，效率非常低下。
    
为什么不是2的31次方呢?

    我们知道，int占四个字节，一个字节占8位，所以是32位整型，也就是说最多32位。那按理说，最大数可以向左移动31位即2的31次幂，在这里为什么不是2的31次方呢？
    实际上，二进制数的最左边那一位是符号位，用来表示正负的，我们来看一下demo代码：
```java
public class Demo{
    public static void main(String[] args){
       System.out.println(1<<30);
       System.out.println(1<<31);
       System.out.println(1<<32);
       System.out.println(1<<33);
       System.out.println(1<<34);
    }
}
```
输出：
```
1073741824
-2147483648
1
2
4
```

哈希表的最小树形化容量为什么是64?

    这是因为容量低于64时，哈希碰撞的机率比较大，
    而这个时候出现长链表的可能性会稍微大一些，
    这种原因下产生的长链表，我们应该优先选择扩容而避免不必要的树化。比如Float类！

注：上述题均来自

    作者：Jay_huaxiao
    链接：https://juejin.im/post/5d7195f9f265da03a6533942
    来源：掘金
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
说一下hashCode算法？
```java
    /**
     * {@link HashMap#hash(Object)}
     * 为什么要先高16位异或低16位再取模运算?
     * hashmap这么做，只是为了降低hash冲突的几率。
     * hash 值的高位，没有参与数组下标计算，而是被掩码给掩盖掉了。
     * 假如有一类 hash，特点是低位都是 0，高位才有变化。比如Float类，参考{@link xyz.zzyitj.java.util.HashMapTest#testHashCode()}
     * 例子，hashCode为：1954974080
     * 原hashCode： 0111 0100 1000 0110 1000 1001 1000 0000
     * 数组长度-1：  0000 0000 0000 0000 0000 0000 0000 1111
     * &（与运算）： 0000 0000 0000 0000 0000 0000 0000 0000
     * 当我们的length为16的时候，只要哈希码的后4位为0，不论不论高位怎么变化，最终的结果均为0。
     * 同样的例子：
     * 原hashCode：          0111 0100 1000 0110 1000 1001 1000 0000
     * (>>>16)无符号右移16位： 0000 0000 0000 0000 0111 0100 1000 0110
     * ^ 异或运算：           0111 0100 1000 0110 1111 1101 0000 0110
     * 数组长度-1：           0000 0000 0000 0000 0000 0000 0000 1111
     * &（与运算）：          0000 0000 0000 0000 0000 0000 0000 0110 = 6
     * 可以有效的解决低位是0的hash冲突
     */
```

扩容的时候为什么1.8 不用重新hash就可以直接定位原节点在新数据的位置呢?

```java
    /**
     * {@link HashMap#resize()}
     * 扩容的时候为什么1.8 不用重新hash就可以直接定位原节点在新数据的位置呢?
     * 这是由于扩容是扩大为原数组大小的2倍，用于计算数组位置的掩码仅仅只是高位多了一个1
     * 扩容前长度为16，用于计算(n-1) & hash 的二进制n-1为0000 1111，扩容为32后的二进制就高位多了1，为0001 1111。
     * 因为是& 运算，1和任何数 & 都是它本身，那就分二种情况，如下图：原数据hashcode高位第4位为0和高位为1的情况；
     * 第四位高位为0，重新hash数值不变，第四位为1，重新hash数值比原来大16（旧数组的容量）
     * 比如：            原数据             二进制
     * 第一种情况：
     * 数组大小16：     0000 0101    &    0000 1111 = 0000 0101 = 5
     * 扩容后数组大小32：0000 0101    &    0001 1111 = 0000 0101 = 5（不变）
     * 第二种情况：
     * 数组大小16：     0001 0101    &    0000 1111 = 0000 0101 = 5
     * 扩容后数组大小32：0001 0101    &    0001 1111 = 0001 0101 = 5 + 16（比扩容前增加了16）
     */
```

为什么要先高16位异或低16位再取模运算?

```java
    /**
     * {@link HashMap#hash(Object)}
     * hashmap这么做，只是为了降低hash冲突的几率。
     * 例子，hashCode为：1954974080
     * 原hashCode： 0111 0100 1000 0110 1000 1001 1000 0000
     * 数组长度-1：  0000 0000 0000 0000 0000 0000 0000 1111
     * &（与运算）： 0000 0000 0000 0000 0000 0000 0000 0000
     * 当我们的length为16的时候，只要哈希码的后4位为0，不论不论高位怎么变化，最终的结果均为0。
     * 同样的例子：
     * 原hashCode：          0111 0100 1000 0110 1000 1001 1000 0000
     * (>>>16)无符号右移16位： 0000 0000 0000 0000 0111 0100 1000 0110
     * ^ 异或运算：           0111 0100 1000 0110 1111 1101 0000 0110
     * 数组长度-1：           0000 0000 0000 0000 0000 0000 0000 1111
     * &（与运算）：          0000 0000 0000 0000 0000 0000 0000 0110 = 6
     * 可以有效的解决低位是0的hash冲突
     */
```

# 引用
- [运算符](../../其他/运算符.jpeg)