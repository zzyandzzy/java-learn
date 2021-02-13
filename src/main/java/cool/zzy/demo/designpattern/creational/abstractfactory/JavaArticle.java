package cool.zzy.demo.designpattern.creational.abstractfactory;

/**
 * xyz.zzyitj.demo.designpattern.creational.abstractfactory
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 10:03 下午
 * @since 1.0
 */
public class JavaArticle extends Article{
    @Override
    public void produce() {
        System.out.println("编写Java课程手记");
    }
}
