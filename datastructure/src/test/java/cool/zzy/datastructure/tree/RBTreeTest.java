package cool.zzy.datastructure.tree;

import org.junit.jupiter.api.Test;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/2/14 16:24
 * @since 1.0
 */
class RBTreeTest {
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