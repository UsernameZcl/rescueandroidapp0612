package com.rescueandroid.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonUtils {
	
	public static JSONArray getArray(String data)
	{
		try {
			JSONArray jsonArray = new JSONArray(data);
			return jsonArray;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getRow(String data)
	{
		try {
			JSONObject jsonObj = new JSONObject(data);
			return jsonObj;
			//JSONArray jsonArray = new JSONArray(data);
			//if(jsonArray.length()>0)
			//	return jsonArray.getJSONObject(0);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map parseJson(JSONObject jsobj)
	{
		Map map = new HashMap();
		Iterator it = jsobj.keys();
		while(it.hasNext())
		{
			String key = it.next().toString();
			try {
				Object obj = jsobj.get(key);
				if(obj instanceof JSONArray){
					JSONArray arr = (JSONArray)obj;
					List list = new ArrayList();
					for(int i=0; i<arr.length(); i++)
					{
						list.add(parseJson(arr.getJSONObject(i)));
					}
					map.put(key, list);
				}
				else if(obj instanceof JSONObject){
					map.put(key, parseJson((JSONObject)obj));//��map�е�map
				}
				else
				{
					map.put(key, jsobj.getString(key));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public static List parseJsonArray(JSONArray jsonarray)
	{
		List list = new ArrayList();
		for(int i=0; i<jsonarray.length(); i++)
		{
			Map map;
			try {
				map = parseJson(jsonarray.getJSONObject(i));
				list.add(map);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}
	
	public static List parseJsonStringArray(JSONArray jsonarray)
	{
		List list = new ArrayList();
		for(int i=0; i<jsonarray.length(); i++)
		{
			//Map map;
			try {
				String url = jsonarray.getString(i);
				list.add(url);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}
}
