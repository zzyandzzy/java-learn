package cool.zzy.designpattern.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.dynamic
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:23
 * @since 1.0
 */
public class MapperProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法: " + method.getName());
        System.out.println("参数: " + Arrays.toString(args));
        return null;
    }
}
