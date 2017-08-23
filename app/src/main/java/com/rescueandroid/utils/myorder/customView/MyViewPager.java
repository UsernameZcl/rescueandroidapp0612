package com.rescueandroid.utils.myorder.customView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by CC on 2017/6/6.
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //去除页面的滑动功能
    /*@Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }*/

    //去除页面切换时的滑动翻页效果
    /*@Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }*/
}
