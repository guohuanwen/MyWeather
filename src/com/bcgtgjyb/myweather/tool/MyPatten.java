package com.bcgtgjyb.myweather.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.R.integer;
import android.util.Log;

public class MyPatten {

	public int getHour(String time) {
		if(time==null){
			return 0;
		}else{
		int re = 0;
		Pattern pattern1 = Pattern.compile("(.*):");
		Matcher matcher1 = pattern1.matcher(time);

		if (matcher1.find()) {
			re = Integer.valueOf(matcher1.group(1));
		}

		return re;}
	}

	public int getMinute(String time) {
		if(time==null){
			return 0;
		}else{
		int re = 0;
		Pattern pattern2 = Pattern.compile(":(.*)");
		Matcher matcher2 = pattern2.matcher(time);
		if (matcher2.find()) {
			re = Integer.valueOf(matcher2.group(1));
		}
		return re;}
	}

	public int getMath(String param) {
		if(param==null){
			return 0;
		}else {
		int re = 0;
		Pattern pattern2 = Pattern.compile("\\d+");
		Matcher matcher2 = pattern2.matcher(param);
		if (matcher2.find()) {
			re = Integer.valueOf(matcher2.group(0));
		}
		return re;}
	}

	public float getMax(float[][] a) {
		float a1 = a[0][1];
		for (int i = 0; i < a[0].length; i++) {
			Log.i("MyPatten", a[0][i]+"");
			if (a1 <= a[0][i]) {
				a1 = a[0][i];
			}
		}
		Log.i("MyPatten", a1+"");
		return a1;
	}

	public float getMin(float[][] a) {
		float a2 = a[1][1];
		Log.i("MyPatten", a[0].length + "");
		for (int i = 0; i < a[1].length; i++) {
			if (a2 >= a[1][i]) {
				a2 = a[1][i];
			}
		}
		return a2;
	}

}
