package cool.zzy.demo.designpattern.creational.abstractfactory;

import org.junit.Test;

/**
 * xyz.zzyitj.demo.designpattern.creational.abstractfactory
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 10:13 下午
 * @since 1.0
 */
public class CourseFactoryTest {

    @Test
    public void javaCourseFactoryTest() {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }

    @Test
    public void pythonCourseFactoryTest() {
        CourseFactory courseFactory = new PythonCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }
}