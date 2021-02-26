package cool.zzy.java.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/3/29 8:20 下午
 * @since 1.0
 */
public class LinkedListTest {
    List<String> list;

    @BeforeEach
    public void init() {
        list = new LinkedList<>();
    }

    @Test
    public void testAdd() {
        list.add("value1");
    }

    /**
     * {@link LinkedList#remove(int)}
     * 双向链表的断开
     * <p>
     * head头结点
     * tail尾节点
     * p<---->q<---->x
     * 1.要删除p，因为p没有前继节点，所以直接令head节点等于q节点，q节点的前继节点等于p节点的前继节点，p的后继节点等于null
     * 2.要删除q，p的后继节点等于q的后继节点，p的前继节点等于null，x的前继节点等于q的前继节点，q的后继节点等于null
     * 3.要删除x。q的后继节点等于x的后继节点，x的前继节点等于null，因为x没有后继节点，所以直接令tail节点等于q
     * 实现代码：{@link LinkedList#unlink(LinkedList.Node)}
     */
    @Test
    public void testRemove() {
        list.add("value1");
        list.remove(1);
    }

    /**
     * 这里做了优化：
     * {@link LinkedList#node(int)}
     * 把要查找的下标和当前链表大小的一半对比，是从头结点开始查找还是从尾节点开始查找，节省1/2的时间
     */
    @Test
    public void testGet() {
        list.add("value1");
        System.out.println(list.get(1));
    }
}
