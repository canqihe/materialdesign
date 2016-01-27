package com.hecanqi.widget;

import android.app.Activity;
import android.view.View;

/**
 * 菜单详情页基类
 *
 * @author Kevin
 */
public abstract class BaseMenuDetailPager {

    public Activity mActivity;

    public View mRootView;// 根布局对象

    public int mEditResource, mSpeakResource;//控件资源对象

    public BaseMenuDetailPager(Activity activity, int editResource, int speakResource) {
        mEditResource = editResource;
        mSpeakResource = speakResource;
        mActivity = activity;
        mRootView = initViews();
    }

    /**
     * 初始化界面
     */
    public abstract View initViews();


}
