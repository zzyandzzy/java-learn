package cool.zzy.demo.concurrent;


/**
 * xyz.zzyitj.demo.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/17 6:15 下午
 * @since 1.0
 */
public class SynchronizedTest {

    /**
     * javac SynchronizedTest.java
     *
     * @param args
     */
    public static void main(String[] args) {
    }

    // access flag 0x22
    private synchronized void sync() {
    }

    // access flag 0x2
    private void sync2() {
    }

    private void sync3() {
    }

    private synchronized void sync4() {
    }

    public synchronized void sync5() {
    }

    public void sync6() {
    }

    void sync7() {
    }
}
