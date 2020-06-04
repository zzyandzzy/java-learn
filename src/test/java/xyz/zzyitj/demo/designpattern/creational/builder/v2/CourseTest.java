package xyz.zzyitj.demo.designpattern.creational.builder.v2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * xyz.zzyitj.demo.designpattern.creational.builder.v2
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/4 9:32 上午
 * @since 1.0
 */
public class CourseTest {
    @Test
    public void testBuild() {
        Course course = new Course.CourseBuilder()
                .buildCourseName("Java设计模式")
                .buildCoursePPT("Java设计模式PPT")
                .buildCourseVideo("Java设计模式Video")
                .buildCourseQA("Java设计模式QA")
                .build();
        System.out.println(course);
    }
}