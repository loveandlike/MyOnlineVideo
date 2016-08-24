package com.example.administrator.myonlinevideo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myonlinevideo.user.Student;

import java.util.ArrayList;


public class NewsDBUtil {
    private DBHelper dbHelper;
    private static NewsDBUtil sInstance;
    private SQLiteDatabase database;

    private NewsDBUtil(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static NewsDBUtil getsInstance(Context context) {

        if (sInstance == null) {
            sInstance = new NewsDBUtil(context);
        }
        return sInstance;
    }

    //增删改查
    public void insertNews(Student student) {//增加

        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("xing", student.getXing());

        database.insert(DBHelper.TABLE_NAME, null, values);
    }
    public ArrayList<Student> getNewsList() {
        ArrayList<Student> list = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String xing = cursor.getString(cursor.getColumnIndex("xing"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            list.add(new Student(name,age,xing));
        }
        return list;
    }

    public ArrayList<Student> removeData() {

        return null;
    }
    public void updateData() {

    }
    //新加的

}
