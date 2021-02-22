package cool.zzy.java.util.concurrent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/6/12 8:21 下午
 * @since 1.0
 */
public class ThreadPoolExecutorTest {
//    int corePoolSize = (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.9));
    int corePoolSize = 2;
    int maximumPoolSize = 4;
    long keepAliveTime = 10;
    TimeUnit unit = TimeUnit.SECONDS;
    ThreadFactory threadFactory = new NameTreadFactory();
    RejectedExecutionHandler handler = new MyIgnorePolicy();
    BlockingQueue<Runnable> workQueue;
    ThreadPoolExecutor executor;

    @BeforeEach
    void before() {
    }

    @AfterEach
    void after() {
        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试无界队列
     * <p>
     * 结果：
     * my-thread-1 has been created
     * my-thread-2 has been created
     * MyTask [name=0] is running! 2021-02-22 05:07:27:072
     * MyTask [name=1] is running! 2021-02-22 05:07:27:072
     * MyTask [name=3] is running! 2021-02-22 05:07:27:281
     * MyTask [name=2] is running! 2021-02-22 05:07:27:281
     * MyTask [name=5] is running! 2021-02-22 05:07:27:384
     * MyTask [name=4] is running! 2021-02-22 05:07:27:384
     * MyTask [name=6] is running! 2021-02-22 05:07:27:487
     * MyTask [name=7] is running! 2021-02-22 05:07:27:492
     * MyTask [name=8] is running! 2021-02-22 05:07:27:592
     * MyTask [name=9] is running! 2021-02-22 05:07:27:597
     * 分析：
     * 只创建了两个线程，但是队列默认是{@link Integer#MAX_VALUE}
     */
    @Test
    public void testUnboundedQueues() {
        workQueue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
    }

    /**
     * 测试有界队列
     */
    @Test
    public void test() {
        workQueue = new ArrayBlockingQueue<>(2);
        executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//            System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running! " +
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
                Thread.sleep(100); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
