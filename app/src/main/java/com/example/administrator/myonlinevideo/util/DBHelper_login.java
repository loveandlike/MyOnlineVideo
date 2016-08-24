package com.example.administrator.myonlinevideo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DBHelper_login extends SQLiteOpenHelper {
    public static final String DB_NAME_login = "user_db_login";
    public static final String TABLE_NAME_login = "table_news_login";

    public DBHelper_login(Context context) {
        super(context, DB_NAME_login, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table if not exists " + TABLE_NAME_login + " ( name varchar(255) ,password varchar(255) , allowed varchar(255) ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
