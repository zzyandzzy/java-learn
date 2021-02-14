package cool.zzy.designpattern.structural.decorator;

/**
 * xyz.zzyitj.demo.designpattern.structural.decorator
 * 煎饼抽象类，也可以是接口类
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/21 12:01 下午
 * @since 1.0
 */
public abstract class AbstractBattercake {
    protected abstract String getDesc();

    protected abstract int getCost();
}
