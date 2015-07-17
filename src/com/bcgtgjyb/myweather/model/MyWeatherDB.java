package com.bcgtgjyb.myweather.model;

import java.util.ArrayList;
import java.util.List;
import com.bcgtgjyb.myweather.db.MyWeatherOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyWeatherDB {
	public static final String DB_NAME="My_weather";
	public static final int VERSION=1;
	private static MyWeatherDB myWeatherDB;
	private SQLiteDatabase db;
	
	private MyWeatherDB(Context context){

		MyWeatherOpenHelper dbHelper =new MyWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
		Log.i("MyWeatherDB", "context");
		
	}
	
	public synchronized static MyWeatherDB getInstance(Context context){
		if(myWeatherDB==null){
			myWeatherDB = new MyWeatherDB(context);
		}
		return myWeatherDB;
	}
	
	
	public void saveProvince (Province province){
		if(province!=null){
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			db.insert("Province", null, values);
		}
	}
	
	public List<Province> loadProvinces(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor =db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			Province province = new Province();
			do{
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
//				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	public void saveCity(City city){
		if(city!=null){
			ContentValues values= new ContentValues();
			values.put("city_name", city.getCityName());
//			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	public List<City> loadCities(String provinceId){
		Log.i("MyWeatherDB", "loadCities");
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?", new String[]{provinceId}, null, null, null);
		Log.i("MyWeatherDB", provinceId);
		if(cursor.moveToFirst()){
			City city =  new City();
			do{
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
//				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				Log.i("MyWeatherDB", city.getCityName());
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
	/*
	 * ��Countyʵ��洢����ݿ�
	 */
	public void saveCounty(County county){
		if(county!=null){
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
			}
		}
	
	/*
	 * ����ݿ��ȡĳ���������Ե�����Ϣ
	 */
	public List<County> loadCounties(String cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?", new String[]{
				cityId
		},null,null,null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
	
	return list;
	}

}
