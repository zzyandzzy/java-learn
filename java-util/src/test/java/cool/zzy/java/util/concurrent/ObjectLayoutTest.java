package cool.zzy.java.util.concurrent;

import org.junit.jupiter.api.Test;
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
        final Object o = new Object();
        System.out.println(Thread.currentThread());
        // java.lang.Object object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           0d 00 00 00 (00001101 00000000 00000000 00000000) (13)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4        (object header)                           00 10 00 00 (00000000 00010000 00000000 00000000) (4096)
        //     12     4        (loss due to the next object alignment)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 获取了hashCode后的对象布局，仔细观察
        // 1214626244
        int hashCode = o.hashCode();
        System.out.println(hashCode);
        // java.lang.Object object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           09 c4 b9 65 (00001001 11000100 10111001 01100101) (1706673161)
        //      4     4        (object header)                           48 00 00 00 (00011010 00000000 00000000 00000000) (26)
        //      8     4        (object header)                           00 10 00 00 (00000000 00010000 00000000 00000000) (4096)
        //     12     4        (loss due to the next object alignment)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    /**
     * 打印数组的对象布局
     */
    @Test
    public void testPrintArray() {
        Object[] oArray = new Object[16];
        // [Ljava.lang.Object; object internals:
        // OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
        //      0     4                    (object header)                           09 00 00 00 (00001001 00000000 00000000 00000000) (9)
        //      4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4                    (object header)                           08 3f 01 00 (00001000 00111111 00000001 00000000) (81672)
        //     12     4                    (object header)                           10 00 00 00 (00010000 00000000 00000000 00000000) (16)
        //     16    64   java.lang.Object Object;.<elements>                        N/A
        //Instance size: 80 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        System.out.println(ClassLayout.parseInstance(oArray).toPrintable());
    }
}
