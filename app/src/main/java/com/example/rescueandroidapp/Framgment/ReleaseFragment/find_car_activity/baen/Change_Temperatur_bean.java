package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen;

import java.util.List;

/**
 * Created by ZCL on 2017/5/17.
 */

public class Change_Temperatur_bean {
    private  int status;
    private List<Mydata>data;

    public Change_Temperatur_bean(int status, List<Mydata> data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Mydata> getData() {
        return data;
    }

    public void setData(List<Mydata> data) {
        this.data = data;
    }

    public class Mydata {
        private  int id;
        private  String value;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
