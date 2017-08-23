package com.example.rescueandroidapp.Framgment.OrderFragment.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.OrderFragment.activity.OrderDetailActivity;
import com.example.rescueandroidapp.Framgment.OrderFragment.model.MyOrderPay;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.adapter.MyRecyclerViewAdapter;
import com.rescueandroid.utils.myorder.utils.BaseModel;
import com.rescueandroid.utils.myorder.utils.HttpAppUtils;
import com.rescueandroid.utils.myorder.utils.JsonCallBack;
import com.rescueandroid.utils.myorder.utils.JsonCallBackString;
import com.rescueandroid.utils.myorder.utils.TextViewUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CC on 2017/5/25
 */

public class MyOrderPayAdapter extends MyRecyclerViewAdapter<MyOrderPay> {
    private Activity mContext;
    private View view;
    private List<MyOrderPay> mDataBase;

    /**
     * 构造器
     *
     * @param mContext  上下文
     * @param mDataBase 数据
     * @param mLayout   item布局
     */
    public MyOrderPayAdapter(Activity mContext, List<MyOrderPay> mDataBase, int mLayout) {
        super(mContext, mDataBase, mLayout);
        this.mContext = mContext;
        this.mDataBase = mDataBase;
    }

    @Override
    public void onCCBind(MyViewHolder holder, final MyOrderPay myOrderPay, int position) {
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_price), myOrderPay.getPaytotal() + "元");
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_OrderNo), myOrderPay.getOrderno());
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_CreateOn), myOrderPay.getCreateon(), "T");
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_fromaddr), myOrderPay.getFromaddr());
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_toaddr), myOrderPay.getToaddr());
        TextViewUtils.setTxt(holder.getInId(R.id.item_pay_carTime), TextViewUtils.startTxt(myOrderPay.getLoaddatestart(), "T") + "->" + TextViewUtils.startTxt(myOrderPay.getArrivaldateend(), "T"));

        //TODO item中点击监听
        RelativeLayout item_pay_go = (RelativeLayout) holder.getInId(R.id.item_pay_go);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("myOrderPay", (Serializable) myOrderPay);
                mContext.startActivity(intent);
            }
        };
        item_pay_go.setOnClickListener(listener);

        String paystate = myOrderPay.getPaystate();
        // 支付状态paystate  0未支付1已支付
        if ("1".equals(paystate)) {
            holder.getInId(R.id.item_txt_paystate).setVisibility(View.GONE);
            holder.getInId(R.id.item_pay_change_money).setVisibility(View.GONE);
        } else if ("0".equals(paystate)) {
            holder.getInId(R.id.item_txt_paystate).setVisibility(View.VISIBLE);
            holder.getInId(R.id.item_pay_change_money).setVisibility(View.VISIBLE);
            TextView item_pay_change_money = (TextView) holder.getInId(R.id.item_pay_change_money);
            View.OnClickListener listener2 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    view = mContext.getLayoutInflater().inflate(R.layout.dialog_change_money, null);
                    DialogInterface.OnClickListener cancel = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*int a = mDataBase.indexOf(myOrderPay);
                            mDataBase.remove(myOrderPay);
                            notifyItemRemoved(a);*/
                        }
                    };
                    DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO 获取Dialog中的数字
                            EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
                            String money = editText.getText().toString();
                            if (!TextUtils.isEmpty(editText.getText())) {
                                JsonCallBackString jsonCallBack = new JsonCallBackString() {
                                    @Override
                                    public void resultData(int requestCode, int resultCode, String reply) {
                                        HttpAppUtils.getOrder(mContext, new JsonCallBack() {
                                            @Override
                                            public void resultData(int requestCode, int resultCode, BaseModel reply) {
                                                List<MyOrderPay> data = (List<MyOrderPay>) reply.getData();
                                                upDatas(data);
                                            }
                                        }, 120003, "sfsy@163.com", "123456", "1", "0");
                                    }
                                };
                                HttpAppUtils.upPayTotal(mContext, jsonCallBack, 120003, "sfsy@163.com", "123456", money, myOrderPay.getId());
                            }
                        }
                    };
                    builder.setView(view)
                            .setNegativeButton("取消", cancel)
                            .setNeutralButton("确定", ok);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(true);
                    alertDialog.show();
                }
            };
            item_pay_change_money.setOnClickListener(listener2);
        }
    }
}
