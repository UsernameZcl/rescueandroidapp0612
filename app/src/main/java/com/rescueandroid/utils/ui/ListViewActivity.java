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
import android.widget.ListView;

import com.example.rescueandroidapp.R;
import com.rescueandroid.config.Define;
import com.rescueandroid.config.Text;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.CommonUtils;
import com.rescueandroid.utils.JsonUtils;

public class ListViewActivity extends Activity {
	private static String TAG = "ListViewActivity";
	protected ListView mListView;
	protected Handler mHandler = new Handler();
	protected ListAdapter mAdapter;
	protected List items = new ArrayList();
	protected List listResult = new ArrayList();

	public ListViewActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new ListAdapter(this);
		mListView = (ListView) this.findViewById(R.id.xListView);
		mListView.setAdapter(mAdapter);

	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mListView.setOnItemClickListener(listener);
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

	class ListAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context context;

		public ListAdapter(Context context) {
			this.context = context;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
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
			return ListViewActivity.this.getView(position, convertView, parent, mInflater);
		}
	}

	public View getView(int position, View convertView, ViewGroup parent, LayoutInflater mInflater) {
		return null;
	}

	protected void geneItems() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					JSONObject jsobj = getDataSource();
					int res = jsobj.getInt("Res");
					if (res == Define.RES_OK) {
						JSONArray jsonArray = jsobj.getJSONArray("Array");
						final List list = JsonUtils.parseJsonArray(jsonArray);
						mHandler.post(new Runnable() {
							public void run() {
								clearItems();
								CommonUtils.copyList(getItems(), list);
								notifyListViewDataSetChange();
							}
						});
					}
				} catch (NetConnectionException e) {
					// TODO Auto-generated catch block
					DialogUtils.showPopMsgInHandleThread(ListViewActivity.this, mHandler, Text.NetConnectFault);
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					DialogUtils.showPopMsgInHandleThread(ListViewActivity.this, mHandler, Text.ParseFault);
					e.printStackTrace();
				}
			}
		}).start();
	}

	public JSONObject getDataSource() throws NetConnectionException, JSONException {
		return null;
	}
}
