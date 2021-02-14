package cool.zzy.designpattern.creational.abstractfactory;

/**
 * xyz.zzyitj.demo.designpattern.creational.abstractfactory
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 10:05 下午
 * @since 1.0
 */
public class PythonArticle extends Article{
    @Override
    public void produce() {
        System.out.println("编写Python课程手记");
    }
}
