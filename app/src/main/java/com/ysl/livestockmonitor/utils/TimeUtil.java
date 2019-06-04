package com.ysl.livestockmonitor.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {

	public static String formatTime(String str){
		SimpleDateFormat sf1 = new SimpleDateFormat("HHmmss");
		SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm");
		String formatStr = "";
		try {
			formatStr = sf2.format(sf1.parse(str));
		}catch (ParseException p){
			p.printStackTrace();
			Log.d("TimeUtil","解析失败");
		}
		return formatStr;
	}
}
