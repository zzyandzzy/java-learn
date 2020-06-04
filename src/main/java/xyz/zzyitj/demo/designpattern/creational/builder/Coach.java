package xyz.zzyitj.demo.designpattern.creational.builder;

/**
 * xyz.zzyitj.demo.designpattern.creational.builder
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/4 9:14 上午
 * @since 1.0
 */
public class Coach {
    private CourseBuilder courseBuilder;

    public void setCourseBuilder(CourseBuilder courseBuilder) {
        this.courseBuilder = courseBuilder;
    }

    public Course makeCourse(String courseName, String coursePPT,
                             String courseVideo, String courseArticle,
                             String courseQA) {
        this.courseBuilder.buildCurseName(courseName);
        this.courseBuilder.buildCursePPT(coursePPT);
        this.courseBuilder.buildCurseVideo(courseVideo);
        this.courseBuilder.buildCurseArticle(courseArticle);
        this.courseBuilder.buildCurseQA(courseQA);
        return this.courseBuilder.makeCourse();
    }
}
