package com.example.administrator.myonlinevideo.ui.comments;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 用于{@link CommentsActivity}标题的TextView。
 * <p/>
 * 重写{@link #isFocused()}方法，让走马灯效果(<em>android:ellipsize="marquee"</em>)始终播放。
 * 作者：yuanchao on 2016/8/18 0018 10:22
 * 邮箱：yuanchao@feicuiedu.com
 */
public class TitleTextView extends TextView {
    public TitleTextView(Context context) {
        super(context);
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override public boolean isFocused() {
        return true;
    }
}
