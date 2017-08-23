package com.example.rescueandroidapp.Framgment.OrderFragment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.rescueandroidapp.Framgment.OrderFragment.activity.OrderGrabDetailActivity;
import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyGrabOrderPay;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.adapter.MyRecyclerViewAdapter;
import com.rescueandroid.utils.myorder.utils.TextViewUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CC on 2017/5/25
 */

public class MyOrderGrabAdapter extends MyRecyclerViewAdapter<MyGrabOrderPay> {
    private Activity mContext;
    private View view;
    /**
     * 构造器
     *
     * @param mContext  上下文
     * @param mDataBase 数据
     * @param mLayout   item布局
     */
    public MyOrderGrabAdapter(Activity mContext, List<MyGrabOrderPay> mDataBase, int mLayout) {
        super(mContext, mDataBase, mLayout);
        this.mContext = mContext;
    }

    @Override
    public void onCCBind(MyViewHolder holder, final MyGrabOrderPay myOrderPay, int position) {
        if ("1".equals(myOrderPay.getItem()) || "2".equals(myOrderPay.getItem())) {
            TextViewUtils.setTxt(holder.getInId(R.id.item_pay_endPoint),myOrderPay.getToaddr());
        }else{
            TextViewUtils.setTxt(holder.getInId(R.id.item_pay_endPoint),myOrderPay.getStoreposition());
        }
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_orderNum),myOrderPay.getName());
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_create_time), TextViewUtils.startTxt(myOrderPay.getPutdate(),"T"));//metest
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_startPoint),myOrderPay.getFromaddr());
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_orderMoney),"联系电话 "+myOrderPay.getAsktel());

        //TODO item中点击监听
        RelativeLayout item_pay_go = (RelativeLayout) holder.getInId(R.id.item_pay_go);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderGrabDetailActivity.class);
                intent.putExtra("MyGrabOrderPay",(Serializable) myOrderPay);
                mContext.startActivity(intent);
            }
        };
        item_pay_go.setOnClickListener(listener);
    }
}
