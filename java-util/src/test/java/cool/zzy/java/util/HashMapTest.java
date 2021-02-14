package cool.zzy.java.util;


import cool.zzy.datastructure.tree.RBTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * xyz.zzyitj.java.util
 * <p>
 * 未解决疑问：
 * 1：为什么在插入的时候使用尾插法？
 * 目前了解到的资料是说尾插法可以防止多线程插入时出现循环链表的问题。
 * <p>
 * 已解决：
 * HashMap默认初始化大小为什么是1 << 4（16）
 * 如果太小，4或者8，扩容比较频繁；如果太大，32或者64甚至太大，又占用内存空间。
 * 打个比喻，假设你开了个情侣咖啡厅，平时一般都是7,8对情侣来喝咖啡，高峰也就10对。
 * 那么，你是不是设置8个桌子就好啦，如果人来得多再考虑加桌子。
 * 如果设置4桌，那么就经常座位不够要加桌子，如果设置10桌或者更多，那么肯定占地方嘛。
 * <p>
 * 默认初始化大小为什么定义为2的幂？
 * 我们知道HashMap的底层数据结构是数组+链表/数组+红黑树，由以上方法，可以发现数组下标索引的定位公式是：
 * i = (n - 1) & hash，当初始化大小n是2的倍数时，(n - 1) & hash等价于n % hash。比如：
 * n = 16，hash = 1010，则(n - 1) & hash = 1111 & 1010 = 1010 = 6
 * n = 16，hash = 1010，则n % hash = 1111 % 1010 = 1010 = 6
 * 定位下标一般用取余法，为什么这里不用取余呢？
 * 因为，与运算（&）比取余（%）运算效率高
 * 求余运算： a % b就相当与a-(a / b)*b 的运算。
 * 与运算： 一个指令就搞定
 * 因此，默认初始化大定义为2的幂，就是为了使用更高效的与运算。
 * <p>
 * 1.hashCode算法：
 * 见{@link #testHashCode()}
 * <p>
 * 2. 扩容的时候为什么1.8 不用重新hash就可以直接定位原节点在新数据的位置呢?
 * 见{@link #testResize()}
 * <p>
 * 分析流程：
 * 1：构造函数分析{@link #testConstructor()}
 * 2: 插入分析<a href="流程图/util/HashMap/HashMap插入流程图.png">HashMap插入流程图</a>
 * 3：删除分析 {@link HashMap#removeNode(int, Object, Object, boolean, boolean)}
 * 如果是树，看{@link RBTree#remove(Comparable)}
 * 4：替换分析 查找到相同的值直接替换
 * 5：查询分析 如果是链表，直接循环整个链表查询
 * 如果是树，二叉搜索树二分查找{@link HashMap.TreeNode#find(int, Object, Class)}
 * {@link RBTree#search(RBTree.RBTNode, Comparable)}
 * 6：扩容分析 {@link #testResize()}
 * 7：红黑树 {@link RBTree}
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 3:53 下午
 * @since 1.0
 */
public class HashMapTest {
    Map<String, String> map;

    @BeforeEach
    public void initHashMap() {
        map = new HashMap<>();
    }

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
     * {@linkplain HashMap#resize() 扩容函数}
     */
    @Test
    public void testResize() {
    }

    /**
     * {@link HashMap#hash(Object)}
     * 为什么要先高16位异或低16位再取模运算?
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
    @Test
    public void testHashCode() {
        /*
         * hash 值的高位，没有参与数组下标计算，而是被掩码给掩盖掉了。
         * 假如有一类 hash，特点是低位都是 0，高位才有变化。
         * 比如 Float类：
         */
        System.out.println("1-10的Float二进制");
        for (int i = 1; i < 10; i++) {
            System.out.println(Integer.toBinaryString(new Float(i).hashCode()));
        }
        System.out.println("Float 10没有扰动函数前");
        int h = new Float(10).hashCode();
        System.out.printf("%8s\t%32s\n", "hashCode", Integer.toBinaryString(h));
        System.out.printf("%8s\t%32s\n", "length:64-1", Integer.toBinaryString(64 - 1));
        System.out.printf("%8s\t%32s\n", "&", Integer.toBinaryString(h & (64 - 1)));
        // 可以看到低位都是0
        // 所以必须用"扰动函数"把高16位都参与运算
        System.out.println("扰动函数");
        System.out.printf("%8s\t%32s\n", "hashCode", Integer.toBinaryString(h));
        System.out.printf("%8s\t%32s\n", ">>>16", Integer.toBinaryString(h >>> 16));
        h = (h >>> 16) ^ h;
        System.out.printf("%8s\t%32s\n", "^", Integer.toBinaryString(h));
        System.out.printf("%8s\t%32s\n", "length:64-1", Integer.toBinaryString(64 - 1));
        System.out.printf("%8s\t%32s\n", "&", Integer.toBinaryString(h & (64 - 1)));
    }

    /**
     * 测试{@link HashMap}的4个构造测试
     * {@linkplain HashMap#loadFactor 负载因子}
     * {@linkplain HashMap#threshold 下一次要扩容的大小}
     * {@linkplain HashMap#tableSizeFor(int) 如果自己传入初始大小k，初始化大小为大于k的 2的整数次方，例如传10，大小为16}
     * {@linkplain HashMap#HashMap() 默认的构造参数}
     * {@linkplain HashMap#HashMap(int) 传入容器大小}
     * {@linkplain HashMap#HashMap(int, float) 传入容器大小和负载因子}
     * {@linkplain HashMap#HashMap(Map)}
     */
    @Test
    public void testConstructor() {
        map = new HashMap<>();
//        map = new HashMap<>(50);
        /**
         * 构造函数{@linkplain HashMap#HashMap(int, float) 传入容器大小和负载因子}
         * 先设置loadFactor
         * 然后调用{@linkplain HashMap#tableSizeFor(int)} 计算传入参数最接近的2的倍数再赋值给threshold
         */
        //map = new HashMap<>(50, 2.0F);
        /**
         * 因为桶为空，所以先调用{@linkplain HashMap#resize() 扩容函数}
         * 初始化桶大小为threshold并设置threshold为当前容器大小 * 负载因子
         * 再计算插入位置，因为第一次插入是空表，所以直接插入
         */
        map.put("key1", "value1");
    }

    /**
     * 测试链表深度大于9但是不转为红黑树只扩容
     * {@link HashMap#MIN_TREEIFY_CAPACITY}
     * 测试链表深度大于9转为红黑树
     * {@link HashMap#putVal(int, Object, Object, boolean, boolean)}
     * {@link HashMap#treeifyBin(HashMap.Node[], int)}
     */
    @Test
    public void testTreeifyBin() {
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            int h;
            h = (h = key.hashCode()) ^ (h >>> 16);
            if ((h & 15) == 0) {
                map.put(key, "value1");
            }
        }
    }

    /**
     * 测试key或者value为null的情况
     */
    @Test
    public void testNull() {
        // key可以是null
        map.put(null, "aaa");
        System.out.printf("%s\n", map.get(null));
        map.remove(null);
        System.out.printf("%s\n", map.get(null));
        map.put("aaa", null);
        System.out.println(map.get("aaa"));
    }
}