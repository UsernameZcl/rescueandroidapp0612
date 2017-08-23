package com.example.rescueandroidapp.Framgment.OrderFragment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyOrderPay;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.utils.HttpAppUtils;
import com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createAddr;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createAddr12;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createPerson;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createStoreInfo;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createTextViewEmpty;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.createTitle;
import static com.rescueandroid.utils.myorder.utils.JavaInterfaceUtils.initLayout;
import static com.rescueandroid.utils.myorder.utils.TextViewUtils.incloudTxt;
import static com.rescueandroid.utils.myorder.utils.TextViewUtils.startTxt;


public class OrderDetailActivity extends AutoLayoutActivity implements View.OnClickListener {
    private MyOrderPay myOrderPay;

    private ImageView m_order_detail_back;
    private TextView m_order_detail_change_money;

    private View view;
    private String paystate;
    private String item;
    private LinearLayout m_view_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_already);
        getTheIntent();
        setJudgeAndSetView();
        initView();
        setupView();
    }

    private void setJudgeAndSetView() {
        paystate = myOrderPay.getPaystate();
        item = myOrderPay.getItem();
    }

    private void initView() {
        m_order_detail_back = (ImageView) findViewById(R.id.order_detail_back);
        m_order_detail_change_money = (TextView) findViewById(R.id.order_detail_change_money);
        m_view_linear = (LinearLayout) findViewById(R.id.view_linear);
    }

    private void setupView() {
        //屏幕适配计算
        initLayout(this);
        if ("1".equals(paystate)) {
            // 支付状态paystate  state == 1  TODO 已支付 状态
            m_order_detail_change_money.setVisibility(View.GONE);
            //如果详情界面是 货主-找库 则隐藏 m_order_title
            if (!"3".equals(item)) {
                m_view_linear.addView(createTitle(this, "已完成"));
            }
            m_view_linear.addView(createTextViewEmpty(this, null));
            m_view_linear.addView(createAddr12(this
                    , myOrderPay.getFromaddr()
                    , myOrderPay.getToaddr()
                    , startTxt(myOrderPay.getLoaddatestart(),"T")
                    , startTxt(myOrderPay.getArrivaldateend(),"T")));
            m_view_linear.addView(createTextViewEmpty(this, "货源信息"));
            JavaInterfaceUtils.GridBulid gridBulid = new JavaInterfaceUtils.GridBulid()
                    .add("货物名称:",myOrderPay.getName())
                    .add("货物品类:",myOrderPay.getCategoryname())
                    .add("货物类型:",myOrderPay.getTypename())
                    .add("货物规格:",myOrderPay.getSpec()+myOrderPay.getUnit())
                    .add("需要车长:",myOrderPay.getCarlength())
                    .add("温度要求:",myOrderPay.getTempmin()+"度"+" → "+myOrderPay.getTempmax()+"度")
                    .add("到货时间:",startTxt(myOrderPay.getArrivaldateend(),"T")+" "+incloudTxt(myOrderPay.getArrivaldateend(),"T","+"));
            m_view_linear.addView(createStoreInfo(this, gridBulid, 0.3, 0.7));
            JavaInterfaceUtils.GridBulid gridBulid2 = new JavaInterfaceUtils.GridBulid()
                    .add("发货人:",myOrderPay.getAnsweruser())
                    .add("联系电话:",myOrderPay.getAnswertel());
            m_view_linear.addView(createPerson(this, R.mipmap.head_portrait, gridBulid2));
            // empty
            m_view_linear.addView(createTextViewEmpty(this, "承运方信息"));
            JavaInterfaceUtils.GridBulid gridBulid3 = new JavaInterfaceUtils.GridBulid()
                    .add("承运方:"  ,myOrderPay.getAskuser())
                    .add("联系电话:",myOrderPay.getAsktel());
            m_view_linear.addView(createStoreInfo(this, gridBulid3, 0.3, 0.7));
            m_view_linear.addView(createTextViewEmpty(this, null));
            JavaInterfaceUtils.GridBulid gridBulid4 = new JavaInterfaceUtils.GridBulid()
                    .add("订单编号:",myOrderPay.getOrderno())
                    .add("生成时间:",startTxt(myOrderPay.getCreateon(),"T"));
            m_view_linear.addView(createStoreInfo(this, gridBulid4, 0.3, 0.7));
        }else if ("0".equals(paystate)) {
            // state == 0 TODO 未支付 状态
            m_view_linear.addView(createTitle(this, "待修改价格"));
            m_view_linear.addView(createTextViewEmpty(this, null));
            m_view_linear.addView(createAddr12(this
                    , myOrderPay.getFromaddr()
                    , myOrderPay.getToaddr()
                    , startTxt(myOrderPay.getLoaddatestart(),"T")
                    , startTxt(myOrderPay.getArrivaldateend(),"T")));
            m_view_linear.addView(createTextViewEmpty(this, "承运方信息"));
            JavaInterfaceUtils.GridBulid gridBulid3 = new JavaInterfaceUtils.GridBulid()
                    .add("承运方:",myOrderPay.getAskuser())
                    .add("联系电话:",myOrderPay.getAsktel());
            m_view_linear.addView(createStoreInfo(this, gridBulid3, 0.3, 0.7));
            m_view_linear.addView(createTextViewEmpty(this, "货源信息"));
            JavaInterfaceUtils.GridBulid gridBulid = new JavaInterfaceUtils.GridBulid()
                    .add("货物名称:",myOrderPay.getName())
                    .add("货物品类:",myOrderPay.getCategoryname())
                    .add("货物类型:",myOrderPay.getTypename())
                    .add("货物规格:",myOrderPay.getSpec()+myOrderPay.getUnit())
                    .add("需要车长:",myOrderPay.getCarlength())
                    .add("需要车型:",myOrderPay.getCartypename())
                    .add("温度要求:",myOrderPay.getTempmin()+"度"+" → "+myOrderPay.getTempmax()+"度")
                    .add("备注:"    ,myOrderPay.getRemark())
                    .add("到货时间:",startTxt(myOrderPay.getArrivaldateend(),"T")+" "+incloudTxt(myOrderPay.getArrivaldateend(),"T","+"));
            m_view_linear.addView(createStoreInfo(this, gridBulid, 0.3, 0.7));
            JavaInterfaceUtils.GridBulid gridBulid2 = new JavaInterfaceUtils.GridBulid()
                    .add("发货人:"  ,myOrderPay.getAnsweruser())
                    .add("联系电话:",myOrderPay.getAnswertel());
            m_view_linear.addView(createPerson(this, R.mipmap.head_portrait, gridBulid2));
            m_view_linear.addView(createTextViewEmpty(this, null));
            JavaInterfaceUtils.GridBulid gridBulid4 = new JavaInterfaceUtils.GridBulid()
                    .add("订单编号:",myOrderPay.getOrderno())
                    .add("生成时间:",startTxt(myOrderPay.getCreateon(),"T"));
            m_view_linear.addView(createStoreInfo(this, gridBulid4, 0.3, 0.7));
        }
        m_order_detail_back.setOnClickListener(this);
        m_order_detail_change_money.setOnClickListener(this);
    }

    /**
     * TODO 获取intent
     */
    private void getTheIntent() {
        Intent intent = getIntent();
        myOrderPay = (MyOrderPay) intent.getSerializableExtra("myOrderPay");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
                view = OrderDetailActivity.this.getLayoutInflater().inflate(R.layout.dialog_change_money, null);
                DialogInterface.OnClickListener cancel = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                };
                DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 获取Dialog中的数字
                        EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
                        String money = editText.getText().toString();
                        HttpAppUtils.upPayTotal(OrderDetailActivity.this, null, 120003, "sfsy@163.com", "123456", money, myOrderPay.getId());
                    }
                };
                builder.setView(view)
                        .setNegativeButton("取消", cancel)
                        .setNeutralButton("确定", ok);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
                break;
        }
    }
}
