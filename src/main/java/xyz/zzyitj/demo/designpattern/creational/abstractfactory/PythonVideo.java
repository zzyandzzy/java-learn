package xyz.zzyitj.demo.designpattern.creational.abstractfactory;

/**
 * xyz.zzyitj.demo.designpattern.creational.abstractfactory
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 10:05 下午
 * @since 1.0
 */
public class PythonVideo extends Video{
    @Override
    public void produce() {
        System.out.println("录制Python课程视频");
    }
}
