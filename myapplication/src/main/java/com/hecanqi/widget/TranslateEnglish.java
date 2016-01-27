package com.hecanqi.widget;


import android.app.Activity;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hecanqi.entity.Translate;
import com.hecanqi.myapplication.R;
import com.hecanqi.utils.Constant;
import com.hecanqi.utils.ParseJson;
import com.hecanqi.utils.StringUtils;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateEnglish extends BaseMenuDetailPager implements View.OnClickListener {

    private EditText trans_content;
    private TextView result, sound1, sound2, ex1, ex2, ex3, webvalue, webkey, web, textresult;
    private ImageView sound, imageView;
    private Button trans;
    private FloatingActionButton voice;
    private View view;
    private String translateText, voiceTranslateContent;
    private Translate translate;

    public TranslateEnglish(Activity activity, int editResource, int speakResource) {
        super(activity, editResource, speakResource);
    }

    public View initViews() {
        view = View.inflate(mActivity, R.layout.translate_english, null);
        findById();
        return view;
    }

    private void findById() {
        trans_content = (EditText) view.findViewById(R.id.translate_content);
        trans = (Button) view.findViewById(R.id.translate);
        result = (TextView) view.findViewById(R.id.result);
        sound1 = (TextView) view.findViewById(R.id.sound1);
        sound2 = (TextView) view.findViewById(R.id.sound2);
        ex1 = (TextView) view.findViewById(R.id.explains1);
        ex2 = (TextView) view.findViewById(R.id.explains2);
        ex3 = (TextView) view.findViewById(R.id.explains3);
        webvalue = (TextView) view.findViewById(R.id.webvalue);
        webkey = (TextView) view.findViewById(R.id.webkey);
        sound = (ImageView) view.findViewById(R.id.sound);
        web = (TextView) view.findViewById(R.id.web);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textresult = (TextView) view.findViewById(R.id.textresult);
        voice = (FloatingActionButton) view.findViewById(R.id.voice);

        trans.setOnClickListener(this);
        sound.setOnClickListener(this);
        voice.setOnClickListener(this);
    }

    public void initData() {
        textresult.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        sound.setVisibility(View.VISIBLE);

        result.setText(translate.getTranslation());
        String soundone = translate.getUkPhonetic();
        String soundtwo = translate.getUsPhonetic();
        if (soundone != null || soundtwo != null) {
            sound1.setText("[" + soundone + "]");
            sound2.setText("[" + soundtwo + "]");
        } else {
            sound1.setText("");
            sound2.setText("");
        }

        String exthree = translate.getExplainsD();
        if (exthree == null) web.setVisibility(View.GONE);
        else web.setVisibility(View.VISIBLE);

        ex1.setText(translate.getExplainsN());
        ex2.setText(translate.getExplainsA());
        ex3.setText(exthree);
        webvalue.setText(translate.getValue());
        webkey.setText(translate.getKey());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.translate:
                StringUtils.HideKeyboard(view);//强制隐藏键盘
                translateText = trans_content.getText().toString();
                boolean netFlag = StringUtils.isNetworkConnected(mActivity);
                if (netFlag == false) StringUtils.getSnackbar(view, "网络不给力，请检查设置");
                else translate(translateText);//翻译输入文本
                break;
            case R.id.sound:
                voiceSpeak(result.getText().toString(), "xiaoyan");
                break;
            case R.id.voice:
                voiceListen();
                break;
        }
    }

    //请求网络进行翻译
    private void translate(String translateContent) {
        //进行非空判断
        if (TextUtils.isEmpty(translateContent)) {
            textresult.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            sound.setVisibility(View.GONE);
            StringUtils.getSnackbar(view, "请输入翻译内容，中英互译~");
        } else {
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("word", translateContent);
            params.addQueryStringParameter("only", "");
            params.addQueryStringParameter("key", Constant.YOUDAO_KEY);
            //发送网络请求
            HttpUtils utils = new HttpUtils();
            utils.send(HttpRequest.HttpMethod.POST, Constant.JUHE_YOUDAO, params, new RequestCallBack<String>() {
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    System.out.println("返回结果:" + result);
                    try {
                        translate = ParseJson.parseTranslate(result, false);//解析数据
                        initData();//填充数据
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(HttpException error, String msg) {
                    error.printStackTrace();
                    Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 语音朗诵
     *
     * @param answer
     */
    public void voiceSpeak(String answer, String person) {
        // 1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(mActivity, null);
        // 2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, person);// 设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围0~100
        // 设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        // 保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
        // 如果不需要保存合成音频，注释该行代码
        //mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        // 3.开始合成
        mTts.startSpeaking(answer, null);
    }

    /**
     * 语音识别
     *
     * @param
     */
    public void voiceListen() {
        RecognizerDialog iatDialog = new RecognizerDialog(mActivity, mInitListener);

        // 1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        //SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(mActivity, mInitListener);

        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        iatDialog.setParameter(SpeechConstant.DOMAIN, "iat");
        iatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        iatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

        // 3.开始听写
        //mIat.startListening(mRecoListener);  //RecognizerListener
        iatDialog.setListener(mRecoListener);
        iatDialog.show();
    }

    InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int arg0) {
            System.out.println("初始化");
        }
    };

    private StringBuffer mTextBuffer = new StringBuffer();
    private RecognizerDialogListener mRecoListener = new RecognizerDialogListener() {
        // 听写结果回调接口(返回Json格式结果，用户可参见附录)；
        // 一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        // 关于解析Json的代码可参见MscDemo中JsonParser类；
        // isLast等于true时会话结束。
        @Override
        public void onResult(com.iflytek.cloud.RecognizerResult resultText, boolean isLast) {

            String voiceText = ParseJson.parseVoice(resultText.getResultString());
            mTextBuffer.append(voiceText);
            voiceTranslateContent = mTextBuffer.toString();
            trans_content.setText(voiceTranslateContent);//拿到语音文本
            if (isLast) {
                translate(voiceTranslateContent);//开始翻译语音文本
                mTextBuffer = new StringBuffer();//清空
            }
        }

        @Override
        public void onError(SpeechError arg0) {
        }
    };

}