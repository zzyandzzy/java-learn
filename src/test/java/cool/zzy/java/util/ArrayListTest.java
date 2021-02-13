package cool.zzy.java.util;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * xyz.zzyitj.java.util
 * <p>
 * ArrayList 的插入删除一定慢么？
 * 取决于你删除的元素离数组末端有多远，ArrayList拿来作为堆栈来用还是挺合适的，push和pop操作完全不涉及数据移动操作。
 * <p>
 * ArrayList 的默认数组大小为什么是10？
 * 据说是因为sun的程序员对一系列广泛使用的程序代码进行了调研，结果就是10这个长度的数组是最常用的最有效率的。
 * 也有说就是随便起的一个数字，8个12个都没什么区别，只是因为10这个数组比较的圆满而已。
 * <p>
 * ArrayList 中的 elementData 为什么是 Object 而不是泛型 E ？
 * Java 中泛型运用的目的就是实现对象的重用，泛型T和Object类其实在编写时没有太大区别,
 * 只是JVM中没有T这个概念，T只是存在于编写时,进入虚拟机运行时,虚拟机会对泛型标志进行擦除，
 * 也就是替换T会限定类型替换（根据运行时类型）,如果没有限定就会用Object替换。
 * 同时Object可以new Object()，就是说可以实例化，而T则不能实例化。
 * 在反射方面来说，从运行时,返回一个T的实例时,不需要经过强制转换,然后Object则需要经过转换才能得到。
 * <p>
 * ArrayList list = new ArrayList(20); 中的list扩充几次？
 * 默认ArrayList的长度是10个，所以如果你要往list里添加20个元素肯定要扩充一次（newCapacity 扩充为原来的1.5倍，
 * 但和输入的minCapacity相比发现小于minCapacity，
 * 于是 newCapacity = minCapacity，所以只扩容一次，具体见扩容里的grow方法），
 * 但是这里显示指明了需要多少空间，所以就一次性为你分配这么多空间，也就是不需要扩充了！
 * <p>
 * 为什么ArrayList实现了RandomAccess接口？
 * 只要List集合实现这个接口，就能支持快速随机访问，然而又有人问，快速随机访问是什么东西？有什么作用？
 * 查看源码：
 * {@link java.util.Collections#binarySearch(List, Object)}
 * 通过查看源代码，发现实现RandomAccess接口的List集合采用一般的for循环遍历，而未实现这接口则采用迭代器。
 * RandomAccess接口这个空架子的存在，是为了能够更好地判断集合是否ArrayList或者LinkedList，
 * 从而能够更好选择更优的遍历方式，提高性能！
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/29 12:42 下午
 * @since 1.0
 */
public class ArrayListTest {
    List<String> list;

    @Before
    public void init() {
        list = new ArrayList<>(20);
    }

    /**
     * 测试{@link ArrayList}和{@link java.util.LinkedList}使用for循环和迭代器哪个更快？
     * <p>
     * 测试结果：
     * ArrayList For: 3
     * ArrayList Iterator: 2
     * LinkedList For: 1610
     * LinkedList Iterator: 3
     * ArrayList用for循环遍历和iterator迭代器差不多，LinkedList用iterator迭代器遍历比for循环遍历快
     */
    @Test
    public void testFor() {
        // test ArrayList
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            arrayList.add(i);
        }
        long arrayStartFor = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            Integer x = arrayList.get(i);
        }
        long arrayStopFor = System.currentTimeMillis();
        System.out.println("ArrayList For: " + (arrayStopFor - arrayStartFor));
        // test Iterator
        long arrayStartIterator = System.currentTimeMillis();
        Iterator<Integer> arrayListIterator = arrayList.iterator();
        while (arrayListIterator.hasNext()) {
            Integer x = arrayListIterator.next();
        }
        long arrayStopIterator = System.currentTimeMillis();
        System.out.println("ArrayList Iterator: " + (arrayStopIterator - arrayStartIterator));
        // test LinkedList
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 50000; i++) {
            linkedList.add(i);
        }
        long linkedStartFor = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            Integer x = linkedList.get(i);
        }
        long linkedStopFor = System.currentTimeMillis();
        System.out.println("LinkedList For: " + (linkedStopFor - linkedStartFor));
        // test Iterator
        long linkedStartIterator = System.currentTimeMillis();
        Iterator<Integer> linkedListIterator = arrayList.iterator();
        while (linkedListIterator.hasNext()) {
            Integer x = linkedListIterator.next();
        }
        long linkedStopIterator = System.currentTimeMillis();
        System.out.println("LinkedList Iterator: " + (linkedStopIterator - linkedStartIterator));
    }

    /**
     * 测试扩容
     */
    @Test
    public void testGrow() {
        list.add("value1");
        list.add("value2");
        list.add("value3");
        list.add("value4");
    }

    /**
     * {@link ArrayList#remove(Object)}
     * ArrayList的删除问题
     */
    @Test
    public void testRemove() {
        list.add("AA");
        list.add("BBB");
        list.add("CCCC");
        list.add("DDDD");
        list.add("EEE");
        /**
         * 输出结果为：AA,BBB,DDDD,EEE
         * 错误之处：DDDD元素竟然没有删除掉。
         * 这是因为：
         * {@link ArrayList#remove(int)}
         * 里面的System.arraycopy方法是将该元素从数组中删除，并且将后一个元素移动至当前位置，
         * 导致下一次循环遍历时后一个字符串并没有遍历到，所以无法删除。
         * 解决办法：倒序删除
         *         for (int i = list.size() - 1; i >= 0; i--) {
         *             if (list.get(i).length() == 4) {
         *                 list.remove(i);
         *             }
         *         }
         */
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.remove(i);
            }
        }
        /**
         * 下面的代码报ConcurrentModificationException
         * for (String s : list) {
         *             if (s.equals("BBB")) {
         *                 list.remove(s);
         *             }
         *         }
         * 原因是因为{@link ArrayList#fastRemove(int)}第一行修改了modCount
         * 而在ArrayList的父类{@link AbstractList#iterator()}里面调用了
         * {@link AbstractList.Itr#checkForComodification()}判断modCount是否被修改了，修改了就抛出异常
         * 要避免这种情况的出现则在使用迭代器迭代时（显示或for-each的隐式）不要使用ArrayList的remove，改为用Iterator的remove即可。
         *      Iterator<String> iterator = list.iterator();
         *         while (iterator.hasNext()){
         *             String s = iterator.next();
         *             if (s.equals("BBB")) {
         *                 iterator.remove();
         *             }
         *         }
         */
        for (String s : list) {
            System.out.println("element : " + s);
        }
    }
}
