package com.example.rescueandroidapp.Framgment.inter_face;

/**
 * Created by ZCL on 2017/5/9.
 */

public interface KeyPath {

    interface Path {
        //请求体
        String head = "http://114.215.122.32:16006";
        //验证手机号是否申请过
        String phonr_verify = "/v1/baseuser/verify?u=%s";
        //注册
        String register = "/v1/baseuser?u=%s&p=%s";
        //找车中发布的车的接口
        String for_car_library = "/v1/findcarinfo/?u=%s&p=%s";
        //求车中的接口,需要判断state状态，运输状态  0求车中；1 运输中；2已完成；3 已失效,必传
        //http://114.215.122.32:16006/v1/findcarinfo/mylist?u=13963409641&p=000000&state=0
        String for_car = "/v1/findcarinfo/mylist?u=%s&p=%s&state=%s";
        //车删除
        String delete = "/v1/findcarinfo/%s?u=%s&p=%s";

        //路线删除
        String routedelete = "/v1/routeinfo/%s?u=%s&p=%s";

        //提交找库的接口
        String find_library = "/v1/findstoreinfo?u=%s&p=%s";
        //找库资源中，求车库的接口http://114.215.122.32:16006/v1/findstoreinfo/mylist?u=13963409641&p=000000
        String find_library_seekinglidrary = "/v1/findstoreinfo/mylist?u=%s&p=%s";
        //找库数据的删除
        String library_delete = "/v1/findstoreinfo/%s?u=%s&p=%s";

        //找路线提交的接口
        String findcar_route = "/v1/routeinfo?u=%s&p=%s";
        //找路线请求数据的接口
        String requset_route = "/v1/routeinfo/list?u=abc&p=123456";

        //修该密码
        String changpwa = "/v1/baseuser/editpass?u=%s&p=%s&oldp=%s&newp=%s";
        //TODO myorder
        // 获取订单参数
        String MY_ORDER = "/v1/orderinfo/mylist";
        String MY_GRAB_ORDER = "/v1/askorderinfo/mylist";
        String MY_ORDER_PUT = "/v1/orderinfo/";//需要加id
        String MY_GRAB_ORDER_PUT = "/v1/askorderinfo/answer/";
        String MY_GRAB_ORDER_ = "/v1/askorderinfo";
    }

}
