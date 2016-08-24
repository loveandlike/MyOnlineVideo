package com.example.administrator.myonlinevideo.bombapi.other;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * 作者：yuanchao on 2016/8/18 0018 16:58
 * 邮箱：yuanchao@feicuiedu.com
 */
public class RelationOperation {

    public enum Operation{
        AddRelation,
        RemoveRelation
    }

    @SerializedName("__op")
    private Operation operation;

    private List<Pointer> objects;

    public RelationOperation(Operation operation,Pointer... pointers){
        this.operation = operation;
        this.objects = Arrays.asList(pointers);
    }

//    "__op": "AddRelation",   // 代表此操作是添加一个Relation
//    "objects": [
//    {
//        "__type": "Pointer",
//            "className": "_User",  // 用户表名
//            "objectId": 用户Id
//    }
//    ]
}
