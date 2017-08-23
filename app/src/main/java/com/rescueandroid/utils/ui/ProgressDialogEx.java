package com.rescueandroid.utils.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

/**
 * 加载提示窗体
 * @author zhb
 */
public class ProgressDialogEx{
	private Context context = null;
	private Handler handler = null;
	private ProgressDialogHandle pdlghandle = null;

	public ProgressDialogEx(Context context, Handler handler)
	{
		this.context = context;
		this.handler = handler;
	}

	/**
	 * 程进度条(handle线)
	 */
	public void simpleModeShowHandleThread()
	{
		handler.post(new Runnable() {
			public void run() {
				if(pdlghandle != null)
				{
					pdlghandle.progressDialog.show();
				}
				else
				{
					pdlghandle =  new ProgressDialogEx.ProgressDialogHandle(ProgressDialog.show(context, "请稍等...", "获取数据中...", false));
				}
			}
		});
	}

	public void closeHandleThread()
	{
		handler.post(new Runnable() {
			public void run() {
				if(pdlghandle !=null)
					pdlghandle.progressDialog.dismiss();
			}
		});
	}

	public void simpleModeShow()
	{
		pdlghandle =  new ProgressDialogEx.ProgressDialogHandle(ProgressDialog.show(context, "请稍等...", "获取数据中...", false));
	}

	public void close()
	{
		pdlghandle.progressDialog.dismiss();
	}

	public static class ProgressDialogHandle
	{
		public ProgressDialog progressDialog;

		public ProgressDialogHandle(ProgressDialog progressDialog) {
			this.progressDialog = progressDialog;
			//progressDialog.setCancelable(true); 
		}
	}

	private static ProgressDialog show;
	public static void showDialog(Context context) {
		show = ProgressDialog.show(context, "请稍等...", "获取数据中...", false);
		show.show();
	}

	public static void hideDialog() {
		show.dismiss();
		show = null;
	}
}
