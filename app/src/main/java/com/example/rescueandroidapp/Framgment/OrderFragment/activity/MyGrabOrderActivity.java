package com.example.rescueandroidapp.Framgment.OrderFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.OrderFragment.adapter.MyFragmentPagerAdapter;
import com.example.rescueandroidapp.Framgment.OrderFragment.fragment.GrabOrderFragment;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.myorder.customView.MyViewPager;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MyGrabOrderActivity extends AutoLayoutActivity implements View.OnClickListener {

    private TabLayout m_grab_order_tab;
    private MyViewPager m_grab_order_vp;
    private ImageView m_grab_order_back;
    private TextView m_grab_order_title;

    private List<Fragment> data;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grab_order);
        getTheIntent();
        initView();
        setupView();
    }

    private void getTheIntent() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
    }

    private void setupView() {
        m_grab_order_title.setText(title);
        String[] titles = new String[2];
        if (title.equals("我的抢单")) {
            titles = new String[]{"抢路线", "抢货源", "抢仓库"};
        }else if (title.equals("接受订单")){
            titles = new String[]{"接路线", "接货源", "接仓库"};
        }
        // ViewPager TabLayout

        data = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            data.add(new GrabOrderFragment());
        }

        m_grab_order_vp.setOffscreenPageLimit(3);
        m_grab_order_vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), data, titles));
        m_grab_order_tab.setupWithViewPager(m_grab_order_vp);

        m_grab_order_tab.setTabMode(TabLayout.MODE_FIXED);
        m_grab_order_tab.setTabGravity(TabLayout.GRAVITY_FILL);
        // other
        m_grab_order_back.setOnClickListener(this);
        m_grab_order_title.setOnClickListener(this);
    }

    private void initView() {
        m_grab_order_tab = (TabLayout) findViewById(R.id.grab_order_tab);
        m_grab_order_vp = (MyViewPager) findViewById(R.id.grab_order_vp);
        m_grab_order_back = (ImageView) findViewById(R.id.grab_order_back);
        m_grab_order_title = (TextView) findViewById(R.id.grab_order_title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grab_order_back:
                finish();
                break;
            case R.id.grab_order_title:
//                GrabOrderFragment fragment = (GrabOrderFragment) data.get(m_grab_order_vp.getCurrentItem());
//                fragment.initData("2");
                break;
        }
    }
}
