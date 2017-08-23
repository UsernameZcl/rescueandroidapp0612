package com.rescueandroid.update.network.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZCL on 2017/4/25.
 */

public abstract class MyBaseActivity extends AutoLayoutActivity {
    protected Context context =MyBaseActivity.this;
    protected static List<Activity> allactivity = new ArrayList<>();
  public ImageView mImageView;
    public TextView mTextView;
    public RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setview());
//        boolean networkConnect = NetWorkUtils.isNetworkConnect(context);
//        if(networkConnect==true){
            ActivityManager.getInstance().pushActivity(this);
            context=this;
            allactivity.add(getactivity());
            init();
            setbase();
//        }else {
//            NToast.shortToast(context,"网络连接失败，请联网重试！");
//
//        }

    }
    /**
     * 进行布局的添加
     *
     * @return
     */
    public abstract int setview();
    /**
     * 对控件进行初始化
     */
    public abstract void init();

    /**
     * 对数据进行操作
     */
    public abstract void setbase();

    /**
     * 将activity封装进集合里方便遍历杀死
     */
    public abstract MyBaseActivity getactivity();


}
