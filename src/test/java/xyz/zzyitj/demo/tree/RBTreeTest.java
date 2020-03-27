package xyz.zzyitj.demo.tree;

import org.junit.Test;

/**
 * xyz.zzyitj.java.util
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 3:52 下午
 * @since 1.0
 */
public class RBTreeTest {
    @Test
    public void test() {
        RBTree<Integer> rbTree = new RBTree<>();
        rbTree.mRoot = new RBTree.RBTNode<>(80, true, null, null, null);
        RBTree.RBTNode<Integer> x = new RBTree.RBTNode<>(40, false, rbTree.mRoot, null, null);
        RBTree.RBTNode<Integer> y = new RBTree.RBTNode<>(120, false, rbTree.mRoot, null, null);
        rbTree.mRoot.left = x;
        rbTree.mRoot.right = y;
        rbTree.print();
        System.out.println("左旋,预期 120 80 40");
        rbTree.leftRotate(rbTree.mRoot);
        rbTree.print();
    }
}