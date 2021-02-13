package cool.zzy.demo.designpattern.creational.builder;

/**
 * xyz.zzyitj.demo.designpattern.creational.builder
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/4 9:12 上午
 * @since 1.0
 */
public class CourseActualBuilder extends CourseBuilder {
    private Course course = new Course();

    @Override
    public void buildCurseName(String courseName) {
        course.setCourseName(courseName);
    }

    @Override
    public void buildCursePPT(String coursePPT) {
        course.setCoursePPT(coursePPT);
    }

    @Override
    public void buildCurseVideo(String courseVideo) {
        course.setCourseVideo(courseVideo);
    }

    @Override
    public void buildCurseArticle(String courseArticle) {
        course.setCourseArticle(courseArticle);
    }

    @Override
    public void buildCurseQA(String courseQA) {
        course.setCourseQA(courseQA);
    }

    @Override
    public Course makeCourse() {
        return course;
    }
}
