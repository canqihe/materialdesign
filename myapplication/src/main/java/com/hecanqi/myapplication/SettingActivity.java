package com.hecanqi.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hecanqi.utils.HorizontalListView;
import com.hecanqi.utils.ShareUtils;
import com.hecanqi.utils.StringUtils;
import com.hecanqi.widget.TranslateEnglish;

import java.util.ArrayList;

import circleimageview.CircleImageView;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private TextView count, minutes;
    private FloatingActionButton fab;
    ArrayList<Integer> image;
    int[] resource = {R.mipmap.pa};
    private HorizontalListView listView;
    private ImageAdapter imgadapter;
    private Handler mHandler;
    private TranslateEnglish te;
    int countnum = 0;
    int fenzhong = 0;
    private long exitTime = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findById();
        initData();
    }

    private void findById() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (HorizontalListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        count = (TextView) findViewById(R.id.seconds);
        minutes = (TextView) findViewById(R.id.minutes);

        fab.setOnClickListener(this);
    }

    private void initData() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home图标

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("ListView Timer");
        getData();
        Timer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                countnum++;
                    image.add(StringUtils.getPhoto());
                    if (image.size() >= 5) {
                        image.remove(image.get(0));
                    }
                    imgadapter.notifyDataSetChanged();
                count.setText(countnum + "");
                break;
            default:
                break;
        }
    }

    private void getData() {
        image = new ArrayList<Integer>();
        if (image.size() < 5) {
            int k = 5 - resource.length;
            for (int i = 0; i < k; i++) {
                image.add(0);
            }
        }

        for (int i = 0; i < resource.length; i++) {
            image.add(resource[i]);
        }

        imgadapter = new ImageAdapter(this, image);
        listView.setAdapter(imgadapter);
    }

    private void Timer() {
        te = new TranslateEnglish(SettingActivity.this, 0, 0);
        mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                countnum++;
                count.setText(countnum + "");
                fenzhong = countnum / 60;
                if (fenzhong == 10) {
                    te.voiceSpeak("累计超过" + fenzhong + "分钟，再接再厉", "xiaoyan");
                } else if (fenzhong == 20) {
                    te.voiceSpeak("累计超过" + fenzhong + "分钟，你真行", "xiaoyan");
                } else if (fenzhong == 30) {
                    te.voiceSpeak("累计超过" + fenzhong + "分钟，你真牛逼", "xiaoyan");
                }

                minutes.setText(fenzhong + "分钟");

                image.add(StringUtils.getPhoto());
                if (image.size() >= 5) {
                    image.remove(image.get(0));
                }
                imgadapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(0, 1000);// 继续延时x秒发消息,形成循环
            }
        };
        mHandler.sendEmptyMessageDelayed(0, 1000);// 延时x秒后发消息
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                StringUtils.getSnackbar(count, "再按一次退出，退出后计时归零。");
                exitTime = System.currentTimeMillis();
            } else {
                mHandler.removeMessages(0);
                SettingActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showDialog();
                return true;
            case R.id.action_settings:
                ShareUtils.share(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class ImageAdapter extends BaseAdapter {
        private ArrayList<Integer> image;
        private LayoutInflater inflater;

        public ImageAdapter(Context context, ArrayList<Integer> image) {
            this.image = image;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return image.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.test_list_item, null);
            CircleImageView imageView = (CircleImageView) view.findViewById(R.id.testphoto);
            imageView.setImageResource(image.get(position));
            return view;
        }
    }


    protected void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setMessage("退出后计时归零，确定要退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mHandler.removeMessages(0);
                dialog.dismiss();
                SettingActivity.this.finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
