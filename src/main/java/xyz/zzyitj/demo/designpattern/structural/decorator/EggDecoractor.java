package xyz.zzyitj.demo.designpattern.structural.decorator;

/**
 * xyz.zzyitj.demo.designpattern.structural.decorator
 * 加蛋！装饰者实现类
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/21 12:05 下午
 * @since 1.0
 */
public class EggDecoractor extends AbstractDecorator {
    public EggDecoractor(AbstractBattercake battercake) {
        super(battercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    protected int getCost() {
        return super.getCost() + 1;
    }
}
