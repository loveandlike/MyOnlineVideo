package com.example.administrator.myonlinevideo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "user_db";
    public static final String TABLE_NAME = "table_news";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table if not exists " + TABLE_NAME + " ( name varchar(255) , age int , xing varchar(255) ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
