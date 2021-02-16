package cool.zzy.java.util;

import cool.zzy.datastructure.tree.RBTree;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * xyz.zzyitj.java.util
 * <p>
 * {@link TreeSet}底层是{@link java.util.TreeMap}也就是红黑树
 * 具体实现加注解:
 * {@link RBTree}
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/30 5:28 下午
 * @since 1.0
 */
public class TreeSetTest {
    Set<String> set = new TreeSet<>();

    /**
     * 因为底层是红黑树，保证了数据的顺序性，插入就是已经排好序的
     */
    @Test
    public void test() {
        set.add("c");
        set.add("a");
        set.add("b");
        set.add("d");
        set.add("e");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
