package cool.zzy.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * xyz.zzyitj.demo.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/2 6:05 下午
 * @since 1.0
 */
public class VolatileTest {
    volatile boolean isRun = true;

    void m() {
        System.out.println("m start");
        while (isRun) {
        }
        System.out.println("m stop");
    }

    /**
     * 测试volatile关键字的作用
     * 加了volatile关键字的其中一个作用是保证了2个线程间的可见性
     * 主线程修改的值在t1线程也更新了
     */
    @Test
    public void test1() throws InterruptedException {
        new Thread(this::m, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        isRun = false;
    }


    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    /**
     * 测试指令重排序
     * 运行结果：
     * 第 374602 次执行!
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            // 因为线程one和线程other开始a = 1,b = 1，所以有这几种情况：
            // 1：one线程先执行: a = 1,x = 0，b = 1,y = 1
            // 2：one线程执行的时候other线程执行: a = 1,b = 1,x = 1,y = 1
            // 3：other线程先执行: b = 1,y = 0,a = 1,x = 1
            // 4：other线程执行的时候one线程执行: b = 1,a = 1,y = 1,x = 1
            // 可以看出，x和y永远不可能出现x = 0, y = 0这种情况，除非在线程内部指令执行顺序调换了
            // 如：one线程先x = b;再执行a = 1;则有可能出现one线程执行的时候other线程执行: x = 0, y = 0, a = 1, b = 1这种情况
            // 这就是发生了指令重排序
            // 而加了volatile关键字的另外一个作用就是防止指令重排序
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1; // b = 1
                    y = a; // y = 1
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            if (x == 0 && y == 0) {
                System.out.println("第 " + i + " 次执行!");
                break;
            }
        }
    }
}
