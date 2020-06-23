package xyz.zzyitj.demo.designpattern.structural.proxy.dynamic;

/**
 * xyz.zzyitj.demo.designpattern.structural.proxy.dynamic
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/23 7:46 下午
 * @since 1.0
 */
public class User {
    private Integer id;
    private String username;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
