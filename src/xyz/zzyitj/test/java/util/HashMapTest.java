package xyz.zzyitj.test.java.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author intent
 * @version 1.0
 * @date 2020/3/26 12:58 上午
 * @email zzy.main@gmail.com
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + i);
        }
        map.remove("key1");
        map.remove("key2");
        map.remove("key3");
        map.replace("key1", "value1");
    }
}
