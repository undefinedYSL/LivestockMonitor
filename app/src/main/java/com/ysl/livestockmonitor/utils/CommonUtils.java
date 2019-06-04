package com.ysl.livestockmonitor.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/*
	获取手机屏幕长宽，分辨率
 */

public class CommonUtils {

	public static int getScreenWidth(Context context){
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		float density = dm.density;
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		return width;
	}
}
