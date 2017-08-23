package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.bean.Lirbrary_Seek_bean;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.update.network.util.MyBaseActivity;
import com.rescueandroid.utils.mytoast.LogUtil;

import java.io.Serializable;

/**
 * Created by ZCL on 2017/6/7.
 */
//找库详情页的加载
public class Details_Page_Activity extends MyBaseActivity implements View.OnClickListener {

    private ImageView mImageView1;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6, mTextView7, mTextView8, mTextView9, mTextView10, mTextView11,
            mTextView12, mTextView13, mTextView14, mTextView15, mTextView16, mTextView17;
    private Lirbrary_Seek_bean.MyDate mMyDate;
    private String mRoleid;
    private String role;
    private  String mpayment;
    private  String misneedbill;
    @Override
    public int setview() {
        return R.layout.details_page;
    }

    @Override
    public void init() {
        Intent intent = getIntent();

        mMyDate = (Lirbrary_Seek_bean.MyDate) intent.getSerializableExtra("mMyDate");
        mRoleid = Data.getInstance().roleid;
        setRole(mRoleid);

        mImageView1 = (ImageView) findViewById(R.id.details_page_activity_iv1);
        mImageView1.setOnClickListener(this);
//        //发货人
        mTextView1 = (TextView) findViewById(R.id.details_page_activity_tv1);
        //角色
        mTextView2 = (TextView) findViewById(R.id.details_page_activity_tv2);
        //发布时间
        mTextView3 = (TextView) findViewById(R.id.details_page_activity_tv3);
        //仓库所在地
        mTextView4 = (TextView) findViewById(R.id.details_page_activity_tv4);
        //需要的温度
        mTextView5 = (TextView) findViewById(R.id.details_page_activity_tv5);
        //入库的时间
        mTextView6 = (TextView) findViewById(R.id.details_page_activity_tv6);
        //预计库存
        mTextView7 = (TextView) findViewById(R.id.details_page_activity_tv7);
        //运输费用
        mTextView8 = (TextView) findViewById(R.id.details_page_activity_tv8);
        //支付类型
        mTextView9 = (TextView) findViewById(R.id.details_page_activity_tv9);
        //货物名称
        mTextView10 = (TextView) findViewById(R.id.details_page_activity_tv10);
        //货物类型
        mTextView11 = (TextView) findViewById(R.id.details_page_activity_tv11);
        //包装类型
        mTextView12 = (TextView) findViewById(R.id.details_page_activity_tv12);
        //货物规格
        mTextView13 = (TextView) findViewById(R.id.details_page_activity_tv13);
        //是否需要发票
        mTextView14 = (TextView) findViewById(R.id.details_page_activity_tv14);
        //备注
        mTextView15 = (TextView) findViewById(R.id.details_page_activity_tv15);
        //，联系人
        mTextView16 = (TextView) findViewById(R.id.details_page_activity_tv16);
        //联系电话
        mTextView17 = (TextView) findViewById(R.id.details_page_activity_tv17);

        //货物的id
        int id = mMyDate.getId();
//通过他的值得改变来确定该信息位于那个fragment中
        int state = mMyDate.getState();
        //库位置
        String storeposition = mMyDate.getStoreposition();
        //库位置省
        String provincef = mMyDate.getProvincef();
        //库位置市
        String cityf = mMyDate.getCityf();
        //库位置地区
        String districtf = mMyDate.getDistrictf();
        //库位置街
        String streetf = mMyDate.getStreetf();
        //入库时间
        String putdate = mMyDate.getPutdate();

        //储存时间
        float storedate = mMyDate.getStoredate();
        //货物的名称
        String name = mMyDate.getName();
        //货物类型ID
        int typeid = mMyDate.getTypeid();
        //货物类型的名称
        String typename = mMyDate.getTypename();
        //货物规格
        float spec = mMyDate.getSpec();
        //货物单位
        String unit = mMyDate.getUnit();
        //最低温
        int tempmin = mMyDate.getTempmin();
        //最高温
        int tempmax = mMyDate.getTempmax();
        //包装类型
        String packtype = mMyDate.getPacktype();
        //价格
        float price = mMyDate.getPrice();
        //支付方式
        int payment = mMyDate.getPayment();
        String set = set(payment);
//        //发票
//        int isneedbill = mMyDate.getIsneedbill();
//        String isneedbill1 = isneedbill(isneedbill);

        //发货人
        String senduser = mMyDate.getSenduser();
        //联系方式
        String sendtel = mMyDate.getSendtel();
        //上传时间
        String modifiedon = mMyDate.getSenddate();
        //备注
        String remark = mMyDate.getRemark();
        mTextView1.setText(senduser);
        mTextView2.setText("[" + role + "]");
        // 截取时间
        String[] starTimestr = modifiedon.split("T");
        String startcreateDate = starTimestr[0];
        mTextView3.setText(startcreateDate);
        mTextView4.setText(storeposition);
        mTextView5.setText(tempmin + "℃" + "/" + tempmax + "℃");
        // 截取时间
        String[] starTimestr1 = putdate.split("T");
        String startcreateDate1 = starTimestr1[0];

        mTextView6.setText(startcreateDate1);
        mTextView7.setText(storedate+"个月");
        mTextView8.setText(price+"");
        mTextView9.setText(set);
        mTextView10.setText(name);
        mTextView11.setText(typename);
        mTextView12.setText(packtype);
        mTextView13.setText(spec+unit);
//        mTextView14.setText(isneedbill1);
        mTextView15.setText(remark);
        mTextView16.setText(senduser);
        mTextView17.setText(sendtel);

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
            case R.id.details_page_activity_iv1:

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
    public  String set(int isneedbill){
        if (isneedbill == 1) {
            mpayment = "在线支付";
        }
        if (isneedbill == 0) {
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
