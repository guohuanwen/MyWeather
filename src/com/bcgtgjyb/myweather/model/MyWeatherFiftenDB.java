package com.bcgtgjyb.myweather.model;

import com.bcgtgjyb.myweather.db.MyWeatherFiftenHelper;
import com.bcgtgjyb.myweather.db.MyWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyWeatherFiftenDB {
	/*
	 * ���ݿ�����
	 */
	public static final String DB_NAME="My_FiftenWeather";
	/*
	 * ���ݿ�汾
	 */
	public static final int VERSION=1;
	private static MyWeatherFiftenDB myWeatherFiftenDB;
	private SQLiteDatabase db;
	private MyWeatherFiftenDB(Context context){
		
		MyWeatherFiftenHelper dbHelper =new MyWeatherFiftenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
		Log.i("MyWeatherFiftenDB", "context");
		
	}
	/*
	 * ��ȡMyWeatherDBʵ��
	 */
	public synchronized static MyWeatherFiftenDB getInstance(Context context){
		if(myWeatherFiftenDB==null){
			myWeatherFiftenDB = new MyWeatherFiftenDB(context);
		}
		return myWeatherFiftenDB;
	}
	
	
	/**
	 * ����weatherFiften������
	 * @param WeatherFiften����
	 */
	public void saveWeather(WeatherFifteen weatherFiften){
		if(weatherFiften!=null){
			ContentValues values =new ContentValues();
			values.put("day", weatherFiften.getDay());
			values.put("time", weatherFiften.getTime());
			values.put("temperature", weatherFiften.getTemperature());
			values.put("myweather", weatherFiften.getWeather());
			db.insert("FiftenWeather", null, values);
			Log.i("MyWeatherFiftenDB", "saveWeather");
		}
	}
	
	public void saveMyWeatherPlace(WeatherPlace weatherPlace){
		if(weatherPlace!=null){
			ContentValues values=new ContentValues();
			values.put("myWeatherPlace", weatherPlace.getWeatnerPlace());
			values.put("myWeatherCode", weatherPlace.getWeatherCode());
			db.insert("WeatherPlace", null, values);
		}
	}
}
