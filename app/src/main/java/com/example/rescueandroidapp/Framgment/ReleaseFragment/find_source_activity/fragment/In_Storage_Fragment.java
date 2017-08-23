package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.adapter.Find_Source_Adapter;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.bean.Lirbrary_Seek_bean;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.example.rescueandroidapp.R;
import com.google.gson.Gson;
import com.rescueandroid.utils.SharedPreferencesUtils;
import com.rescueandroid.utils.mytoast.LogUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZCL on 2017/6/6.
 */
//存储中
public class In_Storage_Fragment extends Fragment implements AdapterView.OnItemClickListener {
    private View mInflate;


    private ListView mListView;
    private TextView mTextView;
    private String mUserName;
    private String mUserPass;
    private Handler mHandler = new Handler();

    private void In_Storage_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mInflate = inflater.inflate(R.layout.in_storage_fragment, null, false);
// 获取用户名
        mUserName = SharedPreferencesUtils.getString(getActivity(), "userName", "");
        // 获取密码
        mUserPass = SharedPreferencesUtils.getString(getActivity(), "userPass", "");
        return mInflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mListView= (ListView) mInflate.findViewById(R.id.in_storage_fragment_lv);
       mTextView= (TextView) mInflate.findViewById(R.id.in_storage_fragment_tv);

        mListView.setEmptyView(mTextView);
        TextView textView = new TextView(getActivity());
        textView.setText("已加载全部");
        textView.setTextSize(24);

        textView.setPadding(0,30,0,30);
        textView.setGravity(Gravity.CENTER);
        mListView.addFooterView(textView);
        mListView.setOnItemClickListener(this);
        init();
    }


    private void init() {

        OkHttpClient okHttpClient = new OkHttpClient();
        String format = String.format(KeyPath.Path.head + KeyPath.Path.find_library_seekinglidrary, mUserName, mUserPass);
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
                    Lirbrary_Seek_bean lirbrary_seek_bean = gson.fromJson(string, Lirbrary_Seek_bean.class);
                    int status = lirbrary_seek_bean.getStatus();
                    final List<Lirbrary_Seek_bean.MyDate> data = lirbrary_seek_bean.getData();
                    if (status == 1) {

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Find_Source_Adapter find_source_adapter = new Find_Source_Adapter(getActivity());
                                find_source_adapter.setdatas(data);
                                mListView.setAdapter(find_source_adapter);
                            }
                        });

                    }

                }
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
