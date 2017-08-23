package com.rescueandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZCL on 2017/4/25.
 */

public class SharedPreferencesUtils {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    public  static  void init(Context context, String name){
        mSharedPreferences = context.getSharedPreferences(name, context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
    public static void putint (String key, int value) {
        if (mEditor != null) {
            mEditor.putInt(key, value);
            mEditor.commit();
        }

    }

    public static void putstring(String key, String value) {
        if (mEditor != null) {
            mEditor.putString(key, value);
            mEditor.commit();
        }
    }
    public static void putboolean(String key, boolean value) {
        if (mEditor != null) {
            mEditor.putBoolean(key, value);
            mEditor.commit();
        }
    }

    /**
     * 取得方法
     */

    public static String getstring(String key, String deaflut) {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(key, deaflut);
        }
        return "";
    }
    public static boolean getboolean(String key, boolean aaa) {
        if (mSharedPreferences != null) {
            boolean aBoolean = mSharedPreferences.getBoolean(key, aaa);
            return aBoolean;
        }
        return false;
    }


    public static int  getint(String key, int  deaflut) {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getInt(key, deaflut);
        }
        return 0;
    }

    public SharedPreferencesUtils() {
        // TODO Auto-generated constructor stub
    }

    public static void put(Context context, String key, String value)
    {
        SharedPreferences.Editor sharedata = context.getSharedPreferences("data", 0).edit();
        sharedata.putString(key,value);
        sharedata.commit();
    }

    public static String getString(Context context, String key, String defvalue)
    {
        SharedPreferences sharedata = context.getSharedPreferences("data", 0);
        return sharedata.getString(key, defvalue);
    }

    public static int getInt(Context context, String key, int defvalue)
    {
        SharedPreferences sharedata = context.getSharedPreferences("data", 0);
        return sharedata.getInt(key, defvalue);
    }

}
