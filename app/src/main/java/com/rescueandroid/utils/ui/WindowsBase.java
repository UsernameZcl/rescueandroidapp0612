package com.rescueandroid.utils.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 窗体基类
 *
 * @author mjf
 */
public class WindowsBase extends LinearLayout {
	public boolean isInit = false;
	public boolean isShowMainMenu = true;
	public String windowKey = "";
	public boolean isRootWindow = false;

	public WindowsBase(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setWindowKey(String windowKey) {
		this.windowKey = windowKey;
	}

	public String getWindowKey() {
		return this.windowKey;
	}

	public void setShowMainMenu(boolean isShowMainMenu) {
		this.isShowMainMenu = isShowMainMenu;
	}

	public void onInit() {
		isInit = true;
	}

	protected void onFastRefresh() {
	}

	protected void onStart() {
	}

	public void onResume() {
	}

	public void onStop() {
	}

	public void onRestart() {
	}

	public void onPause() {
	}

	public void onDestroy() {
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	public void menuOnClick(int code) {
	}

	public void onSaveInstanceState(Bundle outState) {
	}
}
