package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.Car_Details_Page_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen.Car_Seek_bean;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseAdapter;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.NToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/6/5.
 */

public class Find_Car_Adapter extends MyBaseAdapter<Car_Seek_bean.MyDate> {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private String str = "";
    private String stt = "";
    private MyHolder mMyHolder;
    private TextView submit;
    private TextView mSubmit;
    private TextView mCancel;
    private Dialog mDialog;
    private String mUserName;
    private String mUserPass;
    private Handler mHandler =new Handler();
    public Find_Car_Adapter( Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        // 获取用户名
        mUserName = SharedPreferencesUtils.getString(mContext, "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(mContext, "userPass", "");
    }

    @Override
    public View getview(final int position, View convertView, ViewGroup parent) {
        mMyHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.find_car_adapter, null, false);

            mMyHolder = new MyHolder(convertView);
            convertView.setTag(mMyHolder);

        } else {
            mMyHolder = (MyHolder) convertView.getTag();
        }

        final Car_Seek_bean.MyDate myDate = list.get(position);
        //发布时的id
        final int id = myDate.getId();
        //通过他的值得改变来确定该信息位于那个fragment中
        int state = myDate.getState();
        //始发地
        final String fromaddr = myDate.getFromaddr();
        //始发地省
        String provincef = myDate.getProvincef();
        //始发地市
        String cityf = myDate.getCityf();
        //始发地区
        String districtf = myDate.getDistrictf();
        //始发地详
        String streetf = myDate.getStreetf();
        //目的地
        String toaddr = myDate.getToaddr();
        //目的地省
        String provincet = myDate.getProvincet();
        //目的地市
        String cityt = myDate.getCityt();
        //目的地区
        String districtt = myDate.getDistrictt();
        //目的地详
        String streett = myDate.getStreett();
        //货物的名称
        String name = myDate.getName();
        //货物品类
        String categoryname = myDate.getCategoryname();
        //货物类型的名称
        String typename = myDate.getTypename();
        //货物规格
        float spec = myDate.getSpec();
        //货物单位
        String unit = myDate.getUnit();
        //车长
        String carlength = myDate.getCarlength();
        //车的类型
        String cartypename = myDate.getCartypename();
        //最低温
        int tempmin = myDate.getTempmin();
        //最高温
        int tempmax = myDate.getTempmax();
        //最早装车时间
        String loaddatestart = myDate.getLoaddatestart();
        //最晚装车时间
        String loaddateend = myDate.getLoaddateend();
        //最早到货时间
        String arrivaldatestart = myDate.getArrivaldatestart();
        //最晚到货时间
        String arrivaldateend = myDate.getArrivaldateend();

//价格
        float price = myDate.getPrice();
        //支付类型
        int payment = myDate.getPayment();

        //发票
        int isneedbill = myDate.getIsneedbill();
        //发货人
        String senduser = myDate.getSenduser();
        //联系方式
        String sendtel = myDate.getSendtel();
//上传时间
        String modifiedon = myDate.getSenddate();


        // TODO: 2017/6/5
        if (price == 0) {
            str = "电话联系";

        } else {

            str = "￥" + price;
        }

        if (payment == 1) {
            stt = "现金到付";
        }


        mMyHolder.car_adapter_tv1.setText(provincef + cityf);

        mMyHolder.car_adapter_tv2.setText(provincet + cityt);
        // 截取时间
        String[] starTimestr = modifiedon.split("T");
        String startcreateDate = starTimestr[0];
        mMyHolder.car_adapter_tv3.setText(startcreateDate);

        mMyHolder.car_adapter_tv4.setText(cityf + "到" + cityt + "," + "有" + name + spec + unit + "," + "求" + carlength+ cartypename +","+ "价格" + str + "," + stt);
        // 截取时间
        String[] starTimestr1 = loaddatestart.split("T");
        String startcreateDate1 = starTimestr1[0];

        mMyHolder.car_adapter_tv5.setText(startcreateDate1);
        // 截取时间
        String[] starTimestr2 = loaddateend.split("T");
        String startcreateDate2 = starTimestr2[0];

        mMyHolder.car_adapter_tv6.setText(startcreateDate2);

        mMyHolder.car_adapter_tv7.setText(str);
        mMyHolder.mButton2.setTag(id);
        mMyHolder.mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog = new Dialog(mContext, R.style.selectorDialog);
                mDialog.setContentView(R.layout.dialog);
                mDialog.setCancelable(false);

                mDialog.show();
                mSubmit = (TextView) mDialog.findViewById(R.id.submit);
                mCancel = (TextView) mDialog.findViewById(R.id.cancel);
                mSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int tag = (int) mMyHolder.mButton2.getTag();
                        new Thread(){
                            @Override
                            public void run() {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                String format = String.format(KeyPath.Path.head+KeyPath.Path.delete,id, mUserName,mUserPass);
                                Request request = new Request.Builder()
                                        .url(format)
                                        .delete()
                                        .build();
                                okHttpClient.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String string = response.body().string();
                                        if(string!=null){

                                            try {
                                                JSONObject jsonObject = new JSONObject(string);
                                                int status = jsonObject.getInt("status");
                                                if(status==1){
                                                    mHandler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mDialog.dismiss();
                                                            NToast.shortToast(mContext,"货源已被成功删除");

                                                            list.remove(position);
                                                            notifyDataSetChanged();
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
                        }.start();




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
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Car_Details_Page_Activity.class);
                intent.putExtra("myDate",(Serializable) myDate);

                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class MyHolder extends ViewHolder {
        private TextView car_adapter_tv1, car_adapter_tv2, car_adapter_tv3, car_adapter_tv4, car_adapter_tv5, car_adapter_tv6, car_adapter_tv7;

        private Button mButton1, mButton2;

        public MyHolder(View view) {
            super(view);
            car_adapter_tv1 = (TextView) view.findViewById(R.id.car_adapter_tv1);
            car_adapter_tv2 = (TextView) view.findViewById(R.id.car_adapter_tv2);
            car_adapter_tv3 = (TextView) view.findViewById(R.id.car_adapter_tv3);
            car_adapter_tv4 = (TextView) view.findViewById(R.id.car_adapter_tv4);
            car_adapter_tv5 = (TextView) view.findViewById(R.id.car_adapter_tv5);
            car_adapter_tv6 = (TextView) view.findViewById(R.id.car_adapter_tv6);
            car_adapter_tv7 = (TextView) view.findViewById(R.id.car_adapter_tv7);
            mButton1 = (Button) view.findViewById(R.id.car_adapter_btn1);
            mButton2 = (Button) view.findViewById(R.id.car_adapter_btn2);

        }
    }
}
