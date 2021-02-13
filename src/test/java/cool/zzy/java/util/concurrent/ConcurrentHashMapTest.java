package cool.zzy.java.util.concurrent;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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


    /**
     * {@linkplain ConcurrentHashMap#putVal(Object, Object, boolean) 初始化代码在1016行}
     * {@linkplain ConcurrentHashMap#initTable() 初始化table}
     */
    @Test
    public void testInit() {
        // 无参构造方法
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("test", "test");
        // 指定大小
        Map<String, String> map1 = new ConcurrentHashMap<>(32);
        map1.put("test", "test");
    }

    /**
     * 这个方法就是把int的二进制的0设置为1
     * 比如19的二进制是：
     * 0000 0000 0001 0011 = 19
     * 先减去1得18，二进制
     * 0000 0000 0001 0010 = 18
     * <p>
     * 第一轮：18右移1位，再和原来的值相与(OR)
     * 0000 0000 0001 0010 = 18
     * >>> 1(无符号右移1位，大小减一半)
     * 0000 0000 0000 1001 = 9
     * | (OR)
     * 0000 0000 0001 0010 = 18
     * = (结果)
     * 0000 0000 0001 1011 = 27
     * <p>
     * 第二轮：27右移2位，再和原来的值相与(OR)
     * 0000 0000 0001 1011 = 27
     * >>> 2(无符号右移2位，大小为原来的1/4，向下取整)
     * 0000 0000 0000 0110 = 6
     * | (OR)
     * 0000 0000 0001 1011 = 27
     * = (结果)
     * 0000 0000 0001 1111 = 31
     * <p>
     * 如此反复，直到第5轮，无符号右移16位，因为int类型最大为16位
     * {@link ConcurrentHashMap#tableSizeFor(int)}
     */
    @Test
    public void testTableSizeFor() {
    }
}