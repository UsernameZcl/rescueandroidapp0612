package com.rescueandroid.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

/**
 * �ļ�����
 * 
 * @author zhb
 */

public class FileUtils {
	//
	// ��ѯĿ¼�Ƿ����
	//
	public static boolean isDirExist(String dir) {
		File file = new File(dir);
		return file.exists();
	}

	public static String getNewFileName(String ext) {
		return String.valueOf(new Date().getTime()) + "." + ext;
	}

	public static boolean copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // �ļ�����ʱ
				InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ��� �ļ���С
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void delFile(String dir) {
		File file = new File(dir);
		if (file.exists())
			file.delete();
	}

	public static boolean isDirExist(File file) {
		return file.exists();
	}

	//
	// ����Ŀ¼
	//
	public static boolean mkDir(String dir) {
		File file = new File(dir);
		return mkDir(file);
	}

	public static boolean mkDir(File dir) {
		if (isDirExist(dir)) {
			return true;
		} else {
			boolean res = dir.mkdir();
			return res;
		}
	}

	//
	// ��ȡandroidϵͳ��Ƭ��Ŀ¼
	//
	public static File getSystemPictureRootDir() {
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	}

	public static String getSystemPictureRootDirStr() {
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
	}

	public static void FileWrite(String dir, String text) {
		try {
			File fileData = new File(dir);
			if (!fileData.exists())
				fileData.createNewFile();

			FileWriter bw = new FileWriter(fileData);
			bw.write(text);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getPath(Context context, Uri uri) {

		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { "_data" };
			Cursor cursor = null;

			try {
				cursor = context.getContentResolver().query(uri, projection, null, null, null);
				int column_index = cursor.getColumnIndexOrThrow("_data");
				if (cursor.moveToFirst()) {
					return cursor.getString(column_index);
				}
			} catch (Exception e) {
				// Eat it
			}
		}

		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}
}
