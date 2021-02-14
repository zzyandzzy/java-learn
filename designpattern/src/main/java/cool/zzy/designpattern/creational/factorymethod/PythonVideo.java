package cool.zzy.designpattern.creational.factorymethod;

/**
 * xyz.zzyitj.demo.designpattern.creational.factorymethod
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 8:03 下午
 * @since 1.0
 */
public class PythonVideo extends Video {
    @Override
    void produce() {
        System.out.println("录制Python视频");
    }
}
