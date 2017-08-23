package com.rescueandroid.utils.ui;

import android.content.Context;

import android.widget.EditText;

import com.rescueandroid.utils.SystemUtil;

public class VerifyInputUtils {

	public static boolean isNotNull(Context context, EditText et) {
		if (et.getText().toString().trim().length() == 0) {
			DialogUtils.showPopMsg(context, "������" + et.getHint());
			et.requestFocus();
			SystemUtil.showInputWindows(context);
			return false;
		}
		return true;
	}

	public static boolean isNotNull(Context context, EditText et, String msg) {
		if (et.getText().toString().trim().length() == 0) {
			DialogUtils.showPopMsg(context, msg);
			et.requestFocus();
			SystemUtil.showInputWindows(context);
			return false;
		}
		return true;
	}
}
