package com.hecanqi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hecanqi.myapplication.R;

public class newsFragment extends Fragment {

    private TabLayout tabLayout;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_news, null);
        findById();
        return view;
    }

    private void findById() {
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("头条"));
        tabLayout.addTab(tabLayout.newTab().setText("财经"));
        tabLayout.addTab(tabLayout.newTab().setText("社会"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.message));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.like));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.notifications));
    }


}
