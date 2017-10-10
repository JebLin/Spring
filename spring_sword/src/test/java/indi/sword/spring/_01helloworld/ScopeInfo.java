package indi.sword.spring._01helloworld;

/**
 * @Description
 * @Author rd_jianbin_lin
 * @Date 19:55 2017/10/6
 */
public class ScopeInfo {

    private String user;

    public ScopeInfo() {
        System.out.println("ScopeInfo's constructor without param...");
    }

    public void setUser(String user) {
        System.out.println("setUser:" + user);
        this.user = user;
    }

    public ScopeInfo(String user) {
        System.out.println("ScopeInfo's constructor with user param...");
        this.user = user;
    }

    public void hello(){
        System.out.println("Hello: " + user);
    }

}

