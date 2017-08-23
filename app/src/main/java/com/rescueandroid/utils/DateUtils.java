package com.rescueandroid.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String formatTimeToNow(int minute) {
		if (minute < 1)
			return "�ո�";
		else if (minute < 30)
			return "" + minute + "������ǰ";
		else if (minute >= 30 && minute < 60)
			return "" + "��Сʱ��ǰ";
		else if (minute >= 60 && minute < 24 * 60)
			return "" + minute / 60 + "Сʱ��ǰ";
		else if (minute > 24 * 60 && minute < 7 * 24 * 60)
			return "" + minute / 60 / 24 + "����ǰ";
		else if (minute >= 7 * 24 * 60 && minute < 4 * 7 * 24 * 60)
			return "" + minute / 60 / 24 / 7 + "����ǰ";
		else if (minute < 365 * 24 * 60)
			return "" + minute / 60 / 24 / 30 + "������ǰ";
		else
			return "" + minute / 60 / 24 / 365 + "����ǰ";
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(Date date, String format) {
		Date currentTime = date;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
	 */
	public static String getDefaultStringDateTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
	 */
	public static String getDefaultStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
	 */
	public static String getDefaultStringDate1() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String formatDateToStr(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static Calendar parseStrToCalendar(String strdate, String format) {
		Date date = parseStrToDateTime(strdate, format);
		if (date != null)
			return DateToCalendar(date);
		return null;
	}

	public static Date parseStrToDateTime(String strdate, String format) {
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		try {
			return formatDate.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����ת����
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar DateToCalendar(Date date) {
		Calendar startdate = Calendar.getInstance();
		startdate.setTime(date);
		return startdate;
	}

	public static Date parseStrToDate(String strdate, String format) {
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		try {
			return formatDate.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
