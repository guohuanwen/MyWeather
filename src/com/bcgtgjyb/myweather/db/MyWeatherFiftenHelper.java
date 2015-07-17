package com.bcgtgjyb.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyWeatherFiftenHelper extends SQLiteOpenHelper{

	
	/*
	 * ʮ������������洢
	 */
	public static final String CREATE_FIFTENWEATHER = "create table FiftenWeather("+
			 "id integer primary key autoincrement,"+"day text,"+"time text," +
			 		"temperature text,"+"myweather text)";
	
	
	/*
	 * ������ϸ��������
	 */
	public static final String CREATE_SIXWEATHER = "create table SixWeather("+
			 "id integer primary key autoincrement,"+"day text,"+"time text," +
		 		"temperature text,"+"myweather text)";
	
	/*
	 * �洢��������
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
		db.execSQL(CREATE_FIFTENWEATHER);//����fiftenweather��	
		db.execSQL(CREATE_MYWEATHER);//����weatherPlace��
		db.execSQL(CREATE_SIXWEATHER);//����sixweather��
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
