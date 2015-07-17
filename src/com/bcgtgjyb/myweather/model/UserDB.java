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

import com.amap.api.location.c;
import com.bcgtgjyb.myweather.db.UserOpenHelper;

public class UserDB {
	public static final String DB_NAME = "user";
	public static final int VERSION = 1;
	private static SmallButton smallButton;
	private static UserDB buttonDB;
	private SQLiteDatabase db;
	private WeatherDB weatherDB;

	private UserDB(Context context) {
		Log.i("UserDB", "UserDB");
		UserOpenHelper buttonOpenHelper = new UserOpenHelper(context, DB_NAME,
				null, VERSION);
		db = buttonOpenHelper.getWritableDatabase();
		weatherDB=WeatherDB.getInstence(context);
	}

	public synchronized static UserDB getInstance(Context context) {
		if (buttonDB == null) {
			buttonDB = new UserDB(context);
		}
		return buttonDB;
	}

	public void saveSmallButton(SmallButton smallButton) {
		Log.i("ButtonDb", "saveSmallButton");
		if (smallButton != null) {
			int id = smallButton.getId();
			int x = smallButton.getX();
			int y = smallButton.getY();
			int width = smallButton.getWidth();
			int height = smallButton.getHeight();
			int text = smallButton.getText();
			int picture = smallButton.getPicture();
			ContentValues contentValues = new ContentValues();
			contentValues.put("myText", text);
			contentValues.put("name", id);
			contentValues.put("x", x);
			contentValues.put("y", y);
			contentValues.put("width", width);
			contentValues.put("height", height);
			contentValues.put("picture", picture);
			Log.i("ButtonDb", "saveSmallButton" + contentValues.toString());
			db.insert("SmallButton", null, contentValues);
		}
	}

	public void saveBigButton(BigButton smallButton) {
		if (smallButton != null) {
			int x = smallButton.getX();
			int y = smallButton.getY();
			int id = smallButton.getId();
			int width = smallButton.getWidth();
			int height = smallButton.getHeight();
			int text = smallButton.getText();
			int picture = smallButton.getPicture();
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", id);
			contentValues.put("myText", text);
			contentValues.put("x", x);
			contentValues.put("y", y);
			contentValues.put("width", width);
			contentValues.put("height", height);
			contentValues.put("picture", picture);
			db.insert("BigButton", null, contentValues);
		}
	}

	public List<SmallButton> loadSmallButton() {
		List<SmallButton> list = new ArrayList<SmallButton>();
		Cursor cursor = db.query("SmallButton", null, null, null, null, null,
				null);

		if (cursor.moveToFirst()) {
			SmallButton smallButton = new SmallButton();
			do {
				smallButton.setId(cursor.getInt(cursor.getColumnIndex("name")));
				smallButton.setX(cursor.getInt(cursor.getColumnIndex("x")));
				smallButton.setY(cursor.getInt(cursor.getColumnIndex("y")));
				smallButton.setText(cursor.getInt(cursor
						.getColumnIndex("myText")));
				smallButton.setWidth(cursor.getInt(cursor
						.getColumnIndex("width")));
				smallButton.setHeight(cursor.getInt(cursor
						.getColumnIndex("height")));
				smallButton.setPicture(cursor.getInt(cursor
						.getColumnIndex("picture")));
				list.add(smallButton);
			} while (cursor.moveToNext());
		}
		return list;

	}

	public List<BigButton> loadBigButton() {
		List<BigButton> list = new ArrayList<BigButton>();
		Cursor cursor = db.query("BigButton", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			BigButton smallButton = new BigButton();
			do {
				smallButton.setId(cursor.getInt(cursor.getColumnIndex("name")));
				smallButton.setX(cursor.getInt(cursor.getColumnIndex("x")));
				smallButton.setY(cursor.getInt(cursor.getColumnIndex("y")));
				smallButton.setText(cursor.getInt(cursor
						.getColumnIndex("myText")));
				smallButton.setWidth(cursor.getInt(cursor
						.getColumnIndex("width")));
				smallButton.setHeight(cursor.getInt(cursor
						.getColumnIndex("height")));
				smallButton.setPicture(cursor.getInt(cursor
						.getColumnIndex("picture")));
				list.add(smallButton);
			} while (cursor.moveToNext());
		}
		return list;

	}

	public void delSmallButton(String id) {
		db.delete("SmallButton", "name=?", new String[] { id });

	}

	public void delBigButton(String id) {
		db.delete("BigButton", "name=?", new String[] { id });

	}

	public int getSmallNub() {

		Cursor cursor = db.query("SmallButton", null, null, null, null, null,
				null);
		int last;
		if (cursor.moveToFirst()) {
			cursor.moveToLast();
			last = cursor.getInt(cursor.getColumnIndex("name"));
		} else {
			last = 0;
		}
		return last;
	}

	public int getBigNub() {
		Cursor cursor = db.query("BigButton", null, null, null, null, null,
				null);
		int last;
		if (cursor.moveToFirst()) {
			cursor.moveToLast();
			last = cursor.getInt(cursor.getColumnIndex("name"));
		} else {
			last = 0;
		}
		return last;
	}

	public void saveCity(String cityName, String cityId, int i) {
		if (cityName != null && cityId != null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("cityName", cityName);
			contentValues.put("cityId", cityId);
			contentValues.put("mainCity", i);
			db.insert("UserCity", null, contentValues);
		}
		if(i==1){
			setMainCity(cityName);
		}
	}

	
	/**
	 * 设置名字为param的城市为主城市
	 * @param cityName
	 */
	public void setMainCity(String cityName) {
		Log.i("UserDB", "setMainCity");
		if (cityName != null) {
			ContentValues contentValues = new ContentValues();
			ContentValues contentValuesMain = new ContentValues();
			contentValuesMain.put("mainCity", 0);
			db.update("UserCity", contentValuesMain, "mainCity = ?",new String[] {1+""});
			contentValues.put("mainCity", 1);
			db.update("UserCity", contentValues, "cityName = ?",new String[] {cityName});
		}
	}

	public List loadCity() {
		Log.i("UserDB", "loadCity");
		List listName = new ArrayList<String>();
		Cursor cursor1 = db.query("UserCity", new String[]{"mainCity","cityName"}, "mainCity = ?", new String[] {1+""}, null, null, null);
		if (cursor1.moveToFirst()) {
			do {
				String cityName = cursor1.getString(cursor1
						.getColumnIndex("cityName"));
				listName.add(cityName);
			} while (cursor1.moveToNext());
		}
		Cursor cursor2 = db.query("UserCity", new String[]{"mainCity","cityName"}, "mainCity = ?", new String[] {0+""}, null, null, null);
		if (cursor2.moveToFirst()) {
			do {
				String cityName = cursor2.getString(cursor2
						.getColumnIndex("cityName"));
				listName.add(cityName);
			} while (cursor2.moveToNext());
		}
		return listName;
	}

	public void deleteCity(String cityName) {
		Log.i("UserDB", "deleteCity");
		if (cityName != null) {
			db.delete("UserCity", "cityName = ?", new String[] { cityName });
		}
	}
	/**
	 * 根据name查询id
	 * @param allName
	 * @return
	 */
	public String searchCityId(String allName) {
		Log.i("UserDB", "searchCityId");
		String cityId=null;
		Cursor cursor = db.query("UserCity", new String[] { "cityId" },
				"cityName = ?", new String[] { allName }, null, null, null);
		if (cursor.moveToFirst()) {
			Log.i("UserDB", "true");
			cityId = cursor.getString(cursor.getColumnIndex("cityId"));
		}else{
			Log.i("UserDB", "false");
			cityId=weatherDB.findCityId(allName);
		}
		return cityId;
	}
	
	/**
	 * 是否存在MainCity
	 * @return
	 */
	public boolean isMainCity(){
		boolean have=false;
		Cursor cursor = db.query("UserCity", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				int i=cursor.getInt(cursor.getColumnIndex("mainCity"));
				if(i==1){
					have=true;
				}
			}while(cursor.moveToNext());
		}
		return have;
	}
	/**
	 * 该城市是否在用户列表
	 * @param name
	 * @return
	 */
	public boolean isCityIn(String name){
		boolean in=false;
		Cursor cursor=db.query("UserCity", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String cityName=cursor.getString(cursor.getColumnIndex("cityName"));
				if(name.equals(cityName)){
					in=true;
				}
			}while(cursor.moveToNext());
		}
		
		
		return in;
	}
	
	public void setSkin(int skin){
		Cursor cursor=db.query("UserDate", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			ContentValues contentValues=new ContentValues();
			contentValues.put("skin", skin);
			db.update("UserDate",contentValues,"id = ?",new String[] {"1"});
		}else{
			ContentValues contentValues=new ContentValues();
			contentValues.put("skin", skin);
			db.insert("UserDate", null, contentValues);
		}
	}
	
	public int getSkin(){
		int skin=0;
		Cursor cursor=db.query("UserDate", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			skin=cursor.getInt(cursor.getColumnIndex("skin"));
		}
		Log.i("UserDB", skin+"");
		return skin;
	}
	public void setWaveHeight(int height){
		Cursor cursor=db.query("UserDate", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			ContentValues contentValues=new ContentValues();
			contentValues.put("waveHeight", height);
			db.update("UserDate",contentValues,"id = ?",new String[] {"1"});
		}else{
			ContentValues contentValues=new ContentValues();
			contentValues.put("waveHeight", height);
			db.insert("UserDate", null, contentValues);
		}
		
	}
	public int getWaveHeight(){
		int waveHeight = 0;
		Cursor cursor=db.query("UserDate", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			waveHeight=cursor.getInt(cursor.getColumnIndex("waveHeight"));
		}
		return waveHeight;
	}
	
	
	

}
