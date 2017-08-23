package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import org.json.JSONException;

import org.json.JSONObject;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.data.Data;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.LogUtil;
import com.rescueandroid.utils.mytoast.NToast;
import com.rescueandroid.utils.ui.DialogUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 修改密码
 * @author 冯志强 on 2017/6/2
 */
public class ChangePwd extends Activity {

	private Handler mHandler = new Handler();
	private ProgressDialogEx progressDlgEx;
	private EditText etChangePwd_pwdOld, etChangePwd_pwd, etChangePwd_pwdNew;
	private String pwdOld, pwd, pwdNew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_pwd);

		progressDlgEx = new ProgressDialogEx(this, mHandler);

		etChangePwd_pwdOld = (EditText) this.findViewById(R.id.etChangePwd_pwdOld);
		etChangePwd_pwd = (EditText) this.findViewById(R.id.etChangePwd_pwd);
		etChangePwd_pwdNew = (EditText) this.findViewById(R.id.etChangePwd_pwdNew);

		// 返回
		ImageView ivChangePwd_back = (ImageView) this.findViewById(R.id.ivChangePwd_back);
		ivChangePwd_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// 保存
		TextView tvChangePwd_submit = (TextView) this.findViewById(R.id.tvChangePwd_submit);
		tvChangePwd_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						pwdOld = etChangePwd_pwdOld.getText().toString();
						if (pwdOld.equals("")) {
							DialogUtils.showPopMsgInHandleThread(ChangePwd.this, mHandler, "请输入旧密码！");
							return;
						}
						if (!(Data.getInstance().pwd).equals(pwdOld)) {
							DialogUtils.showPopMsgInHandleThread(ChangePwd.this, mHandler, "旧密码不正确！");
							return;
						}
						pwd = etChangePwd_pwd.getText().toString();
						if (pwd.equals("")) {
							DialogUtils.showPopMsgInHandleThread(ChangePwd.this, mHandler, "请输入新密码！");
							return;
						}
						pwdNew = etChangePwd_pwdNew.getText().toString();
						if (!pwdNew.equals(pwd)) {
							DialogUtils.showPopMsgInHandleThread(ChangePwd.this, mHandler, "密码输入不一致！");
							return;
						}

                        /**
                         * put方式提交数据
                         */

                        final String username = SharedPreferencesUtils.getString(ChangePwd.this, "userName", "");// 获取用户名
                        final String userPass = SharedPreferencesUtils.getString(ChangePwd.this, "userPass", "");// 获取密码
                        OkHttpClient okHttpClient = new OkHttpClient();
                        FormBody build = new FormBody.Builder()
                                .add("u", username)
                                .add("p", userPass)
                                .add("oldp", pwdOld)
                                .add("newp", pwdNew)
                                .build();
                        String format = String.format(KeyPath.Path.head + KeyPath.Path.changpwa, username, userPass,pwdOld,pwdNew);
                        Request build1 = new Request.Builder()
                                .url(format)
                                .put(build)
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
                                        progressDlgEx.simpleModeShowHandleThread();
                                        JSONObject jsonObject = new JSONObject(string);
                                        int status = jsonObject.getInt("status");
                                        if (status == 1) {
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    DialogUtils.showPopMsgInHandleThread(ChangePwd.this, mHandler, "修改成功！");
                                                    finish();
                                                    SharedPreferencesUtils.put(ChangePwd.this, "userPass", pwdNew);
                                                }
                                            });
                                        } else {
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    DialogUtils.showPopMsgInHandleThread(ChangePwd.this, mHandler, "请稍后重试！");
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
				}).start();
			}
		});
	}
}
