package com.example.administrator.myonlinevideo.bombapi.entity;

/**用户实体类(注册时的请求体)
 * Created by Administrator on 2016/8/19.
 */
public class UserEntity  extends BaseEntity{

    private String username;

    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //    {
//        "username" : username,   // 用户名
//            "password" : password    // 密码
//    }
}
