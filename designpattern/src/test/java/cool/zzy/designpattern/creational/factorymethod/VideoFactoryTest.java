package cool.zzy.designpattern.creational.factorymethod;

import org.junit.jupiter.api.Test;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 8:05 下午
 * @since 1.0
 */
class VideoFactoryTest {
    @Test
    public void testJavaFactory() {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
        ArticleFactory articleFactory = new JavaArticleFactory();
        Article article = articleFactory.getArticle();
        article.produce();
    }

    @Test
    public void testPythonFactory() {
        VideoFactory videoFactory = new PythonVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
        ArticleFactory articleFactory = new PythonArticleFactory();
        Article article = articleFactory.getArticle();
        article.produce();
    }
}