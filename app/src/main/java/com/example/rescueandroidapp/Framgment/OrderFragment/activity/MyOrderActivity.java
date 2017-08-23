package com.example.rescueandroidapp.Framgment.OrderFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.OrderFragment.adapter.MyOrderPayAdapter;
import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyOrderPay;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.utils.BaseModel;
import com.rescueandroid.utils.myorder.utils.HttpAppUtils;
import com.rescueandroid.utils.myorder.utils.HttpJsonUtils;
import com.rescueandroid.utils.myorder.utils.JsonCallBack;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.rescueandroidapp.Framgment.OrderFragment.activity.SelectPayStateDialog.DIALOG_PAY_RESULT;
import static com.rescueandroid.utils.myorder.utils.HttpAppUtils.ADDDATE;
import static com.rescueandroid.utils.myorder.utils.HttpAppUtils.UPDATE;


public class MyOrderActivity extends AutoLayoutActivity implements View.OnClickListener, JsonCallBack {

    private RecyclerView m_myOrder_List;
    private TextView m_myOrder_spinner;
    private ImageView m_myOrder_back;
    private TextView m_myOrder_lookup_center;
    private TextView m_myOrder_lookup_right;

    private List<MyOrderPay> datas;
    private MyOrderPayAdapter myOrderPayAdapter;

    public static int DIALOG_PAY_SEND = 10001;

    private String item = "1";
    private String paystate = "0";
    private LinearLayout m_canVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initView();
        setupView();
        initRecyclerView(m_myOrder_List);
        initData();
    }

    private void initData() {
        // TODO 网络获取回调
        HttpAppUtils.getOrder(this, this, UPDATE , "sfsy@163.com", "123456", item, paystate);
//        HttpAppUtils.answerFrom(this, null, REQUEST_CODE, "sfsy@163.com", "123456", item, paystate,"1");
//        HttpAppUtils.addorderinfo(this, null, REQUEST_CODE + 333, "sfsy@163.com", "123456", item, paystate);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO 在用户返回该界面时根据已经设置好的 item和paystate变量 更新数据
        if ("1".equals(item)) {
            HttpAppUtils.getOrderNoLoading(this, this, UPDATE , "sfsy@163.com", "123456", item, paystate);
        }else if ("3".equals(item)) {
            myOrderPayAdapter.clearAll();
            HttpAppUtils.getOrderNoLoading(this, this, ADDDATE , "sfsy@163.com", "123456", item, "0");
            HttpAppUtils.getOrderNoLoading(this, this, ADDDATE , "sfsy@163.com", "123456", item, "1");
        }
    }

    private void initRecyclerView(RecyclerView m_myOrder_List) {
        datas = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        m_myOrder_List.setLayoutManager(layoutManager);
        myOrderPayAdapter = new MyOrderPayAdapter(this, datas, R.layout.item_order_pay);
        m_myOrder_List.setAdapter(myOrderPayAdapter);
    }

    private void setupView() {
        m_myOrder_spinner.setOnClickListener(this);
        m_myOrder_lookup_right.setOnClickListener(this);
        m_myOrder_back.setOnClickListener(this);
    }

    private void initView() {
        m_myOrder_List = (RecyclerView) findViewById(R.id.myOrder_List);
        m_myOrder_spinner = (TextView) findViewById(R.id.myOrder_spinner);
        m_myOrder_back = (ImageView) findViewById(R.id.myOrder_back);
        m_myOrder_lookup_center = (TextView) findViewById(R.id.myOrder_lookup_center);
        m_myOrder_lookup_right = (TextView) findViewById(R.id.myOrder_lookup_right);
        m_canVisible = (LinearLayout) findViewById(R.id.canVisible);
    }

    /**
     * TODO 网络获取回调
     */
    @Override
    public void resultData(int requestCode, int resultCode, BaseModel reply) {
        switch (requestCode) {
            case UPDATE:
                if (resultCode == HttpJsonUtils.RESULT_SUCCESS) {
                    List<MyOrderPay> newData = (List<MyOrderPay>) reply.getData();
                    myOrderPayAdapter.upDatas(newData);
                }
                break;
            case ADDDATE:
                if (resultCode == HttpJsonUtils.RESULT_SUCCESS) {
                    List<MyOrderPay> newData = (List<MyOrderPay>) reply.getData();
                    myOrderPayAdapter.addDatas(newData);
                }
                break;
        }
    }

    private boolean isConnon = true;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myOrder_spinner:
                Intent intent = new Intent(this, SelectPayStateDialog.class);
                startActivityForResult(intent, DIALOG_PAY_SEND);
                break;
            case R.id.myOrder_lookup_right:
                if (isConnon) {
                    m_myOrder_lookup_center.setText("货主-找库");
                    m_myOrder_lookup_right.setText("货主-找车");
                    m_canVisible.setVisibility(View.GONE);
                    // TODO 更新数据 ：货主-找库
                    myOrderPayAdapter.clearAll();
                    item = "3";
                    HttpAppUtils.getOrder(this, this, ADDDATE , "sfsy@163.com", "123456", item, "0");
                    HttpAppUtils.getOrder(this, this, ADDDATE , "sfsy@163.com", "123456", item, "1");
                    isConnon = false;
                }else{
                    m_myOrder_lookup_center.setText("货主-找车");
                    m_myOrder_lookup_right.setText("货主-找库");
                    m_canVisible.setVisibility(View.VISIBLE);
                    // TODO 更新数据 ：货主-找车 根据paystate判定支付未支付
                    myOrderPayAdapter.clearAll();
                    item = "1";
                    HttpAppUtils.getOrder(this, this, UPDATE , "sfsy@163.com", "123456", item, paystate);
                    isConnon = true;
                }
                break;
            case R.id.myOrder_back:
                finish();
                break;
        }
    }

    /**
     * TODO Dialog返回
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DIALOG_PAY_SEND && resultCode == DIALOG_PAY_RESULT) {
            String txt = data.getStringExtra("txt");
            switch (txt) {
                case "取消":
                    break;
                default:
                    m_myOrder_spinner.setText(txt);
                    // 根据选择的状态显示数据，有Dialog返回的intent中获取
                    paystate = data.getStringExtra("paystate");
                    // TODO 更新数据 ：货主-找车 根据paystate判定支付未支付
                    HttpAppUtils.getOrder(this, this, UPDATE , "sfsy@163.com", "123456", item, paystate);
                    break;
            }
        }
    }
}
