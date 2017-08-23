package com.rescueandroid.utils.myorder.utils;

import android.app.Activity;

import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyGrabOrderPay;
import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyOrderPay;
import com.example.rescueandroidapp.Framgment.inter_face.KeyPath;
import com.rescueandroid.data.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CC on 2017/5/27
 */

public class HttpAppUtils {
    public static final int UPDATE = 300001;
    public static final int ADDDATE = 300002;

    private static final String head = "http://114.215.122.32:16006";
    private static final String headlocal = "http://192.168.1.10:16006";
    /**
     * 订单信息列表 get
     * */
    public static void getOrder(Activity activity, JsonCallBack jsonCallBack, int requestCode, String u, String p, String item, String paystate){
        String url = head + KeyPath.Path.MY_ORDER;
        Map<String,String> params = new HashMap<>();
        u = Data.getInstance().username;
        p = Data.getInstance().pwd;
        params.put("u",u);
        params.put("p",p);
        params.put("item",item);
        params.put("paystate",paystate);
        HttpJsonUtils httpJsonUtils = new HttpJsonUtils(activity);
        httpJsonUtils.getDataLoading(jsonCallBack,requestCode,url,params,"data", MyOrderPay.class);
    }

    /**
     * 订单信息列表 无loading
     * */
    public static void getOrderNoLoading(Activity activity, JsonCallBack jsonCallBack, int requestCode, String u, String p, String item, String paystate) {
        String url = head + KeyPath.Path.MY_ORDER;
        Map<String,String> params = new HashMap<>();
        u = Data.getInstance().username;
        p = Data.getInstance().pwd;
        params.put("u",u);
        params.put("p",p);
        params.put("item",item);
        params.put("paystate",paystate);
        HttpJsonUtils httpJsonUtils = new HttpJsonUtils(activity);
        httpJsonUtils.getData(jsonCallBack,requestCode,url,params,"data", MyOrderPay.class);
    }

    /**
     * 抢单信息列表 get
     * */
    public static void getGrabOrder(Activity activity, JsonCallBack jsonCallBack, int requestCode, String u, String p, String ordertype, String state, String item){
        String url = head + KeyPath.Path.MY_GRAB_ORDER;
        Map<String,String> params = new HashMap<>();
        u = Data.getInstance().username;
        p = Data.getInstance().pwd;
        params.put("u",u);
        params.put("p",p);
        params.put("ordertype",ordertype);
        params.put("state",state);
        params.put("item",item);
        HttpJsonUtils httpJsonUtils = new HttpJsonUtils(activity);
        httpJsonUtils.getDataLoading(jsonCallBack,requestCode,url,params,"data", MyGrabOrderPay.class);
    }

    /**
     * put提交修改价格
     * */
    public static void upPayTotal(Activity activity, JsonCallBackString jsonCallBack, int requestCode, String u, String p, String price, String id) {
        String url = head + KeyPath.Path.MY_ORDER_PUT+id;
        Map<String,String> params = new HashMap<>();
        u = Data.getInstance().username;
        p = Data.getInstance().pwd;
        params.put("u",u);
        params.put("p",p);
        params.put("price",price);
        HttpJsonUtils httpJsonUtils = new HttpJsonUtils(activity);
        httpJsonUtils.putDataString(jsonCallBack,requestCode,url,params,"status");
    }

    /**
     * 抢单接口 ：put应单   /askorderinfo/answer/{id}
     * */
    public static void answerFrom(Activity activity, JsonCallBackString jsonCallBack, int requestCode, String u, String p, String state, String item, String id) {
        String url = head + KeyPath.Path.MY_GRAB_ORDER_PUT+id;
        Map<String,String> params = new HashMap<>();
        u = Data.getInstance().username;
        p = Data.getInstance().pwd;
        params.put("u",u);
        params.put("p",p);
        params.put("state",state);
        params.put("item",item);
        HttpJsonUtils httpJsonUtils = new HttpJsonUtils(activity);
        httpJsonUtils.putDataString(jsonCallBack,requestCode,url,params,"status");
    }

    /**
     * 抢单接口 ：添加抢单信�  /askorderinfo/
     * */
    public static void addorderinfo(Activity activity, JsonCallBack jsonCallBack, int requestCode, String u, String p, String item, String answerid) {
        String url = head + KeyPath.Path.MY_GRAB_ORDER_;
        Map<String,String> params = new HashMap<>();
        u = Data.getInstance().username;
        p = Data.getInstance().pwd;
        params.put("u",u);
        params.put("p",p);
        params.put("item",item);
        params.put("answerid",answerid);
        HttpJsonUtils httpJsonUtils = new HttpJsonUtils(activity);
        httpJsonUtils.postDataLoading(jsonCallBack,requestCode,url,params,"status",String.class);
    }
}
