package com.example.mwqi.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * ============================================================
 * <p/>
 * 版      权 ： 黑马程序员教育集团 版权所有 (c) 2015
 * <p/>
 * 作      者  :  马伟奇
 * <p/>
 * 版      本 ： 1.0
 * <p/>
 * 创建日期 ： 2015/6/21  18:01
 * <p/>
 * 描      述 ：
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */
public class AppFragmenet extends Fragment {

    private View view;
    private TextInputLayout mTextInputLayout;
    private EditText mEditText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.text_input_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        demoTextInputLayout();
        demomFloatingActionButton();
    }

    private void demomFloatingActionButton() {
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.button);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "结束当前Acitivty", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "哈哈", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    private void demoTextInputLayout() {
        mTextInputLayout = (TextInputLayout) view.findViewById(R.id.text_input_layout);
        mEditText = mTextInputLayout.getEditText();
        mTextInputLayout.setHint("请输入4位学号");

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 4) {
                    mTextInputLayout.setError("学号输入错误！");
                    mTextInputLayout.setErrorEnabled(true);
                } else {
                    mTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
