package com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.bean.Lirbrary_Seek_bean;
import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.mytoast.LogUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZCL on 2017/5/9.
 * 服务协议
 */


public class User_information_Activity extends MyBaseActivity implements View.OnClickListener {
    private ImageView mImageView ;
    @Override
    public int setview() {
        return R.layout.user_information_activity;
    }

    @Override
    public void init() {
        mImageView = (ImageView)  findViewById(R.id.user_agreement_iv1);
        mImageView.setOnClickListener(this);

    }

    @Override
    public void setbase() {

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_agreement_iv1:

                finish();
                break;


        }
    }
}
