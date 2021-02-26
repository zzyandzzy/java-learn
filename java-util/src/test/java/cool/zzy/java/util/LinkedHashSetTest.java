package cool.zzy.java.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * {@link LinkedHashSet}继承{@link HashSet}
 * HashSet在内部专门为LinkedHashSet写了一个构造方法{@link HashSet#HashSet(int, float, boolean)}
 * 从而初始化map的时候指定初始化为{@link java.util.LinkedHashMap}
 * 而不是{@link java.util.HashMap}
 * 所以{@link LinkedHashSet}是保证插入有序的Set
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/30 5:23 下午
 * @since 1.0
 */
public class LinkedHashSetTest {
    Set<String> set = new LinkedHashSet<>();

    /**
     * 仅仅是为了测试LinkedHashSet的有序性
     * 无序的{@link HashSet}请看{@link HashSetTest#test()}
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
