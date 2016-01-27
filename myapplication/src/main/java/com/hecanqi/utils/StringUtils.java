package com.hecanqi.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.hecanqi.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by user on 2015/10/28.
 */
public class StringUtils {

    public static String[] itemName = {"新闻快读", "翻译助手", "天气预报", "历史今天", "美女赏析"};

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * Snackbar
     *
     * @param view
     * @param content
     */
    public static void getSnackbar(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 强制隐藏键盘
     *
     * @param v
     */
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }

        //键盘开关
        //InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 E");//可以方便地修改日期格式

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date1 = c.get(Calendar.DATE);
        int[] nongLi = Lauar.solarToLunar(year, month, date1);

        String dateText = dateFormat.format(date) + " 农历" + nongLi[1] + "月" + nongLi[2];
        return dateText;
    }

    /**
     * 获取随机格言
     *
     * @return
     */
    public static String getMotto() {
        String[] motto = {"快乐有三法:舍得,放下,忘记.", "梦想很轻,却因此拥有飞向蓝天的力量.", "那些最容易脸红的人,往往是最善良的.",
                "随着年龄的增长,我们并不是失去了一些朋友,而是我们懂得了谁才是真正的朋友.", "其实真正对你好的人,你一辈子也不会遇到几个.",
                "如果有选择,那就选择最好的;如果没有选择,那就努力做到最好.", "当你真心相信一切都会好的时候,一切就会真的好了.",
                "带着曾经的梦中幻想,告别昨日的紧张彷徨,新的一天要大踏步迈向前方.", "时光的残忍正在于,她只能带你走向未来,却不能带你回到过去.",
                "人生,说到底,活的是心情.", "把自己从过去解放出来,前进的唯一方法是别往后看.", "不懂时,别乱说.懂得时,别多说.",
                "生活是可以去漂泊,可以是孤独的,但是灵魂必须是有所归依.", "接受过去和现在的模样,才会有能量去追寻自己的未来.",
                "每天要以微笑开始,并且要坚持到这一天过去.", "择善友而交,择善书而读,择善言而听,择善行而从.",
                "成长会让人明白,唯一后悔的只是那些自己不曾尝试的事.", "问候不一定要慎重其事,但一定要真诚感人."};
        Random r = new Random();
        int num = r.nextInt(motto.length);
        return motto[num];
    }

    /**
     * 获取随机图片
     *
     * @return
     */
    public static int getHeader() {
        int[] photo = {R.mipmap.header, R.mipmap.headeone, R.mipmap.headetwo, R.mipmap.headethree, R.mipmap.headefour, R.mipmap.headefive,
                R.mipmap.headsix};
        Random r = new Random();
        int num = r.nextInt(photo.length);
        return photo[num];
    }

    /**
     * 获取随机头像
     *
     * @return
     */
    public static int getPhoto() {
        int[] pic = {R.mipmap.photo, R.mipmap.pa, R.mipmap.pb, R.mipmap.pc, R.mipmap.pd, R.mipmap.pe, R.mipmap.pf, R.mipmap.pg, R.mipmap.ph, R.mipmap.pi, R.mipmap.pj,
                R.mipmap.pk, R.mipmap.pl, R.mipmap.pm, R.mipmap.pn, R.mipmap.po, R.mipmap.pq, R.mipmap.pr,};
        Random r = new Random();
        int num = r.nextInt(pic.length);
        return pic[num];
    }

    /**
     * 随机获取资料背景图
     *
     * @return
     */
    public static int getPerPic() {
        int[] pic = {R.mipmap.perone, R.mipmap.pertwo, R.mipmap.perthree, R.mipmap.perfour, R.mipmap.perfive,};
        Random r = new Random();
        int num = r.nextInt(pic.length);
        return pic[num];
    }

    /***
     * 简单Intent意图
     * @param context
     * @param clazz
     */
    public static void useIntent(Context context,Class clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);

    }

}
