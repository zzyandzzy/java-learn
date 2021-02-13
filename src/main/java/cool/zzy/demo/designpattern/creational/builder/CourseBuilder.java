package cool.zzy.demo.designpattern.creational.builder;

/**
 * xyz.zzyitj.demo.designpattern.creational.builder
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/4 9:10 上午
 * @since 1.0
 */
public abstract class CourseBuilder {
    private String courseName;
    private String coursePPT;
    private String courseVideo;
    private String courseArticle;
    private String courseQA;

    public abstract void buildCurseName(String courseName);

    public abstract void buildCursePPT(String coursePPT);

    public abstract void buildCurseVideo(String courseVideo);

    public abstract void buildCurseArticle(String courseArticle);

    public abstract void buildCurseQA(String courseQA);

    public abstract Course makeCourse();
}
