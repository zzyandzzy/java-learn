package cool.zzy.demo.designpattern.creational.factorymethod;

/**
 * xyz.zzyitj.demo.designpattern.creational.factorymethod
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 8:03 下午
 * @since 1.0
 */
public class PythonVideoFactory implements VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
