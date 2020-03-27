package xyz.zzyitj.demo.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent
 * @version 1.0
 * @date 2020/3/27 3:52 下午
 * @email zzy.main@gmail.com
 */
class RBTreeTest {
    @Test
    public void test() {
        RBTree<Integer> rbTree = new RBTree<>();
        rbTree.mRoot = new RBTree.RBTNode<>(80, true, null, null, null);
        RBTree.RBTNode<Integer> x = new RBTree.RBTNode<>(40, false, rbTree.mRoot, null, null);
        RBTree.RBTNode<Integer> y = new RBTree.RBTNode<>(120, false, rbTree.mRoot, null, null);
        rbTree.mRoot.left = x;
        rbTree.mRoot.right = y;
        print(rbTree.mRoot);
        rbTree.leftRotate(rbTree.mRoot);
        print(rbTree.mRoot);
    }

    private void print(RBTree.RBTNode<Integer> mRoot) {
        RBTree.RBTNode<Integer> l = mRoot.left, r = mRoot.right;
        while (l != null) {
            System.out.println("l: " + l.key);
            l = l.left;
        }
        while (r != null) {
            System.out.println("r: " + r.key);
            r = r.right;
        }
    }
}