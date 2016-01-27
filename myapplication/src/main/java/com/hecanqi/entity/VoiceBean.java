package com.hecanqi.entity;

import java.util.ArrayList;

/**
 * 语音识别数据对象
 *
 * @author
 *
 */
public class VoiceBean {
    public int bg;
    public int ed;
    public boolean ls;
    public int sn;
    public ArrayList<WsBean> ws;

    public class WsBean {
        public int bg;
        public ArrayList<CwBean> cw;
    }

    public class CwBean {
        public String sc;
        public String w;
    }
}
