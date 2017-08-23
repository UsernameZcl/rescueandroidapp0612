package com.example.rescueandroidapp.Framgment.HomePagerFragment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.config.Text;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.ui.DialogUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by 冯志强 on 2017/6/9 0009.
 */

public class MyhouseAcitivity extends Activity implements View.OnClickListener {

    private ProgressDialogEx progressDlgEx;
    protected Handler mHandler = new Handler();
    private LayoutInflater factory;
    private ImageView findrount_back;
    private LinearLayout findroute_layout;
    private TextView library_adapter_tv1, library_adapter_tv2, library_adapter_tv3, library_adapter_tv4, library_adapter_tv5;
    private int answerid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.myhouse);

        Intent getIntent = getIntent();
        answerid = getIntent.getIntExtra("id", 0);

        progressDlgEx = new ProgressDialogEx(MyhouseAcitivity.this, mHandler);
        factory = LayoutInflater.from(this);

        findrount_back = (ImageView) findViewById(R.id.findrount_back);
        findrount_back.setOnClickListener(this);
        findroute_layout = (LinearLayout) findViewById(R.id.findroute_layout);
        show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findrount_back:

                finish();

                break;
        }
    }

    private void show() {

        final String username = SharedPreferencesUtils.getString(this, "userName", "");// 获取用户名
        final String userPass = SharedPreferencesUtils.getString(this, "userPass", "");// 获取密码
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            public void run() {
                try {
                    progressDlgEx.simpleModeShowHandleThread();
                    final JSONObject jsobj = BaseDataService.findstoreinfo(username, userPass, 0);
                    int code = jsobj.getInt("status");
                    if (code == 1) {
                        JSONArray results = jsobj.getJSONArray("data");
                        final List listt = JsonUtils.parseJsonArray(results);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                findroute_layout.removeAllViews();
                                for (int q = 0; q < listt.size(); q++) {
                                    Map map = (Map) listt.get(q);
                                    LinearLayout view = (LinearLayout) factory.inflate(R.layout.findhouse_layout, null);

                                    library_adapter_tv1 = (TextView) view.findViewById(R.id.library_adapter_tv1);
                                    library_adapter_tv2 = (TextView) view.findViewById(R.id.library_adapter_tv2);
                                    library_adapter_tv3 = (TextView) view.findViewById(R.id.library_adapter_tv3);
                                    library_adapter_tv4 = (TextView) view.findViewById(R.id.library_adapter_tv4);
                                    library_adapter_tv5 = (TextView) view.findViewById(R.id.library_adapter_tv5);


                                    final int id = new Integer(map.get("id").toString());

                                    String storeposition = map.get("storeposition").toString();
                                    //货物的名称
                                    String name = map.get("name").toString();
                                    //入库时间
                                    String putdate = map.get("putdate").toString();
                                    // 截取时间
                                    String[] starTimestr = putdate.split("T");
                                    String startcreateDate = starTimestr[0];
                                    //储存时间
                                    Float storedate = new Float(map.get("storedate").toString());

                                    library_adapter_tv1.setText(storeposition);
                                    library_adapter_tv2.setText(name);
                                    library_adapter_tv3.setText(startcreateDate);
                                    library_adapter_tv4.setText(storedate + "个月");

                                    findroute_layout.addView(view);


                                    //选择路线
                                    library_adapter_tv5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // TODO Auto-generated method stub
                                                    try {
                                                        progressDlgEx.simpleModeShowHandleThread();
                                                        final JSONObject jsobj = BaseDataService.askorderinfo(username, userPass, 3, id, answerid);
                                                        String code = jsobj.getString("status");
                                                        if (code.equals("1")) {
                                                            DialogUtils.showPopMsgInHandleThread(MyhouseAcitivity.this, mHandler, "选择路线成功！");
                                                            finish();
                                                        } else {
                                                            DialogUtils.showPopMsgInHandleThread(MyhouseAcitivity.this, mHandler, "选择路线失败    ！");
                                                            return;
                                                        }
                                                    } catch (NetConnectionException e) {
                                                        // TODO Auto-generated catch block
                                                        DialogUtils.showPopMsgInHandleThread(MyhouseAcitivity.this, mHandler, Text.NetConnectFault);
                                                        e.printStackTrace();
                                                    } catch (JSONException e) {
                                                        // TODO Auto-generated catch block
                                                        DialogUtils.showPopMsgInHandleThread(MyhouseAcitivity.this, mHandler, Text.ParseFault);
                                                        e.printStackTrace();
                                                    } finally {
                                                        progressDlgEx.closeHandleThread();
                                                    }
                                                }
                                            }).start();
                                        }
                                    });

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
