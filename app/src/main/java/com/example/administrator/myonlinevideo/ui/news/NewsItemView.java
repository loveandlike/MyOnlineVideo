package com.example.administrator.myonlinevideo.ui.news;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.myonlinevideo.R;
import com.example.administrator.myonlinevideo.bombapi.entity.NewsEntity;
import com.example.administrator.myonlinevideo.common.CommonUtils;
import com.example.administrator.myonlinevideo.ui.base.BaseItemView;
import com.example.administrator.myonlinevideo.ui.comments.CommentsActivity;
import com.example.videoplayer.full.list.MediaPlayerManager;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新闻列表的单项视图, 将使用 MediaPlayer播放视频,TextureView来显示视频
 * <p/>
 * 作者：yuanchao on 2016/8/17 0017 14:51
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsItemView extends BaseItemView<NewsEntity>
        implements TextureView.SurfaceTextureListener, MediaPlayerManager.OnPlaybackListener {
    @BindView(R.id.textureView) TextureView textureView;
    @BindView(R.id.ivPreview) ImageView ivPreview;
    @BindView(R.id.tvNewsTitle) TextView tvNewsTitle;
    @BindView(R.id.tvCreatedAt) TextView tvCreatedAt;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.ivPlay) ImageView ivPlay;

    private NewsEntity newsEntity;
    private MediaPlayerManager mediaPlayerManager;
    private Surface surface;

    public NewsItemView(Context context) {
        super(context);
    }

    @Override protected void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_news, this, true);
        ButterKnife.bind(this);
        // MediaPlayerManager初始化
        mediaPlayerManager = MediaPlayerManager.getsInstance(getContext());
        mediaPlayerManager.addPlaybackListener(this);
        // TextureView初始化
        textureView.setSurfaceTextureListener(this);
    }

    @Override protected void bindModel(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        // 初始视图状态
        tvNewsTitle.setVisibility(View.VISIBLE);
        ivPreview.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        ivPlay.setVisibility(View.VISIBLE);
        // 设置标题,创建时间和预览图
        tvNewsTitle.setText(newsEntity.getNewsTitle());
        tvNewsTitle.setText(newsEntity.getNewsTitle());
        tvCreatedAt.setText(CommonUtils.format(newsEntity.getCreatedAt()));
        // 设置预览图像
        String url = CommonUtils.encodeUrl(newsEntity.getPreviewUrl());
        Picasso.with(getContext()).load(url).into(ivPreview);
    }

    @OnClick(R.id.tvCreatedAt)
    public void navigateToComments() {
        CommentsActivity.open(getContext(), newsEntity);
    }

    @OnClick(R.id.ivPreview)
    public void startPlay() {
        if (surface == null) {
            return;
        }
        String path = newsEntity.getVideoUrl();
        String videoId = newsEntity.getObjectId();
        mediaPlayerManager.startPlayer(surface, path, videoId);
    }

    @OnClick(R.id.textureView)
    public void stopPlay() {
        mediaPlayerManager.stopPlayer();
    }

    // --------------------------------------------------------
    @Override public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        this.surface = new Surface(surfaceTexture);
    }

    @Override public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {

    }

    @Override public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.surface.release();
        this.surface = null;
        // 谁离开，停止谁
        if (newsEntity.getObjectId().equals(mediaPlayerManager.getVideoId())) {
            mediaPlayerManager.stopPlayer();
        }
        return false;
    }

    @Override public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    // --------------------------------------------------------
    private boolean isCurrentVideo(String videoId) {
        if (videoId == null || newsEntity == null) return false;
        return newsEntity.getObjectId().equals(videoId);
    }

    @Override public void onStartPlay(String videoId) {
        if (isCurrentVideo(videoId)) {
            // 开始播放，显示TextureView
            ivPreview.setVisibility(View.INVISIBLE);
            tvNewsTitle.setVisibility(View.INVISIBLE);
            ivPlay.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override public void onSizeMeasured(String videoId, int width, int height) {
        if (isCurrentVideo(videoId)) {
            // 获取到视频尺寸，调整TextureView大小
        }
    }

    @Override public void onStartBuffering(String videoId) {
        if (isCurrentVideo(videoId)) {
            // 开始缓冲，显示进度条
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override public void onStopBuffering(String videoId) {
        if (isCurrentVideo(videoId)) {
            // 结束缓冲，隐藏进度条
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override public void onStopPlay(String videoId) {
        if (isCurrentVideo(videoId)) {
            // 停止播放，显示标题和预览图
            ivPreview.setVisibility(View.VISIBLE);
            tvNewsTitle.setVisibility(View.VISIBLE);
            ivPlay.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}