package com.bcgtgjyb.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyWeatherFiftenHelper extends SQLiteOpenHelper{

	
	/*
	 * 十五日天气建表存储
	 */
	public static final String CREATE_FIFTENWEATHER = "create table FiftenWeather("+
			 "id integer primary key autoincrement,"+"day text,"+"time text," +
			 		"temperature text,"+"myweather text)";
	
	
	/*
	 * 六日详细天气表储存
	 */
	public static final String CREATE_SIXWEATHER = "create table SixWeather("+
			 "id integer primary key autoincrement,"+"day text,"+"time text," +
		 		"temperature text,"+"myweather text)";
	
	/*
	 * 存储天气地名
	 */
	public static final String CREATE_MYWEATHER = "create table WeatherPlace("+
			 "id integer primary key autoincrement,"+"myWeatherPlace text,"+"myWeatherCode text)";
	
	public MyWeatherFiftenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_FIFTENWEATHER);//创建fiftenweather表	
		db.execSQL(CREATE_MYWEATHER);//创建weatherPlace表
		db.execSQL(CREATE_SIXWEATHER);//创建sixweather表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
