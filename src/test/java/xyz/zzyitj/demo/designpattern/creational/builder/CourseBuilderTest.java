package xyz.zzyitj.demo.designpattern.creational.builder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * xyz.zzyitj.demo.designpattern.creational.builder
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/4 9:19 上午
 * @since 1.0
 */
public class CourseBuilderTest {

    @Test
    public void makeCourse() {
        CourseBuilder courseBuilder = new CourseActualBuilder();
        Coach coach = new Coach();
        coach.setCourseBuilder(courseBuilder);
        Course course = coach.makeCourse(
                "Java设计模式", "Java设计模式PPT",
                "Java设计模式Video", "Java设计模式Article",
                "Java设计模式QA");
        System.out.println(course);
    }
}