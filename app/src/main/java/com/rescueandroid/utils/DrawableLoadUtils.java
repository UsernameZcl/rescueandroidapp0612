package com.rescueandroid.utils;

import java.net.URL;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class DrawableLoadUtils {

	public static Drawable loadDrawable(final String imageUrl) {
		return loadImageFromUrl(imageUrl);
	}

	public static Drawable loadImageFromUrl(String imageUrl) {
		try {
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "imageSync");
			/*
			 * Resources res = MainActivity.mainActivity.getResources();
			 * Drawable drawable = res.getDrawable(R.drawable.news_icon); return
			 * drawable;
			 */
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException(e);
		}
		return null;
	}
}
