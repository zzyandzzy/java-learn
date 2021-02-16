package cool.zzy.java.util.concurrent;

import org.junit.jupiter.api.Test;

/**
 * xyz.zzyitj.demo.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/2 11:57 上午
 * @since 1.0
 */
public class LockTest {
    /**
     * 锁消除
     * 我们都知道StringBuffer是线程安全的，因为它关键的方法都被synchronized修饰过
     * 但看下面这段代码，stringBuffer这个引用只会在testLock1方法中使用，
     * 不可能被其他线程引用（因为是局部变量，栈私有）
     * 因此stringBuffer不可能是共享资源，所以JVM自动消除StringBuffer内部的锁
     */
    @Test
    public void testLock1() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("xx").append("lock");
    }

    /**
     * JVM对检测到这样一连串的操作都对同一个对象加锁，
     * 此时JVM就会将锁的范围粗化到这一连串操作的外部，
     * 使得这一连串操作只需要一次加锁即可
     */
    @Test
    public void testLock2() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (i < 100){
            stringBuffer.append(i);
            i++;
        }
    }
}
