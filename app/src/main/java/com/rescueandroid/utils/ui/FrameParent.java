package com.rescueandroid.utils.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * ������
 * 
 * @author mjf 2013-11-18
 */
public class FrameParent extends WindowsBase {
	private LinearLayout layoutMain;
	private Map dlgMap = new HashMap();
	private String currentKey = null;

	public FrameParent(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		isRootWindow = true;
	}

	public void addDialog(String key, View view) {
		dlgMap.put(key, view);
	}

	public void setLayoutMain(LinearLayout layoutMain) {
		this.layoutMain = layoutMain;
	}

	public boolean containDialog(String key) {
		return dlgMap.containsKey(key);
	}

	public int getDlalogNum() {
		return dlgMap.size();
	}

	public String getCurrentKey() {
		return currentKey;
	}

	public void setCurrentDialog(String key, boolean isFastLoad) {
		if (currentKey != null) {
			WindowsBase view = (WindowsBase) layoutMain.getChildAt(0);
			if (view != null) {
				view.onPause();

			}
			layoutMain.removeAllViews();
		}
		if (dlgMap.containsKey(key)) {
			WindowsBase view = (WindowsBase) dlgMap.get(key);

			if (!isFastLoad) {
				if (!view.isInit) {
					view.onInit();
					view.isInit = true;
				}
				view.onResume();
			} else {
				view.onFastRefresh();
			}
			// �Ƿ���ʾ�ײ����˵�
			Application.appMain.showMainMenu(view.isShowMainMenu);

			layoutMain.addView(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			currentKey = key;
		}
	}

	public WindowsBase getCurrentDialog() {
		WindowsBase view = (WindowsBase) dlgMap.get(currentKey);
		return view;
	}

	@Override
	public void onResume() {
		getCurrentDialog().onResume();
	}

	@Override
	public void onPause() {
		getCurrentDialog().onPause();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (getCurrentDialog() != null)
			getCurrentDialog().onActivityResult(requestCode, resultCode, data);
	}
}
