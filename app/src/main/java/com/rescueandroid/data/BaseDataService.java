package com.rescueandroid.data;

import com.rescueandroid.config.Define;
import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.SystemUtil;
import com.rescueandroid.utils.net.HttpClientUtils;
import com.rescueandroid.utils.net.NameValuePairEx;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BaseDataService {
	private static final String TAG = "BaseDataService";

	public static JSONObject getAppNewsList(int count, int page, String type,
			String column) throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"count", new Integer(10).toString()));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"page", new Integer(page).toString()));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"type", type));

		String key = Define.key;// "tpjddsf";
		String skey = Define.skey;// "b35$45%698@65h#Ak#ff$cS%adc";

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"key", key));
		String sign = SystemUtil.Md5("" + type + count + page + key + skey);
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"sign", sign));

		String columnRes = "";
		try {
			columnRes = URLEncoder.encode(column, "utf-8").replace("*", "*")
					.replace("~", "~").replace("+", " ");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"column", columnRes));

		JSONObject jsonobj = HttpClientUtils.requestByPost(Define.BASEURL
				+ "getAppNewsList.html?fileType=json", nameValuePairs);
		return jsonobj;
	}


	// 登录
	//http://192.168.1.10:16006/v1/baseuser/?u=111111&p=111111
	public static JSONObject login(String loginName, String pwd)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/baseuser/", nameValuePairs);
		return jsonobj;
	}

	// 修改密码
	// /WqService/member/changePwd.do?memberkey=2&pwd=123&newPwd=123
	public static JSONObject gr_changePwd(String loginName, String password, String pwd,String newpwd) throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", password + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING, "pwd", pwd));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING, "newpwd", newpwd));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/baseuser/editpass", nameValuePairs);
		return jsonobj;
	}


	//找路线
	//http://114.215.122.32:16006/v1/routeinfo/listall?u=13111111111&p=111111
	public static JSONObject routeinfo(String loginName, String pwd)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/routeinfo/listall", nameValuePairs);
		return jsonobj;
	}

	//找仓库
	//http://114.215.122.32:16006/v1/findstoreinfo/list?u=13111111111&p=111111
	public static JSONObject findstoreinfo(String loginName, String pwd)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/findstoreinfo/list", nameValuePairs);
		return jsonobj;
	}

	////获取我发布的路线
	//http://114.215.122.32:16006/v1/routeinfo/list?u=18353269340&p=111111
	public static JSONObject myrouteinfo(String loginName, String pwd)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/routeinfo/list", nameValuePairs);
		return jsonobj;
	}

	////抢路线
	//http://114.215.122.32:16006/v1/askorderinfo/?u=13111111111&p=121212&item=1&askid=406&answerid=40
	public static JSONObject askorderinfo(String loginName, String pwd,int item,int id,int answerid)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"item", item + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"askid", id + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"answerid", id + ""));

		JSONObject jsonobj = HttpClientUtils.requestByPost(Define.BASEURL
				+ "/v1/askorderinfo/", nameValuePairs);
		return jsonobj;
	}


	////获取我发布的找车列表
	//http://114.215.122.32:16006/v1/findcarinfo/mylist?u=18353269340&p=111111&state=1
	public static JSONObject findcarinfo(String loginName, String pwd,int state)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"state", state + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/findcarinfo/mylist", nameValuePairs);
		return jsonobj;
	}


	//找仓库
	//http://114.215.122.32:16006/v1/coldstore/list?u=13111111111&p=121212
	public static JSONObject coldstore(String loginName, String pwd)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/coldstore/list", nameValuePairs);
		return jsonobj;
	}

	////获取我发布的找库列表
	//http://114.215.122.32:16006/v1/findstoreinfo/mylist?u=13111111111&p=121212&state=0
	public static JSONObject findstoreinfo(String loginName, String pwd,int state)
			throws NetConnectionException, JSONException {
		final List<NameValuePairEx> nameValuePairs = new ArrayList<NameValuePairEx>();

		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"u", loginName + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"p", pwd + ""));
		nameValuePairs.add(new NameValuePairEx(NameValuePairEx.TYPE_STRING,
				"state", state + ""));

		JSONObject jsonobj = HttpClientUtils.requestByGet(Define.BASEURL
				+ "/v1/findstoreinfo/mylist", nameValuePairs);
		return jsonobj;
	}


}

