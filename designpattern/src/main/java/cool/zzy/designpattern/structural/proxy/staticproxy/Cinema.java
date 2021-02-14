package cool.zzy.designpattern.structural.proxy.staticproxy;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.staticproxy
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:28
 * @since 1.0
 */
public class Cinema implements Movie {
    RealMovie realMovie;

    public Cinema(RealMovie realMovie) {
        this.realMovie = realMovie;
    }

    @Override
    public void play() {
        guanggao(true);
        realMovie.play();
        guanggao(false);
    }

    public void guanggao(boolean isStart) {
        if (isStart) {
            System.out.println("电影马上开始了，爆米花、可乐、口香糖9.8折，快来买啊！");
        } else {
            System.out.println("电影马上结束了，爆米花、可乐、口香糖9.8折，买回家吃吧！");
        }
    }
}
