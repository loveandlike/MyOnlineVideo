package com.example.administrator.myonlinevideo.user;

/**
 * Created by Administrator on 2016/8/17.
 */
public class Student {
    private String name;
    private int age;
    private String xing;

    public Student() {
    }

    public Student( String name,int age, String xing) {
        this.age = age;
        this.name = name;
        this.xing = xing;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getXing() {
        return xing;
    }

    public void setXing(String xing) {
        this.xing = xing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
