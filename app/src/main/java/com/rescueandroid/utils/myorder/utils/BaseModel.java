package com.rescueandroid.utils.myorder.utils;

/**
 * Created by CC on 2017/5/27
 * 基本对象，存储最外层信息，视情况进行改变。
 */

public class BaseModel {

    private int status;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
