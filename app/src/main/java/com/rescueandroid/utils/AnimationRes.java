package com.rescueandroid.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationRes {
	 /**  
     * ������Ҳ����Ķ���Ч��  
     * @return  
     */  
	public static Animation inFromRightAnimation() {  
        Animation inFromRight = new TranslateAnimation(  
                Animation.RELATIVE_TO_PARENT, +1.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f);  
        inFromRight.setDuration(200);  
        inFromRight.setInterpolator(new AccelerateInterpolator());  
        return inFromRight;  
    }  
   
    /**  
     * ���������˳��Ķ���Ч��  
     * @return  
     */  
    public static Animation outToLeftAnimation() {  
        Animation outtoLeft = new TranslateAnimation(  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, -1.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f);  
        outtoLeft.setDuration(200);  
        outtoLeft.setInterpolator(new AccelerateInterpolator());  
        return outtoLeft;  
    }  
   
    /**  
     * �����������Ķ���Ч��  
     * @return  
     */  
    public static Animation inFromLeftAnimation() {  
        Animation inFromLeft = new TranslateAnimation(  
                Animation.RELATIVE_TO_PARENT, -1.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f);  
        inFromLeft.setDuration(200);  
        inFromLeft.setInterpolator(new AccelerateInterpolator());  
        return inFromLeft;  
    }  
    
    /**  
     * ������Ҳ��˳�ʱ�Ķ���Ч��  
     * @return  
     */  
    public static Animation outToRightAnimation() {  
        Animation outtoRight = new TranslateAnimation(  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, +1.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_PARENT, 0.0f);  
        outtoRight.setDuration(200);  
        outtoRight.setInterpolator(new AccelerateInterpolator());  
        return outtoRight;  
    }  
}
