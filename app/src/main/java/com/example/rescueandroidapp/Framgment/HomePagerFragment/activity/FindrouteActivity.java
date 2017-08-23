package com.example.rescueandroidapp.Framgment.HomePagerFragment.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.data.BaseDataService;
import com.rescueandroid.data.Data;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by 冯志强 on 2017/6/5 0005.
 * 找路线
 */

public class FindrouteActivity extends Activity implements View.OnClickListener{

    private ProgressDialogEx progressDlgEx;
    protected Handler mHandler = new Handler();
    private LayoutInflater factory;
    private ImageView findrount_back;
    private LinearLayout findroute_layout;
    private TextView findroute_name,findroute_tv1 ,findroute_tv2,findroute_time,findroute_cartype,findroute_carline,
            findroute_tel,findroute_route,findroute_route2;
    private ImageView findroute_img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.findroute);

        progressDlgEx = new ProgressDialogEx(FindrouteActivity.this, mHandler);
        factory = LayoutInflater.from(this);

        findrount_back = (ImageView) findViewById(R.id.findrount_back);
        findrount_back.setOnClickListener(this);
        findroute_layout = (LinearLayout) findViewById(R.id.findroute_layout);

        show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findrount_back:

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
                    final JSONObject jsobj = BaseDataService.routeinfo(username,userPass);
                    String code = jsobj.getString("status");
                    if (code.equals("1")) {
                        JSONArray results = jsobj.getJSONArray("data");
                        final List listt = JsonUtils.parseJsonArray(results);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                findroute_layout.removeAllViews();
                                for (int q = 0; q < listt.size(); q++) {
                                    Map map = (Map) listt.get(q);
                                    LinearLayout convertView = (LinearLayout) factory.inflate(R.layout.findroute_layout, null);

                                    findroute_name = (TextView)  convertView.findViewById(R.id.findroute_name);

                                    findroute_tv1 = (TextView)  convertView.findViewById(R.id.findroute_tv1);

                                    findroute_tv2 = (TextView)  convertView.findViewById(R.id.findroute_tv2);

                                    findroute_time = (TextView)  convertView.findViewById(R.id.findroute_time);

                                    findroute_cartype = (TextView)  convertView.findViewById(R.id.findroute_cartype);

                                    findroute_carline = (TextView)  convertView.findViewById(R.id.findroute_carline);

                                    findroute_tel = (TextView)  convertView.findViewById(R.id.findroute_tel);

                                    findroute_route = (TextView)  convertView.findViewById(R.id.findroute_route);

                                    findroute_route2 = (TextView)  convertView.findViewById(R.id.findroute_route2);

                                    findroute_img = (ImageView) convertView.findViewById(R.id.findroute_img);

                                    String caruser = map.get("caruser").toString();

                                    if(caruser == username){
                                        findroute_tel.setVisibility(View.GONE);
                                        findroute_route.setVisibility(View.GONE);
                                        findroute_route2.setVisibility(View.VISIBLE);
                                    }else{
                                        findroute_tel.setVisibility(View.VISIBLE);
                                        findroute_route.setVisibility(View.VISIBLE);
                                        findroute_route2.setVisibility(View.GONE);
                                    }

                                    final int answerid = new Integer(map.get("id").toString());
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

                                    if(cartypename.equals("冷藏车")){
                                        findroute_img.setVisibility(View.VISIBLE);
                                    }else{
                                        findroute_img.setVisibility(View.GONE);
                                    }

                                    String cardesc = map.get("cardesc").toString();
                                    final String cartel = map.get("cartel").toString();

                                    findroute_name.setText(caruser);
                                    findroute_tv1.setText(provincef+cityf+districtf);
                                    findroute_tv2.setText(provincet+cityt+districtt);
                                    findroute_time.setText(startcreateDate);
                                    findroute_cartype.setText(cartypename);
                                    findroute_carline.setText(cardesc);


                                    findroute_layout.addView(convertView);
                                    //拨打电话
                                    findroute_tel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder builde  = new AlertDialog.Builder(FindrouteActivity.this);
                                            builde.setMessage(cartel) ;
                                            builde.setPositiveButton("取消",new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            builde.setNegativeButton("呼叫", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    //用intent启动拨打电话
                                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ cartel));
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
                                            Intent intent = new Intent();
                                            intent.putExtra("answerid",answerid);
                                            intent.setClass(FindrouteActivity.this, MyrouteActivity.class);
                                            startActivity(intent);
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
