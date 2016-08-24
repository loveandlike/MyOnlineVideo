package com.example.administrator.myonlinevideo.bombapi.other;

import com.google.gson.annotations.SerializedName;

/**通用POINTER
 * Created by Administrator on 2016/8/19.
 */
public class Pointer {
    //    "__type": "Object",
//    "className": "_User",
//    "objectId": "D5vlAAAJ",

    @SerializedName("__type")
    private String type = "Pointer";

    private String className;

    private String objectId;

    public Pointer(String className, String objectId) {
        this.className = className;
        this.objectId = objectId;
    }
}
