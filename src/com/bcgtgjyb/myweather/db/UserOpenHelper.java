package com.bcgtgjyb.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserOpenHelper extends SQLiteOpenHelper{

	//建表语句
	public static final String SmallButton= "create table SmallButton("
			+ "id integer primary key autoincrement," + "name integer," +
			"x integer," + "y integer," + "width integer,"
			+ "height integer," + "myText integer," + "picture integer)";
		
	public static final String BigButton= "create table BigButton("
			+ "id integer primary key autoincrement," + "name integer," +
			"x integer," + "y integer," + "width integer,"
			+ "height integer," + "myText integer," + "picture integer)";
	
	
	public static final String UserCity="create table UserCity(" + 
			"id integer primary key autoincrement," + "cityName text," 
			+ "cityId text," + "mainCity integer)";
	
	public static final String UserDate = "create table UserDate( " +  "id integer primary key autoincrement," +
	"waveHeight integer," + "skin integer)";
	
	public UserOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("UserOpenHelper", "onCreate");
		db.execSQL(BigButton);
		db.execSQL(SmallButton);
		db.execSQL(UserCity);
		db.execSQL(UserDate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
