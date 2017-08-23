package com.rescueandroid.utils.mytoast;

import android.util.Log;

/**
 * 日志输出工具
 * Created by juanyuan on 15/12/11.
 */
public class LogUtil {
    public static String TAG = "TAG";

    public static void i(String infos) {
        Log.i(TAG, infos);
    }

    public static void e(String infos) {
        Log.e(TAG, infos);
    }

    public static void v(String infos) {
        Log.v(TAG, infos);
    }

    public static void d(String infos) {
        Log.d(TAG, infos);
    }

}
