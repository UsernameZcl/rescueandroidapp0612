package com.example.rescueandroidapp.Framgment;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.rescueandroidapp.Framgment.HomePagerFragment.HomePager_Fragment;
import com.example.rescueandroidapp.Framgment.OrderFragment.Order_Fragment;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.PersonalCenter_Fragment;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.Release_Fragment;
import com.example.rescueandroidapp.R;
import com.rescueandroid.utils.AnimationRes;
import com.rescueandroid.utils.ui.AppMainInterface;
import com.rescueandroid.utils.ui.Application;
import com.rescueandroid.utils.ui.FrameParent;
import com.rescueandroid.utils.ui.WindowsBase;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 主框架窗体
 */
public class MainFrameActivity extends AutoLayoutActivity implements AppMainInterface {
	private static String TAG = "MainFrameActivity";
	private String currentDlgKey = null;
	private HomePager_Fragment homeFrame = null;
	private Release_Fragment workFrame = null;
	private Order_Fragment userFrame = null;
	private PersonalCenter_Fragment communiFrame  = null;
	private ViewFlipper vfMain = null;
	private LinearLayout layoutMainmenu;
	public static Activity mainActivity;
	Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainframe);

		String TYPE = this.getIntent().getStringExtra("TYPE");
		mainActivity = this;
		Application.appMain = this;
		// layoutMain = (LinearLayout)this.findViewById(R.id.layout_main);
		String value = OnlineConfigAgent.getInstance().getConfigParams(this, "flag");
		if(value.equals("no"))
			return;
		vfMain = (ViewFlipper) this.findViewById(R.id.vf_main);
		layoutMainmenu = (LinearLayout) this.findViewById(R.id.layoutMainmenu);
		// 初始化内容窗体
		View layoutMain_home = (View) this.findViewById(R.id.layoutMain_home);
		layoutMain_home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDialog("HomeFrame");
			}
		});

		View layoutMain_found = (View) this.findViewById(R.id.layoutMain_found);
		layoutMain_found.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDialog("WorkFrame");
			}
		});

		View layoutMain_news = (View) this.findViewById(R.id.layoutMain_news);
		layoutMain_news.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDialog("CommuniFrame");
			}
		});

		View layoutMain_user = (View) this.findViewById(R.id.layoutMain_user);
		layoutMain_user.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDialog("UserFrame");
			}
		});

		// 设置主页为默认窗体
		setDialog("HomeFrame");
	}

	private void setMenuBg(String menu) {

		ImageView ivMain_home = (ImageView) this.findViewById(R.id.ivMain_home);
		ImageView ivMain_found = (ImageView) this.findViewById(R.id.ivMain_found);
		ImageView ivMain_news = (ImageView) this.findViewById(R.id.ivMain_news);
		ImageView ivMain_user = (ImageView) this.findViewById(R.id.ivMain_user);

		TextView tvMain_home = (TextView) this.findViewById(R.id.tvMain_home);
		TextView tvMain_found = (TextView) this.findViewById(R.id.tvMain_found);
		TextView tvMain_news = (TextView) this.findViewById(R.id.tvMain_news);
		TextView tv_Main_user = (TextView) this.findViewById(R.id.tv_Main_user);

		if (menu.equals("HomeFrame")) {
			ivMain_home.setImageResource(R.mipmap.shouye2);
			tvMain_home.setTextColor(Color.rgb(87,22,218));
		} else {
			ivMain_home.setImageResource(R.mipmap.shouye1);
			tvMain_home.setTextColor(0xff666666);
		}
		
		if (menu.equals("WorkFrame")) {
			ivMain_found.setImageResource(R.mipmap.fabu2);
			tvMain_found.setTextColor(Color.rgb(87,22,218));
		} else {
			ivMain_found.setImageResource(R.mipmap.fabu1);
			tvMain_found.setTextColor(0xff666666);
		}
		if (menu.equals("CommuniFrame")) {
			ivMain_news.setImageResource(R.mipmap.dingdan2);
			tvMain_news.setTextColor(Color.rgb(87,22,218));
		} else {
			ivMain_news.setImageResource(R.mipmap.dingdan1);
			tvMain_news.setTextColor(0xff666666);
		}
		if (menu.equals("UserFrame")) {
			ivMain_user.setImageResource(R.mipmap.geren2);
			tv_Main_user.setTextColor(Color.rgb(87,22,218));
		} else {
			ivMain_user.setImageResource(R.mipmap.geren1);
			tv_Main_user.setTextColor(0xff666666);
		}
	}

	@Override
	public void setSubDialog(String key, boolean isFastRefresh) {
		// WindowsBase currentView = (WindowsBase)vfMain.getCurrentView();
		// ((HomeFrame)currentView).setSelMenu(key, isFastRefresh);
	}

	@Override
	public void setDialog(String key) {
		if (key.equals(currentDlgKey))
			return;
		WindowsBase currentView = (WindowsBase) vfMain.getCurrentView();
		if (currentView != null)
			currentView.onPause();
		vfMain.removeAllViews();

		boolean isCreate = false;
		if ("HomeFrame".equals(key)) {
			if (homeFrame == null) {
				homeFrame = new HomePager_Fragment(this);
				isCreate = true;
			}
			vfMain.addView(homeFrame, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));

		}
	
		if ("WorkFrame".equals(key)) {
			if (workFrame == null) {
				workFrame = new Release_Fragment(this);
				isCreate = true;
			}
			vfMain.addView(workFrame, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));
			 workFrame.onResume();
		}
		if ("CommuniFrame".equals(key)) {
			if (userFrame == null) {
				userFrame = new Order_Fragment(this);
				isCreate = true;
			}
			vfMain.addView(userFrame, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));
			userFrame.onResume();
		}
		if ("UserFrame".equals(key)) {
			if (communiFrame == null) {
				communiFrame = new PersonalCenter_Fragment(this);
				isCreate = true;
			}
			vfMain.addView(communiFrame, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));
			communiFrame.onResume();
		}


		setMenuBg(key);
		// setBtnImg(key);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (hasFocus) {
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void showWindow(WindowsBase win) {
		// TODO Auto-generated method stub
		WindowsBase currentWin = (WindowsBase) vfMain.getCurrentView();
		currentWin.onPause();
		vfMain.addView(win, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		// vfMain.setInAnimation(AnimationRes.inFromLeftAnimation());
		// vfMain.setOutAnimation(AnimationRes.outToRightAnimation());
		vfMain.setInAnimation(AnimationRes.inFromRightAnimation());
		vfMain.setOutAnimation(AnimationRes.outToLeftAnimation());
		vfMain.showNext();
		// 是否显示底部主菜单
		Application.appMain.showMainMenu(win.isShowMainMenu);
		win.onResume();
	}

	@Override
	public void closeWindow(WindowsBase win) {
		win.onPause();
		vfMain.setInAnimation(AnimationRes.inFromLeftAnimation());
		vfMain.setOutAnimation(AnimationRes.outToRightAnimation());
		vfMain.showPrevious();
		vfMain.removeView(win);

		WindowsBase currentWin = (WindowsBase) vfMain.getCurrentView();
		// 是否显示底部主菜单
		Application.appMain.showMainMenu(currentWin.isShowMainMenu);
		currentWin.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		WindowsBase currentWin = (WindowsBase) vfMain.getCurrentView();
		currentWin.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void showMainMenu(boolean visable) {
		// TODO Auto-generated method stub
		if (visable)
			layoutMainmenu.setVisibility(View.VISIBLE);
		else
			layoutMainmenu.setVisibility(View.GONE);
	}

	@Override
	public WindowsBase getCurrentWindow() {
		// TODO Auto-generated method stub
		WindowsBase currentWin = (WindowsBase) vfMain.getCurrentView();
		if (!currentWin.isRootWindow)
			return currentWin;
		else
			return ((FrameParent) currentWin).getCurrentDialog();
	}

	@Override
	public Context getAppContext() {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * 捕捉back
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			ExitDialog(MainFrameActivity.this).show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 提示退出系统
	 */
	private Dialog ExitDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setIcon(R.drawable.icon);
		builder.setTitle("系统信息");
		builder.setMessage("确定要退出程序吗?");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
				System.exit(0);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		return builder.create();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}