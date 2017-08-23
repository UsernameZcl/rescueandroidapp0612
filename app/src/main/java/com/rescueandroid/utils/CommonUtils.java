package com.rescueandroid.utils;

import java.util.List;

public class CommonUtils {

	public CommonUtils() {
		// TODO Auto-generated constructor stub
	}

	public static List copyList(List dstList, List srcList) {
		for (int i = 0; i < srcList.size(); i++) {
			dstList.add(srcList.get(i));
		}
		return dstList;
	}
}
