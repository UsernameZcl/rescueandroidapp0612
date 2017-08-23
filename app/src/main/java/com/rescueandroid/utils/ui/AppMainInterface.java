package com.rescueandroid.utils.ui;

import android.content.Context;

public interface AppMainInterface {
	void showWindow(WindowsBase win);

	void closeWindow(WindowsBase win);

	void showMainMenu(boolean visable);

	WindowsBase getCurrentWindow();

	Context getAppContext();

	public void setDialog(String key);

	void setSubDialog(String key, boolean isFastRefresh);
}
