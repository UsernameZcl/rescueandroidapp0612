package com.rescueandroid.utils.net;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.rescueandroid.exception.NetConnectionException;
import com.rescueandroid.utils.JsonUtils;

/**
 * Http请求客户端工具类
 *
 * @author asus
 *
 */
public class HttpClientUtils {
	private final static String TAG = "HttpClientUtils";
	private final static String ServerHSessionIdName = "PHPSESSID";
	private static String SessionID = null;
	private static String Token = null;

	public static String requestByGetstr(String url) throws NetConnectionException {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
			throw new NetConnectionException(cpe);
		} catch (IOException e) {
			e.printStackTrace();
			throw new NetConnectionException(e);
		}
		return builder.toString();
	}

	/**
	 * Post提交字符串参数
	 *
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws NetConnectionException
	 */
	/*
	 * public static String requestByPost(String url, Map<String,String>
	 * paramMap) throws NetConnectionException { HttpPost httpRequest =new
	 * HttpPost(url); List <NameValuePair> params=new
	 * ArrayList<NameValuePair>();
	 *
	 * Iterator it = paramMap.keySet().iterator(); while(it.hasNext()) { String
	 * key = (String)it.next(); params.add(new BasicNameValuePair(key,
	 * paramMap.get(key).toString())); }
	 *
	 * try{ httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	 * HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
	 *
	 * if(httpResponse.getStatusLine().getStatusCode()==200) { String strResult
	 * = EntityUtils.toString(httpResponse.getEntity()); return strResult; }
	 * else { Log.e(TAG, "post request fault, StatusCode=" +
	 * httpResponse.getStatusLine().getStatusCode()); throw new
	 * NetConnectionException(); } } catch(ClientProtocolException e){
	 * e.printStackTrace(); throw new NetConnectionException(e); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); throw new
	 * NetConnectionException(e); } catch (IOException e) { e.printStackTrace();
	 * throw new NetConnectionException(e); } }
	 */

	/**
	 * Post提交文件及参数
	 *
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws NetConnectionException
	 */
	public static String requestByPostStr(String url, List<NameValuePairEx> nameValuePairs)
			throws NetConnectionException, JSONException {
		NameValuePairEx nvpe = null;
		InputStream is;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			MultipartEntity multipartEntity = new MultipartEntity();

			// 设置Token session超时及默认登录情况，用户登录状态恢复
			if (Token != null && !"".equals(Token))
				multipartEntity.addPart("Token", new StringBody(Token));

			Iterator it = nameValuePairs.iterator();
			while (it.hasNext()) {
				nvpe = (NameValuePairEx) it.next();
				if (nvpe.getType().equals(NameValuePairEx.TYPE_STRING)) {
					Log.e("", "nvpe.getName()" + nvpe.getName());
					multipartEntity.addPart(nvpe.getName(), new StringBody(nvpe.getValue()));
				} else {
					InputStream fis = new FileInputStream(nvpe.getValue());
					InputStreamBody isb = new InputStreamBody(fis, nvpe.getValue());
					multipartEntity.addPart(nvpe.getName(), isb);
				}
			}
			post.setEntity(multipartEntity);
			// HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);

			// 设置sessionid
			if (null != SessionID) {
				post.setHeader("Cookie", ServerHSessionIdName + "=" + SessionID);
			}

			HttpResponse response;
			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// is = response.getEntity().getContent();
				// String result = inStream2String(is);
				String strResult = EntityUtils.toString(response.getEntity());
				String a = strResult;

				Log.e(TAG, "strResult=" + strResult);

				// 保存sessionid
				saveSessionId(client);

				// JSONObject jsonobj = JsonUtils.getRow(strResult);
				// Token = jsonobj.getString("Token");
				// Log.e(TAG, "requestByPost SessionID=" + SessionID + ",Token="
				// + Token);
				return strResult;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NetConnectionException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NetConnectionException(e);
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// throw new JSONException(e.toString());
		// }
		return null;
	}

	/**
	 * Post提交文件及参数
	 *
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws NetConnectionException
	 */
	public static JSONObject requestByPost(String url, List<NameValuePairEx> nameValuePairs)
			throws NetConnectionException, JSONException {
		NameValuePairEx nvpe = null;
		InputStream is;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			MultipartEntity multipartEntity = new MultipartEntity();

			// 设置Token session超时及默认登录情况，用户登录状态恢复
			if (Token != null && !"".equals(Token))
				multipartEntity.addPart("Token", new StringBody(Token));

			Iterator it = nameValuePairs.iterator();
			while (it.hasNext()) {
				nvpe = (NameValuePairEx) it.next();
				if (nvpe.getType().equals(NameValuePairEx.TYPE_STRING)) {
					Log.e("", "nvpe.getName()" + nvpe.getName());
					multipartEntity.addPart(nvpe.getName(), new StringBody(nvpe.getValue()));
				} else {
					InputStream fis = new FileInputStream(nvpe.getValue());
					InputStreamBody isb = new InputStreamBody(fis, nvpe.getValue());
					multipartEntity.addPart(nvpe.getName(), isb);
				}
			}
			post.setEntity(multipartEntity);
			// HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);

			// 设置sessionid
			if (null != SessionID) {
				post.setHeader("Cookie", ServerHSessionIdName + "=" + SessionID);
			}

			HttpResponse response;
			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// is = response.getEntity().getContent();
				// String result = inStream2String(is);
				String strResult = EntityUtils.toString(response.getEntity());
				String a = strResult;

				Log.e(TAG, "strResult=" + strResult);

				// 保存sessionid
				saveSessionId(client);

				JSONObject jsonobj = JsonUtils.getRow(strResult);
				// Token = jsonobj.getString("Token");
				// Log.e(TAG, "requestByPost SessionID=" + SessionID + ",Token="
				// + Token);
				return jsonobj;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NetConnectionException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NetConnectionException(e);
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// throw new JSONException(e.toString());
		// }
		return null;
	}

	public static JSONObject requestByGet(String url,
										  List<NameValuePairEx> nameValuePairs) throws NetConnectionException {
		String param = "";
		MultipartEntity multipartEntity = null;
		NameValuePairEx nvpe = null;
		Iterator it = nameValuePairs.iterator();
		while (it.hasNext()) {
			if (param.length() != 0) {
				param += "&";
			}
			nvpe = (NameValuePairEx) it.next();
			// if(nvpe.getType().equals(NameValuePairEx.TYPE_STRING))
			// {
			Log.e("", "nvpe.getName()" + nvpe.getName());
			// multipartEntity.addPart(nvpe.getName(), new
			// StringBody(nvpe.getValue()));

			param += nvpe.getName() + "=" + nvpe.getValue();
			// }


		}

		DefaultHttpClient client = new DefaultHttpClient();
		//client.set
		StringBuilder builder = new StringBuilder();
		HttpGet get = new HttpGet(url + "?" + param);
		if(SessionID != null)
			get.setHeader("Cookie", ServerHSessionIdName + "=" + SessionID);
		//get.addHeader(name, value);
		try {
			HttpResponse response = client.execute(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			//saveSessionId(client);
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
			throw new NetConnectionException(cpe);
		} catch (IOException e) {
			e.printStackTrace();
			throw new NetConnectionException(e);
		}
		String str = builder.toString();
		JSONObject jsonobj = JsonUtils.getRow(str);
		return jsonobj;
	}


	public static void saveSessionId(DefaultHttpClient client) {
		CookieStore mCookieStore = client.getCookieStore();
		List<Cookie> cookies = mCookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			// 这里是读取Cookie['PHPSESSID']的值存在静态变量中，保证每次都是同一个值
			if (ServerHSessionIdName.equals(cookies.get(i).getName())) {
				SessionID = cookies.get(i).getValue();
				break;
			}
		}
	}
}
