package com.bcgtgjyb.myweather.tool;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private static  Context context;
	/**
	 * 获取全局context
	 */
	@Override
	public void onCreate() {
	context = getApplicationContext();
	}
	public static Context getContext() {
	return context;
	}

}
