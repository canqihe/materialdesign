package com.hecanqi.widget;


import android.app.Activity;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hecanqi.myapplication.R;
import com.hecanqi.utils.ParseJson;
import com.hecanqi.utils.StringUtils;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateDialect extends BaseMenuDetailPager implements View.OnClickListener {

    private View view;
    private EditText trans_content;
    private ImageView speak, voice, clean;
    private TranslateEnglish te;
    private String mSpeakPerson;

    /***
     * @param activity
     * @param editResource 编辑框资源文件
     * @param speakResource 播音控件资源文件
     * @param speakPerson 发音人
     */
    public TranslateDialect(Activity activity, int editResource, int speakResource, String speakPerson) {
        super(activity,editResource,speakResource);
        this.mSpeakPerson = speakPerson;
    }

    public View initViews() {
        view = View.inflate(mActivity, R.layout.translate_dialect, null);
        findById();

        return view;
    }

    private void findById() {
        trans_content = (EditText) view.findViewById(R.id.translate_content);
        voice = (ImageView) view.findViewById(R.id.voice);
        speak = (ImageView) view.findViewById(R.id.speak);
        clean = (ImageView) view.findViewById(R.id.clean);

        trans_content.setBackgroundResource(mEditResource);
        speak.setBackgroundResource(mSpeakResource);

        voice.setOnClickListener(this);
        speak.setOnClickListener(this);
        clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        te = new TranslateEnglish(mActivity, 0, 0);
        switch (view.getId()) {
            case R.id.voice:
                voiceListen();
                break;
            case R.id.speak:
                String text = trans_content.getText().toString();
                if (TextUtils.isEmpty(text)) StringUtils.getSnackbar(view, "请输入转译内容哦~");
                else te.voiceSpeak(text, mSpeakPerson);
                break;
            case R.id.clean:
                trans_content.setText("");
                break;
        }
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
            trans_content.setText(mTextBuffer.toString());//拿到语音文本
            if (isLast) {
                mTextBuffer = new StringBuffer();//清空
            }
        }

        @Override
        public void onError(SpeechError arg0) {
        }
    };

}