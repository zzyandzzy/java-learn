package xyz.zzyitj.demo.designpattern.structural.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.dynamic
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:43
 * @since 1.0
 */
public class MapperProxyFactory<T> {
    public T newInstance(Class<T> mapperInterface) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{mapperInterface}, new MapperProxy());
    }
}
