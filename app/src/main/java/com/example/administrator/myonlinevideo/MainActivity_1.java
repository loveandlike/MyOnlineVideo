package com.example.administrator.myonlinevideo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myonlinevideo.fragment.Fragment_look;
import com.example.administrator.myonlinevideo.fragment.MoreActivity;
import com.example.administrator.myonlinevideo.ui.local.LocalVideoFragment;
import com.example.administrator.myonlinevideo.ui.news.likes.LikesFragment;
import com.example.administrator.myonlinevideo.ui.news.likes.NewsFragment;
import com.example.administrator.myonlinevideo.user.Student;
import com.example.administrator.myonlinevideo.util.NewsDBUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/15.
 */
public class MainActivity_1 extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btnLikes)
    Button btnLikes;
    @BindView(R.id.btnLocal)
    Button btnLocal;
    @BindView(R.id.btnNews)
    Button btnNews;
    @BindView(R.id.btnStudent)
    Button btnStudent;
    @BindView(R.id.nav_main)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drwerLayout)
    DrawerLayout drawerLayout;

    private Context context;
//    @BindView(R.id.home_name) TextView home_name;
//    @BindView(R.id.home_quan) TextView home_quan;
    private final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewsFragment();
                case 1:
                    return new LocalVideoFragment();
                case 2:
                    return new LikesFragment();
                case 3:
                    return new Fragment_look();
                default:
                    throw new RuntimeException("不存在的数据");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为了让ToolBar各项设置都生效的话，这个方法必须放到最后
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();//将开关同步设置
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.zj:
                        NewsDBUtil dbUtil = NewsDBUtil.getsInstance(getBaseContext());
                        dbUtil.insertNews(new Student("张三", 12, "男"));
                        dbUtil.insertNews(new Student("李四", 15, "男"));
                        dbUtil.insertNews(new Student("王麻子", 22, "男"));
                        Intent intent = new Intent(MainActivity_1.this, MoreActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.sc:

                        break;
                    case R.id.gb:

                        break;
                    case R.id.ck:

                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    //此方法定义Menu的布局样式，返回false则不显示Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //注销账户
        if (id == R.id.action_pull) {
            Intent intent = new Intent(MainActivity_1.this, MainActivity_0.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_out) {
            //退出整个应用
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        viewPager.setAdapter(adapter);
        // 对ViewPager进行监听(为了在Pager切换时，下方Button的切换)
        viewPager.addOnPageChangeListener(this);
        // 首次进入默认选中在线新闻
        btnNews.setSelected(true);
    }

    @OnClick({R.id.btnLocal, R.id.btnNews, R.id.btnLikes, R.id.btnStudent})
    public void chooseFragment(View view) {
        switch (view.getId()) {
            case R.id.btnNews:
                viewPager.setCurrentItem(0, false);
                return;
            case R.id.btnLocal:
                viewPager.setCurrentItem(1, false);
                return;
            case R.id.btnLikes:
                viewPager.setCurrentItem(2, false);
                return;
            case R.id.btnStudent:
                viewPager.setCurrentItem(3, false);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // ViewPager页面变化时设置下方按钮的选中状态
        btnNews.setSelected(position == 0);
        btnLocal.setSelected(position == 1);
        btnLikes.setSelected(position == 2);
        btnStudent.setSelected(position == 3);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
