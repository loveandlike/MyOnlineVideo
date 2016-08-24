package com.example.administrator.myonlinevideo.ui.local;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myonlinevideo.R;
import com.example.videoplayer.full.VideoViewActivity;

import butterknife.ButterKnife;


/**
 * 作者：yuanchao on 2016/8/12 0012 14:34
 * 邮箱：yuanchao@feicuiedu.com
 */
public class LocalVidioView extends FrameLayout {

    //    @BindView(R.id.ivPreview)ImageView ivPreview; // 视频预览图
//    @BindView(R.id.tvVideoName)TextView tvVideoName; // 视频名称
    ImageView ivPreview;
    TextView tvVideoName;
    private String filePath; // 本地视频文件路径

    public LocalVidioView(Context context) {
        this(context, null);
    }

    public LocalVidioView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocalVidioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_local_video, this, true);
        ButterKnife.bind(this);
        tvVideoName= (TextView) findViewById(R.id.tvVideoName);
        ivPreview= (ImageView) findViewById(R.id.ivPreview);
    }

    public void bind(Cursor cursor) {
        filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        String videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
        tvVideoName.setText(videoName);
        // 清除old预览图
        ivPreview.setImageBitmap(null);
        ivPreview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoViewActivity.open(getContext(), filePath);
            }
        });
    }

//    @OnClick
//    public void onClick() {
//        // 全屏播放
//        VideoViewActivity.open(getContext(), filePath);
//
//    }

    @UiThread
    public void setPreview(@NonNull Bitmap bitmap) {
        ivPreview.setImageBitmap(bitmap);
    }

    public void setPreview(final String filePath, final Bitmap bitmap) {
        if (!filePath.equals(this.filePath)) return;
        post(new Runnable() {
            @Override
            public void run() {
                // 二次确认
                if (!filePath.equals(LocalVidioView.this.filePath)) return;
                ivPreview.setImageBitmap(bitmap);
            }
        });
    }

    public String getFilePath() {
        return filePath;
    }
}
