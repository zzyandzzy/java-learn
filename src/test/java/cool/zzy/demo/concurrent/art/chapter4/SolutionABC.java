package cool.zzy.demo.concurrent.art.chapter4;

import java.io.IOException;

/**
 * xyz.zzyitj.demo.concurrent.art.chapter4
 * 这个文件是我测试三个线程依次打印ABC的代码
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/19 7:56 下午
 * @since 1.0
 */
public class SolutionABC {
    private static volatile boolean one = false;
    private static volatile boolean two = false;

    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
                one = true;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (one) {
                    System.out.println("B");
                    two = true;
                    one = false;
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (two) {
                    System.out.println("C");
                    two = false;
                }
            }
        }).start();
        System.in.read();
    }
}
