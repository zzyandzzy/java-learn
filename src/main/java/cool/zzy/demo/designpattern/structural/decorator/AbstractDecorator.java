package cool.zzy.demo.designpattern.structural.decorator;

/**
 * xyz.zzyitj.demo.designpattern.structural.decorator
 * 煎饼装饰者类
 * 这里可以为抽象类，具体可以看业务场景
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/21 12:03 下午
 * @since 1.0
 */
public class AbstractDecorator extends AbstractBattercake {
    private AbstractBattercake battercake;

    public AbstractDecorator(AbstractBattercake battercake) {
        this.battercake = battercake;
    }

    @Override
    protected String getDesc() {
        return battercake.getDesc();
    }

    @Override
    protected int getCost() {
        return battercake.getCost();
    }
}
