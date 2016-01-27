package com.example.mwqi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kogitune.activity_transition.ActivityTransition;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.t01dc7539400e75af98).into(imageView);
        imageView.setBackgroundResource(R.mipmap.aaa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String value = intent.getStringExtra("value");

        CollapsingToolbarLayout collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsing_toolbar.setTitle(value);


        ActivityTransition.with(getIntent()).to(findViewById(R.id.main_content)).start(savedInstanceState);
        ActivityTransition.with(getIntent()).to(findViewById(R.id.backdrop)).start(savedInstanceState);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
