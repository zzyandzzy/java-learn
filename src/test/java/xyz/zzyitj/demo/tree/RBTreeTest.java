package xyz.zzyitj.demo.tree;

import org.junit.Test;

/**
 * xyz.zzyitj.demo.tree
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 3:52 下午
 * @since 1.0
 */
public class RBTreeTest {
    @Test
    public void testPut() {
        RBTree<String, String> tree = new RBTree<>();
        for (int i = 0; i < 10; i++) {
            tree.put(String.valueOf(i), null);
        }
        TreeOperation.show(tree.getRoot());
    }
}