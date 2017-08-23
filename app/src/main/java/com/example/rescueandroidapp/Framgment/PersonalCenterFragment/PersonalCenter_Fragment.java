package com.example.rescueandroidapp.Framgment.PersonalCenterFragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rescueandroidapp.Framgment.HomePagerFragment.HomePager_Fragment;
import com.example.rescueandroidapp.Framgment.HomePagerFragment.activity.FindrouteActivity;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.LoginActivity;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.Set_up_Activity;
import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.User_information;
import com.example.rescueandroidapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rescueandroid.config.Define;
import com.rescueandroid.data.Data;
import com.rescueandroid.utils.DensityUtil;
import com.rescueandroid.utils.ui.DialogUtils;
import com.rescueandroid.utils.ui.ProgressDialogEx;
import com.rescueandroid.utils.ui.RoundAngleImageView;
import com.rescueandroid.utils.ui.WindowsBase;

/**
 * 个人中心
 * 
 */
public class PersonalCenter_Fragment extends WindowsBase implements View.OnClickListener {

	public static PersonalCenter_Fragment mPersonalCenter_fragment = null;
	private LayoutInflater factory;
	private Context context;
	private ImageView User_news;
	private RoundAngleImageView User_image;
	private LinearLayout User_wallet,User_admini,User_set,User_route,User_warehouse,User_layoutLog,User_layout_name;
	private TextView User_login,User_name,User_platform,User_balance;
	private Handler mHandler =new Handler();
	private ProgressDialogEx progressDlgEx;

	public PersonalCenter_Fragment(Context context) {
		super(context);
		mPersonalCenter_fragment = this;
		this.context = context;
		// TODO Auto-generated constructor stub
		factory = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) factory.inflate(R.layout.personalcenter_fragment, null);
		addView(layout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		progressDlgEx = new ProgressDialogEx(this.getContext(), mHandler);
		init();
		show();
	}

	private void init(){

		User_news = (ImageView) findViewById(R.id.User_news);//消息
		User_image = (RoundAngleImageView) findViewById(R.id.User_image);//头像

		// 把图片设置成圆形图
		int w = DensityUtil.dip2px(PersonalCenter_Fragment.this.getContext(), 50);
		int h = DensityUtil.dip2px(PersonalCenter_Fragment.this.getContext(), 50);
		User_image.setParam(w, h);
		User_image.setArc(true, true, true, true);

		User_image.setOnClickListener(this);

		User_wallet = (LinearLayout) findViewById(R.id.User_wallet);//账户余额
		User_admini = (LinearLayout) findViewById(R.id.User_admini);//货源管理
		User_route = (LinearLayout) findViewById(R.id.User_route);//路线管理
		User_warehouse = (LinearLayout) findViewById(R.id.User_warehouse);//仓库管理
		User_set = (LinearLayout) findViewById(R.id.User_set);//设置
		User_set.setOnClickListener(this);
		User_layoutLog = (LinearLayout) findViewById(R.id.User_layoutLog);
		User_layout_name = (LinearLayout) findViewById(R.id.User_layout_name);

		User_login = (TextView) findViewById(R.id.User_login);//登录
		User_name = (TextView) findViewById(R.id.User_name);//用户名
		User_platform = (TextView) findViewById(R.id.User_platform);//平台
		User_balance = (TextView) findViewById(R.id.User_balance);//余额

		User_login.setOnClickListener(this);

	}

	private void show(){

		if((Data.getInstance().isLogin) == true){
			String name = Data.getInstance().realname;
			User_name.setText(name);
			User_layoutLog.setVisibility(View.GONE);
			User_layout_name.setVisibility(View.VISIBLE);
			String icon = Data.getInstance().photo;

			ImageLoader.getInstance().displayImage(Define.BASEURL + icon, User_image,
					Define.getDisplayImageOptions());

		}else {
			User_layoutLog.setVisibility(View.VISIBLE);
			User_layout_name.setVisibility(View.GONE);
			User_image.setImageResource(R.mipmap.touxiang);
		}

	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent();
		Activity ac = (Activity) PersonalCenter_Fragment.this.getContext();
		switch (view.getId()){

			case R.id.User_login:
//				PersonalCenter_Fragment.this.getContext().startActivity(new Intent(PersonalCenter_Fragment.this.getContext(),LoginActivity.class));


				intent.setClass(PersonalCenter_Fragment.this.getContext(), LoginActivity.class);
				// 有返回值的跳页，需要墙砖为Activity
				ac.startActivityForResult(intent, 0);

				break;
			case R.id.User_set:
//				PersonalCenter_Fragment.this.getContext().startActivity(new Intent(PersonalCenter_Fragment.this.getContext(),Set_up_Activity.class));

				intent.setClass(PersonalCenter_Fragment.this.getContext(), Set_up_Activity.class);
				// 有返回值的跳页，需要墙砖为Activity
				ac.startActivityForResult(intent, 0);

				break;
			case R.id.User_image:

				if((Data.getInstance().isLogin) == false){
					PersonalCenter_Fragment.this.getContext().startActivity(new Intent(PersonalCenter_Fragment.this.getContext(), LoginActivity.class));

				}else {
					PersonalCenter_Fragment.this.getContext().startActivity(new Intent(PersonalCenter_Fragment.this.getContext(),User_information.class));

				}
				break;

		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 区分2次返回索要显示的不同结果
		show();
		if (resultCode == 2) {
			show();
		}
	}

}
