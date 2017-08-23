package com.rescueandroid.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;


public class Data {
	private static Data data = null;
	public boolean isLogin = false;
	public Map mapUser = new HashMap();
	// public String Imgurl = Define.getPathBase() +"/Book/Resource/";
	public List listBanner = new ArrayList();
	public String provinceName;
	public int type = 0;
	public String username,realname,roleid,photo,pwd;

	public static Data getInstance() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}

	public List listThread = new ArrayList();

	public void delThread(int bookcode) {
		int sel = -1;
		for (int i = 0; i < listThread.size(); i++) {
			Map map = (Map) listThread.get(i);
			int _bookcode = (Integer) map.get("bookcode");
			if (bookcode == _bookcode) {
				sel = i;
			}
		}

		if (sel >= 0)
			listThread.remove(sel);
	}

	public Map getThread(int bookcode) {
		for (int i = 0; i < listThread.size(); i++) {
			Map map = (Map) listThread.get(i);
			int _bookcode = (Integer) map.get("bookcode");
			if (bookcode == _bookcode) {
				return map;
			}
		}
		return null;
	}
}
