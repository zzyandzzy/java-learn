package xyz.zzyitj.java.util;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author intent
 * @version 1.0
 * @date 2020/3/27 3:53 下午
 * @email zzy.main@gmail.com
 */
public class HashMapTest {
    Map<String, String> map;

    @Before
    public void initHashMap() {
        map = new HashMap<>();
    }

    /**
     * 测试put操作链表深度大于9但是不转为红黑树只扩容
     * 测试put操作链表深度大于9转为红黑树
     */
    @Test
    public void testPutTreeifyBinResize() {
        for (int i = 0; i < 1000; i++) {
            String key = "key" + i;
            int h;
            h = (h = key.hashCode()) ^ (h >>> 16);
            if ((h & 15) == 0) {
                map.put(key, "value1");
            }
        }
    }

    @Test
    public void testNull() {
        // key可以是null
        map.put(null, "aaa");
        System.out.printf("%s\n", map.get(null));
        map.remove(null);
        System.out.printf("%s\n", map.get(null));
    }
}