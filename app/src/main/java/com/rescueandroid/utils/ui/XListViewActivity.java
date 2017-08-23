package com.rescueandroid.utils.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.rescueandroidapp.R;
import com.rescueandroid.config.Define;
import com.rescueandroid.config.Text;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.CommonUtils;
import com.rescueandroid.utils.JsonUtils;
import com.rescueandroid.view.XListView;

public class XListViewActivity extends Activity implements XListView.IXListViewListener {
	private static String TAG = "XListViewActivity";
	protected XListView mListView;
	protected Handler mHandler = new Handler();
	public ListAdapter mAdapter;
	protected List items = new ArrayList();
	protected List listResult = new ArrayList();
	// protected LayoutInflater factory;
	protected LinearLayout layoutMain;
	private int page = 0;
	private int start = 0;
	private int refreshCnt = 0;
	protected List dataArray = new ArrayList();

	public XListViewActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAdapter = new ListAdapter(this);
		mListView = (XListView) this.findViewById(R.id.xListView);
		mListView.setAdapter(mAdapter);
		mListView.setAdapter(mAdapter);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mListView.setOnItemClickListener(listener);
	}

	public void startRefresh() {
		mListView.startRefresh();
	}

	protected void notifyListViewDataSetChange() {
		mAdapter.notifyDataSetChanged();
	}

	protected void clearItems() {
		items.clear();
	}

	protected List getItems() {
		return items;
	}

	public class ListAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context context;

		public ListAdapter(Context context) {
			this.context = context;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return XListViewActivity.this.getCount();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// convertView = mInflater.inflate(R.layout.hotel_listitem, null);
			// Map map = (Map)items.get(position);
			// ImageView ivImg =
			// (ImageView)convertView.findViewById(R.id.ivImg);
			// TextView tvName =
			// (TextView)convertView.findViewById(R.id.tvName);
			// ImageLoader.getInstance().displayImage(Define.getImgPath() +
			// map.get("Chain_Img").toString(), ivImg);
			// return convertView;
			// return convertView;
			return XListViewActivity.this.getView(position, convertView, parent, mInflater);
		}
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	public View getView(int position, View convertView, ViewGroup parent, LayoutInflater mInflater) {
		return null;
	}

	protected void geneItems(boolean isRefresh) {
		try {
			int _page = isRefresh ? 1 : page + 1;
			JSONObject jsobj = getDataSource(_page);
			int res = jsobj.getInt("Res");
			if (res == Define.RES_OK) {
				if (isRefresh)
					dataArray.clear();
				page = _page;

				JSONArray jsonArray = jsobj.getJSONArray("Array");
				final List list = JsonUtils.parseJsonArray(jsonArray);
				for (int i = 0; i < list.size(); i++) {
					dataArray.add(list.get(i));
				}

				mHandler.post(new Runnable() {
					public void run() {
						clearItems();
						copyData();
						notifyListViewDataSetChange();
					}
				});
			}
		} catch (NetConnectionException e) {
			// TODO Auto-generated catch block
			DialogUtils.showPopMsgInHandleThread(XListViewActivity.this, mHandler, Text.NetConnectFault);
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			DialogUtils.showPopMsgInHandleThread(XListViewActivity.this, mHandler, Text.ParseFault);
			e.printStackTrace();
		}
	}

	public void copyData() {
		CommonUtils.copyList(getItems(), dataArray);
	}

	public JSONObject getDataSource(int page) throws NetConnectionException, JSONException {
		return null;
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("�ո�");
	}

	@Override
	public void onRefresh() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				// items.clear();
				geneItems(true);

				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						mAdapter.notifyDataSetChanged();
						onLoad();
					}
				}, 0);
			}
		}).start();
	}

	@Override
	public void onLoadMore() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				geneItems(false);
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						mAdapter.notifyDataSetChanged();
						onLoad();
					}
				}, 0);
			}
		}).start();
	}
}
