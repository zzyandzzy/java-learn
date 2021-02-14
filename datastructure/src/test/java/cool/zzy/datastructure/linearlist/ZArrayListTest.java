package cool.zzy.datastructure.linearlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/2/14 16:23
 * @since 1.0
 */
class ZArrayListTest {
    ZList<String> list = new ZArrayList<>();

    @BeforeEach
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