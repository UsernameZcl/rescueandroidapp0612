package com.rescueandroid.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static String replaceEnter(String oldString)
	{
		 //System.out.println("old:"+oldString.length());
		 Pattern pattern=Pattern.compile("(\r\n|\r|\n|\n\r)");
	
		 //������ʽ��ƥ��һ��Ҫ�������������滻\r|\n��ʱ������
		 Matcher matcher=pattern.matcher(oldString);
		 String newString=matcher.replaceAll("<br>");
		 //System.out.println("new:"+newString.length());
		 return newString;
	}
}
