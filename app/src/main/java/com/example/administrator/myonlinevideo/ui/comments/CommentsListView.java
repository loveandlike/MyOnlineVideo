package com.example.administrator.myonlinevideo.ui.comments;

import android.content.Context;
import android.util.AttributeSet;

import com.example.administrator.myonlinevideo.bombapi.BombConst;
import com.example.administrator.myonlinevideo.bombapi.entity.CommentsEntity;
import com.example.administrator.myonlinevideo.bombapi.other.InQuery;
import com.example.administrator.myonlinevideo.bombapi.result.QueryResult;
import com.example.administrator.myonlinevideo.ui.base.BaseResourceView;

import retrofit2.Call;

/**
 * 新闻评论列表视图
 *
 * 作者：yuanchao on 2016/8/18 0018 16:07
 * 邮箱：yuanchao@feicuiedu.com
 */
public class CommentsListView extends BaseResourceView<CommentsEntity,CommmentsItemView> {
    public CommentsListView(Context context) {
        super(context);
    }

    public CommentsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String newsId;

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
    @Override protected Call<QueryResult<CommentsEntity>> queryData(int limit, int skip) {
        // 针对评论表的查询条件
        // 目的:查询出评论表中，所有针对当前这条新闻(news的objectid),的所有评论
        // 评论表的什么字段? - news字段(每条评论是针对哪条新闻的)
        // 由于有一对多的关系(Pointer), 指到什么表去查? News - ObjectId
        InQuery where = new InQuery(BombConst.FIELD_NEWS, BombConst.TABLE_NEWS,newsId);
        return newsApi.getComments(limit, skip, where);
    }

    @Override protected int getLimit() {
        return 20;
    }

    @Override protected CommmentsItemView createItemView() {
        return new CommmentsItemView(getContext());
    }
}
