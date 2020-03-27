package xyz.zzyitj.java.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent
 * @version 1.0
 * @date 2020/3/27 3:53 下午
 * @email zzy.main@gmail.com
 */
class HashMapTest {
    Map<String, String> map = new HashMap<>();

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            map.put("key" + i, "value" + i);
        }
        map.remove("key1");
        map.replace("key1", "value1");
    }

    @Test
    public void testNull() {
        // key可以是null
        map.put(null, "aaa");
        System.out.printf("%s", map.get(null));
        map.remove(null);
        System.out.printf("%s", map.get(null));
    }
}