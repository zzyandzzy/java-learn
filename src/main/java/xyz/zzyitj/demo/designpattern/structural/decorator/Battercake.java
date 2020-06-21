package xyz.zzyitj.demo.designpattern.structural.decorator;

/**
 * xyz.zzyitj.demo.designpattern.structural.decorator
 * 煎饼类，产品类
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/21 12:02 下午
 * @since 1.0
 */
public class Battercake extends AbstractBattercake {
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int getCost() {
        return 8;
    }
}
