package xyz.zzyitj.demo.reference;

import java.io.IOException;

/**
 * xyz.zzyitj.demo.reference
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/5 10:51 上午
 * @since 1.0
 */
public class NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.in.read();
    }
}
