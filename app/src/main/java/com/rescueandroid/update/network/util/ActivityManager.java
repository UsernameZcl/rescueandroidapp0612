/**
 * Copyright(c)2012 Beijing PeaceMap Co. Ltd.
 * All right reserved. 
 */
package com.rescueandroid.update.network.util;

import java.util.Stack;

import android.app.Activity;


/**
 * Activity管理器
 * @author： aokunsang
 * @date： 2012-12-21
 */
public class ActivityManager {

	private static ActivityManager activityManager;
	private static Stack<Activity> activityList = new Stack<Activity>();

	private ActivityManager(){}

	public static ActivityManager getInstance(){
		if(activityManager==null){
			activityManager = new ActivityManager();
		}
		return activityManager;
	}

	/**
	 * 把当前Activity放入栈中
	 * @param activity
	 */
	public void pushActivity(Activity activity){
		activityList.push(activity);
	}

	/**
	 * 把当前Activity移除
	 * @param activity
	 */
	public void popActivity(Activity activity){
		if(activity!=null){
			activity.finish();
			activityList.remove(activity);
			activity = null;
		}
	}
	/**
	 * 退出系统
	 */
	public void exit(){
		while(!activityList.isEmpty()){
			popActivity(activityList.pop());
		}
		System.exit(0);
	}
}
