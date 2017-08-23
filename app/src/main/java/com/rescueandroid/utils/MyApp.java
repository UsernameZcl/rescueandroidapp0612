package com.rescueandroid.utils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by ZCL on 2017/4/25.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtils.init(getApplicationContext(),"Myapp");
        x.Ext.init(this);
    }
}
