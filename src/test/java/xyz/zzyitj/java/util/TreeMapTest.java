package xyz.zzyitj.java.util;

import org.junit.Test;
import xyz.zzyitj.demo.tree.RBTree;

/**
 * xyz.zzyitj
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/29 9:09 下午
 * @since 1.0
 */
public class TreeMapTest {
    /**
     * TreeMap的底层实现就是红黑树，参考
     * {@link xyz.zzyitj.demo.tree.RBTreeTest} 测试用例
     * {@link xyz.zzyitj.demo.tree.RBTree} 源码带详细注释
     * {@link xyz.zzyitj.demo.tree.RBTree#putVal(RBTree.RBTNode)} 插入节点
     * {@link xyz.zzyitj.demo.tree.RBTree#putValFixUp(RBTree.RBTNode)} 插入节点后修复红黑树
     * {@link xyz.zzyitj.demo.tree.RBTree#remove(RBTree.RBTNode)} 删除节点
     * {@link xyz.zzyitj.demo.tree.RBTree#removeFixUp(RBTree.RBTNode, RBTree.RBTNode)} 删除节点后修复红黑树
     * {@link xyz.zzyitj.demo.tree.RBTree#search(RBTree.RBTNode, Comparable)} 二分查找节点
     * {@link xyz.zzyitj.demo.tree.RBTree#leftRotate(RBTree.RBTNode)} 左旋
     * {@link xyz.zzyitj.demo.tree.RBTree#rightRotate(RBTree.RBTNode)} 右旋
     */
    @Test
    public void test() {
    }
}
