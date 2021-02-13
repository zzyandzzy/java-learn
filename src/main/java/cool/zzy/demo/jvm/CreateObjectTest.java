package cool.zzy.demo.jvm;

/**
 * xyz.zzyitj.demo.jvm
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/4 4:47 下午
 * @since 1.0
 */
public class CreateObjectTest {
    public static void main(String[] args) {
        // javap -c -l CreateObjectTest
        // 该命令可以看到Java汇编代码
        Object o = new Object();
    }
}
