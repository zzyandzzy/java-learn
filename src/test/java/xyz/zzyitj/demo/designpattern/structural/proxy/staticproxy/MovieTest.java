package xyz.zzyitj.demo.designpattern.structural.proxy.staticproxy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.staticproxy
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:30
 * @since 1.0
 */
public class MovieTest {

    @Test
    public void play() {
        RealMovie realmovie = new RealMovie();
        Movie movie = new Cinema(realmovie);
        movie.play();
    }
}