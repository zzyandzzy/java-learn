package cool.zzy.java.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * xyz.zzyitj.java.util
 * <p>
 * {@link LinkedHashMap.Entry}比{@link HashMap.Node}多了after和before节点
 * 并且在LinkedHashMap内部维护了一个双向链表{@link LinkedHashMap#head}头结点{@link LinkedHashMap#tail}尾节点
 * 为了让LinkedHashMap有序，每次在插入节点的时候由HashMap调用{@link LinkedHashMap#afterNodeAccess(HashMap.Node)}让双向链表有序
 * LinkedHashMap内部还维护了一个迭代器{@link LinkedHashMap.LinkedEntrySet#forEach(Consumer)}
 * 可以很方便的访问数据
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/30 9:26 上午
 * @since 1.0
 */
public class LinkedHashMapTest {
    Map<String, String> map;
    Map<String, String> map1;

    @BeforeEach
    public void init() {
        map = new LinkedHashMap<>();
        map1 = new LinkedHashMap<>(16, 0.75F, true);
    }

    /**
     * {@link LinkedHashMap#accessOrder}
     * 如果accessOrder为true的话，则会把访问过的元素放在链表后面，放置顺序是访问的顺序
     * 如果accessOrder为false的话，则按插入顺序来遍历
     */
    @Test
    public void testAccessOrder() {
        map1.put("1", "1");
        map1.put("2", "2");
        map1.put("3", "3");
        map1.put("4", "4");
        System.out.println("map" + map1);
        map1.get("2");
        map1.get("3");
        System.out.println("获取了数据" + map1);
    }

    /**
     * {@link LinkedHashMap#get(Object)}
     * 如果accessOrder为false，就是HasMap的get{@link HashMap#get(Object)}
     * 如果accessOrder为true，将调用{@link LinkedHashMap#afterNodeAccess(HashMap.Node)}把获取的节点放到链表后面
     */
    @Test
    public void testGet() {
        map.put("c", "c");
        map.put("a", "a");
        map.put("e", "e");
        map.put("d", "d");
        map.put("b", "b");
        /**
         * 这里调用了父类{@link AbstractMap#toString()}方法，里面调用了实现类的迭代器
         * {@link LinkedHashMap.LinkedEntrySet#forEach(Consumer)}方法
         */
        System.out.println(map);
    }

    /**
     * {@link LinkedHashMap#put(Object, Object)}
     * 就是用HashMap的put方法{@link HashMap#put(Object, Object)}之后
     * 如果是插入链表，则由HashMap调用子类函数{@link LinkedHashMap#afterNodeAccess(HashMap.Node)}
     * 建立与插入节点的关系
     * 插入完成有HashMap调用子类函数{@link LinkedHashMap#afterNodeInsertion(boolean)}
     * 这个函数可以继承LinkedHashMap重载{@link LinkedHashMap#removeEldestEntry(Map.Entry)}函数实现删除旧的数据
     */
    @Test
    public void testPut() {
        map.put("key1", "value1");
    }
}
