package com.maweiqi.androidl.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.maweiqi.androidl.R;
import com.maweiqi.androidl.widget.BaseMenuDetailPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDetialPager extends BaseMenuDetailPager {
    private String title;
    private TextView txt;

    public TabDetialPager(Activity activity, String title) {
        super(activity);
        this.title = title;
    }

    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_text, null);
        txt = (TextView) view.findViewById(R.id.txt);
        return view;
    }

    public void initData() {
        txt.setText("City is "+title);
    }
}