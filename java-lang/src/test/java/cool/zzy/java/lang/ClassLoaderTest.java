package cool.zzy.java.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 双亲委派机制：
 * 如果一个类加载器收到了类加载的请求,它不会自己去尝试加载这个类,而是把这个请求委派给父类加载器去完成,
 * 这样层层递进,最终所有的加载请求都被传到最顶层的启动类加载器中,
 * 只有当父类加载器无法完成这个加载请求(它的搜索范围内没有找到所需的类)时,才会交给子类加载器去尝试加载
 *
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/2/28 19:02
 * @since 1.0
 */
class ClassLoaderTest {
    @BeforeEach
    void before() {
    }

    @Test
    void testClassLoader1() {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        System.out.println(loader.toString());
        System.out.println(loader.getParent().toString());
        System.out.println(loader.getParent().getParent());
    }

}
