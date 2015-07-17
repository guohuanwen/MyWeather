package com.bcgtgjyb.myweather.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aps.o;
import com.bcgtgjyb.myweather.db.MyWeatherCITY;

public class MyWeatherCITYDB {
	public static final String DB_NAME = "city";
	/*
	 * ��ݿ�汾
	 */
	public static final int VERSION = 1;
	private static MyWeatherCITYDB myWeatherCITYDB;
	private SQLiteDatabase db;

	private MyWeatherCITYDB(Context context) {
		MyWeatherCITY dbHelper = new MyWeatherCITY(context, DB_NAME, null,
				VERSION);
		db = dbHelper.getWritableDatabase();
		Log.i("MyweatherCITYDB", "context");
	}

	public synchronized static MyWeatherCITYDB getInstance(Context context) {
		if (myWeatherCITYDB == null) {
			myWeatherCITYDB = new MyWeatherCITYDB(context);
		}
		return myWeatherCITYDB;
	}

	/**
	 * 
	 * @param ����Ҫ��ĳ���
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List loadCity(String param) {
		Log.i("MyweatherCITYDB", param);
		List list = new ArrayList<String>();
		Cursor cursor = db.query("CITY", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {

				String cityName = cursor.getString(cursor
						.getColumnIndex("cityname"));
				String cityPY = cursor.getString(cursor
						.getColumnIndex("pycityname"));
				String cityPyShort = cursor.getString(cursor
						.getColumnIndex("pyshort"));
				Pattern pattern = Pattern.compile(param + "(.*?)");
				Matcher matcher1 = pattern.matcher(cityName);
				Matcher matcher2 = pattern.matcher(cityPY);
				Matcher matcher3 = pattern.matcher(cityPyShort);
				// Log.i("MyweatherCITYDB", cityName);
				if (matcher1.find() || matcher2.find() || matcher3.find()) {
					list.add(cityName);
					Log.i("MyweatherCITYDB", cityName);
				}
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * �洢EveryDayWeather����
	 * 
	 * @param everyDayWeather
	 */
	public void saveEveryDayWeather(List list) {

		if (list.size() > 0) {
			delet("EVERYDAYWEATHER");
			for (Object object : list) {
				EveryDayWeather everyDayWeather = (EveryDayWeather) object;
				ContentValues contentValues = new ContentValues();
				contentValues.put("date", everyDayWeather.getDate());
				contentValues.put("maxTmp", everyDayWeather.getMaxTmp());
				contentValues.put("minTmp", everyDayWeather.getMinTmp());
				contentValues.put("wind", everyDayWeather.getWind());
				contentValues.put("run", everyDayWeather.getRun());
				contentValues.put("sky", everyDayWeather.getSky());
				Log.i("MyWeatherCITYDB", contentValues.toString());
				db.insert("EVERYDAYWEATHER", null, contentValues);
			}
		}
	}

	public EveryDayWeather findEveryDayWeather(String param) {
		Log.i("MyWeatherCITYDB", "findEveryDayWeather");

		Cursor cursor = db.query("EVERYDAYWEATHER", null, null, null, null,
				null, null);
		EveryDayWeather myEveryDayWeather = null;
		try {
			if (cursor.moveToFirst()) {
				do {

					if (cursor.getString(cursor.getColumnIndex("date")).equals(
							param)) {
						EveryDayWeather everyDayWeather = new EveryDayWeather();
						everyDayWeather.setMaxTmp(cursor.getString(cursor
								.getColumnIndex("maxTmp")));
						everyDayWeather.setDate(cursor.getString(cursor
								.getColumnIndex("date")));
						everyDayWeather.setMinTmp(cursor.getString(cursor
								.getColumnIndex("minTmp")));
						everyDayWeather.setRun(cursor.getString(cursor
								.getColumnIndex("run")));
						everyDayWeather.setSky(cursor.getString(cursor
								.getColumnIndex("sky")));
						everyDayWeather.setWind(cursor.getString(cursor
								.getColumnIndex("wind")));
						myEveryDayWeather = everyDayWeather;
						break;
					} else {
					}

				} while (cursor.moveToNext());

			}
		} 
		catch (Exception e) {
			Log.i("MyWeatherCITYDB", e.toString());
		}
		return myEveryDayWeather;

	}

	/**
	 * ����EveryDayWeather����
	 * 
	 * @return EveryDayWeather
	 */
	public List loadEveryDayWeather() {
		List list = new ArrayList<EveryDayWeather>();

		Cursor cursor = db.query("EVERYDAYWEATHER", null, null, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			do {
				EveryDayWeather everyDayWeather = new EveryDayWeather();
				everyDayWeather.setMaxTmp(cursor.getString(cursor
						.getColumnIndex("maxTmp")));
				everyDayWeather.setDate(cursor.getString(cursor
						.getColumnIndex("date")));
				everyDayWeather.setMinTmp(cursor.getString(cursor
						.getColumnIndex("minTmp")));
				everyDayWeather.setRun(cursor.getString(cursor
						.getColumnIndex("run")));
				everyDayWeather.setSky(cursor.getString(cursor
						.getColumnIndex("sky")));
				everyDayWeather.setWind(cursor.getString(cursor
						.getColumnIndex("wind")));
				list.add(everyDayWeather);
			} while (cursor.moveToNext());
		}
		return list;
	}

	private int getColumnIndex(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * ����TodayWeather
	 * 
	 * @param todayWeather
	 */
	public void saveTodayWeather(List list) {

		if (list.size() > 0) {
			delet("HOURWEATHER");
			for (Object object : list) {
				TodayWeather todayWeather = (TodayWeather) object;
				ContentValues contentValues = new ContentValues();
				contentValues.put("sky", todayWeather.getSky());
				contentValues.put("run", todayWeather.getRun());
				contentValues.put("tmp", todayWeather.getTmp());
				contentValues.put("wind", todayWeather.getWind());
				contentValues.put("hour", todayWeather.getHour());
				db.insert("HOURWEATHER", null, contentValues);
			}
		}
	}

	/**
	 * ����TodayWeather
	 * 
	 * @return TodayWeather
	 */
	public List loadTodayWeather() {
		List list = new ArrayList<TodayWeather>();
		Cursor cuesor = db.query("HOURWEATHER", null, null, null, null, null,
				null);
		if (cuesor.moveToFirst()) {
			do {
				Log.i("MyWeatherCITYDB",
						cuesor.getString(cuesor.getColumnIndex("hour")));
				TodayWeather todayWeather = new TodayWeather();
				todayWeather.setHour(cuesor.getString(cuesor
						.getColumnIndex("hour")));
				todayWeather.setRun(cuesor.getString(cuesor
						.getColumnIndex("run")));
				todayWeather.setSky(cuesor.getString(cuesor
						.getColumnIndex("sky")));
				todayWeather.setTmp(cuesor.getString(cuesor
						.getColumnIndex("tmp")));
				todayWeather.setWind(cuesor.getString(cuesor
						.getColumnIndex("wind")));
				list.add(todayWeather);
			} while (cuesor.moveToNext());
		}
		return list;
	}

	public void delet(String tableName) {
		db.execSQL("DELETE FROM " + tableName);
	}

	public EveryDayWeather getToday(String param) {
		Cursor cursor = db.query("EVERYDAYWEATHER", null, "date = ?",
				new String[] { param }, null, null, null);
		EveryDayWeather everyDayWeather = new EveryDayWeather();
		if (cursor.moveToFirst()) {
			everyDayWeather.setDate(cursor.getString(cursor
					.getColumnIndex("date")));
			everyDayWeather.setMinTmp(cursor.getString(cursor
					.getColumnIndex("minTmp")));
			everyDayWeather.setMaxTmp(cursor.getString(cursor
					.getColumnIndex("maxTmp")));
			everyDayWeather.setSky(cursor.getString(cursor
					.getColumnIndex("sky")));
			everyDayWeather.setRun(cursor.getString(cursor
					.getColumnIndex("run")));
			everyDayWeather.setWind(cursor.getString(cursor
					.getColumnIndex("wind")));
		}

		return everyDayWeather;
	}
}
