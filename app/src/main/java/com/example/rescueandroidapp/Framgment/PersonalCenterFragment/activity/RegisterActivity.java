package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.mytoast.LogUtil;
import com.rescueandroid.utils.mytoast.NToast;
import com.rescueandroid.utils.ui.AMUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/5/9.
 */

public class RegisterActivity extends MyBaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG";
    //1825b4
    private ImageView mImageView1, mImageView2;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4;
    boolean eyeOpen = false;
    private TextView mTextView, tvRegister_submit, tvRegister_submit2;
    private int mStatus;
    private String mPhone;
    private int a = 11;

    private String mTrim1;
    private String mTrim2;
    private String mTrim3;
    private String mTrim4;
    private ProgressDialog mProgressDialog;
    private ImageView ivRegistered_agrement;
    private int type = 1;
    private Handler mHandler = new Handler();
    private ProgressDialogEx progressDlgEx;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public int setview() {
        return R.layout.register_activity;
    }

    @Override
    public void init() {

        progressDlgEx = new ProgressDialogEx(this, mHandler);
        mImageView1 = (ImageView) findViewById(R.id.register_activity_iv1);
        mImageView2 = (ImageView) findViewById(R.id.register_activity_iv2);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);


        mEditText1 = (EditText) findViewById(R.id.register_activity_et1);
        mEditText2 = (EditText) findViewById(R.id.register_activity_et2);
        mEditText2.setOnClickListener(this);

        tvRegister_submit = (TextView) findViewById(R.id.tvRegister_submit);
        tvRegister_submit2 = (TextView) findViewById(R.id.tvRegister_submit2);
        tvRegister_submit.setOnClickListener(this);

        ivRegistered_agrement = (ImageView) findViewById(R.id.ivRegistered_agrement);
        ivRegistered_agrement.setOnClickListener(this);

        mEditText3 = (EditText) findViewById(R.id.register_activity_et3);
        mEditText4 = (EditText) findViewById(R.id.register_activity_et4);

        //用户协议
        mTextView = (TextView) findViewById(R.id.register_activity_tv1);
        mTextView.setOnClickListener(this);

        mEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            /**
             *
             * @param charSequence 显示的是字符窜
             * @param i     有几个字符，从0开始
             * @param i1   18956965693
             * @param i2
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 11) {
                    if (AMUtils.isMobile(charSequence.toString().trim())) {
                        mPhone = charSequence.toString().trim();
                        AMUtils.onInactive(RegisterActivity.this, mEditText3);

                        new Thread() {
                            @Override
                            public void run() {
                                OkHttpClient okHttpClient = new OkHttpClient();

                                String format = String.format(KeyPath.Path.head + KeyPath.Path.phonr_verify, mPhone);
                                Request build1 = new Request.Builder().
                                        url(format)

                                        .build();
                                okHttpClient.newCall(build1).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String string = response.body().string();
                                        if (string != null) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(string);
                                                int status = jsonObject.getInt("status");
                                                if (String.valueOf(status).equals("1")) {
                                                    mHandler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            NToast.shortToast(RegisterActivity.this, "你的手机号未被注册过");
                                                        }
                                                    });


                                                } else {
                                                    mHandler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            NToast.shortToast(RegisterActivity.this, "你的手机号已注册过");

                                                        }
                                                    });

                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });

                            }
                        }.start();

                    } else {
                        Toast.makeText(RegisterActivity.this, "非法手机号", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEditText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 6) {

                    //mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_blue));
                    Toast.makeText(RegisterActivity.this, "密码设置成功", Toast.LENGTH_SHORT).show();

                } else {


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


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
            //点击消失
            case R.id.register_activity_iv1:
                finish();
                break;
            case R.id.register_activity_iv2:
                String s = "" + a;
                if (eyeOpen) {
                    //密码
                    mEditText4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mImageView2.setImageResource(R.mipmap.eyes1);
                    eyeOpen = false;
                } else {
                    //明文
                    mEditText4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mImageView2.setImageResource(R.mipmap.eyes);
                    eyeOpen = true;
                }
                break;
            case R.id.register_activity_tv1:

                startActivity(new Intent(RegisterActivity.this, User_information_Activity.class));


                break;


            case R.id.register_activity_et2:

                Role_Popwindow role_popwindow = new Role_Popwindow(RegisterActivity.this);
                role_popwindow.setType("货主");
                role_popwindow.showAtLocation(mEditText2, Gravity.BOTTOM, 0, 0);
                role_popwindow.setAddresskListener(new Role_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {
                        mEditText2.setText(type);


                        if ("货主".equals(type)) {
                            a = 11;

                        }
                        if ("物流公司".equals(type)) {
                            a = 12;
                        }

                        if ("信息部".equals(type)) {
                            a = 13;
                        }


                    }
                });

                break;
            case R.id.tvRegister_submit:

                mTrim1 = mEditText1.getText().toString().trim();
                mTrim2 = mEditText2.getText().toString().trim();
                mTrim3 = mEditText3.getText().toString().trim();
                mTrim4 = mEditText4.getText().toString().trim();
                if (TextUtils.isEmpty(mTrim1)) {

                    NToast.shortToast(RegisterActivity.this, "昵称不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mTrim2)) {

                    NToast.shortToast(RegisterActivity.this, "你没有选择你的角色");
                    return;
                }
                if (TextUtils.isEmpty(mTrim3)) {

                    NToast.shortToast(RegisterActivity.this, "你没有输入你的手机号");
                    return;
                }
                if (TextUtils.isEmpty(mTrim4)) {
                    NToast.shortToast(RegisterActivity.this, "你没有输入你的密码");
                    return;
                }
                inia();
                break;

            case R.id.ivRegistered_agrement:

                /**
                 * 判断复选框是否选择
                 */
                if (type == 1) {
                    type = 2;
                    ivRegistered_agrement.setImageResource(R.mipmap.checkbox1);
                    tvRegister_submit.setVisibility(View.VISIBLE);
                    tvRegister_submit2.setVisibility(View.GONE);
                } else {
                    type = 1;
                    ivRegistered_agrement.setImageResource(R.mipmap.checkbox2);
                    tvRegister_submit.setVisibility(View.GONE);
                    tvRegister_submit2.setVisibility(View.VISIBLE);
                }

                break;
        }
    }

    /**
     * 注册接口
     */
    private void inia() {
        new Thread() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody build = new FormBody.Builder()
                        .add("username", mTrim3)
                        .add("realname", mTrim1)
                        .add("roleid", "" + a)
                        .add("telephone", mTrim3)
                        .add("userpassword", mTrim4)
                        .build();
                String format = String.format(KeyPath.Path.head + KeyPath.Path.register, mTrim3, mTrim4);
                Request build1 = new Request.Builder()
                        .url(format)
                        .post(build)
                        .build();
                okHttpClient.newCall(build1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        LogUtil.e(string);
                        if (string != null) {
                            try {
                                progressDlgEx.simpleModeShowHandleThread();
                                JSONObject jsonObject = new JSONObject(string);
                                int status = jsonObject.getInt("status");
                                if (status == 1) {
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            NToast.shortToast(RegisterActivity.this, "你已注册成功");
                                            finish();
                                        }
                                    });
                                } else {
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            NToast.shortToast(RegisterActivity.this, "注册失败，请重新操作");
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } finally {
                                progressDlgEx.closeHandleThread();
                            }
                        }
                    }
                });
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
