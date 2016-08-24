package com.example.administrator.myonlinevideo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myonlinevideo.MainActivity_1;
import com.example.administrator.myonlinevideo.R;
import com.example.administrator.myonlinevideo.user.SharedPrfHelper1;
import com.example.administrator.myonlinevideo.user.UserInfo;
import com.example.administrator.myonlinevideo.util.NewsDBUtil_login;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ZhuCe extends AppCompatActivity {
    private Button zhuce;
    private EditText name1, password1, pass_two;
    private CheckBox checkBox;
    Context context;

    String name = "", password = "", password_two = "", allowed = "";
    MyClick click = new MyClick();
    MyTextWatcher watcher = new MyTextWatcher();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        init();
    }

    private void init() {
        name1 = (EditText) findViewById(R.id.name);
        pass_two = (EditText) findViewById(R.id.pass_two);
        password1 = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.cb);
        zhuce = (Button) findViewById(R.id.qrzc);
        zhuce.setOnClickListener(click);
        name1.addTextChangedListener(watcher);
        password1.addTextChangedListener(watcher);
        pass_two.addTextChangedListener(watcher);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    allowed = "学生";
                } else {
                    allowed = "教师";
                }
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
            password = password1.getText().toString();
            password_two = pass_two.getText().toString();
            if (name.length() > 0 && password.length() > 0 && password.equals(password_two)) {
                checkBox.setEnabled(true);

            } else {
                checkBox.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //即使是点击了登录或者注册  也应该保存下当前的帐号密码
            switch (v.getId()) {
                case R.id.qrzc:
                    //存入数据库
                    NewsDBUtil_login login = NewsDBUtil_login.getsInstance(context);
                    login.insertNews(new UserInfo(name, password, allowed));
                    //保存进共享参数
                    SharedPrfHelper1 helper1 = SharedPrfHelper1.getInstance(getBaseContext());
                    helper1.saveInfo(new UserInfo(name, password, allowed));
                    Toast.makeText(ZhuCe.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ZhuCe.this, MainActivity_1.class);
                    intent.putExtra("name",name);
                    intent.putExtra("allowed",allowed);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}
