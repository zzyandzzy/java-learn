package cool.zzy.designpattern.structural.proxy.dynamic;

import org.junit.jupiter.api.Test;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:37
 * @since 1.0
 */
class UserMapperTest {

    @Test
    public void selectAll() {
        UserMapper userMapper = (UserMapper) new MapperProxyFactory().newInstance(UserMapper.class);
        userMapper.selectAll();
    }
}