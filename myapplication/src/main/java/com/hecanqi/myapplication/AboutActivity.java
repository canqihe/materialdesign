package com.hecanqi.myapplication;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.hecanqi.utils.ShareUtils;
import com.hecanqi.utils.StringUtils;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private RelativeLayout relativeLayoutPer;
    private FloatingActionButton fab;
    private CollapsingToolbarLayout collapsing_toolbar;
    private CoordinatorLayout coordlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        findById();
        initData();

    }


    private void findById() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        relativeLayoutPer = (RelativeLayout) findViewById(R.id.relativeLayout_per);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        coordlayout = (CoordinatorLayout) findViewById(R.id.main_content);

        fab.setOnClickListener(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home图标
    }

    private void initData() {
        relativeLayoutPer.setBackgroundResource(StringUtils.getPerPic());
        collapsing_toolbar.setTitle("关于");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                StringUtils.getSnackbar(coordlayout.getRootView(), "一款乱七八糟的应用！");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_share:
                ShareUtils.share(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
