package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.adapter.Find_Car_Adapter;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen.Car_Seek_bean;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.google.gson.Gson;
import com.rescueandroid.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/5/6.
 */
//已失效
public class Lapsed_Fragment extends Fragment {
    private View mInflate;
    private ListView mListView;
    private TextView mTextView ;

    private Handler mHandler = new Handler();
    private String mUserName;
    private String mUserPass;
    public Lapsed_Fragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mInflate=inflater.inflate(R.layout.lapsed_fragment,null,false);
        // 获取用户名
        mUserName = SharedPreferencesUtils.getString(getActivity(), "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(getActivity(), "userPass", "");
        return mInflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mListView= (ListView) mInflate.findViewById(R.id.lapsed_fragment_lv);
mTextView= (TextView) mInflate.findViewById(R.id.lapsed_fragment_tv);
        mListView.setEmptyView(mTextView);
        TextView textView = new TextView(getActivity());
        textView.setText("已加载全部");
        textView.setTextSize(24);

        textView.setPadding(0,30,0,30);
        textView.setGravity(Gravity.CENTER);
        mListView.addFooterView(textView);
        init();
    }
    private void init() {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                String format = String.format(KeyPath.Path.head + KeyPath.Path.for_car,mUserName,mUserPass,3);
                Request build = new Request.Builder().url(format).build();
                okHttpClient.newCall(build).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();

                        if (string != null) {
                            Gson gson = new Gson();
                            Car_Seek_bean car_seek_bean = gson.fromJson(string, Car_Seek_bean.class);
                            int status = car_seek_bean.getStatus();

                            final List<Car_Seek_bean.MyDate> data = car_seek_bean.getData();
                            if(status==1){
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Find_Car_Adapter find_car_adapter = new Find_Car_Adapter(getContext());

                                        find_car_adapter.setdatas(data);
                                        mListView.setAdapter(find_car_adapter);
                                    }
                                });
                            }


                        }



                    }
                });
            }
        }.start();

    }



}
