package com.rescueandroid.utils.ui;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rescueandroidapp.R;

public class BannerPoint extends LinearLayout {
	public int pointwidth = 12;
	public int pointHeight = 12;
	public int space = 12;
	public int num= 0; 
	private OnSelItemListener onSelItemListener = null;
	public List list = new ArrayList();
	
	public BannerPoint(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setOrientation(LinearLayout.HORIZONTAL);
	} 
	
	public void setCurrentPoint(int idx)
	{
		for(int i=0; i<list.size(); i++)
		{
			ImageView p = (ImageView)list.get(i);
			if(i != idx)
				p.setBackgroundResource(R.mipmap.point);
			else
				p.setBackgroundResource(R.mipmap.point_sel);
		}
	}
	
	
	public void setPoint(int num)
	{
		list.clear();
		this.removeAllViews();
		//this.removeAllViews();
		this.num = num;
		for(int i=0; i<num; i++)
		{
//			LinearLayout p = new LinearLayout(this.getContext());
//			//p.setPadding(space, 0, 0, 0);
//			p.setBackgroundColor(Color.GRAY);
//			p.setLayoutParams(new LinearLayout.LayoutParams(pointwidth, pointHeight));
//			this.addView(p, pointwidth, pointHeight);
//			
//			LinearLayout pSpace = new LinearLayout(this.getContext());
//			pSpace.setLayoutParams(new LinearLayout.LayoutParams(space, pointHeight));
//			this.addView(pSpace, space, pointHeight);
			
			LayoutParams lp = new LayoutParams(pointwidth, pointHeight);
			ImageView iv = new ImageView(this.getContext());
			iv.setBackgroundResource(R.mipmap.point);
			this.addView(iv, lp);
			list.add(iv);
			
			LinearLayout space = new LinearLayout(this.getContext());
			LayoutParams lpSpace = new LayoutParams(10, 10);
			this.addView(space, lpSpace);
			
			final int index = i; 
			iv.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(onSelItemListener != null)
						onSelItemListener.onSelItemListener(index);
				}
			});
		}
		
		setCurrentPoint(0);
		//this.setLayoutParams(new LinearLayout.LayoutParams(pointwidth*num + (num-1)*space, pointHeight));
	}
	
//	public int getClientWidth()
//	{
//		return pointwidth*num + (num-1)*space;
//	}
//	
//	public int getClientHeight()
//	{
//		return pointHeight;
//	}
	
	public void setOnSelItemListener(OnSelItemListener onSelItemListener)
	{
		this.onSelItemListener = onSelItemListener;
	}
	
	public interface OnSelItemListener
	{
		public void onSelItemListener(int idx);
	}
}
