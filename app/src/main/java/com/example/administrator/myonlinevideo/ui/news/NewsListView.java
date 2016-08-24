package com.example.administrator.myonlinevideo.ui.news;

import android.content.Context;
import android.util.AttributeSet;

import com.example.administrator.myonlinevideo.bombapi.entity.NewsEntity;
import com.example.administrator.myonlinevideo.bombapi.result.QueryResult;
import com.example.administrator.myonlinevideo.ui.base.BaseResourceView;

import retrofit2.Call;

/**
 * 视频新闻列表视图，使用 {@link BaseResourceView}来完成
 *
 * 作者：yuanchao on 2016/8/17 0017 14:50
 * 邮箱：yuanchao@feicuiedu.com
 */
public class NewsListView extends BaseResourceView<NewsEntity,NewsItemView> {
    public NewsListView(Context context) {
        super(context);
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected Call<QueryResult<NewsEntity>> queryData(int limit, int skip) {
        return newsApi.getVideoNewsList(limit,skip);
    }

    @Override protected int getLimit() {
        return 5;
    }

    @Override protected NewsItemView createItemView() {
        return new NewsItemView(getContext());
    }
}
