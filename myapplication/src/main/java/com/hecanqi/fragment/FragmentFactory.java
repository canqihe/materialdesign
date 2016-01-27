package com.hecanqi.fragment;


import android.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {

    public static final int FRAGEMENT_NEWS = 0;
    public static final int FRAGEMENT_TRANSLATE = 1;
    public static final int FRAGEMENT_WEATHER = 2;
    public static final int FRAGEMENT_HISTORY = 3;
    public static final int FRAGEMENT_GIRL = 4;

    private static Map<Integer, Fragment> mFragmentCache = new HashMap<Integer, Fragment>();

    public static Fragment createFragment(int position) {
        Fragment fragment = mFragmentCache.get(position);
        if (fragment == null) {
            switch (position) {
                case FRAGEMENT_NEWS:
                    fragment = new newsFragment();
                    break;
                case FRAGEMENT_TRANSLATE:
                    fragment = new translateFragment();
                    break;
                case FRAGEMENT_WEATHER:
                    fragment = new weatherFragment();
                    break;
                case FRAGEMENT_HISTORY:
                    fragment = new historyFragment();
                    break;
                case FRAGEMENT_GIRL:
                    fragment = new girlFragment();
                    break;
                default:
                    break;
            }
            mFragmentCache.put(position, fragment);
        }
        return fragment;
    }

    public static void clear() {
        mFragmentCache.clear();
    }
}
