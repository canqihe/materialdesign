package com.hecanqi.utils;

import android.content.Context;
import android.content.Intent;


/**
 * Created by user
 */
public class ShareUtils {

    public static void share(Context context) {
        share(context, "Supper App - 一款乱七八糟的应用 - http://www.weibo.com/colinhi");
    }

    public static void share(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享"));
    }
}
