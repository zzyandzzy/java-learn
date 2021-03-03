package cool.zzy.java.lang.ref;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/4/5 10:53 上午
 * @since 1.0
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
