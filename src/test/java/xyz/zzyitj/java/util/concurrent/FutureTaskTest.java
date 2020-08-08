package xyz.zzyitj.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * xyz.zzyitj.java.util.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/8/8 11:31
 * @since 1.0
 */
public class FutureTaskTest {
    public static final int SLEEP_GAP = 500;

    public static String getCurrentThread() {
        return System.currentTimeMillis() + " " + Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println(getCurrentThread() + " 洗好水壶");
                System.out.println(getCurrentThread() + " 灌上凉水");
                System.out.println(getCurrentThread() + " 放在火上");
                Thread.sleep(SLEEP_GAP);
                System.out.println(getCurrentThread() + " 水开了");
            } catch (InterruptedException e) {
                System.err.println("烧水发生异常被中断");
                return false;
            }
            System.out.println(getCurrentThread() + " 运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println(getCurrentThread() + " 洗茶壶");
                System.out.println(getCurrentThread() + " 洗茶杯");
                System.out.println(getCurrentThread() + " 拿茶叶");
                Thread.sleep(SLEEP_GAP);
                System.out.println(getCurrentThread() + " 洗完了");
            } catch (InterruptedException e) {
                System.err.println("清洗发生异常被中断");
                return false;
            }
            System.out.println(getCurrentThread() + " 运行结束");
            return true;
        }
    }

    public static void drinkTea(boolean waterOk, boolean capOk) {
        if (waterOk && capOk) {
            System.out.println(getCurrentThread() + " 泡茶喝");
        } else if (!waterOk) {
            System.out.println(getCurrentThread() + " 烧水失败，没有茶喝");
        } else {
            System.out.println(getCurrentThread() + " 清洗失败，没有茶喝");
        }
    }

    @Test
    public void test() {
        Callable<Boolean> hotWaterJob = new HotWaterJob();
        FutureTask<Boolean> hotWaterTask = new FutureTask<>(hotWaterJob);
        Thread hotWaterThread = new Thread(hotWaterTask, "烧水线程");

        Callable<Boolean> washJob = new WashJob();
        FutureTask<Boolean> washTask = new FutureTask<>(washJob);
        Thread washThread = new Thread(washTask, "清洗线程");

        hotWaterThread.start();
        washThread.start();
        Thread.currentThread().setName("主线程");
        try {
            boolean waterOk = hotWaterTask.get();
            boolean washOk = hotWaterTask.get();
            drinkTea(waterOk, washOk);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(getCurrentThread() + " 运行结束");
    }
}
