package com.hecanqi.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hecanqi.fragment.FragmentFactory;
import com.hecanqi.utils.StringUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private FrameLayout mFrameLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private TextView headDate, headMotto;
    private RelativeLayout relativeLayoutHead;
    private CircleImageView headPhoto;
    public static int theme = -1;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=559e2f05");//科大讯飞MSC初始化
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initData();
    }

    private void findViewById() {
        mFrameLayout = (FrameLayout) findViewById(R.id.container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigation = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        headDate = (TextView) findViewById(R.id.head_date);
        headMotto = (TextView) findViewById(R.id.head_motto);
        relativeLayoutHead = (RelativeLayout) findViewById(R.id.relativeLayout_head);
        headPhoto = (CircleImageView) findViewById(R.id.head_photo);

        mDrawerLayout.setDrawerListener(MainActivity.this);
        mNavigation.setNavigationItemSelectedListener(this);
    }

    private void initData() {

        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        actionBar = getSupportActionBar();
        //actionBar.setTitle("新闻快读");//设置actionbar的标题
        //设置当前的控件可用
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        headDate.setText(StringUtils.getDate());//header获取当前时间
        headMotto.setText(StringUtils.getMotto());//header获取随机格言
        relativeLayoutHead.setBackgroundResource(StringUtils.getHeader());//header获取随机图片
        headPhoto.setImageResource(StringUtils.getPhoto());//heade获取随机头像

        //初始化开关，并和drawer关联
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToggle.syncState();//该方法会自动和actionBar关联

        onFragmenItemSelected(1);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                onFragmenItemSelected(0);
                return true;
            case R.id.nav_translate:
                onFragmenItemSelected(1);
                return true;
            case R.id.nav_weather:
                onFragmenItemSelected(2);
                return true;
            case R.id.nav_history:
                onFragmenItemSelected(3);
                return true;
            case R.id.nav_girl:
                StringUtils.useIntent(this, AboutActivity.class);
                return true;
            case R.id.nav_about:
                StringUtils.useIntent(this, AboutActivity.class);
                return true;
            case R.id.nav_setting:
                StringUtils.useIntent(this, SettingActivity.class);
                return true;
            default:
                return true;

        }
    }


    public void onFragmenItemSelected(int position) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = FragmentFactory.createFragment(position);
        actionBar.setTitle(StringUtils.itemName[position]);//设置标题
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }
        return mToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }


    /**
     * Drawer的回调方法，需要在该方法中对Toggle做对应的操作
     */
    @Override
    public void onDrawerOpened(View drawerView) {// 打开drawer
        mToggle.onDrawerOpened(drawerView);//需要把开关也变为打开
        invalidateOptionsMenu();
    }

    @Override
    public void onDrawerClosed(View drawerView) {// 关闭drawer
        mToggle.onDrawerClosed(drawerView);//需要把开关也变为关闭
        invalidateOptionsMenu();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {// drawer滑动的回调
        mToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerStateChanged(int newState) {// drawer状态改变的回调
        mToggle.onDrawerStateChanged(newState);
    }

    //执行onCreate方法
    protected void reload() {
        Intent intent = MainActivity.this.getIntent();
        MainActivity.this.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        MainActivity.this.finish();
        FragmentFactory.clear();
        MainActivity.this.overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                StringUtils.getSnackbar(headDate, "再按一次退出应用。");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
