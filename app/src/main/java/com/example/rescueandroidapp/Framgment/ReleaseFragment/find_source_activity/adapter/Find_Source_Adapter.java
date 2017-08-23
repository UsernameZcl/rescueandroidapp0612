package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.User_information_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.adapter.Find_Car_Adapter;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.Details_Page_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.bean.Lirbrary_Seek_bean;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseAdapter;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.NToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/6/6.
 */
//find_library_adapter.xml
public class Find_Source_Adapter extends MyBaseAdapter<Lirbrary_Seek_bean.MyDate> {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private Dialog mDialog;
    private TextView mLibrary_dialog_submit;
    private TextView mLibrary_dialog_cancel;
    private Handler mHandler = new Handler();
    private MyHolder mMyHolder;
    private String mUserName;
    private String mUserPass;

    private Lirbrary_Seek_bean.MyDate mMyDate;

    public Find_Source_Adapter(Context context) {
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
            convertView = layoutInflater.inflate(R.layout.find_library_adapter, null, false);
            mMyHolder = new Find_Source_Adapter.MyHolder(convertView);
            convertView.setTag(mMyHolder);

        } else {
            mMyHolder = (Find_Source_Adapter.MyHolder) convertView.getTag();
        }
        mMyDate = list.get(position);

        //货物的id
        final int id = mMyDate.getId();
//通过他的值得改变来确定该信息位于那个fragment中
        int state = mMyDate.getState();
        //库位置
        String storeposition = mMyDate.getStoreposition();
        //库位置省
        String provincef = mMyDate.getProvincef();
        //库位置市
        String cityf = mMyDate.getCityf();
        //库位置地区
        String districtf = mMyDate.getDistrictf();
        //库位置街
        String streetf = mMyDate.getStreetf();
        //入库时间
        String putdate = mMyDate.getPutdate();
        // 截取时间
        String[] starTimestr = putdate.split("T");
        String startcreateDate = starTimestr[0];
        //储存时间
        float storedate = mMyDate.getStoredate();
        //货物的名称
        String name = mMyDate.getName();
        //货物类型ID
        int typeid = mMyDate.getTypeid();
        //货物类型的名称
        String typename = mMyDate.getTypename();
        //货物规格
        float spec = mMyDate.getSpec();
        //货物单位
        String unit = mMyDate.getUnit();
        //最低温
        int tempmin = mMyDate.getTempmin();
        //最高温
        int tempmax = mMyDate.getTempmax();
        //包装类型
        String packtype = mMyDate.getPacktype();
        //价格
        float price = mMyDate.getPrice();
        //支付方式
        int payment = mMyDate.getPayment();
        //发票
        int isneedbill = mMyDate.getIsneedbill();
        //发货人
        String senduser = mMyDate.getSenduser();
        //联系方式
        String sendtel = mMyDate.getSendtel();
        //上传时间
        String modifiedon = mMyDate.getSenddate();
        //备注
        String remark = mMyDate.getRemark();
        mMyHolder.library_adapter_tv1.setText(storeposition);
        mMyHolder.library_adapter_tv2.setText(name);
        mMyHolder.library_adapter_tv3.setText(startcreateDate);
        mMyHolder.library_adapter_tv4.setText(storedate + "个月");
        mMyHolder.library_adapter_linlay1.setTag(id);
        mMyHolder.library_adapter_linlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new Dialog(mContext, R.style.selectorDialog);
                mDialog.setContentView(R.layout.library_dialog);
                mDialog.setCancelable(false);
                mDialog.show();
                mLibrary_dialog_submit = (TextView) mDialog.findViewById(R.id.library_dialog_submit);
                mLibrary_dialog_cancel = (TextView) mDialog.findViewById(R.id.library_dialog_cancel);
                mLibrary_dialog_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int tag = (int) mMyHolder.library_adapter_linlay1.getTag();
                        new Thread() {
                            @Override
                            public void run() {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                String format = String.format(KeyPath.Path.head + KeyPath.Path.library_delete, id, mUserName, mUserPass);
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
                                        if (string != null) {

                                            try {
                                                JSONObject jsonObject = new JSONObject(string);
                                                int status = jsonObject.getInt("status");
                                                if (status == 1) {
                                                    mHandler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mDialog.dismiss();
                                                            NToast.shortToast(mContext, "货源已被成功删除");

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
                mLibrary_dialog_cancel.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(mContext, Details_Page_Activity.class);
                intent.putExtra("mMyDate",(Serializable) mMyDate);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class MyHolder extends ViewHolder {
        private TextView library_adapter_tv1, library_adapter_tv2, library_adapter_tv3, library_adapter_tv4;
        private LinearLayout library_adapter_linlay1;

        public MyHolder(View view) {
            super(view);
            library_adapter_tv1 = (TextView) view.findViewById(R.id.library_adapter_tv1);
            library_adapter_tv2 = (TextView) view.findViewById(R.id.library_adapter_tv2);
            library_adapter_tv3 = (TextView) view.findViewById(R.id.library_adapter_tv3);
            library_adapter_tv4 = (TextView) view.findViewById(R.id.library_adapter_tv4);
            library_adapter_linlay1 = (LinearLayout) view.findViewById(R.id.library_adapter_linlay1);

        }
    }
}
