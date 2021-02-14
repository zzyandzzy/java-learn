package cool.zzy.java.util;

import cool.zzy.demo.datastructure.tree.RBTree;
import cool.zzy.demo.datastructure.tree.RBTreeTest;
import org.junit.Test;

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
     * {@link RBTreeTest} 测试用例
     * {@link RBTree} 源码带详细注释
     * {@link RBTree#putVal(RBTree.RBTNode)} 插入节点
     * {@link RBTree#putValFixUp(RBTree.RBTNode)} 插入节点后修复红黑树
     * {@link RBTree#remove(RBTree.RBTNode)} 删除节点
     * {@link RBTree#removeFixUp(RBTree.RBTNode, RBTree.RBTNode)} 删除节点后修复红黑树
     * {@link RBTree#search(RBTree.RBTNode, Comparable)} 二分查找节点
     * {@link RBTree#leftRotate(RBTree.RBTNode)} 左旋
     * {@link RBTree#rightRotate(RBTree.RBTNode)} 右旋
     */
    @Test
    public void test() {
    }
}
