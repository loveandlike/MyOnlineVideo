package com.example.administrator.myonlinevideo.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/8/17.
 */
public class SharedPrfHelper1 {
    //单例模式

    private static SharedPrfHelper1 instance;

    private SharedPreferences preferences;

    private SharedPrfHelper1(Context context) {
        preferences = context.getSharedPreferences("remember",Context.MODE_PRIVATE);
    }

    //饿汉模式
//    public static SharedPrfHelper getInstance() {
//        instance = new SharedPrfHelper();
//        return instance;
//    }

    //懒汉模式
    public static SharedPrfHelper1 getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrfHelper1(context);
        }
        return instance;
    }

    /**
     * 保存数据的方法
     * */
    public void saveInfo(UserInfo info){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name",info.getName());
        editor.putString("user_pwd",info.getPassword());
        editor.putString("allowed",info.getAllowed());
        editor.commit();//提交  务必不要忘记
    }

    /**
     * 读取数据的方法
     * */
    public UserInfo getInfo(){
        //参数一是键，  参数二是默认值
        String name = preferences.getString("user_name","");
        String password = preferences.getString("user_pwd","");
        String allowed=preferences.getString("allowed","");
        return new UserInfo(name,password,allowed);
    }

}
