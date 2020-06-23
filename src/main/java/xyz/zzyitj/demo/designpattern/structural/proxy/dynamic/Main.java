package xyz.zzyitj.demo.designpattern.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.dynamic
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/23 7:47 下午
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{UserMapper.class},
                (proxy, method, args1) -> {
                    System.out.println(method.getName());
                    return null;
                });
        userMapper.selectAll();
    }
}
