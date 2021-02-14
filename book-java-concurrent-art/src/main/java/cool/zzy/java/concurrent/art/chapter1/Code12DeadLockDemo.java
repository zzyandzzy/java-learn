package cool.zzy.java.concurrent.art.chapter1;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/2/14 20:49
 * @since 1.0
 */
public class Code12DeadLockDemo {
    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) throws InterruptedException {
        new Code12DeadLockDemo().deadLock();
    }

    private void deadLock() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            // 第一步：thread1获得A锁
            synchronized (A) {
                System.out.print("1A");
                // 第三步：休眠2秒，因为可能thread1获得A锁，thread2还没有获得B锁，这样就不能造成死锁
                // 休眠了2秒thread2怎么说也获得了B锁了吧。。。
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 第四步，等待B锁释放，此时thread2持有B锁，也在等待thread1持有的A锁释放，您等待我释放锁，我等待您释放锁
                synchronized (B) {
                    System.out.println("2AB");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            // 第一步：thread2获得B锁
            synchronized (B) {
                System.out.print("1B");
                // 第二步：休眠1秒，这里是为了防止thread2先获得B锁后又获得A锁，导致thread2线程直接执行完成
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 第四步，等待A锁释放，此时thread1持有A锁，也在等待thread2持有的B锁释放，您等待我释放锁，我等待您释放锁
                synchronized (A) {
                    System.out.print("2BA");
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println();
    }
}
