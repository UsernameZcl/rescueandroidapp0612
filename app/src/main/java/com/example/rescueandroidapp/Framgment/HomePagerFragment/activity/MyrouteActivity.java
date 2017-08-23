package com.example.rescueandroidapp.Framgment.HomePagerFragment.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
 * Created by 冯志强 on 2017/6/5 0005.
 * 选择路线
 */

public class MyrouteActivity extends Activity implements View.OnClickListener {

    private ProgressDialogEx progressDlgEx;
    protected Handler mHandler = new Handler();
    private LayoutInflater factory;
    private ImageView findrount_back;
    private LinearLayout findroute_layout;
    private TextView findroute_name, findroute_tv1, findroute_tv2, findroute_time, findroute_cartype, findroute_carline,
            findroute_tel, findroute_route, car_adapter_tv4, car_adapter_tv5, car_adapter_tv6;
    private String str = "";
    private String stt = "";
    private String cartel;
    private int answerid;
    private String mUsername;
    private String mUserPass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.myroute);
        Intent getIntent = getIntent();
        answerid = getIntent.getIntExtra("answerid",answerid);

        progressDlgEx = new ProgressDialogEx(MyrouteActivity.this, mHandler);
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

        // 获取用户名
        mUsername = SharedPreferencesUtils.getString(this, "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(this, "userPass", "");
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            public void run() {
                try {
                    progressDlgEx.simpleModeShowHandleThread();
                    final JSONObject jsobj = BaseDataService.findcarinfo(mUsername, mUserPass, 0);
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
                                    LinearLayout view = (LinearLayout) factory.inflate(R.layout.layout_findroute, null);

                                    findroute_tv1 = (TextView) view.findViewById(R.id.car_adapter_tv1);
                                    findroute_tv2 = (TextView) view.findViewById(R.id.car_adapter_tv2);
                                    findroute_time = (TextView) view.findViewById(R.id.car_adapter_tv3);
                                    car_adapter_tv4 = (TextView) view.findViewById(R.id.car_adapter_tv4);
                                    car_adapter_tv5 = (TextView) view.findViewById(R.id.car_adapter_tv5);
                                    car_adapter_tv6 = (TextView) view.findViewById(R.id.car_adapter_tv6);
                                    findroute_tel = (TextView) view.findViewById(R.id.findroute_tel);

                                    findroute_route = (TextView) view.findViewById(R.id.findroute_route);

                                    final int id = new Integer(map.get("id").toString());

                                    String provincef = map.get("provincef").toString();
                                    String cityf = map.get("cityf").toString();
                                    String districtf = map.get("districtf").toString();

                                    String provincet = map.get("provincet").toString();
                                    String cityt = map.get("cityt").toString();
                                    String districtt = map.get("districtt").toString();

                                    String modifiedon = map.get("modifiedon").toString();
                                    // 截取时间
                                    String[] modifiedons = modifiedon.split("T");
                                    String modified = modifiedons[0];
                                    //货物的名称
                                    String name = map.get("name").toString();
                                    //货物规格
                                    String spec = map.get("spec").toString();
                                    //货物单位
                                    String unit = map.get("unit").toString();
                                    //车长
                                    String carlength = map.get("carlength").toString();
                                    //车的类型
                                    String cartypename = map.get("cartypename").toString();
                                    Float price = new Float(map.get("price").toString());
                                    if (price==0) {
                                        str = "电话联系";
                                    } else {
                                        str = "￥" + price;
                                    }
                                    //支付类型
                                    String payment = map.get("payment").toString();
                                    if (payment.equals("1")) {
                                        stt = "现金到付";
                                    }

                                    //最早装车时间
                                    String loaddatestart = map.get("loaddatestart").toString();
                                    //最晚装车时间
                                    String loaddateend = map.get("loaddateend").toString();

                                    // 截取时间
                                    String[] starTimestr = loaddatestart.split("T");
                                    String startcreateDate = starTimestr[0];

                                    // 截取时间
                                    String[] endTimestr = loaddateend.split("T");
                                    String endtcreateDate = endTimestr[0];

                                    final String cartel = map.get("sendtel").toString();

                                    findroute_tv1.setText(provincef + cityf + districtf);
                                    findroute_tv2.setText(provincet + cityt + districtt);
                                    findroute_time.setText(modified);
                                    car_adapter_tv4.setText(cityf + "到" + cityt + "," + "有" + name + spec + unit + "," +
                                            "求" + carlength + "," + cartypename + "," + "价格" + str + "," + stt);
                                    car_adapter_tv5.setText(startcreateDate);
                                    car_adapter_tv6.setText(endtcreateDate);

                                    findroute_layout.addView(view);

                                    //拨打电话
                                    findroute_tel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder builde = new AlertDialog.Builder(MyrouteActivity.this);
                                            builde.setMessage(cartel);
                                            builde.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            builde.setNegativeButton("呼叫", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    //用intent启动拨打电话
                                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cartel));
                                                    startActivity(intent);
                                                }
                                            });
                                            builde.show();
                                        }
                                    });

                                    //选择路线
                                    findroute_route.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // TODO Auto-generated method stub
                                                    try {
                                                        progressDlgEx.simpleModeShowHandleThread();
                                                        final JSONObject jsobj = BaseDataService.askorderinfo(mUsername, mUserPass, 1, id,answerid);
                                                        String code = jsobj.getString("status");
                                                        if (code.equals("1")) {
                                                            DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, "选择路线成功！");
                                                            finish();
                                                        } else {
                                                            DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, "选择路线失败    ！");
                                                            return;
                                                        }
                                                    } catch (NetConnectionException e) {
                                                        // TODO Auto-generated catch block
                                                        DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, Text.NetConnectFault);
                                                        e.printStackTrace();
                                                    } catch (JSONException e) {
                                                        // TODO Auto-generated catch block
                                                        DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, Text.ParseFault);
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


//    private void show() {
//        final String username = SharedPreferencesUtils.getString(this, "userName", "");// 获取用户名
//        final String userPass = SharedPreferencesUtils.getString(this, "userPass", "");// 获取密码
//        // TODO Auto-generated method stub
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    progressDlgEx.simpleModeShowHandleThread();
//                    final JSONObject jsobj = BaseDataService.myrouteinfo(username,userPass);
//                    String code = jsobj.getString("status");
//                    if (code.equals("1")) {
//                        JSONArray results = jsobj.getJSONArray("data");
//                        final List listt = JsonUtils.parseJsonArray(results);
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//
//                                findroute_layout.removeAllViews();
//                                for (int q = 0; q < listt.size(); q++) {
//                                    Map map = (Map) listt.get(q);
//                                    LinearLayout convertView = (LinearLayout) factory.inflate(R.layout.layout_findroute, null);
//
//
//                                    findroute_tv1 = (TextView)  convertView.findViewById(R.id.findroute_tv1);
//
//                                    findroute_tv2 = (TextView)  convertView.findViewById(R.id.findroute_tv2);
//
//                                    findroute_time = (TextView)  convertView.findViewById(R.id.findroute_time);
//
//                                    findroute_cartype = (TextView)  convertView.findViewById(R.id.findroute_cartype);
//
//                                    findroute_carline = (TextView)  convertView.findViewById(R.id.findroute_carline);
//
//                                    findroute_tel = (TextView)  convertView.findViewById(R.id.findroute_tel);
//
//                                    findroute_route = (TextView)  convertView.findViewById(R.id.findroute_route);
//
//                                    final int id = new Integer(map.get("id").toString());
//
//                                    String provincef = map.get("provincef").toString();
//                                    String cityf = map.get("cityf").toString();
//                                    String districtf = map.get("districtf").toString();
//
//                                    String provincet = map.get("provincet").toString();
//                                    String cityt = map.get("cityt").toString();
//                                    String districtt = map.get("districtt").toString();
//                                    String denddate = map.get("denddate").toString();
//
//                                    // 截取时间
//                                    String[] starTimestr = denddate.split("T");
//                                    String startcreateDate = starTimestr[0];
//
//                                    String cartypename = map.get("cartypename").toString();
//                                    String cardesc = map.get("cardesc").toString();
//                                    final String cartel = map.get("cartel").toString();
//
//                                    findroute_tv1.setText(provincef+cityf+districtf);
//                                    findroute_tv2.setText(provincet+cityt+districtt);
//                                    findroute_time.setText(startcreateDate);
//                                    findroute_cartype.setText(cartypename);
//                                    findroute_carline.setText(cardesc);
//
//
//                                    findroute_layout.addView(convertView);
//
//                                    //拨打电话
//                                    findroute_tel.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            AlertDialog.Builder builde  = new AlertDialog.Builder(MyrouteActivity.this);
//                                            builde.setMessage(cartel) ;
//                                            builde.setPositiveButton("取消",new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    dialog.dismiss();
//                                                }
//                                            });
//                                            builde.setNegativeButton("呼叫", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    //用intent启动拨打电话
//                                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ cartel));
//                                                    startActivity(intent);
//                                                }
//                                            });
//                                            builde.show();
//                                        }
//                                    });
//
//                                    //选择路线
//                                    findroute_route.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//
//                                            new Thread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    // TODO Auto-generated method stub
//                                                    try {
//                                                        progressDlgEx.simpleModeShowHandleThread();
//                                                        final JSONObject jsobj = BaseDataService.askorderinfo(username, userPass,1,id);
//                                                        String code = jsobj.getString("status");
//                                                        if (code.equals("1")) {
//                                                            DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, "选择路线成功！");
//                                                            finish();
//                                                        } else {
//                                                            DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, "选择路线失败    ！");
//                                                            return;
//                                                        }
//                                                    } catch (NetConnectionException e) {
//                                                        // TODO Auto-generated catch block
//                                                        DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, Text.NetConnectFault);
//                                                        e.printStackTrace();
//                                                    } catch (JSONException e) {
//                                                        // TODO Auto-generated catch block
//                                                        DialogUtils.showPopMsgInHandleThread(MyrouteActivity.this, mHandler, Text.ParseFault);
//                                                        e.printStackTrace();
//                                                    } finally {
//                                                        progressDlgEx.closeHandleThread();
//                                                    }
//                                                }
//                                            }).start();
//                                        }
//                                    });
//
//                                }
//                            }
//                        });
//                    }
//                } catch (NetConnectionException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } finally {
//                    progressDlgEx.closeHandleThread();
//                }
//            }
//        }).start();
//
//    }
}
