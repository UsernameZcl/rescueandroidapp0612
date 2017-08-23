package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.ui.ProgressDialogEx;

/**
 * Created by ZCL on 2017/5/9.
 */

public class Forget_password_Activity extends MyBaseActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();
    private ProgressDialogEx progressDlgEx;
    private TextView tvFindpwd_codeSend1, tvFindpwd_codeSend2,tvFindpwd_submit;
    private EditText etFindpwd_tel, etFindpwd_code, etFindpwd_pwd1, etFindpwd_pwd2;
    private String etTel, etCode, etNew1, etNew2, code;
    private ImageView ivFindpwd_back;

    @Override
    public int setview() {
        return R.layout.forget_password_activity;
    }

    @Override
    public void init() {
        progressDlgEx = new ProgressDialogEx(this, mHandler);

        etFindpwd_tel = (EditText) findViewById(R.id.etFindpwd_tel);
//        etFindpwd_code = (EditText) findViewById(R.id.etFindpwd_code);
        etFindpwd_pwd1 = (EditText) findViewById(R.id.etFindpwd_pwd1);
        etFindpwd_pwd2 = (EditText) findViewById(R.id.etFindpwd_pwd2);

        // 后退
        ivFindpwd_back = (ImageView) this.findViewById(R.id.ivFindpwd_back);
        ivFindpwd_back.setOnClickListener(this);
        // 提交(确定)
        tvFindpwd_submit = (TextView) findViewById(R.id.tvFindpwd_submit);
        tvFindpwd_submit.setOnClickListener(this);
    }

    @Override
    public void setbase() {

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivFindpwd_back:
                finish();
                break;
            case R.id.tvFindpwd_submit:
                init();
                break;
        }

    }

}
