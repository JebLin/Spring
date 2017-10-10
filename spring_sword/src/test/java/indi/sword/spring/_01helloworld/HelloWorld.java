package indi.sword.spring._01helloworld;

/**
 * @Description
 * @Author rd_jianbin_lin
 * @Date 19:55 2017/10/6
 */
public class HelloWorld {

    private String user;

    public HelloWorld() {
        System.out.println("HelloWorld's constructor without param...");
    }

    public void setUser(String user) {
        System.out.println("setUser:" + user);
        this.user = user;
    }

    public HelloWorld(String user) {
        System.out.println("HelloWorld's constructor with user param...");
        this.user = user;
    }

    public void hello(){
        System.out.println("Hello: " + user);
    }

}

