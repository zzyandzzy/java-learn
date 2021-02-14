package cool.zzy.designpattern.structural.proxy.staticproxy;

import org.junit.jupiter.api.Test;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:30
 * @since 1.0
 */
class MovieTest {

    @Test
    public void play() {
        RealMovie realmovie = new RealMovie();
        Movie movie = new Cinema(realmovie);
        movie.play();
    }
}