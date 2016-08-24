package com.example.administrator.myonlinevideo.bombapi.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/16.
 */
public abstract class BaseEntity {
    // 唯一Id，由Bomb自动生成
    private String objectId;

    // 创建时间，由Bomb自动生成
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getObjectId() {
        return objectId;
    }

    // 修改时间，由Bomb自动生成
    private Date updatedAt;
}
