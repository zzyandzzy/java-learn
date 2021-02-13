package cool.zzy.demo.designpattern.structural.proxy.staticproxy;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.staticproxy
 * <p>
 * 我们平常去电影院看电影的时候，在电影开始的阶段是不是经常会放广告呢？
 * <p>
 * 电影是电影公司委托给影院进行播放的，但是影院可以在播放电影的时候，产生一些自己的经济收益，
 * 比如卖爆米花、可乐等，然后在影片开始结束时播放一些广告。
 * <p>
 * 现在用代码来进行模拟。
 * <p>
 * 首先得有一个接口，通用的接口是代理模式实现的基础。这个接口我们命名为 Movie，代表电影播放的能力。
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:27
 * @since 1.0
 */
public interface Movie {
    void play();
}
