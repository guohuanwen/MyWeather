package com.bcgtgjyb.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RespOpenHelper extends SQLiteOpenHelper{
	
public static final String TheDayWeather= "create table TheDayWeather("
			+ "id integer primary key autoincrement," + "city text," + 
		"updatetime text," + "wendu text," + "fengli text," + "shidu text," +
		"fengxiang text," + "sunrise text," + "sunset text)" ; 

public static final String Environment = "create table Environment(" + 
		"api text," + "pm25 text," + "suggest text," + "quality text," +
		"MajorPollutants text," + "o3 text," + "co text," + "pm10 text," +
		"so2 text," + "no2 text," + "time text)";

public static final String Yesterday = "create table Yesterday(" + "weather_date text," + 
		"weather_high text," + "weather_low text," + "weather_day_type text," + 
		"weather_day_fengxiang text," +"weather_day_fengli text," + "weather_night_type text," +
		"weather_night_fengxiang text," + "weather_night_fengli text)";

public static final String Weather = "create table Weather(" + "weather_date text," + 
		"weather_high text," + "weather_low text," + "weather_day_type text," + 
		"weather_day_fengxiang text," +"weather_day_fengli text," + "weather_night_type text," +
		"weather_night_fengxiang text," + "weather_night_fengli text)";

public static final String Zhishu = "create table Zhishu(" + "name text," + "value text," + 
		"detail text)";

public static final String NowWeather = "create table NowWeather(" + "city text," + "weather text," +
		"temperature text," + "windDir text," + "windPower text," + "humidity text," + "reportTime text,"
		+ "img1 integer," + "img2 integer)";


public static final String Forecast = "create table ForeCast(" + "easyCity text," + "easyFengXiang text," + 
		"easyFengLi text," + "easyHigh text," + "easyType text," + "easyLow text," + "easyDate text)";

public static final String OpenWeather = "create table OpenWeather(" + "f0 text," + "fa integer," + "fb integer,"
		+ "fc integer," + "fd integer," + "fe integer," +"ff integer," + "fg integer,"+"fh integer," + "fi text,"
		+ "day integer)"; 

public static final String OpenZhiShu = "create table OpenZhiShu(" + "name text," + "chineseName text," + "value text,"+
		"advise text, " + "day integer)";
	public RespOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TheDayWeather);
		db.execSQL(Environment);
		db.execSQL(Weather);
		db.execSQL(Yesterday);
		db.execSQL(Zhishu);
		db.execSQL(NowWeather);
		db.execSQL(Forecast);
		db.execSQL(OpenWeather);
		db.execSQL(OpenZhiShu);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
