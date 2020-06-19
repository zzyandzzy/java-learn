package xyz.zzyitj.demo.concurrent.art.chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * xyz.zzyitj.demo.concurrent.art.chapter4
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/19 8:32 下午
 * @since 1.0
 */
public class Solution432 {
    static boolean flag = true;
    static Object lock = new Object();

    /**
     * 结果
     * Thread[WaitThread,5,main] flag is true, time: 20:40:26
     * Thread[notifyThread,5,main] hold lock, time: 20:40:27
     * Thread[notifyThread,5,main] hold lock again, time: 20:40:32
     * Thread[WaitThread,5,main] flag is false, time: 20:40:37
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }

    private static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true, time: " +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false, time: " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    private static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock, time: " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again, time: " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
