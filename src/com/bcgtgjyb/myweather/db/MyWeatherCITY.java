package com.bcgtgjyb.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyWeatherCITY extends SQLiteOpenHelper {
	/*
	 * Province表建表语句
	 */
	public static final String CREATE_PROVINCE = "create table PROVINCE("
			+ "id integer primary key autoincrement," + "provinceid text,"
			+ "provincename text)";
	/*
	 * City表建表语句
	 */

	public static final String CREATE_CITY = "create table CITY("
			+ "id integer primary key autoincrement," + "cityid text,"
			+ "cityname text," + "pycityname text," + "pyshort text,"
			+ "provinceid integer)";

	
	//创建everyDayWeatner表
	public static final String CREATE_EVERYDAYWEATHER = "creat table EVERYDAYWEATHER("
			+ "id integer primary key autoincrement," + "maxTmp text," +
			"minTmp text," + "sky text," + "run text," + "wind text," +
			"date text)";
	
	
	//创建hourWeather表
	public static final String CREATE_HOURWEATHER = "creat table HOURWEATHER(" + 
			"id integer primary key autoincrement,"+
			"sky text," + "run text," + "tmp text," + "wind text," + "hour text)"
			;
	
	
	public MyWeatherCITY(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_EVERYDAYWEATHER);
		db.execSQL(CREATE_HOURWEATHER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
