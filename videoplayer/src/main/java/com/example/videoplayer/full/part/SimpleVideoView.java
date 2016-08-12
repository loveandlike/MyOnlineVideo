package com.example.videoplayer.full.part;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.videoplayer.R;
import com.example.videoplayer.full.VideoViewActivity;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

/**
 * 一个自定义的VideoView,使用MediaPlayer+SurfaceView来实现视频的播放
 * <p>
 * MediaPlayer来做视频播放的控制，SurfaceView来显示视频
 * <p>
 * 视图方面将简单实现:放一个播放/暂停按钮，一个进度条,一个全屏按钮,和一个SurfaceView
 * <p>
 * 本API实现结构：
 * <ul>
 * <li/>提供setVideoPath方法(一定要在onResume方法调用前来调用): 设置播放谁
 * <li/>提供onResume方法(在activity的onResume来调用): 初始化MediaPlayer,准备MediaPlayer
 * <li/>提供onPause方法 (在activity的onPause来调用): 释放MediaPlayer,暂停mediaPlayer
 * </ul>
 * <p>
 * 作者：yuanchao on 2016/8/10 0010 16:52
 * 邮箱：yuanchao@feicuiedu.com
 */
public class SimpleVideoView extends FrameLayout {
    public SimpleVideoView(Context context) {
        this(context, null);
    }

    public SimpleVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private String videoPath;

    private ImageView ivPreview;
    private ImageButton btnToggle;
    private ProgressBar progressBar;

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private MediaPlayer mediaPlayer;


    private boolean isPrepared; // 当前视频是否已准备好
    private boolean isPlaying; // 当前视频是否正在播放
    private static final int PROGRESS_MAX = 1000;

    private void init() {
        Vitamio.isInitialized(getContext());
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_video_player, this, true);
        // surfaceview的初始化
        initSurfaceView();
        // 控制视图的初始化
        initControllerViews();
    }

    /**
     * 设置播放谁(一定要在onResume方法调用前来调用):
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * 初始化MediaPlayer,准备MediaPlayer(和activity的onResume同步执行):
     */
    public void onResume() {
        // 初始化mediaplayer及监听处理
        initMediaPlayer();
        // 设置资源进行准备
        prepareMediaPlayer();
    }

    /**
     * 暂停mediaPlayer,释放MediaPlayer(和activity的onPasuse同步执行):
     */
    public void onPasuse() {
        pauseMediaPlayer();
        releaseMediaPlayer();
    }

    // 初始化SurfaceView
    private void initSurfaceView() {
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        // 注意：Vitamio在使用SurfaceView播放时,要fotmat
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
    }

    //用来更新播放进度的
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isPlaying) {
                long current = mediaPlayer.getCurrentPosition();
                long duration = mediaPlayer.getDuration();
                int progress = (int) (current * PROGRESS_MAX / duration);
                progressBar.setProgress(progress);
                handler.sendEmptyMessageDelayed(0, 200);
            }
        }
    };

    // 初始化自定义的简单的视频播放控制视图
    private void initControllerViews() {
        // 预览图片
        ivPreview = (ImageView) findViewById(R.id.ivPreview);
        // 播放/暂停 按钮
        btnToggle = (ImageButton) findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    pauseMediaPlayer();
                } else if (isPrepared) {
                    startMediaPlayer();
                } else {
                    Toast.makeText(getContext(), "Can't play now !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 进度条
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(PROGRESS_MAX);
        // 全屏按钮
        ImageButton btnFullScreen = (ImageButton) findViewById(R.id.btnFullScreen);
        btnFullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoViewActivity.open(getContext(), videoPath);

            }
        });
    }

    // 初始化MediaPlayer, 设置一系列的监听
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer(getContext());
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPrepared = true;
                // 准备好后，自动开始播放
                startMediaPlayer();
            }
        });
        //自适应屏幕宽高
        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                //调整视屏的宽度去适配高度
                int videoWidth = surfaceView.getWidth();
                int videoHeight = videoWidth * height / width;
                //重置surfanceview的宽高
                ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) surfaceView.getLayoutParams();
                layoutParams.width = videoWidth;
                layoutParams.height = videoHeight;
                surfaceView.setLayoutParams(layoutParams);

            }
        });
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_FILE_OPEN_OK) {
                    // 注意：Vitamio5.0 要对音频进行设置才能播放
                    // 否则，不能播放在线视频
                    long bufferSize = mediaPlayer.audioTrackInit();
                    mediaPlayer.audioInitedOk(bufferSize);
                    return true;
                }
                return false;
            }
        });
    }

    // 准备MediaPlayer, 同时更新UI状态
    private void prepareMediaPlayer() {
        try {
            mediaPlayer.reset();
            // 设置资源
            mediaPlayer.setDataSource(videoPath);
            //设置循环模式
            mediaPlayer.setLooping(true);
            // 异步准备
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 开始MediaPlayer, 同时更新UI状态
    private void startMediaPlayer() {
        //是否已经准备好，放置好没准备好。用户就按下播放按钮
        if (isPrepared) {
            mediaPlayer.start();
        }
        isPlaying = true;
//更新UI（progressbar）
        handler.sendEmptyMessage(0);
        // 播放和暂停按钮图像的更新
        btnToggle.setImageResource(R.drawable.ic_pause);
    }

    // 暂停mediaPlayer, 同时更新UI状态
    private void pauseMediaPlayer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        isPlaying = false;
        handler.removeMessages(0);
        // 播放和暂停按钮图像的更新
        btnToggle.setImageResource(R.drawable.ic_play_arrow);
    }

    // 释放mediaPlayer, 同时更新UI状态
    private void releaseMediaPlayer() {
        mediaPlayer.release();
        mediaPlayer = null;
        isPlaying = false;
        isPrepared = false;
    }
}