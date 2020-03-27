package xyz.zzyitj.java.util.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * xyz.zzyitj.java.util.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 7:03 下午
 * @since 1.0
 */
public class ConcurrentHashMapTest {

    /**
     * 测试ConcurrentHashMap里面的NCPU参数
     * 返回Java虚拟机可用的处理器数
     * {@link java.util.concurrent.ConcurrentHashMap#NCPU}
     */
    @Test
    public void testNCPU() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}