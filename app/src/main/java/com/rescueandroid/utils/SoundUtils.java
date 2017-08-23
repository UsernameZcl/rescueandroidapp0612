package com.rescueandroid.utils;

import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;

public class SoundUtils {
	public static void playNotification(Context context) {
		Uri uri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION);
		RingtoneManager.getRingtone(context, uri).play();
	}
}
