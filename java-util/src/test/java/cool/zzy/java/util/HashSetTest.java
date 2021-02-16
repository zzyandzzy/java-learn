package cool.zzy.java.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * xyz.zzyitj.java.util
 * {@link HashSet}就是在内部使用了HashMap
 * {@link HashSet#add(Object)}方法就是使用了map的add方法，值为{@link HashSet#PRESENT}
 * 而{@link java.util.LinkedHashSet}是继承HashSet的，HashSet专门为LinkedHashSet预留了一个构造方法
 * {@link HashSet#HashSet(int, float, boolean)} 注意最后面的boolean参数，这个参数无关紧要
 * 仅仅是为了和另外的构造方法区别开来，重要的是里面的初始化map操作，里面初始化了LinkedHashMap以保证LinkedHashSet的插入顺序
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/30 4:59 下午
 * @since 1.0
 */
public class HashSetTest {
    Set<String> set = new HashSet<>();

    /**
     * 仅仅是为了测试HashSet的无序性
     * 有序的{@link java.util.LinkedHashSet}请看{@link java.util.LinkedHashSet#test()}
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
