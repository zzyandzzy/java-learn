package cool.zzy.demo.concurrent;

/**
 * xyz.zzyitj.demo.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/25 9:46 上午
 * @since 1.0
 */
public class DieLock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " get lock1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                }
            }
        }, "thread-1").start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " get lock2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                }
            }
        }, "thread-2").start();
    }
}
