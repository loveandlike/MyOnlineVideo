package com.example.administrator.myonlinevideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.administrator.myonlinevideo.R;
import com.example.administrator.myonlinevideo.user.Student;
import com.example.administrator.myonlinevideo.util.NewsDBUtil;

/**
 * Created by Administrator on 2016/8/22.
 */
public class MoreActivity extends AppCompatActivity {
    private Button zhuce;
    private EditText name1, password1, pass_two;
    private CheckBox checkBox;
    private String name = "";
    private String age = "";
    private String xing = "";
    private int b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        name1 = (EditText) findViewById(R.id.name);
        pass_two = (EditText) findViewById(R.id.pass_two);
        password1 = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.cb);
        zhuce = (Button) findViewById(R.id.qrzc);
        checkBox.setVisibility(View.INVISIBLE);
        zhuce.setText("确认添加");
        name1.setHint("姓名");
        password1.setHint("年龄");
        pass_two.setHint("性别");
        zhuce.setEnabled(false);

        MyTextWatcher watcher = new MyTextWatcher();
        name1.addTextChangedListener(watcher);
        password1.addTextChangedListener(watcher);
        pass_two.addTextChangedListener(watcher);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDBUtil dbUtil = NewsDBUtil.getsInstance(getBaseContext());
                dbUtil.insertNews(new Student(name, b, xing));
            }
        });
    }

    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            name = name1.getText().toString();
            xing = pass_two.getText().toString();

            age = password1.getText().toString();
            if (age != null && age.length() > 0) {
                b = Integer.parseInt(age);
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {
            if (name.length() > 0 && xing.length() > 0) {
                zhuce.setEnabled(true);
            }
        }
    }
}
