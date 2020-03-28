package xyz.zzyitj.java.util;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * xyz.zzyitj.java.util
 * <p>
 * 未解决疑问：
 * 1：为什么在插入的时候使用尾插法？
 * 2：hashCode异或的用意？
 * 分析流程：
 * 1：构造函数分析{@link #testConstructor()}
 * 2: 插入分析<a href="流程图/util/HashMap/HashMap插入流程图.png">HashMap插入流程图</a>
 * 3：删除分析
 * 4：替换分析
 * 5：查询分析
 * 6：扩容分析
 * 7：红黑树 {@link xyz.zzyitj.demo.tree.RBTree}
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 3:53 下午
 * @since 1.0
 */
public class HashMapTest {
    Map<String, String> map;

    @Before
    public void initHashMap() {
        map = new HashMap<>();
    }

    /**
     * 测试{@link java.util.HashMap}的4个构造测试
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
//        map = new HashMap<>();
//        map = new HashMap<>(50);
        /**
         * 构造函数{@linkplain HashMap#HashMap(int, float) 传入容器大小和负载因子}
         * 先设置loadFactor
         * 然后调用{@linkplain HashMap#tableSizeFor(int)} 计算传入参数最接近的2的倍数再赋值给threshold
         */
        map = new HashMap<>(50, 2.0F);
        /**
         * 因为桶为空，所以先调用{@linkplain HashMap#resize() 扩容函数}
         * 初始化桶大小为threshold并设置threshold为当前容器大小 * 负载因子
         * 再计算插入位置，因为第一次插入是空表，所以直接插入
         */
        map.put("key1", "value1");
    }

    /**
     * 测试链表深度大于9但是不转为红黑树只扩容
     * {@link java.util.HashMap#MIN_TREEIFY_CAPACITY}
     * 测试链表深度大于9转为红黑树
     * {@link java.util.HashMap#putVal(int, Object, Object, boolean, boolean)}
     * {@link java.util.HashMap#treeifyBin(HashMap.Node[], int)}
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
     * 测试key为null的情况
     */
    @Test
    public void testNull() {
        // key可以是null
        map.put(null, "aaa");
        System.out.printf("%s\n", map.get(null));
        map.remove(null);
        System.out.printf("%s\n", map.get(null));
    }
}