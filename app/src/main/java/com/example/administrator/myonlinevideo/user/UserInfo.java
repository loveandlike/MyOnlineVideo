package com.example.administrator.myonlinevideo.user;

/**
 * Created by Harpa on 2016/6/8.
 */

public class UserInfo {

    private String name;
    private String password;
    private String allowed;

    public UserInfo() {
    }

    public UserInfo(String name, String password, String allowed) {
        this.name = name;
        this.allowed = allowed;
        this.password = password;
    }

    public String getAllowed() {
        return allowed;
    }

    public void setAllowed(String allowed) {
        this.allowed = allowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
