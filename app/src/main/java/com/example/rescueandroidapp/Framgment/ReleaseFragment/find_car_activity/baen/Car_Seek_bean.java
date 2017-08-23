package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZCL on 2017/5/9.
 */

public class Car_Seek_bean {
    private int status;
    private List<MyDate> data;

    public Car_Seek_bean(int status, List<MyDate> data) {
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

    public class MyDate  implements  Serializable {
        //货物的id
        private int id;
        ////通过他的值得改变来确定该信息位于那个fragment中
        private int state;
        //始发地
        private String fromaddr;
        //始发地省
        private String provincef;
        //始发地市
        private String cityf;
        //始发地区
        private String districtf;
        //始发地详
        private String streetf;
        //目的地
        private String toaddr;
        //目的地省
        private String provincet;
        //目的地市
        private String cityt;
        //目的地区
        private String districtt;
        //目的地详
        private String streett;
        //货物的名称
        private String name;
        //货物品类
        private String categoryname;
        //货物类型的名称
        private String typename;
        //货物规格
        private float spec;
        //货物单位
        private String unit;
        //车长
        private String carlength;
        //车的类型
        private String cartypename;
        //最低温
        private int tempmin;
        //最高温
        private int tempmax;
        //最早装车时间
        private String loaddatestart;
        //最晚装车时间
        private String loaddateend;
        //最早到货时间
        private String arrivaldatestart;
        //最晚到货时间
        private String arrivaldateend;
        //运输费用的类型
        private String costtype;
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

        public MyDate(int id, int state, String fromaddr, String provincef, String cityf, String districtf, String streetf, String toaddr, String provincet, String cityt, String districtt, String streett, String name, String categoryname, String typename, float spec, String unit, String carlength, String cartypename, int tempmin, int tempmax, String loaddatestart, String loaddateend, String arrivaldatestart, String arrivaldateend, String costtype, float price, int payment, int isneedbill, String senduser, String sendtel, String senddate, String remark) {
            this.id = id;
            this.state = state;
            this.fromaddr = fromaddr;
            this.provincef = provincef;
            this.cityf = cityf;
            this.districtf = districtf;
            this.streetf = streetf;
            this.toaddr = toaddr;
            this.provincet = provincet;
            this.cityt = cityt;
            this.districtt = districtt;
            this.streett = streett;
            this.name = name;
            this.categoryname = categoryname;
            this.typename = typename;
            this.spec = spec;
            this.unit = unit;
            this.carlength = carlength;
            this.cartypename = cartypename;
            this.tempmin = tempmin;
            this.tempmax = tempmax;
            this.loaddatestart = loaddatestart;
            this.loaddateend = loaddateend;
            this.arrivaldatestart = arrivaldatestart;
            this.arrivaldateend = arrivaldateend;
            this.costtype = costtype;
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

        public String getFromaddr() {
            return fromaddr;
        }

        public void setFromaddr(String fromaddr) {
            this.fromaddr = fromaddr;
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

        public String getToaddr() {
            return toaddr;
        }

        public void setToaddr(String toaddr) {
            this.toaddr = toaddr;
        }

        public String getProvincet() {
            return provincet;
        }

        public void setProvincet(String provincet) {
            this.provincet = provincet;
        }

        public String getCityt() {
            return cityt;
        }

        public void setCityt(String cityt) {
            this.cityt = cityt;
        }

        public String getDistrictt() {
            return districtt;
        }

        public void setDistrictt(String districtt) {
            this.districtt = districtt;
        }

        public String getStreett() {
            return streett;
        }

        public void setStreett(String streett) {
            this.streett = streett;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
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

        public String getCarlength() {
            return carlength;
        }

        public void setCarlength(String carlength) {
            this.carlength = carlength;
        }

        public String getCartypename() {
            return cartypename;
        }

        public void setCartypename(String cartypename) {
            this.cartypename = cartypename;
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

        public String getLoaddatestart() {
            return loaddatestart;
        }

        public void setLoaddatestart(String loaddatestart) {
            this.loaddatestart = loaddatestart;
        }

        public String getLoaddateend() {
            return loaddateend;
        }

        public void setLoaddateend(String loaddateend) {
            this.loaddateend = loaddateend;
        }

        public String getArrivaldatestart() {
            return arrivaldatestart;
        }

        public void setArrivaldatestart(String arrivaldatestart) {
            this.arrivaldatestart = arrivaldatestart;
        }

        public String getArrivaldateend() {
            return arrivaldateend;
        }

        public void setArrivaldateend(String arrivaldateend) {
            this.arrivaldateend = arrivaldateend;
        }

        public String getCosttype() {
            return costtype;
        }

        public void setCosttype(String costtype) {
            this.costtype = costtype;
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
