package com.aoeng.degut.utils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class ViewUtils {
	public static void toast(Context context, String text, boolean isLong) {

		Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void toastCenter(Context context, String text, boolean isLong) {

		Toast toast = Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

	}

	public static void log(String tag, String msg) {
		Log.i(tag, msg);
	}

}
