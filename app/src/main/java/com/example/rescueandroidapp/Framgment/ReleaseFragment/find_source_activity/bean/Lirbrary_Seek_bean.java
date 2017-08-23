package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.bean;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.Source_Finding_Activity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZCL on 2017/5/9.
 */

public class Lirbrary_Seek_bean   {
    private int status;
    private List<MyDate> data;

    public Lirbrary_Seek_bean(int status, List<MyDate> data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MyDate> getData() {
        return data;
    }

    public void setData(List<MyDate> data) {
        this.data = data;
    }

    public class MyDate  implements Serializable {
        //货物的id
        private int id;
        ////通过他的值得改变来确定该信息位于那个fragment中
        private int state;
        //库位置
        private String storeposition;
        //库位置省
        private String provincef;

        //库位置市
        private String cityf;
        //库位置地区
        private String districtf;
        //库位置街
        private String streetf;
        //入库时间
        private String putdate;
        //储存时间
        private float storedate;

        //货物的名称
        private String name;
        //货物类型ID
        private  int typeid;

        //货物类型的名称
        private String typename;
        //货物规格
        private float spec;
        //货物单位
        private String unit;
        //最低温
        private int tempmin;
        //最高温
        private int tempmax;
        //包装类型
        private String packtype;
        //价格
        private float price;
        //支付方式
        private int payment;
        //发票
        private int isneedbill;
        //发货人
        private String senduser;

        //联系方式
        private String sendtel;
        //上传时间
        private String senddate;
        //备注
        private  String remark;

        public MyDate(int id, int state, String storeposition, String provincef, String cityf, String districtf, String streetf, String putdate, float storedate, String name, int typeid, String typename, float spec, String unit, int tempmin, int tempmax, String packtype, float price, int payment, int isneedbill, String senduser, String sendtel, String senddate, String remark) {
            this.id = id;
            this.state = state;
            this.storeposition = storeposition;
            this.provincef = provincef;
            this.cityf = cityf;
            this.districtf = districtf;
            this.streetf = streetf;
            this.putdate = putdate;
            this.storedate = storedate;
            this.name = name;
            this.typeid = typeid;
            this.typename = typename;
            this.spec = spec;
            this.unit = unit;
            this.tempmin = tempmin;
            this.tempmax = tempmax;
            this.packtype = packtype;
            this.price = price;
            this.payment = payment;
            this.isneedbill = isneedbill;
            this.senduser = senduser;
            this.sendtel = sendtel;
            this.senddate = senddate;
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStoreposition() {
            return storeposition;
        }

        public void setStoreposition(String storeposition) {
            this.storeposition = storeposition;
        }

        public String getProvincef() {
            return provincef;
        }

        public void setProvincef(String provincef) {
            this.provincef = provincef;
        }

        public String getCityf() {
            return cityf;
        }

        public void setCityf(String cityf) {
            this.cityf = cityf;
        }

        public String getDistrictf() {
            return districtf;
        }

        public void setDistrictf(String districtf) {
            this.districtf = districtf;
        }

        public String getStreetf() {
            return streetf;
        }

        public void setStreetf(String streetf) {
            this.streetf = streetf;
        }

        public String getPutdate() {
            return putdate;
        }

        public void setPutdate(String putdate) {
            this.putdate = putdate;
        }

        public float getStoredate() {
            return storedate;
        }

        public void setStoredate(float storedate) {
            this.storedate = storedate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public float getSpec() {
            return spec;
        }

        public void setSpec(float spec) {
            this.spec = spec;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getTempmin() {
            return tempmin;
        }

        public void setTempmin(int tempmin) {
            this.tempmin = tempmin;
        }

        public int getTempmax() {
            return tempmax;
        }

        public void setTempmax(int tempmax) {
            this.tempmax = tempmax;
        }

        public String getPacktype() {
            return packtype;
        }

        public void setPacktype(String packtype) {
            this.packtype = packtype;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public int getIsneedbill() {
            return isneedbill;
        }

        public void setIsneedbill(int isneedbill) {
            this.isneedbill = isneedbill;
        }

        public String getSenduser() {
            return senduser;
        }

        public void setSenduser(String senduser) {
            this.senduser = senduser;
        }

        public String getSendtel() {
            return sendtel;
        }

        public void setSendtel(String sendtel) {
            this.sendtel = sendtel;
        }

        public String getSenddate() {
            return senddate;
        }

        public void setSenddate(String senddate) {
            this.senddate = senddate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
