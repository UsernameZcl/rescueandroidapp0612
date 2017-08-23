package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.config.Text;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.data.Data;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.ui.DialogUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by ZCL on 2017/5/9
 */

public class LoginActivity extends MyBaseActivity implements View.OnClickListener {
    private TextView mTextView1 ,mTextView2;
    private EditText mEditText1,mEditText2;
    private Button mButton;
    private String mTrim1;
    private String mTrim2;
    private Handler mHandler =new Handler();
    private ImageView mImageView;
    private ProgressDialog mProgressDialog;
    private  String mString="";
    boolean eyeOpen = false;
    private ProgressDialogEx progressDlgEx;

    @Override
    public int setview() {
        return R.layout.login_activity;
    }
    @Override
    public void init() {

        progressDlgEx = new ProgressDialogEx(this, mHandler);
        //注册
        mTextView1= (TextView) findViewById(R.id.login_activity_tv1);
        mTextView1.setOnClickListener(this);
        //忘记密码
        mTextView2= (TextView) findViewById(R.id.login_activity_tv2);
        mTextView2.setOnClickListener(this);
        mEditText1= (EditText) findViewById(R.id.login_activity_et1);
        mEditText2= (EditText) findViewById(R.id.login_activity_et2);
        mButton= (Button) findViewById(R.id.login_activity_bt1);
        mButton.setOnClickListener(this);
        mImageView= (ImageView) findViewById(R.id.login_activity_iv1);
        mImageView.setOnClickListener(this);
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
        switch (view.getId()){
            case R.id.login_activity_tv1:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

                break;
            case R.id.login_activity_tv2:

                startActivity(new Intent(LoginActivity.this,Forget_password_Activity.class));

                break;
            case R.id.login_activity_iv1:

                if (eyeOpen) {
                    //密码
                    mEditText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mImageView.setImageResource(R.mipmap.eyes1);
                    eyeOpen = false;
                } else {
                    //明文
                    mEditText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mImageView.setImageResource(R.mipmap.eyes);
                    eyeOpen = true;
                }



                break;
            case R.id.login_activity_bt1:
                mTrim1 = mEditText1.getText().toString();
                mTrim2 = mEditText2.getText().toString();
                if (mTrim1.equals("")) {
                    DialogUtils.showPopMsgInHandleThread(LoginActivity.this, mHandler, "请输入手机号！");
                    return;
                }
                if (mTrim2.equals("")) {
                    DialogUtils.showPopMsgInHandleThread(LoginActivity.this, mHandler, "请输入密码！");
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            progressDlgEx.simpleModeShowHandleThread();
                            final JSONObject jsobj = BaseDataService.login(mTrim1, mTrim2);
                            String code = jsobj.getString("status");
                            if (code.equals("1")) {
                                final JSONObject result = jsobj.getJSONObject("data");
                                mHandler.post(new Runnable() {
                                    public void run() {
                                        SharedPreferencesUtils.put(LoginActivity.this, "userName", mTrim1);
                                        SharedPreferencesUtils.put(LoginActivity.this, "userPass", mTrim2);
                                        Map map = JsonUtils.parseJson(result);
                                        String name = map.get("username").toString();
                                        Data.getInstance().username = name;
                                        Data.getInstance().realname = map.get("realname").toString();
                                        Data.getInstance().roleid = map.get("roleid").toString();
                                        String photo = map.get("photo").toString();
                                        Data.getInstance().photo = photo;
                                        Data.getInstance().pwd = mTrim2;
                                        Data.getInstance().isLogin = true;
                                        LoginActivity.this.setResult(2);
                                        finish();
                                    }
                                });
                            } else {
                                DialogUtils.showPopMsgInHandleThread(LoginActivity.this, mHandler, "用户名或密码不正确！");
                                return;
                            }
                        } catch (NetConnectionException e) {
                            // TODO Auto-generated catch block
                            DialogUtils.showPopMsgInHandleThread(LoginActivity.this, mHandler, Text.NetConnectFault);
                            e.printStackTrace();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            DialogUtils.showPopMsgInHandleThread(LoginActivity.this, mHandler, Text.ParseFault);
                            e.printStackTrace();
                        } finally {
                            progressDlgEx.closeHandleThread();
                        }
                    }
                }).start();

        }
    }

}
