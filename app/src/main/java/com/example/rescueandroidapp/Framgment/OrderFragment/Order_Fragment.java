package com.example.rescueandroidapp.Framgment.OrderFragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rescueandroidapp.Framgment.OrderFragment.activity.MyGrabOrderActivity;
import com.example.rescueandroidapp.Framgment.OrderFragment.activity.MyOrderActivity;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.LoginActivity;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.utils.myorder.customView.MutilColorTextView;
import com.rescueandroid.utils.ui.WindowsBase;

/**
 * 订单
 */
public class Order_Fragment extends WindowsBase implements View.OnClickListener {
    public static Order_Fragment mOrder_Fragment = null;
    private LayoutInflater factory;

    private View view;
    private MutilColorTextView view1,view2,view3;

    public Order_Fragment(Context context) {
        super(context);
        mOrder_Fragment=this;
        factory = LayoutInflater.from(context);
        view = factory.inflate(R.layout.order_fragment, null);
        addView(view, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        initView();
        setupView();
    }

    /**
     * 重新看到该碎片会重新根据登陆身份进行显示
     * */
    @Override
    public void onResume() {
        super.onResume();
        setVisibity();
    }

    /**
     * 重新看到该碎片会重新根据登陆身份进行显示
     * */
    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        setVisibity();
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    /**
     * 根据登陆身份进行显示
     * */
    private void setVisibity(){
        if ("11".equals(Data.getInstance().roleid)) {
            // metest
            view2.setVisibility(VISIBLE);
            view3.setVisibility(VISIBLE);
        }else if ("12".equals(Data.getInstance().roleid)) {
            // metest
            view2.setVisibility(VISIBLE);
            view3.setVisibility(VISIBLE);
        }else if ("13".equals(Data.getInstance().roleid)) {
            // metest
            view2.setVisibility(VISIBLE);
            view3.setVisibility(VISIBLE);
        }else{
            view2.setVisibility(GONE);
            view3.setVisibility(GONE);
        }
    }

    private void setupView() {
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
    }

    private void initView() {
        view1 = (MutilColorTextView) view.findViewById(R.id.view1);
        view2 = (MutilColorTextView) view.findViewById(R.id.view2);
        view3 = (MutilColorTextView) view.findViewById(R.id.view3);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view1:
                if (Data.getInstance().isLogin == false) {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }else {
                    getContext().startActivity(new Intent(getContext(), MyOrderActivity.class));
                }
                break;
            case R.id.view2:
            case R.id.view3:
                Intent intent = new Intent(getContext(), MyGrabOrderActivity.class);
                intent.putExtra("title",(String)v.getTag());
                getContext().startActivity(intent);
                break;
        }
    }
}
