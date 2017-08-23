package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_route_activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.RegisterActivity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.Details_of_Origin_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.Release_Car_Supply_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Drivers_need_Popwindow;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.popwindow.Need_models_Popwindow;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.LogUtil;
import com.rescueandroid.utils.mytoast.NToast;
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
 * Created by ZCL on 2017/5/30.
 */
//z找路线的界面

public class Find_Route_Activity extends MyBaseActivity implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextView;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4, mEditText5, mEditText6;
    private Button mButton;
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
    private Need_models_Popwindow mNeed_models_popwindow;
    private Drivers_need_Popwindow mDrivers_need_popwindow;
    private  int b;
    private String mUsername;
    private String mUserPass;
    private Handler mHandler = new Handler();
    private ProgressDialogEx progressDlgEx;
    @Override
    public int setview() {
        return R.layout.find_route_activity;

    }

    @Override
    public void init() {

        progressDlgEx = new ProgressDialogEx(this, mHandler);
        // 获取用户名
        mUsername = SharedPreferencesUtils.getString(this, "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(this, "userPass", "");
        mImageView = (ImageView) findViewById(R.id.find_route_img);
        mImageView.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.find_route_activity_tv1);
        mTextView.setOnClickListener(this);
        //始发地
        mEditText1 = (EditText) findViewById(R.id.find_route_activity_et1);
        mEditText1.setOnClickListener(this);
        //目的地
        mEditText2 = (EditText) findViewById(R.id.find_route_activity_et2);
        mEditText2.setOnClickListener(this);
        //常用车型
        mEditText3 = (EditText) findViewById(R.id.find_route_activity_et3);
        mEditText3.setOnClickListener(this);
        //需要车长
        mEditText4 = (EditText) findViewById(R.id.find_route_activity_et4);
        mEditText4.setOnClickListener(this);
        //发货人
        mEditText5 = (EditText) findViewById(R.id.find_route_activity_et5);
        //联系电话
        mEditText6 = (EditText) findViewById(R.id.find_route_activity_et6);
        mEditText6.setText(mUsername);
        mButton = (Button) findViewById(R.id.route_activity_bt);
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
            //点击消失
            case R.id.find_route_img:
                finish();
                break;
            //跳转到发布路线的界面
            case R.id.find_route_activity_tv1:
                startActivity(new Intent(Find_Route_Activity.this, Route_Release_Activity.class));

                break;
            //始发地
            case R.id.find_route_activity_et1:
                startActivityForResult(new Intent(Find_Route_Activity.this, Details_of_Origin_Activity.class), 0);
                break;
            //目的地
            case R.id.find_route_activity_et2:
                startActivityForResult(new Intent(Find_Route_Activity.this, Details_of_Origin_Activity.class), 1);
                break;
            //常用车型
            case R.id.find_route_activity_et3:
                mNeed_models_popwindow = new Need_models_Popwindow(Find_Route_Activity.this);
                mNeed_models_popwindow.setType("普通货车");
                mNeed_models_popwindow.showAtLocation(mEditText3, Gravity.BOTTOM, 0, 0);
                mNeed_models_popwindow.setAddresskListener(new Need_models_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {

                        mEditText3.setText(type);
                        if ("普通货车".equals(mEditText3.getText().toString().trim())) {
                            b = 1;
                            return;
                        }
                        if ("冷藏车".equals(mEditText3.getText().toString().trim())) {
                            b = 2;
                            return;
                        }
                        if ("平板车".equals(mEditText3.getText().toString().trim())) {
                            b = 3;
                            return;
                        }
                        if ("常温箱式车".equals(mEditText3.getText().toString().trim())) {
                            b = 4;
                            return;
                        }
                        if ("集装箱车".equals(mEditText3.getText().toString().trim())) {
                            b = 5;
                            return;
                        }
                        if ("高拦车".equals(mEditText3.getText().toString().trim())) {
                            b = 6;
                            return;
                        }
                    }
                });

                break;
            //需要车长
            case R.id.find_route_activity_et4:
                mDrivers_need_popwindow = new Drivers_need_Popwindow(Find_Route_Activity.this);

                mDrivers_need_popwindow.setType("4.2米以下");
                mDrivers_need_popwindow.showAtLocation(mEditText4, Gravity.BOTTOM, 0, 0);
                mDrivers_need_popwindow.setAddresskListener(new Drivers_need_Popwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String type) {

                        mEditText4.setText(type);

                    }
                });

                break;
            //提交的按钮
            case R.id.route_activity_bt:
                if (TextUtils.isEmpty(mEditText1.getText().toString().trim())) {
                    NToast.shortToast(Find_Route_Activity.this, "请选择始发地");
                    return;

                }
                if (TextUtils.isEmpty(mEditText2.getText().toString().trim())) {
                    NToast.shortToast(Find_Route_Activity.this, "请选择目的地");
                    return;

                }
                if (TextUtils.isEmpty(mEditText3.getText().toString().trim())) {
                    NToast.shortToast(Find_Route_Activity.this, "请选择车型");
                    return;

                }
                if (TextUtils.isEmpty(mEditText4.getText().toString().trim())) {
                    NToast.shortToast(Find_Route_Activity.this, "请选择车长");
                    return;

                }
                if (TextUtils.isEmpty(mEditText5.getText().toString().trim())) {
                    NToast.shortToast(Find_Route_Activity.this, "请输入发货人");
                    return;

                }
                if (TextUtils.isEmpty(mEditText6.getText().toString().trim())) {
                    NToast.shortToast(Find_Route_Activity.this, "请输入联系电话");
                    return;
                }

                new Thread(){
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
                                //始发地街
                                .add("districtf", mStartarea)
                                //始发地详
                                .add("streetf", mDetailed_address)
                                //目的地
                                .add("toaddr", mEditText2.getText().toString().trim())
                                //目的地省ProvinceT
                                .add("provincet", mEndprovince.trim())
                                //目的地市
                                .add("cityt", mEndcity.trim())
                                //目的地区
                                .add("districtt", mEndarea)
                                //目的地街
                                .add("streett", mEnd_detailed_address)

                                //车长
                                .add("cardesc", mEditText4.getText().toString().trim())
                                //车的类型的id
                                .add("cartypeid",  ""+b)
                                //车的类型
                                .add("cartypename", mEditText3.getText().toString().trim())

                                //发货人
                                .add("caruser", mEditText5.getText().toString().trim())
                                //联系方式
                                .add("cartel", mEditText6.getText().toString().trim())

                                .build();

                        String format = String.format(KeyPath.Path.head + KeyPath.Path.findcar_route, mUsername, mUserPass);


                        Request request = new Request.Builder()
                                .url(format)
                                .post(formBody)
                                .build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                                LogUtil.e(string);
                                try {
                                    progressDlgEx.simpleModeShowHandleThread();
                                    JSONObject jsonObject = new JSONObject(string);
                                    int status = jsonObject.getInt("status");
                                    if (status == 1) {
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                NToast.shortToast(Find_Route_Activity.this, "发布成功");
                                                finish();
                                            }
                                        });
                                    } else {
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                NToast.shortToast(Find_Route_Activity.this, "发布失败，请重新操作");
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    progressDlgEx.closeHandleThread();
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
}
