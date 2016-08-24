package com.example.administrator.myonlinevideo.bombapi.other;

import com.example.administrator.myonlinevideo.bombapi.BombConst;

/**
 * Created by Administrator on 2016/8/19.
 */
public class UserPointer extends Pointer {
    public UserPointer(String objectId) {
        super(BombConst.TABLE_USER, objectId);
    }
    //
//    "createdAt": "2016-07-11 12:20:07",
//    "updatedAt": "2016-07-11 12:20:09",
//    "username": "飞翔的猪头"

    private String username;

    public String getUsername() {
        return username;
    }
}
