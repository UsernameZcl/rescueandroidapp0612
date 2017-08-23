package com.rescueandroid.update.network.util;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.rescueandroid.update.network.DownloadManager;


/**
 * ������ͼ
 * 
 * @author�� aokunsang
 * @date�� 2012-12-19
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityManager.getInstance().pushActivity(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			new AlertDialog.Builder(this)
					/* �������ڵ�����ͷ���� */
					.setTitle("��Ϣ��ʾ")

			.setMessage("ȷ��Ҫ�˳���").setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialoginterface, int i) {
					ActivityManager.getInstance().exit();
				}
			}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialoginterface, int i) {
					dialoginterface.dismiss();
				}
			}).create().show();
		} else if (item.getItemId() == 2) {
			/* �����[����] */
			DownloadManager downManger = new DownloadManager(this, true);
			downManger.checkDownload();
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* һ���˵� */
		SubMenu sub1 = menu.addSubMenu(1, 1, 0, "�˳�ϵͳ");
		// sub1.setIcon(R.drawable.sysout);
		// sub1.setHeaderIcon(R.drawable.sysout);
		/* �����˵� */
		SubMenu sub2 = menu.addSubMenu(1, 2, 0, "������");
		// sub2.setIcon(R.drawable.upgrade);
		// sub2.setHeaderIcon(R.drawable.upgrade);
		return true;
	}
}
