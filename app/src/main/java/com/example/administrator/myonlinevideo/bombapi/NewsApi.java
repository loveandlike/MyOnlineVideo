package com.example.administrator.myonlinevideo.bombapi;


import com.example.administrator.myonlinevideo.bombapi.entity.CommentsEntity;
import com.example.administrator.myonlinevideo.bombapi.entity.NewsEntity;
import com.example.administrator.myonlinevideo.bombapi.other.InQuery;
import com.example.administrator.myonlinevideo.bombapi.other.LikesOperation;
import com.example.administrator.myonlinevideo.bombapi.result.CreateResult;
import com.example.administrator.myonlinevideo.bombapi.result.QueryResult;
import com.example.administrator.myonlinevideo.bombapi.result.UpdateResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 视频新闻相关操作的Restful接口，包括获取新闻，收藏新闻，发表评论等
 * 作者：yuanchao on 2016/8/16 0016 11:36
 * 邮箱：yuanchao@feicuiedu.com
 */
public interface NewsApi {

    /** 获取所有视频新闻列表, 接时间新到旧排序 */
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getVideoNewsList(@Query("limit") int limit, @Query("skip") int skip);

    /** 获取评论*/
    @GET("1/classes/Comments?include=author&order=-createdAt")
    Call<QueryResult<CommentsEntity>> getComments(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("where") InQuery where);

    /** 发表评论*/
    @POST("1/classes/Comments")
    Call<CreateResult> postComments(@Body CommentsEntity commentsEntity);

    /** 获取收藏列表*/
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getLikedList(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("where") InQuery where
    );

    /** 收藏、取消新闻*/
    @PUT("1/classes/News/{objectId}")
    Call<UpdateResult> changLikes(
            @Path("objectId") String newsId,
            @Body LikesOperation operation);
}
