package com.hecanqi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;


public class GuideActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView btnStart;
    private ViewPager mViewPager;
    private CirclePageIndicator mIndicator;//滚动圆点指示器
    private static final int[] mImageIds = new int[]{R.mipmap.zdone,
            R.mipmap.zdtwo, R.mipmap.zdthree, R.mipmap.zdfour};

    private ArrayList<ImageView> mImageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        btnStart = (TextView) findViewById(R.id.btnStart);
        mImageViewList = new ArrayList<ImageView>();

        // 初始化引导页的3个页面
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);// 设置引导页背景
            mImageViewList.add(image);
        }
        btnStart.setOnClickListener(this);
        mViewPager.setAdapter(new TopPager());
        //圆点指示器
        mIndicator.setViewPager(mViewPager);//与ViewPager绑定
        mIndicator.setSnap(true);// 支持快照显示
        mIndicator.onPageSelected(0);// 让指示器重新定位到第一个点
        mIndicator.setOnPageChangeListener(new GuidePageListener());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }


    class TopPager extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    class GuidePageListener implements ViewPager.OnPageChangeListener {

        // 滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
            if (position == mImageIds.length - 1) {// 最后一个页面
                btnStart.setVisibility(View.VISIBLE);// 显示开始体验的按钮
            } else {
                btnStart.setVisibility(View.INVISIBLE);
            }
        }

        // 滑动状态发生变化
        public void onPageScrollStateChanged(int state) {

        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
