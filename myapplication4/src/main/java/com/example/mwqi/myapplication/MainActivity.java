package com.example.mwqi.myapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String[] names = {"黑马","传智","CSDN"};

    private TabLayout tabLayout;
    private Toolbar mToolbar;
    private ViewPager viewPager;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("黑马"));
        tabLayout.addTab(tabLayout.newTab().setText("传智"));
        tabLayout.addTab(tabLayout.newTab().setText("CSDN"));




        //第一个参数默认颜色
        //第二个参赛选中颜色
//        setTabTextColors(int normalColor, int selectedColor)
        tabLayout.setTabTextColors(R.color.window_background5,R.color.window_background5);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);


        ActionBar actionBar = getSupportActionBar();
        //设置actionbar的标题
        actionBar.setTitle("黑马程序员");
        //设置当前的控件可用
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        //设置actionbar的图片
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        Adapter adapter = new Adapter(getSupportFragmentManager());


        viewPager.setAdapter(adapter);
        setupViewPager(viewPager);
        //viewpager绑定tablayout
        tabLayout.setupWithViewPager(viewPager);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        //对NavigationView添加item的监听事件
        mNavigationView.setNavigationItemSelectedListener(this);



    }



    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "黑马");
        adapter.addFragment(new AppFragmenet(), "传智");
        adapter.addFragment(new HomeFragment(), "CSDN");
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId){
            case R.id.menu_info_details:
                Toast.makeText(this, "黑马程序员", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this, "传智播客", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_agenda:
                Toast.makeText(this, "CSDN", Toast.LENGTH_SHORT).show();
                break;
        }

        return false;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.menu_info_details:
                Log.d("MainActivity", "黑马程序员");
                Toast.makeText(this, "黑马程序员", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this, "CSDN", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "CSDN");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
