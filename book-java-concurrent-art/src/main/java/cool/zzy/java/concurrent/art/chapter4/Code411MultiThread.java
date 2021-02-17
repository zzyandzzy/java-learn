package cool.zzy.java.concurrent.art.chapter4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * xyz.zzyitj.demo.concurrent
 * MultiThread
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/12 11:30 上午
 * @since 1.0
 */
public class Code411MultiThread {

    /**
     * 4-1
     * [4] Signal Dispatcher 分发处理发送给JVM信号的线程
     * [3] Finalizer 调用对象finalize方法的线程
     * [2] Reference Handler 清除Reference的线程
     * [1] main main线程，用户程序入口
     */
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfoArray = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfoArray) {
            System.out.println("[" + threadInfo.getThreadId() + "] "
                    + threadInfo.getThreadName());
        }
    }
}
