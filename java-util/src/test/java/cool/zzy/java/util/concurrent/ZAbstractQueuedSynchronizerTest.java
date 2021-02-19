package cool.zzy.java.util.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/2/19 20:25
 * @since 1.0
 */
class ZAbstractQueuedSynchronizerTest {
    private ZReentrantLock reentrantLock;

    @BeforeEach
    void before() {
        reentrantLock = new ZReentrantLock();
    }

    /**
     * 线程竞争情况
     * t1线程调用{@link cool.zzy.java.util.concurrent.ZReentrantLock.Sync#nonfairTryAcquire(int)}得到锁
     * t2线程同样调用{@link cool.zzy.java.util.concurrent.ZReentrantLock.Sync#nonfairTryAcquire(int)}，
     * 但是得不到锁，返回false到
     * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer#acquire(int)}在这个方法里面，tryAcquire方法返回true则直接退出，
     * 返回false则执行{@link java.util.concurrent.locks.AbstractQueuedSynchronizer#acquireQueued(java.util.concurrent.locks.AbstractQueuedSynchronizer.Node, int)}
     * 在acquireQueued方法里面可以看到是一个无限循环，实际上只循环了2次，第一次循环，再次尝试能否得到锁，如果不能得到锁，则执行
     * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer#shouldParkAfterFailedAcquire(java.util.concurrent.locks.AbstractQueuedSynchronizer.Node, java.util.concurrent.locks.AbstractQueuedSynchronizer.Node)}
     * 这个方法如果是第一次执行则把{@link java.util.concurrent.locks.AbstractQueuedSynchronizer.Node#waitStatus}设置为-1，
     * 下次再进入这个方法检查如果为-1则返回true，返回true则执行
     * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer#parkAndCheckInterrupt()}方法
     * 主要代码是LockSupport.park(this);，将线程挂起，所以acquireQueued方法的循环体只执行两次
     * 当t1线程执行完成调用{@link cool.zzy.java.util.concurrent.ZReentrantLock#unlock()}时，
     * 将调用{@link java.util.concurrent.locks.AbstractQueuedSynchronizer#unparkSuccessor(java.util.concurrent.locks.AbstractQueuedSynchronizer.Node)}
     * 这个方法会将之前挂起的t2线程唤醒！
     */
    @Test
    void testAQS2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t1");
            reentrantLock.unlock();
        });
        Thread t2 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t2");
            reentrantLock.unlock();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * AQS一个线程未竞争情况
     */
    @Test
    void testAQS1() {
        reentrantLock.lock();
        new Thread(System.out::println).start();
        reentrantLock.unlock();
    }
}