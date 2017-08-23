package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_route_activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.rescueandroidapp.Framgment.HomePagerFragment.activity.FindrouteActivity;
import com.example.rescueandroidapp.Framgment.HomePagerFragment.activity.MyrouteActivity;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.RegisterActivity;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.Set_up_Activity;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.google.gson.Gson;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.NToast;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/5/10.
 */

//线路发布的布局
public class Route_Release_Activity extends MyBaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG";
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mListView, route_realease_delete;
    private ProgressDialogEx progressDlgEx;
    protected Handler mHandler = new Handler();
    private LayoutInflater factory;
    private TextView route_realease_name, findroute_tv1, findroute_tv2, findroute_time, findroute_cartype, findroute_carline,
            route_realease_tel, findroute_route, findroute_route2;
    private int tag;
    private Dialog mDialog;
    private String mUsername;
    private String mUserPass;

    @Override
    public int setview() {
        return R.layout.route_release_activity;
    }

    @Override
    public void init() {

        progressDlgEx = new ProgressDialogEx(Route_Release_Activity.this, mHandler);
        factory = LayoutInflater.from(this);
        mImageView = (ImageView) findViewById(R.id.route_release_activity_iv1);
        mListView = (LinearLayout) findViewById(R.id.route_release_activity_lv1);
        mImageView.setOnClickListener(this);
        setbase();
    }

    @Override
    public void setbase() {

        // 获取用户名
        mUsername = SharedPreferencesUtils.getString(this, "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(this, "userPass", "");
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            public void run() {
                try {
                    progressDlgEx.simpleModeShowHandleThread();
                    final JSONObject jsobj = BaseDataService.myrouteinfo(mUsername, mUserPass);
                    String code = jsobj.getString("status");
                    if (code.equals("1")) {
                        JSONArray results = jsobj.getJSONArray("data");
                        final List listt = JsonUtils.parseJsonArray(results);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                mListView.removeAllViews();
                                for (int q = 0; q < listt.size(); q++) {
                                    Map map = (Map) listt.get(q);
                                    LinearLayout convertView = (LinearLayout) factory.inflate(R.layout.route_release_activity_adapter, null);

                                    route_realease_name = (TextView) convertView.findViewById(R.id.route_realease_name);

                                    route_realease_tel = (TextView) convertView.findViewById(R.id.route_realease_tel);

                                    findroute_tv1 = (TextView) convertView.findViewById(R.id.findroute_tv1);

                                    findroute_tv2 = (TextView) convertView.findViewById(R.id.findroute_tv2);

                                    findroute_time = (TextView) convertView.findViewById(R.id.findroute_time);

                                    findroute_cartype = (TextView) convertView.findViewById(R.id.findroute_cartype);

                                    findroute_carline = (TextView) convertView.findViewById(R.id.findroute_carline);

                                    route_realease_delete = (LinearLayout) convertView.findViewById(R.id.route_realease_delete);

                                    tag = new Integer(map.get("id").toString());

                                    String caruser = map.get("caruser").toString();
                                    String cartel = map.get("cartel").toString();

                                    String provincef = map.get("provincef").toString();
                                    String cityf = map.get("cityf").toString();
                                    String districtf = map.get("districtf").toString();

                                    String provincet = map.get("provincet").toString();
                                    String cityt = map.get("cityt").toString();
                                    String districtt = map.get("districtt").toString();
                                    String denddate = map.get("denddate").toString();

                                    // 截取时间
                                    String[] starTimestr = denddate.split("T");
                                    String startcreateDate = starTimestr[0];

                                    String cartypename = map.get("cartypename").toString();
                                    String cardesc = map.get("cardesc").toString();

                                    route_realease_name.setText(caruser);
                                    route_realease_tel.setText(cartel);
                                    findroute_tv1.setText(provincef + cityf + districtf);
                                    findroute_tv2.setText(provincet + cityt + districtt);
                                    findroute_time.setText(startcreateDate);
                                    findroute_cartype.setText(cartypename);
                                    findroute_carline.setText(cardesc);

                                    mListView.addView(convertView);
                                    //点击删除
                                    route_realease_delete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            mDialog = new Dialog(Route_Release_Activity.this, R.style.selectorDialog);
                                            mDialog.setContentView(R.layout.dialog);
                                            mDialog.setCancelable(false);

                                            mDialog.show();
                                            TextView mSubmit = (TextView) mDialog.findViewById(R.id.submit);
                                            TextView mCancel = (TextView) mDialog.findViewById(R.id.cancel);
                                            mSubmit.setOnClickListener(new View.OnClickListener() {

                                                @Override
                                                public void onClick(View view) {
                                                    String format = String.format(KeyPath.Path.head + KeyPath.Path.routedelete, tag, mUsername, mUserPass);
                                                    Request request = new Request.Builder()
                                                            .url(format)
                                                            .delete()
                                                            .build();
                                                    OkHttpClient okHttpClient = new OkHttpClient();
                                                    okHttpClient.newCall(request).enqueue(new Callback() {
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
                                                                                mListView.removeAllViews();
                                                                                setbase();
                                                                                mDialog.dismiss();
                                                                                NToast.shortToast(Route_Release_Activity.this, "删除成功！");
                                                                            }
                                                                        });
                                                                    } else {
                                                                        mHandler.post(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                NToast.shortToast(Route_Release_Activity.this, "删除失败！");
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
                                            });
                                            mCancel.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    mDialog.dismiss();
                                                }
                                            });
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

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.route_release_activity_iv1:
                finish();
                break;


        }
    }
}
