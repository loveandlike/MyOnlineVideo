package com.example.administrator.myonlinevideo.ui.base;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * 作者：yuanchao on 2016/8/17 0017 14:14
 * 邮箱：yuanchao@feicuiedu.com
 */
public abstract class BaseItemView<Model> extends FrameLayout{

    public BaseItemView(Context context) {
        super(context);
        initView();
    }

    /** 初始化当前视图*/
    protected abstract void initView();

    /** 将实体数据设计到当前视图上*/
    protected abstract void bindModel(Model model);
}