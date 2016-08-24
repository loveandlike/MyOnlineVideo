package com.example.administrator.myonlinevideo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myonlinevideo.user.UserInfo;

import java.util.ArrayList;


public class NewsDBUtil_login {
    private DBHelper_login dbHelper_login;
    private static NewsDBUtil_login sInstance;
    private SQLiteDatabase database_login;

    private NewsDBUtil_login(Context context) {
        dbHelper_login = new DBHelper_login(context);
        database_login = dbHelper_login.getWritableDatabase();
    }

    public static NewsDBUtil_login getsInstance(Context context) {

        if (sInstance == null) {
            sInstance = new NewsDBUtil_login(context);
        }
        return sInstance;
    }

    //增删改查
    public void insertNews(UserInfo userInfo) {//增加

        ContentValues values = new ContentValues();
        values.put("name", userInfo.getName());
        values.put("password", userInfo.getPassword());
        values.put("allowed", userInfo.getAllowed());
        database_login.insert(DBHelper_login.TABLE_NAME_login, null, values);
    }
    public ArrayList<UserInfo> getNewsList() {
        ArrayList<UserInfo> list = new ArrayList<>();
        Cursor cursor = database_login.query(DBHelper_login.TABLE_NAME_login, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String allowed = cursor.getString(cursor.getColumnIndex("allowed"));
            list.add(new UserInfo(name,password,allowed));
        }
        return list;
    }

    public ArrayList<UserInfo> removeData() {

        return null;
    }
    public void updateData() {

    }
    //新加的

}
