package cool.zzy.java.util.concurrent.chapter4;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * xyz.zzyitj.demo.concurrent.art.chapter4
 * 这个文件是对《Java并发编程的艺术》第四章第3节"线程间的通讯"的第一个小标题
 * volatile的使用的验证
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/19 8:02 下午
 * @since 1.0
 */
public class Solution431 {
    // 线程停止的标志，没有加volatile线程永远不会停止，加了volatile线程才会停止
    private static boolean isThreadStop = false;

    /**
     * 测试volatile对线程间通讯的影响
     * isThreadStop线程停止的标志，
     * 没有加volatile线程永远不会停止，
     * 加了volatile线程才会停止
     * 体现了加了volatile的变量在线程之间的通讯机制，
     * 即线程缓存变量过期，获取变量要从主内存获取
     * 修改变量要刷新到主内存
     */
    @Test
    public void volatileTest() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            while (!isThreadStop) {
            }
            System.out.println("stop.");
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                // 休眠1秒后把线程停止标志修改为tree
                isThreadStop = true;
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
