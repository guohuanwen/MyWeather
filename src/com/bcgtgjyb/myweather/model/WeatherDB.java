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
import com.bcgtgjyb.myweather.db.WeatherOpenHelper;

public class WeatherDB {
	public static final String DB_NAME = "weather";
	public static final int VERSION = 1;
	private static WeatherDB weatherDB;
	private SQLiteDatabase db;

	private WeatherDB(Context context) {
		WeatherOpenHelper weatherOpenHelper = new WeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		db = weatherOpenHelper.getWritableDatabase();
	}

	public static WeatherDB getInstence(Context context) {
		if (weatherDB == null) {
			weatherDB = new WeatherDB(context);
		}
		return weatherDB;
	}

	public List getTown() {
		List list = new ArrayList<String>();
		Cursor cursor = db.query("city_info", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				String town = cursor.getString(cursor.getColumnIndex("town"));
				list.add(town);
			} while (cursor.moveToNext());
		}
		return list;
	}
	
	public List getProvince() {
		List list = new ArrayList<String>();
		Cursor cursor = db.query("city_info", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				String town = cursor.getString(cursor.getColumnIndex("province"));
				list.add(town);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void savePinyin(String param,String i) {
		Log.i("WeatherDB", param);
		ContentValues contentValues = new ContentValues();
		contentValues.put("pinyin", param);
		db.update("city_info",contentValues,"id = ?",new String[]{i});
	}
	
	public void saveAllName(String param,String i){
		Log.i("WeatherDB", param);
		ContentValues contentValues = new ContentValues();
		contentValues.put("allName", param);
		db.update("city_info",contentValues,"id = ?",new String[]{i});
	}
	
	

	public List findCity(String param){
		Log.i("WeatherDB", "findCity");
		List list=new ArrayList<String>();
		if (param.equals("") == false) {
		char as=param.charAt(0);
		Cursor cursor=db.query("city_info", null, null, null, null, null, null);
		Pattern pattern=Pattern.compile(param+"(.*?)");
		if(('a' <= as&& as <= 'z')||('A' <= as && as <= 'Z')){
			//为拼音
			if(cursor.moveToFirst()){
				do{
					String pinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
					Matcher matcher=pattern.matcher(pinyin);
					if(matcher.find()){
						String province=cursor.getString(cursor.getColumnIndex("province"));
						String town=cursor.getString(cursor.getColumnIndex("town"));
						list.add(province+"-"+town);
					}
				}while(cursor.moveToNext());
			}
			
		}
		else{
			//为汉字
			if(cursor.moveToFirst()){
				do{
					String city=cursor.getString(cursor.getColumnIndex("town"));
					Matcher matcher=pattern.matcher(city);
					if(matcher.find()){
						String province=cursor.getString(cursor.getColumnIndex("province"));
						String town=cursor.getString(cursor.getColumnIndex("town"));
						list.add(province+"-"+town);
					}
				}while(cursor.moveToNext());
			}
		}
		}else{
			list.add("请在上方输入");
		}
		
		return list;
	}
	
	public String findCityId(String allName){
		Log.i("WeatherDB", "findCityId");
		String cityId=null;
		Cursor cursor=db.query("city_info",new String[]{ "cityWeatherCode" } , " allName = ? ", new String[]{allName}, null, null, null);
		
		if(cursor.moveToFirst()){
			Log.i("WeatherDB", allName);
			cityId=cursor.getString(cursor.getColumnIndex("cityWeatherCode"));
			Log.i("WeatherDB", allName);
			}
		return cityId;
	}
	
	
	public String findCityName(String cityId){
		Log.i("WeatherDB", "findCityId");
		String cityName=null;
		Cursor cursor=db.query("city_info",new String[]{ "town" } , " cityWeatherCode = ? ", new String[]{cityId}, null, null, null);
		
		if(cursor.moveToFirst()){
			cityName=cursor.getString(cursor.getColumnIndex("town"));
			}
		Log.i("WeatherDB",cityName);
		return cityName;
	}
	
	/**
	 * 定位形式的名称，是否在用户列表
	 * @param name
	 * @return
	 */
	public List getLocationCity(String province,String city){
		List list=new ArrayList<String>();
		String name=null;
		String code = null;
		Cursor cursor=db.query("city_info", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String provinceName=cursor.getString(cursor.getColumnIndex("province"));
				String cityName=cursor.getString(cursor.getColumnIndex("city"));
				String cityCode=cursor.getString(cursor.getColumnIndex("cityWeatherCode"));
				Pattern patternPro=Pattern.compile(provinceName+"(.*)");
				Pattern patternCity=Pattern.compile(cityName+"(.*)");
				Matcher matcherPro=patternPro.matcher(province);
				Matcher matcherCity=patternCity.matcher(city);
				if(matcherPro.find()&&matcherCity.find()){
					name=provinceName+"-"+cityName;
					code=cityCode;
					break;
				}
			}while(cursor.moveToNext());
		}
		if(name!=null&&code!=null){
			list.add(name);
			list.add(code);
		}
		return list;
	}
}
