package com.hecanqi.utils;

import com.google.gson.Gson;
import com.hecanqi.entity.Translate;
import com.hecanqi.entity.VoiceBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 2015/10/28.
 */
public class ParseJson {
    public static Translate translate;

    /**
     * 解析语音json数据
     */
    public static String parseVoice(String json) {
        Gson gson = new Gson();
        VoiceBean bean = gson.fromJson(json, VoiceBean.class);
        ArrayList<VoiceBean.WsBean> ws = bean.ws;

        StringBuffer sb = new StringBuffer();
        for (VoiceBean.WsBean wsBean : ws) {
            ArrayList<VoiceBean.CwBean> cw = wsBean.cw;
            for (VoiceBean.CwBean cwBean : cw) {
                String w = cwBean.w;
                sb.append(w);
            }
        }
        return sb.toString();
    }

    //json解析请求结果
    public static Translate parseTranslate(String result, boolean flag) {
        try {

            translate = new Translate();

            JSONObject object = new JSONObject(result);
            JSONObject object1 = object.getJSONObject("result");
            JSONObject object2 = object1.getJSONObject("data");
            JSONArray ja = object2.getJSONArray("translation");
            translate.setTranslation(ja.getString(0));
            JSONObject object3 = object2.getJSONObject("basic");
            translate.setUsPhonetic(object3.getString("us-phonetic"));
            translate.setUkPhonetic(object3.getString("uk-phonetic"));
            JSONArray ja1 = object3.getJSONArray("explains");
            translate.setExplainsN(ja1.getString(0));
            translate.setExplainsA(ja1.getString(1));
            translate.setExplainsD(ja1.getString(2));

            JSONArray ja2 = object2.getJSONArray("web");
            JSONObject object4 = (JSONObject) ja2.get(2);
            JSONArray value = object4.getJSONArray("value");
            translate.setValue(value.getString(0));
            translate.setKey(object4.getString("key"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return translate;
    }
}
