package com.handmark.pulltorefresh.library;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PullUtil {

	public static final int dp2px(Context paramContext, float paramFloat) {
		return (int) (0.5F + paramFloat
				* paramContext.getResources().getDisplayMetrics().density);
	}

	public static final int px2dp(Context paramContext, float paramFloat) {
		return (int) (0.5F + paramFloat
				/ paramContext.getResources().getDisplayMetrics().density);
	}
	
	public static String formatDate(long date, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(new Date(date));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
