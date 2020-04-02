package xyz.zzyitj.demo.concurrent;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * xyz.zzyitj.demo.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/1 11:33 上午
 * @since 1.0
 */
public class ObjectLayoutTest {
    /**
     * 打印上了synchronized锁后的变化
     * 锁升级过程
     */
    @Test
    public void testPrintConcurrent() throws InterruptedException {
        Object o = new Object();
        // 打印偏向锁请加：-XX:BiasedLockingStartupDelay=0 参数
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 由无锁/偏向锁升级为轻量级锁
        // 不加-XX:BiasedLockingStartupDelay=0参数时，下面代码由无锁升级为轻量级锁
        // 加了-XX:BiasedLockingStartupDelay=0参数时，下面代码一直是偏向锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(ClassLayout.parseInstance(o).toPrintable());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        // 加上下面的代码两个线程竞争，轻量级锁就会升级为重量级锁
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (o) {
//                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
//                }
//            }
//        }).start();
        Thread.sleep(2000);
    }

    /**
     * 打印对象内存布局
     */
    @Test
    public void testPrint() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 获取了hashCode后的对象布局，仔细观察
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    /**
     * 打印数组的对象布局
     */
    @Test
    public void testPrintArray() {
        Object[] oArray = new Object[16];
        System.out.println(ClassLayout.parseInstance(oArray).toPrintable());
    }
}
