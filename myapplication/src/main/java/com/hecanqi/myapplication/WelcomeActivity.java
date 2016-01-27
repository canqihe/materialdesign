package com.hecanqi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏隐藏状态栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        };
        timer.schedule(tk, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
