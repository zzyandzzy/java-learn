package xyz.zzyitj.demo.designpattern.creational.factorymethod;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * xyz.zzyitj.demo.designpattern.creational.factorymethod
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/3 8:05 下午
 * @since 1.0
 */
public class VideoFactoryTest {
    @Test
    public void testJavaVideoFactory() {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }

    @Test
    public void testPythonVideoFactory() {
        VideoFactory videoFactory = new PythonVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }
}