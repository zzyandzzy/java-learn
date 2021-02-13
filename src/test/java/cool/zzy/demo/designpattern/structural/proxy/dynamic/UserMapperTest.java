package cool.zzy.demo.designpattern.structural.proxy.dynamic;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.dynamic
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:37
 * @since 1.0
 */
public class UserMapperTest {

    @Test
    public void selectAll() {
        UserMapper userMapper = (UserMapper) new MapperProxyFactory().newInstance(UserMapper.class);
        userMapper.selectAll();
    }
}