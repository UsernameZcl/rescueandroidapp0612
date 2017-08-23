package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.baen.Car_Seek_bean;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.bean.Lirbrary_Seek_bean;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.update.network.util.MyBaseActivity;

/**
 * Created by ZCL on 2017/6/7.
 */

//找车货源的详情
public class Car_Details_Page_Activity extends MyBaseActivity implements View.OnClickListener {
    private ImageView mImageView1;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6, mTextView7, mTextView8, mTextView9, mTextView10, mTextView11,
            mTextView12, mTextView13, mTextView14, mTextView15, mTextView16, mTextView17, mTextView18, mTextView19, mTextView20, mTextView21, mTextView22, mTextView23;
    private Car_Seek_bean.MyDate myDate;
    private String mRoleid;
    private String role;
    private String mpayment;
    private String misneedbill;

    @Override
    public int setview() {
        return R.layout.car_details_page_activity;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        myDate = (Car_Seek_bean.MyDate) intent.getSerializableExtra("myDate");
        mRoleid = Data.getInstance().roleid;
        setRole(mRoleid);
        mImageView1 = (ImageView) findViewById(R.id.car_details_page_activity_iv1);
        mImageView1.setOnClickListener(this);
        //        //发货人
        mTextView1 = (TextView) findViewById(R.id.car_details_page_activity_tv1);
        //角色
        mTextView2 = (TextView) findViewById(R.id.car_details_page_activity_tv2);
        //发布时间
        mTextView3 = (TextView) findViewById(R.id.car_details_page_activity_tv3);
        //始发地
        mTextView4 = (TextView) findViewById(R.id.car_details_page_activity_tv4);
        //目的地
        mTextView5 = (TextView) findViewById(R.id.car_details_page_activity_tv5);
        //需要车长
        mTextView6 = (TextView) findViewById(R.id.car_details_page_activity_tv6);
        //需要类型
        mTextView7 = (TextView) findViewById(R.id.car_details_page_activity_tv7);
        //需要温度
        mTextView8 = (TextView) findViewById(R.id.car_details_page_activity_tv8);
        //最早装车时间
        mTextView9 = (TextView) findViewById(R.id.car_details_page_activity_tv9);
        //最晚装车时间
        mTextView10 = (TextView) findViewById(R.id.car_details_page_activity_tv10);
        //最早到货时间
        mTextView11 = (TextView) findViewById(R.id.car_details_page_activity_tv11);
        //最晚到货时间
        mTextView12 = (TextView) findViewById(R.id.car_details_page_activity_tv12);
        //费用
        mTextView13 = (TextView) findViewById(R.id.car_details_page_activity_tv13);
        //元
        mTextView14 = (TextView) findViewById(R.id.car_details_page_activity_tv14);
        //支付方式
        mTextView15 = (TextView) findViewById(R.id.car_details_page_activity_tv15);
        //货物名称
        mTextView16 = (TextView) findViewById(R.id.car_details_page_activity_tv16);
        //货物品类
        mTextView17 = (TextView) findViewById(R.id.car_details_page_activity_tv17);
        //货物类型
        mTextView18 = (TextView) findViewById(R.id.car_details_page_activity_tv18);
        //货物规格
        mTextView19 = (TextView) findViewById(R.id.car_details_page_activity_tv19);
        //需要发票
        mTextView20 = (TextView) findViewById(R.id.car_details_page_activity_tv20);
        //备注
        mTextView21 = (TextView) findViewById(R.id.car_details_page_activity_tv21);
        //联系人
        mTextView22 = (TextView) findViewById(R.id.car_details_page_activity_tv22);
        //联系电话
        mTextView23 = (TextView) findViewById(R.id.car_details_page_activity_tv23);


        //发布时的id
        final int id = myDate.getId();
        //通过他的值得改变来确定该信息位于那个fragment中
        int state = myDate.getState();
        //始发地
        final String fromaddr = myDate.getFromaddr();
        //始发地省
        String provincef = myDate.getProvincef();
        //始发地市
        String cityf = myDate.getCityf();
        //始发地区
        String districtf = myDate.getDistrictf();
        //始发地详
        String streetf = myDate.getStreetf();
        //目的地
        String toaddr = myDate.getToaddr();
        //目的地省
        String provincet = myDate.getProvincet();
        //目的地市
        String cityt = myDate.getCityt();
        //目的地区
        String districtt = myDate.getDistrictt();
        //目的地详
        String streett = myDate.getStreett();
        //货物的名称
        String name = myDate.getName();
        //货物品类
        String categoryname = myDate.getCategoryname();
        //货物类型的名称
        String typename = myDate.getTypename();
        //货物规格
        float spec = myDate.getSpec();
        //货物单位
        String unit = myDate.getUnit();
        //车长
        String carlength = myDate.getCarlength();
        //车的类型
        String cartypename = myDate.getCartypename();
        //最低温
        int tempmin = myDate.getTempmin();
        //最高温
        int tempmax = myDate.getTempmax();
        //最早装车时间
        String loaddatestart = myDate.getLoaddatestart();
        //最晚装车时间
        String loaddateend = myDate.getLoaddateend();
        //最早到货时间
        String arrivaldatestart = myDate.getArrivaldatestart();
        //最晚到货时间
        String arrivaldateend = myDate.getArrivaldateend();

//价格
        float price = myDate.getPrice();
        //支付类型
        int payment = myDate.getPayment();

        //发票
        int isneedbill = myDate.getIsneedbill();
        //发货人
        String senduser = myDate.getSenduser();
        //联系方式
        String sendtel = myDate.getSendtel();
//上传时间
        String modifiedon = myDate.getSenddate();
//备注
        String remark = myDate.getRemark();
        mTextView1.setText(senduser);
        mTextView2.setText("[" + role + "]");
        String[] starTimestr1 = modifiedon.split("T");
        String startcreateDate1 = starTimestr1[0];
        mTextView3.setText(startcreateDate1);
        mTextView4.setText(fromaddr);
        mTextView5.setText(toaddr);
        mTextView6.setText(carlength);
        mTextView7.setText(cartypename);
        mTextView8.setText(tempmin + "℃" + " / " + tempmax + "℃");
        String[] starTimestr2 = loaddatestart.split("T");
        String startcreateDate2 = starTimestr2[0];
        mTextView9.setText(startcreateDate2);
        String[] starTimestr3 = loaddateend.split("T");
        String startcreateDate3 = starTimestr3[0];
        mTextView10.setText(startcreateDate3);
        String[] starTimestr4 = arrivaldatestart.split("T");
        String startcreateDate4 = starTimestr4[0];
        mTextView11.setText(startcreateDate4);
        String[] starTimestr5 = arrivaldateend.split("T");
        String startcreateDate5 = starTimestr5[0];
        mTextView12.setText(startcreateDate5);
        if (price == 0) {
            mTextView13.setText("电话联系");
            mTextView14.setVisibility(View.GONE);
            mTextView15.setVisibility(View.GONE);
        } else {
            String setneedbill = setneedbill(payment);
            mTextView13.setText("" + price);
            mTextView14.setText("元");
            mTextView15.setText(setneedbill);

        }
        mTextView16.setText(name);
        mTextView17.setText(categoryname);
        mTextView18.setText(typename);
        mTextView19.setText(spec+unit);
        String isneedbill1 = isneedbill(isneedbill);
        mTextView20.setText(isneedbill1);
        mTextView21.setText(remark);
        mTextView22.setText(senduser);
        mTextView23.setText(sendtel);
    }

    @Override
    public void setbase() {

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.car_details_page_activity_iv1:
                finish();
                break;
        }
    }

    //获取角色
    public String setRole(String str) {
        if ("11".equals(str)) {
            role = "货主";


        }
        if ("12".equals(str)) {
            role = "物流公司";

        }
        if ("13".equals(str)) {
            role = "信息部";

        }
        return role;
    }

    //获取支付的类型
    public String setneedbill(int isneedbill) {
        if (isneedbill == 0) {
            mpayment = "在线支付";
        }
        if (isneedbill == 1) {
            mpayment = "现金到付";
        }
        if (isneedbill == 2) {
            mpayment = "回单支付";
        }
        return mpayment;
    }

    //是否需要发票
    public String isneedbill(int isneedbill) {
        if (isneedbill == 0) {
            misneedbill = "否";
        }
        if (isneedbill == 1) {
            misneedbill = "是";
        }
        return misneedbill;
    }
}
