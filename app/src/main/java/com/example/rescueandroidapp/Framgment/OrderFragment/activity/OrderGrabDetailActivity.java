package com.example.rescueandroidapp.Framgment.OrderFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyGrabOrderPay;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils;
import com.rescueandroid.utils.myorder.utils.TextViewUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createAddr;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createAddr12;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createPerson;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createStoreInfo;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createTextViewEmpty;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createTitle;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.initLayout;


public class OrderGrabDetailActivity extends AutoLayoutActivity implements View.OnClickListener {
    private MyGrabOrderPay myGrabOrderPay;

    private ImageView m_order_detail_back;
    private TextView m_order_detail_change_money;
    private LinearLayout m_view_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab_order_detail);
        getTheIntent();
        initView();
        setupView();
    }

    private void initView() {
        m_order_detail_back = (ImageView) findViewById(R.id.order_detail_back);
        m_order_detail_change_money = (TextView) findViewById(R.id.order_detail_change_money);
    }

    private void setupView() {
        m_view_linear = (LinearLayout) findViewById(R.id.view_linear);
        //屏幕适配计算
        initLayout(this);
        // title
        m_view_linear.addView(createTitle(this, "货主抢单中，等待承运方选择"));
        //出发地 结束地
        m_view_linear.addView(createTextViewEmpty(this,null));
        if ("1".equals(myGrabOrderPay.getItem()) || "2".equals(myGrabOrderPay.getItem())) {
            m_view_linear.addView(createAddr12(this
                    ,myGrabOrderPay.getFromaddr()
                    ,myGrabOrderPay.getToaddr()
                    ,TextViewUtils.startTxt(myGrabOrderPay.getLoaddatestart(),"T")
                    ,TextViewUtils.startTxt(myGrabOrderPay.getArrivaldateend(),"T")));
        }else{
            m_view_linear.addView(createAddr(this, myGrabOrderPay.getStoreposition()));
        }
        //货源信息
        m_view_linear.addView(createTextViewEmpty(this,"货源信息"));
        JavaInterfaceUtils.GridBulid gridBulid = new JavaInterfaceUtils.GridBulid()
                .add("货物名称:",myGrabOrderPay.getName())
                .add("货物品类:",myGrabOrderPay.getCategoryname())
                .add("货物类型:",myGrabOrderPay.getTypename())
                .add("货物规格:",myGrabOrderPay.getSpec()+myGrabOrderPay.getUnit())
                .add("需要车长:",myGrabOrderPay.getCarlength())
                .add("需要车型:",myGrabOrderPay.getCartypename())
                .add("温度要求:",myGrabOrderPay.getTempmin()+"度"+" → "+myGrabOrderPay.getTempmax()+"度")
                .add("支付方式:",TextViewUtils.getIntToValue(myGrabOrderPay.getPayment(),"Price值为0","现金到付"))
                .add("价格:"    ,myGrabOrderPay.getPrice())
                .add("到货时间:",TextViewUtils.startTxt(myGrabOrderPay.getArrivaldateend(),"T")+" "+ TextViewUtils.incloudTxt(myGrabOrderPay.getArrivaldateend(),"T","+"))
                .add("联系电话:",myGrabOrderPay.getAsktel())
                .add("备注:"    ,myGrabOrderPay.getRemark());
        m_view_linear.addView(createStoreInfo(this, gridBulid, 0.3, 0.7));
        //承运方信息
        m_view_linear.addView(createTextViewEmpty(this,"承运方信息"));
        JavaInterfaceUtils.GridBulid gridBulid2 = new JavaInterfaceUtils.GridBulid()
                .add("承运方:",myGrabOrderPay.getAnsweruser())
                .add("联系电话:",myGrabOrderPay.getAnswertel());
        m_view_linear.addView(createPerson(this, R.mipmap.head_portrait_big, gridBulid2));

        m_order_detail_back.setOnClickListener(this);
        m_order_detail_change_money.setOnClickListener(this);
    }

    /**
     * TODO 获取intent
     */
    private void getTheIntent() {
        Intent intent = getIntent();
        myGrabOrderPay = (MyGrabOrderPay) intent.getSerializableExtra("MyGrabOrderPay");
    }

    /**
     * TODO 点击监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_detail_back:
                finish();
                break;
            case R.id.order_detail_change_money:
                //已隐藏
                break;
        }
    }
}
