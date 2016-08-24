package com.example.administrator.myonlinevideo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myonlinevideo.fragment.ZhuCe;
import com.example.administrator.myonlinevideo.user.SharedPrfHelper1;
import com.example.administrator.myonlinevideo.user.UserInfo;
import com.example.administrator.myonlinevideo.util.NewsDBUtil_login;

public class MainActivity_0 extends AppCompatActivity {
    private Button zhuce, denglu;
    private EditText name1, password1;
    CheckBox checkbox;
    Context context;
    MyClick click = new MyClick();
    MyTextWatcher watcher = new MyTextWatcher();
    String name = "";
    String allowed = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_0);
        zhuce = (Button) findViewById(R.id.zhuce);
        denglu = (Button) findViewById(R.id.dl);
        name1 = (EditText) findViewById(R.id.name);
        password1 = (EditText) findViewById(R.id.password);
        checkbox = (CheckBox) findViewById(R.id.che);
        //设置二次登陆11

//NewsDBUtil_login login = NewsDBUtil_login.getsInstance(getBaseContext());
        SharedPrfHelper1 helper1 = SharedPrfHelper1.getInstance(this);
        UserInfo userInfo = helper1.getInfo();
        name1.setText(userInfo.getName());
        password1.setText(userInfo.getPassword() + "");

        name1.addTextChangedListener(watcher);
        password1.addTextChangedListener(watcher);
        zhuce.setOnClickListener(click);
        denglu.setOnClickListener(click);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    allowed = "教师";
                    SharedPrfHelper1 helper = SharedPrfHelper1.getInstance(context);
                    helper.saveInfo(new UserInfo(name, password, allowed));
                } else {
                    allowed = "学生";
                }
            }
        });
    }

    class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //即使是点击了登录或者注册  也应该保存下当前的帐号密码
            NewsDBUtil_login login = NewsDBUtil_login.getsInstance(getBaseContext());
            login.insertNews(new UserInfo(name, password, allowed));
            switch (v.getId()) {
                case R.id.dl:
                    SharedPrfHelper1 helper = SharedPrfHelper1.getInstance(context);
                    helper.saveInfo(new UserInfo(name, password, allowed));

                    Intent intent = new Intent(MainActivity_0.this, MainActivity_1.class);
                    intent.putExtra("name", name);
                    intent.putExtra("allowed", allowed);
                    if (name.length() > 0 && password.length() > 0) {
                        Toast.makeText(MainActivity_0.this, "登录成功", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    finish();
                    break;
                case R.id.zhuce:
                    Intent intent1 = new Intent(MainActivity_0.this, ZhuCe.class);
                    startActivity(intent1);
                    finish();
                    break;
            }
        }
    }

    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            name = name1.getText().toString();
            password = password1.getText().toString();
            if (name.length() > 0 && password.length() > 0) {
                checkbox.setEnabled(true);
                denglu.setEnabled(true);
            } else {
                checkbox.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (name.length() > 0 && password.length() > 0) {
                checkbox.setEnabled(true);
                denglu.setEnabled(true);
                denglu.setOnClickListener(click);
            }
        }
    }

    //无论点下返回键 还是主页键 都是会调用onpause   则在onpause中保存一次数据
    @Override
    protected void onPause() {
        super.onPause();
//        login.insertNews(new UserInfo(name, password, allowed));
    }
}
