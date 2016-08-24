package com.example.administrator.myonlinevideo;

import android.app.Application;

import com.example.administrator.myonlinevideo.common.ToastUtils;


public class VideoNewsApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }
}
