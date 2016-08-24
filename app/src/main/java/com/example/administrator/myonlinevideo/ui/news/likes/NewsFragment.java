package com.example.administrator.myonlinevideo.ui.news.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myonlinevideo.R;
import com.example.administrator.myonlinevideo.ui.news.NewsListView;
import com.example.videoplayer.full.list.MediaPlayerManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;

/**
 * 视频新闻列表页面
 * <p/>
 * 作者：yuanchao on 2016/8/16 0016 16:46
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsFragment extends Fragment {

    @BindView(R.id.newsListView) NewsListView newsListView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        Vitamio.isInitialized(getContext());




        // 首次进入，自动刷新
        newsListView.post(new Runnable() {
            @Override
            public void run() {
                newsListView.autoRefresh();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // 当Fragment,onResume，MediaPlayer进行初始化
        MediaPlayerManager.getsInstance(getContext()).onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 当Fragment,onPause，MediaPlayer进行release
        MediaPlayerManager.getsInstance(getContext()).onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) view.getParent()).removeView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 当Fragment,onDestroy，清除所有监听
        MediaPlayerManager.getsInstance(getContext()).removeAllListeners();
    }
}
