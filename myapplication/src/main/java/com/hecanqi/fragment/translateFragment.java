package com.hecanqi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hecanqi.myapplication.R;
import com.hecanqi.widget.BaseMenuDetailPager;
import com.hecanqi.widget.TranslateDialect;
import com.hecanqi.widget.TranslateEnglish;

import java.util.ArrayList;
import java.util.List;

public class translateFragment extends Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_translate, null);
        findById();
        initData();
        return view;
    }

    private void findById() {
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    //填充数据
    public void initData() {
        MenuDetailAdapter m = new MenuDetailAdapter();
        m.addTitle(new TranslateEnglish(getActivity(), 0, 0), "中英文");
        m.addTitle(new TranslateDialect(getActivity(), R.drawable.edit_text_gd, R.drawable.speak_gd,"vixm"), "广东话");
        m.addTitle(new TranslateDialect(getActivity(), R.drawable.edit_text_sc, R.drawable.speak_sc,"vixr"), "四川话");
        m.addTitle(new TranslateDialect(getActivity(), R.drawable.edit_text_hna, R.drawable.speak_hna,"vixqa"), "湖南话");
        m.addTitle(new TranslateDialect(getActivity(), R.drawable.edit_text_sx, R.drawable.speak_sx,"vixying"), "陕西话");
        m.addTitle(new TranslateDialect(getActivity(), R.drawable.edit_text_hn, R.drawable.speak_hn,"vixk"), "河南话");
        m.addTitle(new TranslateDialect(getActivity(), R.drawable.edit_text_pt, R.drawable.speak_pt,"vinn"), "普通话");
        viewPager.setAdapter(m);
        tabLayout.setupWithViewPager(viewPager);
    }


    class MenuDetailAdapter extends PagerAdapter {

        public MenuDetailAdapter() {
            super();
        }

        private final List<BaseMenuDetailPager> mPageList = new ArrayList<>();
        private final List<String> titles = new ArrayList<>();

        public void addTitle(BaseMenuDetailPager translagePager, String title) {
            mPageList.add(translagePager);
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
            BaseMenuDetailPager pager = mPageList.get(position);
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
