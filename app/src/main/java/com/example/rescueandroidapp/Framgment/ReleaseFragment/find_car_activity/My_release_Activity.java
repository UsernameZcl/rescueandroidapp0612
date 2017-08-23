package com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.fragment.Already_completed_Fragment;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.fragment.Car_seeking_Fragment;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.fragment.In_transit_Fragment;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.fragment.Lapsed_Fragment;
import com.example.rescueandroidapp.R;
import com.rescueandroid.update.network.util.MyBaseActivity;


/**
 * Created by ZCL on 2017/5/6.
 */
//找车的发布
public class My_release_Activity extends MyBaseActivity implements View.OnClickListener {

    private  ImageView mImageView;
    private  TextView mTextView;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4;
    private  FrameLayout mFrameLayout;
    private FragmentManager supportFragmentManager;
    private String a;
    private Fragment onfrg;
    private String[] strings = new String[]{"tag1", "tag2", "tag3", "tag4"};
    private Car_seeking_Fragment mCar_seeking_fragment;
    private In_transit_Fragment mIn_transit_fragment;
    private Already_completed_Fragment mAlready_completed_fragment;
    private Lapsed_Fragment mLapsed_fragment;
    private Drawable mDrawable;
    private Drawable mDrawable1;
    @Override
    public int setview() {
        return R.layout.my_release_activity;
    }

    @Override
    public void init() {
     mImageView= (ImageView) findViewById(R.id.my_release_activity_iv1);
        mTextView= (TextView) findViewById(R.id.my_release_activity_tv1);
        mImageView.setOnClickListener(this);
        mTextView.setOnClickListener(this);
        mRadioGroup = (RadioGroup) findViewById(R.id.my_release_activity_RG);
        mRadioButton1 = (RadioButton)findViewById(R.id.my_release_activity_rad1);
        mRadioButton2 = (RadioButton)findViewById(R.id.my_release_activity_rad2);
        mRadioButton3 = (RadioButton) findViewById(R.id.my_release_activity_rad3);
        mRadioButton4 = (RadioButton)findViewById(R.id.my_release_activity_rad4);
        mFrameLayout = (FrameLayout) findViewById(R.id.my_release_activity_fl);
    }


    @Override
    public void setbase() {
        supportFragmentManager = getSupportFragmentManager();
        if (a == null) {
            ////求车中
            mCar_seeking_fragment = new Car_seeking_Fragment();
            addfragment(mCar_seeking_fragment, strings[0], null);
            onfrg = mCar_seeking_fragment;

        }
        //运输中
        mIn_transit_fragment = new In_transit_Fragment();
        //已完成
        mAlready_completed_fragment = new Already_completed_Fragment();
        //已失效
        mLapsed_fragment = new Lapsed_Fragment();

        mDrawable = getResources().getDrawable(R.drawable.car);
        mDrawable1 = getResources().getDrawable(R.drawable.car_back);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.my_release_activity_rad1:
                        mRadioButton1.setBackground(mDrawable);
                        mRadioButton1.setTextColor(Color.parseColor("#4472d4"));
                        mRadioButton2.setTextColor(Color.parseColor("#323232"));
                        mRadioButton3.setTextColor(Color.parseColor("#323232"));
                        mRadioButton4.setTextColor(Color.parseColor("#323232"));

                        mRadioButton2.setBackground(mDrawable1);
                        mRadioButton3.setBackground(mDrawable1);
                        mRadioButton4.setBackground(mDrawable1);

                        refragment(onfrg, mCar_seeking_fragment, strings[0]);

                        onfrg = mCar_seeking_fragment;
                        break;
                    case R.id.my_release_activity_rad2:
                        mRadioButton2.setTextColor(Color.parseColor("#4472d4"));
                        mRadioButton1.setTextColor(Color.parseColor("#323232"));
                        mRadioButton3.setTextColor(Color.parseColor("#323232"));
                        mRadioButton4.setTextColor(Color.parseColor("#323232"));
                        mRadioButton2.setBackground(mDrawable);

                        mRadioButton1.setBackground(mDrawable1);
                        mRadioButton3.setBackground(mDrawable1);
                        mRadioButton4.setBackground(mDrawable1);
                        refragment(onfrg, mIn_transit_fragment, strings[1]);

                        onfrg = mIn_transit_fragment;
                        break;
                    case R.id.my_release_activity_rad3:
                        mRadioButton3.setTextColor(Color.parseColor("#4472d4"));
                        mRadioButton1.setTextColor(Color.parseColor("#323232"));
                        mRadioButton2.setTextColor(Color.parseColor("#323232"));
                        mRadioButton4.setTextColor(Color.parseColor("#323232"));
                        mRadioButton3.setBackground(mDrawable);
                        mRadioButton1.setBackground(mDrawable1);
                        mRadioButton2.setBackground(mDrawable1);
                        mRadioButton4.setBackground(mDrawable1);
                        refragment(onfrg, mAlready_completed_fragment, strings[2]);

                        onfrg = mAlready_completed_fragment;
                        break;
                    case R.id.my_release_activity_rad4:
                        mRadioButton4.setTextColor(Color.parseColor("#4472d4"));
                        mRadioButton1.setTextColor(Color.parseColor("#323232"));
                        mRadioButton2.setTextColor(Color.parseColor("#323232"));
                        mRadioButton3.setTextColor(Color.parseColor("#323232"));
                        mRadioButton4.setBackground(mDrawable);
                        mRadioButton1.setBackground(mDrawable1);
                        mRadioButton2.setBackground(mDrawable1);
                        mRadioButton3.setBackground(mDrawable1);
                        refragment(onfrg, mLapsed_fragment, strings[3]);

                        onfrg = mLapsed_fragment;

                        break;

                }
            }
        });

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.my_release_activity_iv1:
        finish();
        break;
    case R.id.my_release_activity_tv1:

    startActivity(new Intent(My_release_Activity.this,Release_Car_Supply_Activity.class));


        break;


}
    }
    public void addfragment(Fragment fragment, String tag, Fragment[] fragments) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (fragments != null) {
            for (int i = 0; i < fragments.length; i++) {
                fragmentTransaction.hide(fragments[i]);
            }
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.my_release_activity_fl, fragment, tag).show(fragment);
            fragmentTransaction.commit();
        } else {
            if (fragment == null) {
                fragment = supportFragmentManager.findFragmentByTag(tag);
            }
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();

        }

    }

    public void refragment(Fragment on, Fragment to, String tag) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.hide(on);
        if (!to.isAdded()) {
            fragmentTransaction.show(to).add(R.id.my_release_activity_fl, to, tag);

        } else {
            if (to == null) {
                to = supportFragmentManager.findFragmentByTag(tag);
            }
            fragmentTransaction.show(to);
        }
        fragmentTransaction.commit();
    }
}
