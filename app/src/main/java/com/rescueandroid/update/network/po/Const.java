/**
 * Copyright(c)2012 Beijing PeaceMap Co. Ltd.
 * All right reserved. 
 */
package com.rescueandroid.update.network.po;

import java.io.File;


import android.os.Environment;

import com.rescueandroid.config.Define;


/**
 * 常量类
 * @author： aokunsang
 * @date： 2012-12-18
 */
public final class Const {

	/* 检查是否升级url*/
	public final static String apkCheckUpdateUrl = Define.BASEURL + "/user.php?fun=apkupdate";
	/* 在多少天内不检查升级 */
	public final static int defaultMinUpdateDay = 0;

	public final static String apkSavepath = Environment.getExternalStorageDirectory().getPath() + "/_update.apk";
}
