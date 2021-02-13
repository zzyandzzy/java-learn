package cool.zzy.demo.designpattern.structural.decorator;

/**
 * xyz.zzyitj.demo.designpattern.structural.decorator
 * 加香肠！装饰者实现类
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/21 12:05 下午
 * @since 1.0
 */
public class SausageDecorator extends AbstractDecorator {
    public SausageDecorator(AbstractBattercake battercake) {
        super(battercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一根香肠";
    }

    @Override
    protected int getCost() {
        return super.getCost() + 2;
    }
}
