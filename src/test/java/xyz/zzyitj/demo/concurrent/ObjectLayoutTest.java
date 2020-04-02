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
     */
    @Test
    public void testPrintConcurrent() throws InterruptedException {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        new Thread(() -> {
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
        Thread.sleep(1000);
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
