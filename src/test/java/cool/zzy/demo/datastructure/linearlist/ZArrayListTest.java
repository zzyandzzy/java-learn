package cool.zzy.demo.datastructure.linearlist;

import org.junit.Before;
import org.junit.Test;

/**
 * xyz.zzyitj.demo.datastructure
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/5/19 2:32 下午
 * @since 1.0
 */
public class ZArrayListTest {
    ZList<String> list = new ZArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf((char) ('A' + i)));
        }
    }

    @Test
    public void size() {
        System.out.println(list.size());
    }

    @Test
    public void get() {
        System.out.println(list.get(0));
    }

    @Test
    public void set() {
        list.set(0, "intent");
        System.out.println(list.get(0));
    }

    @Test
    public void add() {
        list.add("xxx");
        System.out.println(list.get(list.size() - 1));
    }

    @Test
    public void remove() {
        System.out.println(list.size());
        list.remove(0);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }
}
