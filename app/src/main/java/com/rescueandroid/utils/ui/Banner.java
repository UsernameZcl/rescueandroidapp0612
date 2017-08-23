package com.rescueandroid.utils.ui;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.rescueandroid.config.Define;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Banner extends FrameLayout {
	protected DetialGallery gallery;
	protected boolean isNetMode = false;
	private Handler mHandler;
	private ImageAdapter imageAdapter;
	private LinearLayout layoutBannerPoint;
	private BannerPoint bannerPoint;
	private List listData = new ArrayList();
	TextView tvTitle;
	
	public Banner(Context context, final OnItemClickListener onItemClickListener) {
		super(context);
		mHandler = new Handler();	
		// TODO Auto-generated constructor stub
		gallery = new DetialGallery(context);
		layoutBannerPoint = new LinearLayout(this.getContext());
		bannerPoint = new BannerPoint(this.getContext());
		imageAdapter = new ImageAdapter(context);
		gallery.setAdapter(imageAdapter); 
		
		bannerPoint.setOnSelItemListener(new BannerPoint.OnSelItemListener(){
			@Override
			public void onSelItemListener(int idx) {
				// TODO Auto-generated method stub
				gallery.setSelection(idx);
			}
		});
		
	    gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  
	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View v,int position, long id)
	        { 
	        	Map map = (Map)listData.get(position);
	        	//tvTitle.setText(map.get("param").toString());
	        	bannerPoint.setCurrentPoint(position);
	        	
	        }  
	      
	        @Override  
	        public void onNothingSelected(AdapterView<?> arg0)
	        {  
	        	//���ﲻ����Ӧ  
	        }  
	    });  
		
	    gallery.setOnItemClickListener(onItemClickListener);
	    
		this.addView(gallery, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		tvTitle = new TextView(Banner.this.getContext());
		tvTitle.setTextSize(16);
		tvTitle.setTextColor(Color.WHITE);
		tvTitle.setPadding(5, 0, 130, 3);
		tvTitle.setMaxLines(1);
		tvTitle.setMaxEms(40);
		tvTitle.setEllipsize(TruncateAt.END);
		tvTitle.setTextColor(Color.WHITE);
		
		//tv.setGravity(gravity)(Gravity.BOTTOM);
		//tv.setText("ddddddddddddddddddddddddddd");
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.BOTTOM;
		//FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(this);lp.gravity = Gravity.RIGHT; 
		this.addView(tvTitle, lp);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	            	mHandler.post(new Runnable() {
						public void run() {
							int n = gallery.getSelectedItemPosition() + 1;
							if(n >= gallery.getCount())
								n = 0;
							gallery.setSelection(n);
						}
					});
	            }  
	        }, 2000, 2000);  
	}
	
	
	
	
//	public void showActivities()
//	{
//		int width = SystemUtil.getScreenWidth(this.getContext())*20/100;
//		ImageView iv = new ImageView(this.getContext());
//		iv.setScaleType(ScaleType.FIT_XY);
//		iv.setImageResource(R.drawable.activites);
//		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, width);
//		params.gravity = Gravity.LEFT | Gravity.TOP;
//		Banner.this.addView(iv, params);
//	}
	
//	@Override  
//	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
//	{
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		
//		
//	}
	
	public void clear()
	{
		listData.clear();
	}
	
	public void addImage(String url, String param)
	{
		Map map = new HashMap();
		map.put("url", url);
		map.put("param", param);
		listData.add(map);
	}
	
	public void refresh()
	{
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				imageAdapter.notifyDataSetChanged();
			}
		});
		
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
//				for(int i=0; i<Banner.this.getChildCount(); i++)
//				{
//					View view = Banner.this.getChildAt(i);
//					if(view == bannerPoint)
//					{
//						Banner.this.removeView(bannerPoint);
//						break;
//					}
//				}
				
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
				for(int i=0; i<Banner.this.getChildCount(); i++)
				{
					if(Banner.this.getChildAt(i) == layoutBannerPoint)
					{
						Banner.this.removeView(layoutBannerPoint);
						break;
					}
				}
				//Banner.this.removeAllViews();
				
				Banner.this.addView(layoutBannerPoint, params);
				layoutBannerPoint.setPadding(10, 0, 0, 10);
				
				bannerPoint.setPoint(listData.size());
				LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				params1.gravity = Gravity.RIGHT | Gravity.TOP;
				//int height = Banner.this.getLayoutParams().height;
				//layoutBannerPoint.removeAllViews();
				for(int i=0; i<layoutBannerPoint.getChildCount(); i++)
				{
					if(layoutBannerPoint.getChildAt(i) == bannerPoint)
					{
						layoutBannerPoint.removeView(bannerPoint);
						break;
					}
				}
				layoutBannerPoint.addView(bannerPoint, params1);
				
				bannerPoint.setCurrentPoint(0);
			}
		},1000);
	}
	
	//
	//��ʼ��������ͼƬ
	//
//	public void startLoad()
//	{
//		mHandler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				bannerPoint.setPoint(listNetImage.size());
//				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bannerPoint.getClientWidth(), bannerPoint.getClientHeight());
//				//FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100,100);
//				params.leftMargin = SystemUtil.getScreenWidth(Banner.this.getContext()) - bannerPoint.getClientWidth() - 50;
//				params.topMargin =  Banner.this.getLayoutParams().height - bannerPoint.getClientHeight() - 30;
//				
//				bannerPoint.setLayoutParams(params);
//				Banner.this.addView(bannerPoint);
//			}
//		},0);
//		
//		
////		new Thread(new Runnable(){
////			@Override
////			public void run() {
//				for(int i=0; i<listNetImage.size(); i++)
//				{
//					ImgData data = (ImgData)listNetImage.get(i);
//					data.img = DrawableLoadUtils.loadDrawable(Define.BASEURL + data.url);
//					data.isLoad = true;
//				}
//				
//				mHandler.postDelayed(new Runnable() { 
//					@Override
//					public void run() {
//						imageAdapter.notifyDataSetChanged();
//					}
//				},0);
////			}
////		}).start(); 
//	}
	
//	public void freeImg()
//	{
//		for(int i=0; i<listNetImage.size(); i++)
//		{
//			ImgData data = (ImgData)listNetImage.get(i);
//			if(data != null && data.img != null)
//			{
//				data.img.setCallback(null);
//				data.img = null;
//			}
//		}
//	}
	
//	public void loadImg()
//	{
//		for(int i=0; i<listNetImage.size(); i++)
//		{
//			ImgData data = (ImgData)listNetImage.get(i);
//			data.img = DrawableLoadUtils.loadDrawable(Define.BASEURL + data.url);
//			data.isLoad = true;
//		}
//		
//		mHandler.postDelayed(new Runnable() { 
//			@Override
//			public void run() {
//				imageAdapter.notifyDataSetChanged();
//			}
//		},0);
//	}
	
    public class ImageAdapter extends BaseAdapter {  
        private Context mContext;  
            public ImageAdapter(Context context) {  
            mContext = context;  
        }  
      
        public int getCount() {   
        	return listData.size();
        }  
      
        public Object getItem(int position) {  
            return position;  
        }  
      
        public long getItemId(int position) {  
            return position;  
        }  
      
        public View getView(int position, View convertView, ViewGroup parent) {  
            ImageView image = new ImageView(mContext); 
            //image.setBackgroundColor(Color.BLACK);
            image.setScaleType(ScaleType.FIT_XY);
            Map map = (Map)listData.get(position);
            String url = map.get("url").toString();
            //image.setAdjustViewBounds(true);  
            image.setLayoutParams(new Gallery.LayoutParams(  
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));  
            
            ImageLoader.getInstance().displayImage(url, image, Define.getDisplayImageOptions());
            return image;  
        }
    }  
    
    class ImgData
    {
    	public boolean isLoad = false;
    	public Drawable img = null;
    	public String url;
    	public int imgid;
    	public String param;
    }
}
