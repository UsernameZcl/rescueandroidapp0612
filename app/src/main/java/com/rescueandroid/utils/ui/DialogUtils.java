package com.rescueandroid.utils.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class DialogUtils {
	public static void showPopMsg(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void showPopMsgInHandleThread(final Context context, Handler handler, final String msg) {
		handler.post(new Runnable() {
			public void run() {
				Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
			}
		});
	}
}
