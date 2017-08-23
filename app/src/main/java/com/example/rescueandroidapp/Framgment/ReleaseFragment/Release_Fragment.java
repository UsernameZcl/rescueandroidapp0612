package com.example.rescueandroidapp.Framgment.ReleaseFragment;



import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rescueandroidapp.Framgment.PersonalCenterFragment.activity.LoginActivity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_route_activity.Find_Route_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_car_activity.Release_Car_Supply_Activity;
import com.example.rescueandroidapp.Framgment.ReleaseFragment.find_source_activity.Source_Finding_Activity;
import com.example.rescueandroidapp.R;
import com.rescueandroid.data.Data;
import com.rescueandroid.utils.mytoast.NToast;
import com.rescueandroid.utils.ui.WindowsBase;

/**
 *发布
 *
 */
public class Release_Fragment extends WindowsBase implements View.OnClickListener {

	public static Release_Fragment mRelease_fragment = null;
	private Handler mHandler = new Handler();
	private LayoutInflater factory;
	private LinearLayout mLinearLayout1,mLinearLayout2,mLinearLayout3,mLinearLayout4;

	public Release_Fragment(Context context) {
		super(context);
		mRelease_fragment=this;
		factory = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) factory.inflate(R.layout.release_fragment, null);
		addView(layout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		mLinearLayout1 = (LinearLayout) Release_Fragment.this.findViewById(R.id.realease_fragment_linlay1);
		mLinearLayout2 = (LinearLayout) Release_Fragment.this.findViewById(R.id.realease_fragment_linlay2);
		mLinearLayout3 = (LinearLayout) Release_Fragment.this.findViewById(R.id.realease_fragment_linlay3);
		mLinearLayout4 = (LinearLayout) Release_Fragment.this.findViewById(R.id.realease_fragment_linlay4);

		mLinearLayout1.setOnClickListener(this);
		mLinearLayout2.setOnClickListener(this);
		mLinearLayout3.setOnClickListener(this);
		mLinearLayout4.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.realease_fragment_linlay1:
//				if (Data.getInstance().isLogin == false){
//					getContext().startActivity(new Intent(getContext(), LoginActivity.class));
//				}else{
					//发布货源
					getContext().startActivity(new Intent(getContext(), Release_Car_Supply_Activity.class));
//				}

				break;
			case R.id.realease_fragment_linlay2:
				//发布库源
//				if (Data.getInstance().isLogin == false){
//					getContext().startActivity(new Intent(getContext(), LoginActivity.class));
////				}else {
					getContext().startActivity(new Intent(getContext(), Source_Finding_Activity.class));
//				}
				break;
			case R.id.realease_fragment_linlay3:
				//发布库源
//				if (Data.getInstance().isLogin == false){
//					getContext().startActivity(new Intent(getContext(), LoginActivity.class));
//				}else {
					getContext().startActivity(new Intent(getContext(), Find_Route_Activity.class));
//				}
				break;
			case R.id.realease_fragment_linlay4:
				NToast.shortToast(getContext(),"暂无数据，敬请期待!");
				break;

		}
	}
}
