package xyz.zzyitj.demo.tree;

import org.junit.Test;

/**
 * xyz.zzyitj.demo.tree
 * 红黑树源码实现{@link RBTree}
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 3:52 下午
 * @since 1.0
 */
public class RBTreeTest {
    RBTree<String, String> tree = new RBTree<>();

    @Test
    public void testRemove() {
        tree.put("key1", "value1");
        tree.put("key2", "value2");
        tree.put("key3", "value3");
        System.out.println(tree.get("key1"));
        TreeOperation.show(tree.getRoot());
        tree.remove("key1");
        System.out.println(tree.get("key1"));
        TreeOperation.show(tree.getRoot());
    }

    @Test
    public void testReplace() {
        tree.put("key1", "value1");
        tree.put("key2", "value2");
        tree.put("key3", "value3");
        // 替换key1
        System.out.println(tree.get("key1"));
        tree.replace("key1", "value2");
        System.out.println(tree.get("key1"));
    }

    @Test
    public void testGet() {
        tree.put("key1", "value1");
        tree.put("key2", "value2");
        tree.put("key3", "value3");
        System.out.println(tree.get("key1"));
        TreeOperation.show(tree.getRoot());
    }

    @Test
    public void testPut() {
        for (int i = 0; i < 10; i++) {
            tree.put(String.valueOf(i), null);
        }
        TreeOperation.show(tree.getRoot());
    }
}