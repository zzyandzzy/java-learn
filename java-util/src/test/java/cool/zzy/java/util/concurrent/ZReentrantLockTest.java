package cool.zzy.java.util.concurrent;

import org.junit.jupiter.api.Test;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/2/17 21:59
 * @since 1.0
 */
class ZReentrantLockTest {
    int a = 0;
    int b = 0;

    @Test
    void lock() throws InterruptedException {
        ZReentrantLock reentrantLock = new ZReentrantLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                a++;
                b += 2;
                reentrantLock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                a++;
                b += 2;
                reentrantLock.unlock();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a);
        System.out.println(b);
    }
}