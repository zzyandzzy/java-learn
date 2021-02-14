package cool.zzy.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * xyz.zzyitj.demo.concurrent
 * 锁的级别分为：无锁 --- 偏向锁 --- 轻量级锁 --- 重量级锁
 * CAS自旋锁(乐观锁)是轻量级锁
 * 原理：{@link #testCAS()}
 * CAS可能产生ABA问题
 * {@link #testABA()}
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/1 10:23 上午
 * @since 1.0
 */
public class CompareAndSwapTest {
    private final static String A = "A";
    private final static String B = "B";
    private final static AtomicReference<String> ar = new AtomicReference<>(A);
//    private final static AtomicMarkableReference<String> ar = new AtomicMarkableReference<>(A, false);

    /**
     * ABA问题描述为：
     * 因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，
     * 但是如果一个值原来是A，变成了B，又变成了A，那么使用CAS进行检查时会发现它的值没有发生变化，但是实际上却变化了。
     * 解决办法：
     * ABA问题的解决思路就是使用版本号。
     * 在变量前面追加上版本号，每次变量更新的时候把版本号加一，那么A－B－A 就会变成1A-2B－3A。
     * 从Java1.5开始JDK的atomic包里提供了一个类AtomicStampedReference来解决ABA问题。
     * 这个类的compareAndSet方法作用是首先检查当前引用是否等于预期引用，并且当前标志是否等于预期标志，如果全部相等，
     * 则以原子方式将该引用和该标志的值设置为给定的更新值。
     */
    @Test
    public void testABA() throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里直接执行了，因为线程2把A改为B后，线程3又把B改为A，线程A认为数据没被修改
            if (ar.compareAndSet(A, B)) {
                System.out.println("我是线程1,我成功将A改成了B");
            }
        }).start();
        new Thread(() -> {
            if (ar.compareAndSet(A, B)) {
                System.out.println("我是线程2,我成功将A改成了B");
            }
        }).start();
        new Thread(() -> {
            if (ar.compareAndSet(B, A)) {
                System.out.println("我是线程3,我成功将B改成了A");
            }
        }).start();
        // 休眠JUnit主线程，不然没等子线程运行完会直接结束
        Thread.sleep(1000);
    }

    /**
     * 原理：
     * CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。
     * 更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。
     * {@link sun.misc.Unsafe#getAndAddInt(Object, long, int)}
     * CAS的自旋。循环体当中做了三件事：
     * 1.获取当前值。
     * 2.当前值+1，计算出目标值。
     * 3.进行CAS操作，如果成功则跳出循环，如果失败则重复上述步骤。
     * CAS缺点：
     * 1、CPU开销较大，多线程反复尝试更新某一个变量的时候容易出现；
     * 2、不能保证代码块的原子性，只能保证变量的原子性操作；
     * 3、ABA问题。
     */
    @Test
    public void testCAS() {
        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();
    }
}
