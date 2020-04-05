package xyz.zzyitj.demo.reference;

import java.lang.ref.WeakReference;

/**
 * xyz.zzyitj.demo.reference
 * 弱引用
 * 垃圾回收器一看到弱引用就回收了，不管有没有对象指向它（弱引用）
 * <p>
 * <p>
 * static Map<Object,Object> container = new HashMap<>();
 * public static void putToContainer(Object key,Object value){
 * container.put(key,value);
 * }
 * <p>
 * public static void main(String[] args) {
 * //某个类中有这样一段代码
 * Object key = new Object();
 * Object value = new Object();
 * putToContainer(key,value);
 * <p>
 * //..........
 * <p>
 * 若干调用层次后程序员发现这个key指向的对象没有用了，
 * 为了节省内存打算把这个对象抛弃，然而下面这个方式真的能把对象回收掉吗？
 * 由于container对象中包含了这个对象的引用,所以这个对象不能按照程序员的意向进行回收.
 * 并且由于在程序中的任何部分没有再出现这个键，所以，这个键 / 值 对无法从映射中删除。
 * 很可能会造成内存泄漏。
 * <p>
 * key=null;
 * }
 * 设计 WeakHashMap类是为了解决一个有趣的问题。如果有一个值，对应的键已经不再 使用了， 将会出现什么情况呢？
 * 假定对某个键的最后一次引用已经消亡，不再有任何途径引 用这个值的对象了。
 * 但是，由于在程序中的任何部分没有再出现这个键，所以，这个键 / 值 对无法从映射中删除。
 * 为什么垃圾回收器不能够删除它呢？ 难道删除无用的对象不是垃圾回 收器的工作吗？
 * <p>
 * 遗憾的是，事情没有这样简单。垃圾回收器跟踪活动的对象。只要映射对象是活动的， 其中的所有桶也是活动的，
 * 它们不能被回收。因此，需要由程序负责从长期存活的映射表中 删除那些无用的值。
 * 或者使用 WeakHashMap完成这件事情。当对键的唯一引用来自散列条
 * 目时， 这一数据结构将与垃圾回收器协同工作一起删除键 / 值对。
 * <p>
 * 下面是这种机制的内部运行情况。WeakHashMap 使用弱引用（weak references) 保存键。
 * WeakReference 对象将引用保存到另外一个对象中，在这里，就是散列键。
 * 对于这种类型的 对象，垃圾回收器用一种特有的方式进行处理。
 * 通常，如果垃圾回收器发现某个特定的对象 已经没有他人引用了，就将其回收。
 * 然而， 如果某个对象只能由 WeakReference 引用， 垃圾 回收器仍然回收它，但要将引用这个对象的弱引用放人队列中。
 * WeakHashMap将周期性地检 查队列， 以便找出新添加的弱引用。一个弱引用进人队列意味着这个键不再被他人使用，
 * 并 且已经被收集起来。于是， WeakHashMap将删除对应的条目。
 * <p>
 * 除了WeakHashMap使用了弱引用，ThreadLocal类中也是用了弱引用。
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/5 11:05 上午
 * @since 1.0
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<M> weakReference = new WeakReference<>(new M());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        ThreadLocal<M> threadLocal = new ThreadLocal<>();
        // threadLocal.set实际上是设置到了Thread线程类的threadLocals这个Map上了
        // 每个Thread线程类都有threadLocals参数
        // ThreadLocal.ThreadLocalMap threadLocals = null;
        threadLocal.set(new M());
        // 一旦ThreadLocalMap没用了必须调用remove移除ThreadLocalMap
        threadLocal.remove();
    }
}
