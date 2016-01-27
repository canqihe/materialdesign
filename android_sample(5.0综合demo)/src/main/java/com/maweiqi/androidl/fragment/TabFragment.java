package com.maweiqi.androidl.fragment;


import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.maweiqi.androidl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_tab, null);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        initData();

//      tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE); //设置标签所占空间为内容大小
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    public void initData() {

        MenuDetailAdapter m = new MenuDetailAdapter();
        m.addTitle(new TabDetialPager(getActivity(),"北京"), "北京");
        m.addTitle(new TabDetialPager(getActivity(),"上海"), "上海");
        m.addTitle(new TabDetialPager(getActivity(),"广州"), "广州");
        m.addTitle(new TabDetialPager(getActivity(),"深圳"), "深圳");
        m.addTitle(new TabDetialPager(getActivity(),"纽约"), "纽约");
        m.addTitle(new TabDetialPager(getActivity(),"洛杉矶"), "洛杉矶");
        m.addTitle(new TabDetialPager(getActivity(),"芝加哥"), "芝加哥");
        m.addTitle(new TabDetialPager(getActivity(),"休斯顿"), "休斯顿");
        m.addTitle(new TabDetialPager(getActivity(),"费城"), "费城");
        m.addTitle(new TabDetialPager(getActivity(),"贺灿琪"), "贺灿琪");
        viewPager.setAdapter(m);
    }


    class MenuDetailAdapter extends PagerAdapter {

        public MenuDetailAdapter() {
            super();
        }

        private final List<TabDetialPager> mPageList = new ArrayList<>();
        private final List<String> titles = new ArrayList<>();

        public void addTitle(TabDetialPager tabDetialPager, String title) {
            mPageList.add(tabDetialPager);
            titles.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public int getCount() {
            return mPageList.size();
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            TabDetialPager pager = mPageList.get(position);
            container.addView(pager.mRootView);
            pager.initData();
            return pager.mRootView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    public String getUrl() {
        return "file:///android_asset/drawable.html";
    }

}
