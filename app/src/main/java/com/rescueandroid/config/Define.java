package com.rescueandroid.config;

import android.graphics.Point;
import android.os.Environment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;


public class Define {
	public static boolean isNoImgTestMode = false;

	public static int XListLoad_TYPE_REFRESH = 1;
	public static int XListLoad_TYPE_LOADMORE = 2;
	public static int XListLoad_TYPE_SHOW = 3;
	public static int Default_MapHeihgt = 160;
	public static int CountEveryPage = 6;
	public static int RES_OK = 0; // http://test.spider.com.cn:8091
	public static String BASEADDR = "http://114.215.122.32:16006";//测试版
	public static String BASEADDR1 = BASEADDR + "/WqService/img/";// "http://mreadinter.spider.com.cn/";//"http://test.spider.com.cn:8098/v2.4.0/";
	public static String BASEURL = BASEADDR;
	public static String key = "tpjddsf";
	public static String skey = "b35$45%698@65h#Ak#ff$cS%adc";
	public static Boolean isDebugPrice = true;
	public static String key1 = "tianpingapp2014"; 
	public static String skey1 = "a25$54%589@65d#Ad#fh$aS%dfe"; 

	public static int DATATYPE_REMOTE = 1;
	public static int DATATYPE_LOCAL = 2;
	public static int SMALLPIC_WIDTH = 100;
	public static int TITLE_FONTSIZE = 20;
	public static String SPACE = "         ";
	public static int SHOOT_AND_GETPHOTO = 501000;

	public static String getImgPath() {
		return BASEURL + "/img/";
	}

	public static String getHeadImgPath() {
		return BASEURL + "/img/head/";
	}

	public static Point formatTextPoint(String text) {
		String str = text.substring(6, text.length() - 1);
		String arr[] = str.split(" ");
		return new Point((int) (new Double(arr[0]) * 1E6), (int) (new Double(arr[1]) * 1E6));
	}

	public static String getPathBase() {
		return Environment.getExternalStorageDirectory().getPath() + "/jigouce/";
	}

	public static DisplayImageOptions getDisplayImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory() 
				.cacheOnDisc() 
				.build();
		return options;
	}
}
