package com.rescueandroid.utils.net;

public class NameValuePairEx {
	public static String TYPE_STRING = "string";
	public static String TYPE_FILE = "file";
	
	public String name;
	public String type;
	public String value;
	
	public NameValuePairEx(String type, String name, String value)
	{
		this.type = type;
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
