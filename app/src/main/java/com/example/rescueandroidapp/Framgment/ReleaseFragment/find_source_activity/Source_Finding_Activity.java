package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.Details_of_Origin_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Cargo_type_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Required_temperature_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Required_temperature_Popwindow_One;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Required_temperature_Popwindow_Two;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.NToast;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/5/30.
 */

//发布界面中找库货源的界面
public class Source_Finding_Activity extends MyBaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageView mImageView;
    private TextView mTextView;
    private ImageView mImageView2, mImageView3, mImageView4, mImageView5, mImageView6;
    private Boolean add1 = false;
    private Boolean add2 = true;
    private Boolean add3 = true;
    private Boolean add4 = true;
    private Boolean add5 = true;
    private LinearLayout mLinearLayout1, mLinearLayout2, mLinearLayout3, mLinearLayout4, mLinearLayout5, mLinearLayout6, mLinearLayout7, mLinearLayout8, mLinearLayout9, mLinearLayout10;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4, mEditText5, mEditText6, mEditText7, mEditText8, source_finding22,
            mEditText10, mEditText11, mEditText12, mEditText13;
    private String mKey;
    private String mStartprovince;
    private String mStartcity;
    private String mStartarea;
    private String mDetailed_address;
    private Required_temperature_Popwindow mRequired_temperature_popwindow1;
    private float mLowFloat;
    private float mheigthFloat;
    private Cargo_type_Popwindow mCargo_type_popwindow;
    private RadioGroup mRadioGroup1, mRadioGroup2, mRadioGroup3;
    private String mTrim;
    private String mTrim1;
    private int mInt;
    private Button mButton;
    private String mTrim2;
    private int pay = 0;
    private int b = 1;
    private String mUserName;
    private String mUserPass;
    private String m3;

    private ProgressDialogEx progressDlgEx;
    private Handler mHandler = new Handler();

    private TimePickerView pvCustomTime;

    @Override
    public int setview() {
        return R.layout.source_finding_activity;
    }

    @Override
    public void init() {
        progressDlgEx = new ProgressDialogEx(this, mHandler);
        initCustomTimePicker();
        // 获取用户名
        mUserName = SharedPreferencesUtils.getString(this, "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(this, "userPass", "");
        mImageView = (ImageView) findViewById(R.id.source_finding_activity_iv1);
        mImageView.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.source_finding_activity_tv1);
        mTextView.setOnClickListener(this);

        mLinearLayout1 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay1);
        mLinearLayout3 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay3);
        mLinearLayout5 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay5);
        mLinearLayout7 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay7);
        mLinearLayout9 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay9);
        mLinearLayout1.setOnClickListener(this);
        mLinearLayout3.setOnClickListener(this);
        mLinearLayout5.setOnClickListener(this);
        mLinearLayout7.setOnClickListener(this);
        mLinearLayout9.setOnClickListener(this);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay2);
        mLinearLayout4 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay4);
        mLinearLayout6 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay6);
        mLinearLayout8 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay8);
        mLinearLayout10 = (LinearLayout) findViewById(R.id.source_finding_activity_linlay10);

        mImageView2 = (ImageView) findViewById(R.id.source_finding_activity_iv2);
        mImageView3 = (ImageView) findViewById(R.id.source_finding_activity_iv3);
        mImageView4 = (ImageView) findViewById(R.id.source_finding_activity_iv4);
        mImageView5 = (ImageView) findViewById(R.id.source_finding_activity_iv5);
        mImageView6 = (ImageView) findViewById(R.id.source_finding_activity_iv6);
        //仓库位置
        mEditText1 = (EditText) findViewById(R.id.source_finding_activity_et1);
        mEditText1.setOnClickListener(this);
        //入库的时间
        mEditText2 = (EditText) findViewById(R.id.source_finding_activity_et2);
        mEditText2.setOnClickListener(this);
        //存储的时间
        mEditText3 = (EditText) findViewById(R.id.source_finding_activity_et3);
        //最低温
        mEditText4 = (EditText) findViewById(R.id.source_finding_activity_et4);
        mEditText4.setOnClickListener(this);
        //最高温
        mEditText5 = (EditText) findViewById(R.id.source_finding_activity_et5);
        mEditText5.setOnClickListener(this);
        //货物名称
        mEditText6 = (EditText) findViewById(R.id.source_finding_activity_et6);
        //货物类型
        mEditText7 = (EditText) findViewById(R.id.source_finding_activity_et7);
        mEditText7.setOnClickListener(this);
        //货物规格
        mEditText8 = (EditText) findViewById(R.id.source_finding_activity_et8);
        //包装类型
        source_finding22 = (EditText) findViewById(R.id.source_finding_activity_et9);
        //输入的价格
        mEditText10 = (EditText) findViewById(R.id.source_finding_activity_et10);
        //发货人
        mEditText11 = (EditText) findViewById(R.id.source_finding_activity_et11);
        //联系方式
        mEditText12 = (EditText) findViewById(R.id.source_finding_activity_et12);
        mEditText12.setText(mUserName);
        //备注
        mEditText13 = (EditText) findViewById(R.id.source_finding_activity_et13);

        //货物单位的选择
        mRadioGroup1 = (RadioGroup) findViewById(R.id.source_finding_activity_RG1);
        mRadioGroup1.setOnCheckedChangeListener(this);
        //是否需要发票
        mRadioGroup2 = (RadioGroup) findViewById(R.id.source_finding_activity_RG2);
        mRadioGroup2.setOnCheckedChangeListener(this);
        //支付方式
        mRadioGroup3 = (RadioGroup) findViewById(R.id.source_finding_activity_RG3);
        mRadioGroup3.setOnCheckedChangeListener(this);
        //提交的按钮
        mButton = (Button) findViewById(R.id.source_finding_activity_btn1);
        mButton.setOnClickListener(this);
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
            case R.id.source_finding_activity_iv1:
                finish();
                break;
            case R.id.source_finding_activity_tv1:
                //找库的发布界面
                startActivity(new Intent(Source_Finding_Activity.this, Source_Release_Activity.class));
                break;
            case R.id.source_finding_activity_linlay1:
                if (add1) {
                    //创建旋转动画
                    Animation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView2.startAnimation(animation);//开始动画
                    mLinearLayout2.setVisibility(View.VISIBLE);
                    add1 = false;
                } else {
                    Animation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView2.startAnimation(animation);//开始动画
                    mLinearLayout2.setVisibility(View.GONE);
                    add1 = true;
                }

                break;
            case R.id.source_finding_activity_linlay3:
                if (add2) {
                    Animation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView3.startAnimation(animation);//开始动画


                    mLinearLayout4.setVisibility(View.VISIBLE);
                    add2 = false;
                } else {
                    Animation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView3.startAnimation(animation);//开始动画
                    mLinearLayout4.setVisibility(View.GONE);
                    add2 = true;

                }

                break;
            case R.id.source_finding_activity_linlay5:
                if (add3) {
                    Animation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView4.startAnimation(animation);//开始动画
                    mLinearLayout6.setVisibility(View.VISIBLE);
                    add3 = false;
                } else {
                    Animation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView4.startAnimation(animation);//开始动画
                    mLinearLayout6.setVisibility(View.GONE);
                    add3 = true;

                }


                break;
            case R.id.source_finding_activity_linlay7:
                if (add4) {
                    Animation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView5.startAnimation(animation);//开始动画
                    mLinearLayout8.setVisibility(View.VISIBLE);
                    add4 = false;
                } else {
                    Animation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView5.startAnimation(animation);//开始动画
                    mLinearLayout8.setVisibility(View.GONE);
                    add4 = true;
                }
                break;
            case R.id.source_finding_activity_linlay9:
                if (add5) {
                    Animation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView6.startAnimation(animation);//开始动画
                    mLinearLayout10.setVisibility(View.VISIBLE);
                    add5 = false;
                } else {
                    Animation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(500);
                    animation.setRepeatCount(0);//动画的重复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    mImageView6.startAnimation(animation);//开始动画
                    mLinearLayout10.setVisibility(View.GONE);
                    add5 = true;
                }
                break;
            case R.id.source_finding_activity_et1:
                Intent intent = new Intent(Source_Finding_Activity.this, Details_of_Origin_Activity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.source_finding_activity_et2:


                pvCustomTime.show();

                break;
            case R.id.source_finding_activity_et4:
                if ("".equals(mEditText5.getText().toString().trim())) {
                    mRequired_temperature_popwindow1 = new Required_temperature_Popwindow(Source_Finding_Activity.this);
                    mRequired_temperature_popwindow1.setType("-50");
                    mRequired_temperature_popwindow1.showAtLocation(mEditText4, Gravity.BOTTOM, 0, 0);
                    mRequired_temperature_popwindow1.setAddresskListener(new Required_temperature_Popwindow.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText4.setText(type);
                            mLowFloat = Float.parseFloat(type);
                        }
                    });

                } else {
                    String trim = mEditText5.getText().toString().trim();
                    int i = Integer.parseInt(trim);
                    Required_temperature_Popwindow_One required_temperature_popwindow_one = new Required_temperature_Popwindow_One(Source_Finding_Activity.this, i);
                    required_temperature_popwindow_one.setType("-50");
                    required_temperature_popwindow_one.showAtLocation(mEditText4, Gravity.BOTTOM, 0, 0);
                    required_temperature_popwindow_one.setAddresskListener(new Required_temperature_Popwindow_One.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText4.setText(type);
                            mLowFloat = Float.parseFloat(type);
                        }
                    });
                }

                break;
            case R.id.source_finding_activity_et5:
                if ("".equals(mEditText4.getText().toString().trim())) {
                    mRequired_temperature_popwindow1 = new Required_temperature_Popwindow(Source_Finding_Activity.this);
                    mRequired_temperature_popwindow1.setType("-50");
                    mRequired_temperature_popwindow1.showAtLocation(mEditText10, Gravity.BOTTOM, 0, 0);
                    mRequired_temperature_popwindow1.setAddresskListener(new Required_temperature_Popwindow.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText5.setText(type);
                            mheigthFloat = Float.parseFloat(type);
                        }
                    });

                } else {
                    String trim = mEditText4.getText().toString().trim();
                    int i = Integer.parseInt(trim);
                    Required_temperature_Popwindow_Two required_temperature_popwindow_Two = new Required_temperature_Popwindow_Two(Source_Finding_Activity.this, i);
                    required_temperature_popwindow_Two.setType(trim);
                    required_temperature_popwindow_Two.showAtLocation(mEditText10, Gravity.BOTTOM, 0, 0);
                    required_temperature_popwindow_Two.setAddresskListener(new Required_temperature_Popwindow_Two.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText5.setText(type);
                            mheigthFloat = Float.parseFloat(type);
                        }
                    });
                }
                break;
            case R.id.source_finding_activity_et7:
                mCargo_type_popwindow = new Cargo_type_Popwindow(Source_Finding_Activity.this);

                mCargo_type_popwindow.setType("常温");
                mCargo_type_popwindow.showAtLocation(mEditText5, Gravity.BOTTOM, 0, 0);
                mCargo_type_popwindow.setAddresskListener(new Cargo_type_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {
                        mEditText7.setText(type);
                        if ("普通货车".equals(mEditText7.getText().toString().trim())) {
                            b = 1;
                            return;
                        }
                        if ("冷藏车".equals(mEditText7.getText().toString().trim())) {
                            b = 2;
                            return;
                        }
                        if ("平板车".equals(mEditText7.getText().toString().trim())) {
                            b = 3;
                            return;
                        }
                        if ("常温箱式车".equals(mEditText7.getText().toString().trim())) {
                            b = 4;
                            return;
                        }
                        if ("集装箱车".equals(mEditText7.getText().toString().trim())) {
                            b = 5;
                            return;
                        }
                        if ("高拦车".equals(mEditText7.getText().toString().trim())) {
                            b = 6;
                            return;
                        }
                    }
                });


                break;
            case R.id.source_finding_activity_btn1:
                setunit();
                payment();

                if (TextUtils.isEmpty(mEditText1.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入仓库的位置");
                    return;
                }
                if (TextUtils.isEmpty(mEditText2.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入入库的时间");
                    return;
                }
                if (TextUtils.isEmpty(mEditText3.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入存储的时间");
                    return;
                }
                if (TextUtils.isEmpty(mEditText4.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入仓库的最低温");
                    return;
                }

                if (TextUtils.isEmpty(mEditText5.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入仓库的最高温");
                    return;
                }
                if (TextUtils.isEmpty(mEditText6.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入货物的名称");
                    return;
                }
                if (TextUtils.isEmpty(mEditText7.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请选择货物的类型");
                    return;
                }
                if (TextUtils.isEmpty(mEditText8.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入货物的规格");
                    return;
                }
//                if (TextUtils.isEmpty(mTrim)) {
//                    NToast.shortToast(Source_Finding_Activity.this, "请选择货物的单位");
//                    return;
//                }
                if (TextUtils.isEmpty(mEditText10.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入货物的价格");
                    return;
                }
//                if (TextUtils.isEmpty(mTrim2)) {
//                    NToast.shortToast(Source_Finding_Activity.this, "请选择支付方式");
//                    return;
//                }
//                if (TextUtils.isEmpty(mTrim1)) {
//                    NToast.shortToast(Source_Finding_Activity.this, "请选择是否需要发票");
//                    return;
//                }
                if (TextUtils.isEmpty(mEditText11.getText().toString().trim())) {
                    NToast.shortToast(Source_Finding_Activity.this, "请输入发货人");
                    return;
                }
//                if (TextUtils.isEmpty(mEditText12.getText().toString().trim())) {
//                    NToast.shortToast(Source_Finding_Activity.this, "请输入联系电话");
//                    return;
//                }

                new Thread() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        FormBody formBody = new FormBody.Builder()
                                //库的位置
                                .add("storeposition", mEditText1.getText().toString().trim())
                                //库的位置省
                                .add("provincef", mStartprovince)
                                //库的位置市
                                .add("cityf", mStartcity)
                                //库的位置区
                                .add("districtf", mStartarea)
                                //始发地详
                                .add("streetf", mDetailed_address)
                                //入库的时间
                                .add("putdate", mEditText2.getText().toString().trim())
                                //存储的时长
                                .add("storedate", "" + mEditText3.getText().toString().trim())
                                //最低温
                                .add("tempmin", "" + mLowFloat)
                                //最高温
                                .add("tempmax", "" + mheigthFloat)
                                //货物名称
                                .add("name", mEditText6.getText().toString().trim())
                                //货物类型的id
                                .add("typeid", b + "")
                                //货物类型
                                .add("typename", mEditText7.getText().toString().trim())
                                //货物规格
                                .add("spec", "" + Float.parseFloat(mEditText8.getText().toString().trim()))
                                //货物单位PackType
                                .add("unit", m3)
                                //包装的类型
                                .add("packtype", source_finding22.getText().toString())
                                //价格
                                .add("price", mEditText10.getText().toString().trim())
//                                //支付方式>>>>1是回单支付
//                                .add("payment", "" + pay)
//                                //是否需要发票 IsNeedBill
//                                .add("isneedbill", "" + mInt)
                                //发货人
                                .add("senduser", mEditText11.getText().toString().trim())
                                //发货人电话
                                .add("sendtel",mUserName )
                                //备注
                                .add("remark", mEditText13.getText().toString())
                                .build();

                        String format = String.format(KeyPath.Path.head + KeyPath.Path.find_library, mUserName, mUserPass);
                        Request request = new Request.Builder()
                                .url(format)
                                .post(formBody)
                                .build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                NToast.shortToast(Source_Finding_Activity.this, "数据获取失败，请重新尝试");

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                                if (string != null) {
                                    progressDlgEx.simpleModeShowHandleThread();
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(string);
                                        int status = jsonObject.getInt("status");
                                        if (status == 1) {
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    NToast.shortToast(Source_Finding_Activity.this, "发布成功");

                                                    //跳转到发布界面
                                                    startActivity(new Intent(Source_Finding_Activity.this, Source_Release_Activity.class));
                                                    finish();
                                                }
                                            });

                                        } else {

                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    NToast.shortToast(Source_Finding_Activity.this, "用户名密码不正确");
                                                    finish();
                                                }
                                            });
                                        }
                                    } catch (JSONException e) {

                                    } finally {
                                        progressDlgEx.closeHandleThread();
                                    }

                                }

                            }
                        });


                    }
                }.start();
                break;
        }
    }
    //吨或者方的选择
    public void setunit() {

        if (mTrim == null || "吨".equals(mTrim)) {
            m3 = "吨";

        } else {
            m3 = "方";
        }
    }
    public void payment() {
        if ("现金到付".equals(mTrim2) || mTrim2 == null) {
            pay = 1;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    mKey = data.getStringExtra("key");
                    //始发的省份
                    mStartprovince = data.getStringExtra("province");
                    //始发的城市
                    mStartcity = data.getStringExtra("city");
                    //始发的地区
                    mStartarea = data.getStringExtra("area");
                    //街道
                    mDetailed_address = data.getStringExtra("detailed_address");
                    mEditText1.setText(mKey);


                }

                break;


        }


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.source_finding_activity_rg1:
            case R.id.source_finding_activity_rg2:
                RadioButton viewById = (RadioButton) findViewById(i);
                mTrim = viewById.getText().toString().trim();
                break;
            case R.id.source_finding_activity_rg6:
                RadioButton pay_method = (RadioButton) findViewById(i);
                mTrim2 = pay_method.getText().toString().trim();

                if ("现金到付".equals(mTrim2)) {
                    pay = 1;

                }


                break;
            case R.id.source_finding_activity_rg3:
            case R.id.source_finding_activity_rg4:
                RadioButton fapiao = (RadioButton) findViewById(i);
                mTrim1 = fapiao.getText().toString().trim();
                if ("是".equals(mTrim1)) {
                    mInt = 0;
                } else {
                    mInt = 1;
                }
                break;

        }
    }

    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String[] starTimestr2 = str.split("_");
        String startcreateDate2 = starTimestr2[0];
        String startcreateDate21 = starTimestr2[1];
        String startcreateDate22 = starTimestr2[2];

        Calendar startDate = Calendar.getInstance();
        startDate.set(Integer.valueOf(startcreateDate2), Integer.valueOf(startcreateDate21) - 1, Integer.valueOf(startcreateDate22));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2065, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mEditText2.setText(getTime(date));
            }
        })

                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
               /*.gravity(Gravity.RIGHT)// default is center*/

                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.parseColor("#d8d8d8"))
                .setLineSpacingMultiplier(6f)//设置两横线之间的间隔倍数
                .setOutSideCancelable(false)
                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
