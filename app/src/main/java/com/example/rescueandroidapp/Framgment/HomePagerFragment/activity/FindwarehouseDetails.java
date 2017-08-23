package com.example.rescueandroidapp.Framgment.HomePagerFragment.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by 冯志强 on 2017/6/9 0009.
 * 仓库详情
 */

public class FindwarehouseDetails extends Activity implements View.OnClickListener{

    private ImageView mImageView1;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6, mTextView7, mTextView8, mTextView9;
    private int id;
    private ProgressDialogEx progressDlgEx;
    protected Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.findhouse_details);

        progressDlgEx = new ProgressDialogEx(FindwarehouseDetails.this, mHandler);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        mImageView1 = (ImageView) findViewById(R.id.details_page_activity_iv1);
        mImageView1.setOnClickListener(this);
        //公司
        mTextView1 = (TextView) findViewById(R.id.details_page_activity_tv1);
        //角色
        mTextView2 = (TextView) findViewById(R.id.details_page_activity_tv2);
        //时间
        mTextView3 = (TextView) findViewById(R.id.details_page_activity_tv3);
        //仓库所在地
        mTextView4 = (TextView) findViewById(R.id.details_page_activity_tv4);
        //仓库名
        mTextView5 = (TextView) findViewById(R.id.details_page_activity_tv5);
        //仓库类型
        mTextView6 = (TextView) findViewById(R.id.details_page_activity_tv6);
        //仓库面积
        mTextView7 = (TextView) findViewById(R.id.details_page_activity_tv7);
        //库存容量
        mTextView8 = (TextView) findViewById(R.id.details_page_activity_tv8);
        //备注
        mTextView9 = (TextView) findViewById(R.id.details_page_activity_tv9);

        show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_page_activity_iv1:

                finish();
                break;

        }
    }

    private void show(){
        final String username = SharedPreferencesUtils.getString(this, "userName", "");// 获取用户名
        final String userPass = SharedPreferencesUtils.getString(this, "userPass", "");// 获取密码
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            public void run() {
                try {
                    progressDlgEx.simpleModeShowHandleThread();
                    final JSONObject jsobj = BaseDataService.coldstore(username,userPass);
                    String code = jsobj.getString("status");
                    if (code.equals("1")) {
                        JSONArray results = jsobj.getJSONArray("data");
                        final List listt = JsonUtils.parseJsonArray(results);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                for (int q = 0; q < listt.size(); q++) {
                                    Map map = (Map) listt.get(q);


                                    int mid = new Integer(map.get("id").toString());
                                    if(mid == id){
                                        String name = map.get("name").toString();
                                        String province = map.get("province").toString();
                                        String city = map.get("city").toString();
                                        String district = map.get("district").toString();
                                        String location = map.get("location").toString();
                                        String PutDate = map.get("createon").toString();
                                        String remark = map.get("remark").toString();

                                        //1.阴凉库2.冷藏库3.常温库
                                        final int type = new Integer(map.get("type").toString());
                                        if(type == 1){
                                            mTextView5.setText("阴凉库");
                                            mTextView6.setText("阴凉库");
                                        }
                                        if(type == 2){
                                            mTextView5.setText("冷藏库");
                                            mTextView6.setText("阴凉库");
                                        }
                                        if(type == 3){
                                            mTextView5.setText("常温库");
                                            mTextView6.setText("阴凉库");
                                        }

                                        int capacity = new Integer(map.get("capacity").toString());

                                        // 截取时间
                                        String[] starTimestr = PutDate.split("T");
                                        String startcreateDate = starTimestr[0];

                                        final String cartel = map.get("linktel").toString();

                                        mTextView1.setText(name);
                                        mTextView3.setText(startcreateDate);
                                        mTextView4.setText(location);
                                        mTextView7.setText(capacity+"平方");
                                        mTextView8.setText(capacity+"平方");
                                        mTextView9.setText(remark);

                                    }
                                }
                            }
                        });
                    }
                } catch (NetConnectionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    progressDlgEx.closeHandleThread();
                }
            }
        }).start();

    }
}
