package com.example.administrator.myonlinevideo.bombapi;


import com.example.administrator.myonlinevideo.bombapi.entity.UserEntity;
import com.example.administrator.myonlinevideo.bombapi.result.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：yuanchao on 2016/8/18 0018 17:14
 * 邮箱：yuanchao@feicuiedu.com
 */
public interface UserApi {

    /**
     * 用户注册
     */
    @POST("1/users")
    Call<UserResult> register(@Body UserEntity userEntity);

    /**
     * 用户登录
     */
    @GET("1/login") Call<UserResult> login(
            @Query("username") String username,
            @Query("password") String password
    );
}
