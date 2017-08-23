package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
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
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Cargo_type_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.ChangeTypePopwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Drivers_need_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Need_models_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Required_temperature_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Required_temperature_Popwindow_One;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Required_temperature_Popwindow_Two;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.LogUtil;
import com.rescueandroid.utils.mytoast.NToast;
import com.rescueandroid.utils.ui.DialogUtils;
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
 * Created by ZCL on 2017/5/28.
 */

/**
 * 车的货源的界面
 */
public class
Release_Car_Supply_Activity extends MyBaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "TAG";
    private ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageView5;
    private Boolean add1 = false;
    private Boolean add2 = true;
    private Boolean add3 = true;
    private Boolean add4 = true;
    private LinearLayout mLinearLayout1, mLinearLayout2, mLinearLayout3, mLinearLayout4, mLinearLayout5, mLinearLayout6, mLinearLayout7, mLinearLayout8, mLinearLayout9;
    private RadioGroup mRadioGroup1, mRadioGroup2, mRadioGroup3, mRadioGroup4;
    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4, mRadioButton5, mRadioButton6;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4, mEditText5, mEditText6, mEditText7, mEditText8, mEditText9,
            mEditText10, mEditText11, mEditText12, mEditText13, mEditText14, mEditText15, mEditText16, mEditText17, mEditText18;
    private String mKey;
    private String mStartprovince;
    private String mStartcity;
    private String mStartarea;
    private String mDetailed_address;
    private String mKey1;
    private String mEndprovince;
    private String mEndcity;
    private String mEndarea;
    private String mEnd_detailed_address;
    private ChangeTypePopwindow mChangeTypePopwindow;
    private Cargo_type_Popwindow mCargo_type_popwindow;
    private Drivers_need_Popwindow mDrivers_need_popwindow;
    private Need_models_Popwindow mNeed_models_popwindow;
    private int b;
    private int touch = 0;
    private int mInt = 1;
    private Required_temperature_Popwindow mRequired_temperature_popwindow1;

    private float mLowFloat;
    private float mheigthFloat;
    private TextView mTextView;
    private Button mButton;
    private String mTrim;
    private String m3;
    private String mTrim1;
    private String money = "";
    private String mTrim2;
    private int pay = 0;
    private int c = 0;
    private String mUsername;
    private String mUserPass;
    private Handler mHandler = new Handler();
    private ProgressDialogEx progressDlgEx;

    private TimePickerView pvCustomTime, pvCustomTime2, pvCustomTime3, pvCustomTime4;

    @Override
    public int setview() {
        return R.layout.release_car_supply_activity;
    }

    @Override
    public void init() {
        progressDlgEx = new ProgressDialogEx(this, mHandler);
        initCustomTimePicker();
        initCustomTimePicker2();
        initCustomTimePicker3();
        initCustomTimePicker4();


        // 获取用户名
        mUsername = SharedPreferencesUtils.getString(this, "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(this, "userPass", "");
        //点击图片消失
        mImageView1 = (ImageView) findViewById(R.id.selease_car_supply_activity_iv1);
        mImageView1.setOnClickListener(this);
        //跳转到我的发布的界面
        mTextView = (TextView) findViewById(R.id.selease_car_supply_activity_tv1);
        mTextView.setOnClickListener(this);
        //提交的按钮
        mButton = (Button) findViewById(R.id.selease_car_supply_activity_btn1);
        mButton.setOnClickListener(this);
        //旋转图片
        mImageView2 = (ImageView) findViewById(R.id.selease_car_supply_activity_iv2);
        mImageView3 = (ImageView) findViewById(R.id.selease_car_supply_activity_iv3);
        mImageView4 = (ImageView) findViewById(R.id.selease_car_supply_activity_iv4);
        mImageView5 = (ImageView) findViewById(R.id.selease_car_supply_activity_iv5);
        //显示
        mLinearLayout1 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay1);
        mLinearLayout3 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay3);

        mLinearLayout5 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay5);
        mLinearLayout7 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay7);
        mLinearLayout1.setOnClickListener(this);
        mLinearLayout3.setOnClickListener(this);
        mLinearLayout5.setOnClickListener(this);
        mLinearLayout7.setOnClickListener(this);
        //隐藏
        mLinearLayout2 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay2);
        mLinearLayout4 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay4);
        mLinearLayout6 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay6);
        mLinearLayout8 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay8);
        mLinearLayout2.setOnClickListener(this);
        mLinearLayout4.setOnClickListener(this);
        mLinearLayout6.setOnClickListener(this);
        mLinearLayout8.setOnClickListener(this);
        //费用 或者电话联系的布局与隐藏
        mLinearLayout9 = (LinearLayout) findViewById(R.id.selease_car_supply_activity_linlay9);

        //货物单位的选择
        mRadioGroup1 = (RadioGroup) findViewById(R.id.selease_car_supply_activity_RG1);
        mRadioGroup1.setOnCheckedChangeListener(this);

        //费用 或者电话按钮
        mRadioGroup2 = (RadioGroup) findViewById(R.id.selease_car_supply_activity_RG2);
        mRadioGroup2.setOnCheckedChangeListener(this);

        mRadioButton3 = (RadioButton) findViewById(R.id.selease_car_supply_activity_rg3);
        mRadioButton4 = (RadioButton) findViewById(R.id.selease_car_supply_activity_rg4);
        //是否需要发票
        mRadioGroup3 = (RadioGroup) findViewById(R.id.selease_car_supply_activity_RG3);
        mRadioGroup3.setOnCheckedChangeListener(this);
        //支付方式
        mRadioGroup4 = (RadioGroup) findViewById(R.id.selease_car_supply_activity_RG4);
        mRadioGroup4.setOnCheckedChangeListener(this);
        //始发地
        mEditText1 = (EditText) findViewById(R.id.selease_car_supply_activity_et1);
        mEditText1.setOnClickListener(this);
        //目的地
        mEditText2 = (EditText) findViewById(R.id.selease_car_supply_activity_et2);
        mEditText2.setOnClickListener(this);
        //货物名称
        mEditText3 = (EditText) findViewById(R.id.selease_car_supply_activity_et3);
        //货物品类
        mEditText4 = (EditText) findViewById(R.id.selease_car_supply_activity_et4);
        mEditText4.setOnClickListener(this);
        //货物类型
        mEditText5 = (EditText) findViewById(R.id.selease_car_supply_activity_et5);
        mEditText5.setOnClickListener(this);
        //货物规格
        mEditText6 = (EditText) findViewById(R.id.selease_car_supply_activity_et6);
        //需要车长
        mEditText7 = (EditText) findViewById(R.id.selease_car_supply_activity_et7);
        mEditText7.setOnClickListener(this);
        //需要类型
        mEditText8 = (EditText) findViewById(R.id.selease_car_supply_activity_et8);
        mEditText8.setOnClickListener(this);
        //最低温
        mEditText9 = (EditText) findViewById(R.id.selease_car_supply_activity_et9);
        mEditText9.setOnClickListener(this);

        //最高温
        mEditText10 = (EditText) findViewById(R.id.selease_car_supply_activity_et10);
        mEditText10.setOnClickListener(this);
        //最早装车时间
        mEditText11 = (EditText) findViewById(R.id.selease_car_supply_activity_et11);
        mEditText11.setOnClickListener(this);
        //最晚装车时间
        mEditText12 = (EditText) findViewById(R.id.selease_car_supply_activity_et12);
        mEditText12.setOnClickListener(this);
        //最早到货时间
        mEditText13 = (EditText) findViewById(R.id.selease_car_supply_activity_et13);
        mEditText13.setOnClickListener(this);
        //最晚到货时间
        mEditText14 = (EditText) findViewById(R.id.selease_car_supply_activity_et14);
        mEditText14.setOnClickListener(this);
        //运输费用
        mEditText15 = (EditText) findViewById(R.id.selease_car_supply_activity_et15);
        //发货人
        mEditText16 = (EditText) findViewById(R.id.selease_car_supply_activity_et16);

        //联系电话
        mEditText17 = (EditText) findViewById(R.id.selease_car_supply_activity_et17);
        mEditText17.setText(mUsername);
        //备注
        mEditText18 = (EditText) findViewById(R.id.selease_car_supply_activity_et18);


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
            case R.id.selease_car_supply_activity_iv1:
                finish();
                break;
            //跳转到我的发布页面
            case R.id.selease_car_supply_activity_tv1:
                startActivity(new Intent(Release_Car_Supply_Activity.this, My_release_Activity.class));

                break;
            case R.id.selease_car_supply_activity_linlay1:
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
            case R.id.selease_car_supply_activity_linlay3:
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
            case R.id.selease_car_supply_activity_linlay5:
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
            case R.id.selease_car_supply_activity_linlay7:
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
            //始发地
            case R.id.selease_car_supply_activity_et1:
                startActivityForResult(new Intent(Release_Car_Supply_Activity.this, Details_of_Origin_Activity.class), 0);
                break;
            //目的地
            case R.id.selease_car_supply_activity_et2:
                LogUtil.e("touch" + touch);
                startActivityForResult(new Intent(Release_Car_Supply_Activity.this, Details_of_Origin_Activity.class), 1);


                break;
            //货物品类
            case R.id.selease_car_supply_activity_et4:

                mChangeTypePopwindow = new ChangeTypePopwindow(Release_Car_Supply_Activity.this);
                mChangeTypePopwindow.setType("水产品");
                mChangeTypePopwindow.showAtLocation(mEditText4, Gravity.BOTTOM, 0, 0);
                mChangeTypePopwindow.setAddresskListener(new ChangeTypePopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type, int id) {
                        mEditText4.setText(type);
                        if ("水产品".equals(type)) {
                            c = 0;
                            return;
                        }
                        if ("速冻食品".equals(type)) {
                            c = 1;
                            return;
                        }
                        if ("果蔬".equals(type)) {
                            c = 2;
                            return;
                        }
                        if ("肉类".equals(type)) {
                            c = 3;
                            return;
                        }
                        if ("水产品".equals(type)) {
                            c = 4;
                            return;
                        }
                        if ("其他".equals(type)) {
                            c = 5;
                            return;
                        }


                    }


                });


                break;
            //货物类型
            case R.id.selease_car_supply_activity_et5:
                mCargo_type_popwindow = new Cargo_type_Popwindow(Release_Car_Supply_Activity.this);

                mCargo_type_popwindow.setType("常温");
                mCargo_type_popwindow.showAtLocation(mEditText5, Gravity.BOTTOM, 0, 0);
                mCargo_type_popwindow.setAddresskListener(new Cargo_type_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {
                        mEditText5.setText(type);
                    }
                });

                break;
            //需要车长

            case R.id.selease_car_supply_activity_et7:
                mDrivers_need_popwindow = new Drivers_need_Popwindow(Release_Car_Supply_Activity.this);

                mDrivers_need_popwindow.setType("4.2米以下");
                mDrivers_need_popwindow.showAtLocation(mEditText7, Gravity.BOTTOM, 0, 0);
                mDrivers_need_popwindow.setAddresskListener(new Drivers_need_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {

                        mEditText7.setText(type);


                    }
                });

                break;
            case R.id.selease_car_supply_activity_et8:

                mNeed_models_popwindow = new Need_models_Popwindow(Release_Car_Supply_Activity.this);
                mNeed_models_popwindow.setType("普通货车");
                mNeed_models_popwindow.showAtLocation(mEditText8, Gravity.BOTTOM, 0, 0);
                mNeed_models_popwindow.setAddresskListener(new Need_models_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {
                        mEditText8.setText(type);
                        if ("普通货车".equals(mEditText8.getText().toString().trim())) {
                            b = 1;
                            return;
                        }
                        if ("冷藏车".equals(mEditText8.getText().toString().trim())) {
                            b = 2;
                            return;
                        }
                        if ("平板车".equals(mEditText8.getText().toString().trim())) {
                            b = 3;
                            return;
                        }
                        if ("常温箱式车".equals(mEditText8.getText().toString().trim())) {
                            b = 4;
                            return;
                        }
                        if ("集装箱车".equals(mEditText8.getText().toString().trim())) {
                            b = 5;
                            return;
                        }
                        if ("高拦车".equals(mEditText8.getText().toString().trim())) {
                            b = 6;
                            return;
                        }
                    }
                });

                break;
            //最低温
            case R.id.selease_car_supply_activity_et9:

                if ("".equals(mEditText10.getText().toString().trim())) {
                    mRequired_temperature_popwindow1 = new Required_temperature_Popwindow(Release_Car_Supply_Activity.this);
                    mRequired_temperature_popwindow1.setType("-50");
                    mRequired_temperature_popwindow1.showAtLocation(mEditText9, Gravity.BOTTOM, 0, 0);
                    mRequired_temperature_popwindow1.setAddresskListener(new Required_temperature_Popwindow.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText9.setText(type);
                            mLowFloat = Float.parseFloat(type);
                        }
                    });

                } else {
                    String trim = mEditText10.getText().toString().trim();
                    int i = Integer.parseInt(trim);
                    Required_temperature_Popwindow_One required_temperature_popwindow_one = new Required_temperature_Popwindow_One(Release_Car_Supply_Activity.this, i);
                    required_temperature_popwindow_one.setType("-50");
                    required_temperature_popwindow_one.showAtLocation(mEditText9, Gravity.BOTTOM, 0, 0);
                    required_temperature_popwindow_one.setAddresskListener(new Required_temperature_Popwindow_One.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText9.setText(type);
                            mLowFloat = Float.parseFloat(type);
                        }
                    });
                }


                break;
            //最高温
            case R.id.selease_car_supply_activity_et10:

                if ("".equals(mEditText9.getText().toString().trim())) {
                    mRequired_temperature_popwindow1 = new Required_temperature_Popwindow(Release_Car_Supply_Activity.this);
                    mRequired_temperature_popwindow1.setType("-50");
                    mRequired_temperature_popwindow1.showAtLocation(mEditText10, Gravity.BOTTOM, 0, 0);
                    mRequired_temperature_popwindow1.setAddresskListener(new Required_temperature_Popwindow.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText10.setText(type);
                            mheigthFloat = Float.parseFloat(type);
                        }
                    });

                } else {

                    String trim = mEditText9.getText().toString().trim();
                    int i = Integer.parseInt(trim);
                    Required_temperature_Popwindow_Two required_temperature_popwindow_Two = new Required_temperature_Popwindow_Two(Release_Car_Supply_Activity.this, i);
                    required_temperature_popwindow_Two.setType(trim);
                    required_temperature_popwindow_Two.showAtLocation(mEditText10, Gravity.BOTTOM, 0, 0);
                    required_temperature_popwindow_Two.setAddresskListener(new Required_temperature_Popwindow_Two.OnAddressCListener() {
                        @Override
                        public void onClick(String type) {
                            mEditText10.setText(type);
                            mheigthFloat = Float.parseFloat(type);
                        }
                    });
                }


                break;
            //最早装车时间
            case R.id.selease_car_supply_activity_et11:

                pvCustomTime.show(); //弹出自定义时间选择器
                break;
            //最晚装车时间
            case R.id.selease_car_supply_activity_et12:

                pvCustomTime2.show(); //弹出自定义时间选择器
                break;
            //最早到货时间
            case R.id.selease_car_supply_activity_et13:

                pvCustomTime3.show(); //弹出自定义时间选择器
                break;
            //最晚到货时间
            case R.id.selease_car_supply_activity_et14:

                pvCustomTime4.show(); //弹出自定义时间选择器
                break;
            //提交按钮
            case R.id.selease_car_supply_activity_btn1:


                setunit();
                payment();
                if (TextUtils.isEmpty(mEditText1.getText().toString().trim())) {
                    DialogUtils.showPopMsgInHandleThread(Release_Car_Supply_Activity.this, mHandler, "请输入始发地");

                    return;
                }
                if (TextUtils.isEmpty(mEditText2.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入目的地");
                    return;
                }
                if (TextUtils.isEmpty(mEditText3.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入货物名称");
                    return;
                }
                if (TextUtils.isEmpty(mEditText4.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入货物品类");
                    return;
                }
                if (TextUtils.isEmpty(mEditText5.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入货物类型");
                    return;
                }
                if (TextUtils.isEmpty(mEditText6.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入货物规格");
                    return;
                }

                if (TextUtils.isEmpty(mEditText7.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入车长");
                    return;
                }
                if (TextUtils.isEmpty(mEditText8.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入车辆类型");
                    return;
                }

                if (TextUtils.isEmpty(mEditText9.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入最低温");
                    return;
                }
                if (TextUtils.isEmpty(mEditText10.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入最高温");
                    return;
                }
                if (TextUtils.isEmpty(mEditText11.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入最早装车时间");
                    return;
                }
                if (TextUtils.isEmpty(mEditText12.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入最晚装车时间");
                    return;
                }
                if (TextUtils.isEmpty(mEditText13.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入最早到货时间");
                    return;
                }
                if (TextUtils.isEmpty(mEditText14.getText().toString().trim())) {
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入最晚到货时间");
                    return;
                }
// TODO: 2017/6/8
                if (TextUtils.isEmpty(mEditText15.getText().toString())) {
                    if (touch == 0) {
                        NToast.shortToast(Release_Car_Supply_Activity.this, "请输入运输费用");
                        return;
                    }
                    if (touch == 1) {
                        mEditText15.setText("0");
                    }

                }
                if(TextUtils.isEmpty(mEditText16.getText().toString().trim())){
                    NToast.shortToast(Release_Car_Supply_Activity.this, "请输入发货人");
                    return;


                }

//                if (TextUtils.isEmpty(mTrim2)) {
//                    NToast.shortToast(Release_Car_Supply_Activity.this, "请选择支付方式");
//                    return;
//                }
//
//
//                if (TextUtils.isEmpty(mTrim1)) {
//                    NToast.shortToast(Release_Car_Supply_Activity.this, "请选择是否需要发票");
//                    return;
//                }


                new Thread() {
                    @Override
                    public void run() {

                        OkHttpClient okHttpClient = new OkHttpClient();
                        FormBody formBody = new FormBody.Builder()
                                //始发地
                                .add("fromaddr", mEditText1.getText().toString().trim())
                                //始发地省
                                .add("provincef", mStartprovince)
                                //始发地市
                                .add("cityf", mStartcity)
                                //始发地区
                                .add("districtf", mStartarea)
//                        始发地详
                                .add("streetf", mDetailed_address)
                                //目的地
                                .add("toaddr", mEditText2.getText().toString().trim())
                                //目的地省ProvinceT
                                .add("provincet", mEndprovince.trim())
                                //目的地市
                                .add("cityt", mEndcity.trim())
                                //目的地区
                                .add("districtt", mEndarea)
                                //目的地详
                                .add("streett", mEnd_detailed_address)
//                        //货物的名称
                                .add("name", mEditText3.getText().toString().trim())
                                //货物品类
                                .add("categoryname", mEditText4.getText().toString().trim())
                                //货物类型的名称
                                .add("typename", mEditText5.getText().toString().trim())
                                //货物品类的id
                                .add("typeid", "" + c)
                                //货物规格
                                .add("spec", mEditText6.getText().toString().trim())
                                //货物单位
                                .add("unit", m3)
                                //车长
                                .add("carlength", mEditText7.getText().toString().trim())
//车的类型的id
                                .add("cartypeid", b + "")
                                //车的类型
                                .add("cartypename", mEditText8.getText().toString().trim())
                                //最低温
                                .add("tempmin", "" + mLowFloat)
                                //最高温
                                .add("tempmax", "" + mheigthFloat)
                                //最早装车时间
                                .add("loaddatestart", "" + mEditText11.getText().toString().trim())
                                //最晚装车时间
                                .add("loaddateend", "" + mEditText12.getText().toString().trim())
                                //最早到货时间
                                .add("arrivaldatestart", "" + mEditText13.getText().toString().trim())
                                //最晚到货时间
                                .add("arrivaldateend", "" + mEditText14.getText().toString().trim())
                                //运输费用的类型
                                .add("costtype", touch + "")

                                //价格
                                .add("price", mEditText15.getText().toString())
                                .add("payment", "" + pay)
//                                //发票
//                                .add("isneedbill", mInt + "")
                                //发货人
                                .add("senduser", mEditText16.getText().toString().trim())
                                //联系方式
                                .add("sendtel", mUsername)
                                .add("remark", mEditText18.getText().toString())
                                .build();

                        String format = String.format(KeyPath.Path.head + KeyPath.Path.for_car_library, mUsername, mUserPass);


                        Request request = new Request.Builder()
                                .url(format)
                                .post(formBody)
                                .build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                NToast.shortToast(Release_Car_Supply_Activity.this, "数据获取失败，请重新尝试");

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                                if (string != null) {
                                    try {
                                        progressDlgEx.simpleModeShowHandleThread();
                                        JSONObject jsonObject = new JSONObject(string);
                                        int status = jsonObject.getInt("status");
                                        if (status == 1) {
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {

                                                    NToast.shortToast(Release_Car_Supply_Activity.this, "发布成功");

                                                    //跳转到发布界面
                                                    startActivity(new Intent(Release_Car_Supply_Activity.this, My_release_Activity.class));
                                                    finish();
                                                }
                                            });

                                        } else {

                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    NToast.shortToast(Release_Car_Supply_Activity.this, "用户名密码不正确");

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
            case 1:

                if (resultCode == RESULT_OK) {
                    mKey1 = data.getStringExtra("key");
                    //目的地的省份
                    mEndprovince = data.getStringExtra("province");
                    //目的地的城市
                    mEndcity = data.getStringExtra("city");
                    //目的地的地区
                    mEndarea = data.getStringExtra("area");
                    //街道
                    mEnd_detailed_address = data.getStringExtra("detailed_address");

                    //详细地址
                    mEditText2.setText(mKey1);


                }


                break;

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.selease_car_supply_activity_rg1:
            case R.id.selease_car_supply_activity_rg2:
                RadioButton viewById = (RadioButton) findViewById(i);
                mTrim = viewById.getText().toString().trim();
                break;
            case R.id.selease_car_supply_activity_rg3:


                mRadioButton3.setBackground(getResources().getDrawable(R.drawable.button_back));
                mRadioButton3.setTextColor(Color.parseColor("#ffffff"));
                mLinearLayout9.setVisibility(View.VISIBLE);
                mRadioButton4.setBackground(getResources().getDrawable(R.drawable.button_back2));

                mRadioButton4.setTextColor(Color.parseColor("#1a26c7"));
                touch = 0;

                break;
            case R.id.selease_car_supply_activity_rg4:
                mRadioButton4.setBackground(getResources().getDrawable(R.drawable.button_back3));


                mRadioButton4.setTextColor(Color.parseColor("#ffffff"));
                mRadioButton3.setBackground(getResources().getDrawable(R.drawable.button_back1));

                mRadioButton3.setTextColor(Color.parseColor("#1a26c7"));
                mLinearLayout9.setVisibility(View.GONE);
                touch = 1;
                setad();
                break;
//            case R.id.selease_car_supply_activity_rg5:
//            case R.id.selease_car_supply_activity_rg6:
//                RadioButton fapiao = (RadioButton) findViewById(i);
//                mTrim1 = fapiao.getText().toString().trim();
//                if ("是".equals(mTrim1)) {
//                    mInt = 0;
//                } else {
//                    mInt = 1;
//                }

            //获取支付方式
            case R.id.selease_car_supply_activity_rg8:
                RadioButton pay_method = (RadioButton) findViewById(i);
                mTrim2 = pay_method.getText().toString().trim();


                break;

        }
    }

    // TODO: 2017/6/8
    //判断运输费用价格的方法
    private String setad() {

        if (touch == 0) {
            String trim = mEditText15.getText().toString();
            money = trim;
        } else {

            money = "100";
        }

        return money;
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

        startDate.set(Integer.parseInt(startcreateDate2), Integer.parseInt(startcreateDate21) - 1, Integer.parseInt(startcreateDate22));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2065, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mEditText11.setText(getTime(date));
            }
        })

                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色

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

    private void initCustomTimePicker2() {

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
        startDate.set(Integer.parseInt(startcreateDate2), Integer.parseInt(startcreateDate21) - 1, Integer.parseInt(startcreateDate22));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2065, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime2 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                mEditText12.setText(getTime2(date2));
            }
        })

                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
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
                                pvCustomTime2.returnData();
                                pvCustomTime2.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime2.dismiss();
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

    private String getTime2(Date date2) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date2);
    }

    private void initCustomTimePicker3() {

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
        startDate.set(Integer.parseInt(startcreateDate2), Integer.parseInt(startcreateDate21) - 1, Integer.parseInt(startcreateDate22));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2065, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime3 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date3, View v) {//选中事件回调
                mEditText13.setText(getTime3(date3));
            }
        })

                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
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
                                pvCustomTime3.returnData();
                                pvCustomTime3.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime3.dismiss();
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

    private String getTime3(Date date3) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date3);
    }

    private void initCustomTimePicker4() {

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
        pvCustomTime4 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date4, View v) {//选中事件回调
                mEditText14.setText(getTime4(date4));
            }
        })

                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
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
                                pvCustomTime4.returnData();
                                pvCustomTime4.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime4.dismiss();
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

    private String getTime4(Date date4) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date4);
    }

}
