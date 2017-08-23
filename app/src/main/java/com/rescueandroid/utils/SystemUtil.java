package com.rescueandroid.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.R;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.Uri;
import android.os.IBinder;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class SystemUtil {
	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenwidth = wm.getDefaultDisplay().getWidth();// ��Ļ���
		return screenwidth;
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();// ��Ļ���
	}

	/**
	 * �ر����뷨����
	 * 
	 * @param context
	 * @param windowToken
	 */
	public static void hideInputWindows(Context context, IBinder windowToken) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static void showInputWindows(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// �õ�InputMethodManager��ʵ��
		if (imm.isActive()) {
			// �������
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
			// �ر�����̣�����������ͬ������������л�������ر�״̬��
		}
	}

	public static boolean isAvilible(Context context, String packageName) {
		final PackageManager packageManager = context.getPackageManager();// ��ȡpackagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// ��ȡ�����Ѱ�װ����İ���Ϣ
		List<String> pName = new ArrayList<String>();// ���ڴ洢�����Ѱ�װ����İ���
		// ��pinfo�н���������һȡ����ѹ��pName list��
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				pName.add(pn);
			}
		}
		return pName.contains(packageName);// �ж�pName���Ƿ���Ŀ�����İ�������TRUE��û��FALSE
	}

	public static void setEditTextReadOnly(TextView view) {
		// view.setTextColor(R.color.read_only_color); //����ֻ��ʱ��������ɫ
		view.setCursorVisible(false);
		view.setFocusable(false);
		view.setFocusableInTouchMode(false);
	}

	public static int dpToPx(Context context, int dp) {
		int padding_in_dp = dp; // 6 dps
		final float scale = context.getResources().getDisplayMetrics().density;
		int padding_in_px = (int) (padding_in_dp * scale + 0.5f);
		return padding_in_px;
	}

	//
	//
	//
	public static int getApplicationViewAreaHeight(Context context) {
		return getScreenHeight(context) - dpToPx(context, 110 + 30);
		// Rect rect= new Rect();
		// context.getApplicationContext().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		// int statusBarHeight = rect.top; //״̬���߶�
	}

	public static Uri filePathToUrl(String path) {
		File file = new File(path);
		Uri fileUri = Uri.fromFile(file);
		return fileUri;
	}

	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
			// System.out.println("result: " + buf.toString());//32λ�ļ���
			// System.out.println("result: " +
			// buf.toString().substring(8,24));//16λ�ļ���
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String getVersionName(Context context) {
		// ��ȡpackagemanager��ʵ��
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String version = packInfo.versionName;
		return version;
	}
}
