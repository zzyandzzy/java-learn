package xyz.zzyitj.demo.designpattern.structural.proxy.staticproxy;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.staticproxy
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/7/18 21:28
 * @since 1.0
 */
public class RealMovie implements Movie {
    @Override
    public void play() {
        System.out.println("您正在观看电影 《肖申克的救赎》");
    }
}
