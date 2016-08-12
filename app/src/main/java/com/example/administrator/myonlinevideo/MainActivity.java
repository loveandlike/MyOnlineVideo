package com.example.administrator.myonlinevideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.videoplayer.full.VideoViewActivity;

public class MainActivity extends AppCompatActivity {
  private Button button,btnLikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button= (Button) findViewById(R.id.btnLocal);
        btnLikes= (Button) findViewById(R.id.btnLikes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 开启VideoViewActivity进行视频播放
                VideoViewActivity.open(MainActivity.this,getTestVideo1());
            }
        });
        btnLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 进入部分播放Activity
                Intent intent = new Intent(MainActivity.this,PartPlayActivity.class);
                intent.putExtra("path",getTestVideo1());
                startActivity(intent);
            }
        });
    }
    private String getTestVideo1(){
        return "http://o9ve1mre2.bkt.clouddn.com/raw_%E6%B8%A9%E7%BD%91%E7%94%B7%E5%8D%95%E5%86%B3%E8%B5%9B.mp4";
    }
}
