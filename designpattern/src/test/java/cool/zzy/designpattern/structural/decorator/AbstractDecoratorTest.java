package cool.zzy.designpattern.structural.decorator;

import org.junit.jupiter.api.Test;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/6/21 12:09 下午
 * @since 1.0
 */
class AbstractDecoratorTest {
    @Test
    public void test() {
        AbstractBattercake battercake = new Battercake();
        battercake = new EggDecoractor(battercake);
        battercake = new EggDecoractor(battercake);
        battercake = new SausageDecorator(battercake);
        System.out.println(battercake.getDesc() + " 价格：" + battercake.getCost());
    }
}