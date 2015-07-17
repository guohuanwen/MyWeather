package com.bcgtgjyb.myweather.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amap.api.location.c;
import com.aps.o;
import com.aps.w;
import com.bcgtgjyb.myweather.db.RespOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RespDB {
	public static final String DB_NAME = "Resp";
	public static final int VERSION = 1;
	private static RespDB respDB;
	private SQLiteDatabase db;

	private RespDB(Context context) {
		RespOpenHelper respOpenHelper = new RespOpenHelper(context, DB_NAME,
				null, VERSION);
		db = respOpenHelper.getWritableDatabase();
	}

	public static RespDB getInstance(Context context) {
		if (respDB == null) {
			respDB = new RespDB(context);
		}
		return respDB;
	}

	public void saveEasyForeCast(List list) {
		if (list.size() > 0) {
			del("Forecast");
			for (int i = 0; i < list.size(); i++) {
				ContentValues contentValues = new ContentValues();
				contentValues.put("easyCity",
						((Weather) list.get(i)).getEasyCity());
				contentValues.put("easyFengXiang",
						((Weather) list.get(i)).getEasyFengxiang());
				contentValues.put("easyFengLi",
						((Weather) list.get(i)).getEasyFengli());
				contentValues.put("easyHigh",
						((Weather) list.get(i)).getEasyHighTmp());
				contentValues.put("easyType",
						((Weather) list.get(i)).getEasyType());
				contentValues.put("easyLow",
						((Weather) list.get(i)).getEasyLowTmp());
				contentValues.put("easyDate",
						((Weather) list.get(i)).getEasyDate());
				db.insert("Forecast", null, contentValues);
			}
		}
	}

	public List loadEasyWeather() {
		Weather weather;
		List list = new ArrayList<Weather>();
		Cursor cursor = db
				.query("Forecast", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				weather = new Weather();
				weather.setEasyCity(cursor.getString(cursor
						.getColumnIndex("easyCity")));
				weather.setEasyDate(cursor.getString(cursor
						.getColumnIndex("easyDate")));
				weather.setEasyFengli(cursor.getString(cursor
						.getColumnIndex("easyFengLi")));
				weather.setEasyFengxiang(cursor.getString(cursor
						.getColumnIndex("easyFengXiang")));
				weather.setEasyHighTmp(cursor.getString(cursor
						.getColumnIndex("easyHigh")));
				weather.setEasyLowTmp(cursor.getString(cursor
						.getColumnIndex("easyLow")));
				weather.setEasyType(cursor.getString(cursor
						.getColumnIndex("easyType")));
				list.add(weather);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveTheDayWeather(TheDayWeather theDayWeather) {
		Log.i("RespDB", "saveTheDayWeather");
		if (theDayWeather != null) {
			del("TheDayWeather");
			ContentValues contentValues = new ContentValues();
			Log.i("RespDB", theDayWeather.getCity());
			contentValues.put("city", theDayWeather.getCity());
			contentValues.put("updatetime", theDayWeather.getUpdatetime());
			contentValues.put("wendu", theDayWeather.getWendu());
			contentValues.put("fengli", theDayWeather.getFengli());
			contentValues.put("shidu", theDayWeather.getShidu());
			contentValues.put("fengxiang", theDayWeather.getFengxiang());
			contentValues.put("sunrise", theDayWeather.getSunrise());
			contentValues.put("sunset", theDayWeather.getSunset());
			db.insert("TheDayWeather", null, contentValues);
		}
	}

	public void saveEnvironment(Environment environment) {
		if (environment != null) {
			del("Environment");
			ContentValues contentValues = new ContentValues();
			contentValues.put("api", environment.getApi());
			contentValues.put("pm25", environment.getPm25());
			contentValues.put("suggest", environment.getSuggest());
			contentValues.put("quality", environment.getQuality());
			contentValues.put("MajorPollutants",
					environment.getMajorPollutants());
			contentValues.put("o3", environment.getO3());
			contentValues.put("co", environment.getCo());
			contentValues.put("pm10", environment.getPm10());
			contentValues.put("so2", environment.getSo2());
			contentValues.put("no2", environment.getNo2());
			contentValues.put("time", environment.getTime());
			db.insert("Environment", null, contentValues);

		}
	}

	public void saveYesterday(Weather weather) {
		Log.i("RespDB", "saveTheDayWeather");
		if (weather != null) {
			del("Yesterday");
			Log.i("RespDB", "weather");
			ContentValues contentValues = new ContentValues();
			contentValues.put("weather_date", weather.getDate());
			contentValues.put("weather_high", weather.getHigh());
			contentValues.put("weather_low", weather.getLow());
			contentValues.put("weather_day_type", weather.getDay_type());
			contentValues.put("weather_day_fengxiang", weather.getDay_fx());
			contentValues.put("weather_day_fengli", weather.getDay_fl());
			contentValues.put("weather_night_type", weather.getNight_type());
			contentValues.put("weather_night_fengxiang", weather.getNight_fx());
			contentValues.put("weather_night_fengli", weather.getNight_fl());
			db.insert("Yesterday", null, contentValues);
		}
	}

	public void saveWeather(Weather weather) {
		if (weather != null) {
			del("Weather");
			ContentValues contentValues = new ContentValues();
			contentValues.put("weather_date", weather.getDate());
			contentValues.put("weather_high", weather.getHigh());
			contentValues.put("weather_low", weather.getLow());
			contentValues.put("weather_day_type", weather.getDay_type());
			contentValues.put("weather_day_fengxiang", weather.getDay_fx());
			contentValues.put("weather_day_fengli", weather.getDay_fl());
			contentValues.put("weather_night_type", weather.getNight_type());
			contentValues.put("weather_night_fengxiang", weather.getNight_fx());
			contentValues.put("weather_night_fengli", weather.getNight_fl());
			db.insert("Weather", null, contentValues);
		}
	}

	public void saveZhishu(Zhishu zhishu) {
		if (zhishu != null) {
			del("Zhishu");
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", zhishu.getName());
			contentValues.put("value", zhishu.getValue());
			contentValues.put("detail", zhishu.getDetail());
			db.insert("Zhishu", null, contentValues);
		}
	}

	public TheDayWeather loadTheDayWeather() {
		TheDayWeather theDayWeather = new TheDayWeather();
		Cursor cursor = db.query("TheDayWeather", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			theDayWeather.setCity(cursor.getString(cursor
					.getColumnIndex("city")));
			theDayWeather.setUpdatetime(cursor.getString(cursor
					.getColumnIndex("updatetime")));
			theDayWeather.setWendu(cursor.getString(cursor
					.getColumnIndex("wendu")));
			theDayWeather.setFengli(cursor.getString(cursor
					.getColumnIndex("fengli")));
			theDayWeather.setShidu(cursor.getString(cursor
					.getColumnIndex("shidu")));
			theDayWeather.setFengxiang(cursor.getString(cursor
					.getColumnIndex("fengxiang")));
			theDayWeather.setSunrise(cursor.getString(cursor
					.getColumnIndex("sunrise")));
			theDayWeather.setSunset(cursor.getString(cursor
					.getColumnIndex("sunset")));
		}
		cursor.close();
		return theDayWeather;
	}

	public Weather loadYesterday() {
		Weather weather = null;
		Cursor cursor = db.query("Yesterday", null, null, null, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			Log.i("RespDB", "loadYesterday");
			weather = new Weather();
			weather.setDate(cursor.getString(cursor
					.getColumnIndex("weather_date")));
			weather.setHigh(cursor.getString(cursor
					.getColumnIndex("weather_high")));
			weather.setLow(cursor.getString(cursor
					.getColumnIndex("weather_low")));
			weather.setDay_type(cursor.getString(cursor
					.getColumnIndex("weather_day_type")));
			weather.setDay_fx(cursor.getString(cursor
					.getColumnIndex("weather_day_fengxiang")));
			weather.setDay_fl(cursor.getString(cursor
					.getColumnIndex("weather_day_fengli")));
			weather.setNight_type(cursor.getString(cursor
					.getColumnIndex("weather_night_type")));
			weather.setNight_fx(cursor.getString(cursor
					.getColumnIndex("weather_night_fengxiang")));
			weather.setNight_fl(cursor.getString(cursor
					.getColumnIndex("weather_night_fengli")));
		}
		cursor.close();
		return weather;
	}

	public List loadWeather() {
		List list = new ArrayList<Weather>();
		Cursor cursor = db.query("Weather", null, null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			Weather weather;
			do {
				weather = new Weather();
				weather.setDate(cursor.getString(cursor
						.getColumnIndex("weather_date")));
				weather.setHigh(cursor.getString(cursor
						.getColumnIndex("weather_high")));
				weather.setLow(cursor.getString(cursor
						.getColumnIndex("weather_low")));
				weather.setDay_type(cursor.getString(cursor
						.getColumnIndex("weather_day_type")));
				weather.setDay_fx(cursor.getString(cursor
						.getColumnIndex("weather_day_fengxiang")));
				weather.setDay_fl(cursor.getString(cursor
						.getColumnIndex("weather_day_fengli")));
				weather.setNight_type(cursor.getString(cursor
						.getColumnIndex("weather_night_type")));
				weather.setNight_fx(cursor.getString(cursor
						.getColumnIndex("weather_night_fengxiang")));
				weather.setNight_fl(cursor.getString(cursor
						.getColumnIndex("weather_night_fengli")));
				list.add(weather);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	public List loadZhishu() {
		List list = new ArrayList<Zhishu>();
		Zhishu zhishu;
		Cursor cursor = db.query("Zhishu", null, null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				zhishu = new Zhishu();
				zhishu.setName(cursor.getString(cursor.getColumnIndex("name")));
				zhishu.setValue(cursor.getString(cursor.getColumnIndex("value")));
				zhishu.setDetail(cursor.getString(cursor
						.getColumnIndex("datail")));
				list.add(zhishu);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	/**
	 * 清空表
	 * 
	 * @param tableName
	 */
	public void del(String tableName) {
		db.execSQL("DELETE FROM " + tableName);
	}

	public void saveNowWeather(NowWeather nowWeather) {
		if (nowWeather != null) {
			del("NowWeather");
			ContentValues contentValues = new ContentValues();
			contentValues.put("city", nowWeather.getCity());
			contentValues.put("weather", nowWeather.getWeather());
			contentValues.put("temperature", nowWeather.getTemperature());
			contentValues.put("windDir", nowWeather.getWindDir());
			contentValues.put("windPower", nowWeather.getWindPower());
			contentValues.put("humidity", nowWeather.getHumidity());
			contentValues.put("reportTime", nowWeather.getReportTime());
			contentValues.put("img1", nowWeather.getImg1());
			contentValues.put("img2", nowWeather.getImg2());
			db.insert("NowWeather", null, contentValues);
		}
	}

	public NowWeather loadNowWeather() {
		NowWeather nowWeather = null;
		Cursor cursor = db.query("NowWeather", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			nowWeather=new NowWeather();
			nowWeather.setCity(cursor.getString(cursor.getColumnIndex("city")));
			nowWeather.setWeather(cursor.getString(cursor
					.getColumnIndex("weather")));
			nowWeather.setTemperature(cursor.getString(cursor
					.getColumnIndex("temperature")));
			nowWeather.setWindDir(cursor.getString(cursor
					.getColumnIndex("windDir")));
			nowWeather.setWindPower(cursor.getString(cursor
					.getColumnIndex("windPower")));
			nowWeather.setHumidity(cursor.getString(cursor
					.getColumnIndex("humidity")));
			nowWeather.setReportTime(cursor.getString(cursor
					.getColumnIndex("reportTime")));
			nowWeather.setImg1(cursor.getInt(cursor.getColumnIndex("img1")));
			nowWeather.setImg2(cursor.getInt(cursor.getColumnIndex("img2")));
		}
		return nowWeather;
	}

	public void saveOpenWeather(List list) {
		OpenWeatherCode openWeatherCode = new OpenWeatherCode();
		if (list.size() != 0) {
			del("OpenWeather");
			for (int i = 0; i < list.size(); i++) {
				OpenWeather openWeather = (OpenWeather) list.get(i);
				if (openWeather != null) {
					// if(openWeather.getFa()>=0){
					ContentValues contentValues = new ContentValues();
					contentValues.put("f0", openWeather.getF0());
					contentValues.put("fa", openWeather.getFa());
					contentValues.put("fb", openWeather.getFb());
					contentValues.put("fc", openWeather.getFc());
					contentValues.put("fd", openWeather.getFd());
					contentValues.put("fe", openWeather.getFe());
					contentValues.put("ff", openWeather.getFf());
					contentValues.put("fg", openWeather.getFg());
					contentValues.put("fh", openWeather.getFh());
					contentValues.put("fi", openWeather.getFi());
					contentValues.put("day", openWeather.getDay());
					db.insert("OpenWeather", null, contentValues);
					// }else if(openWeather.getFa()==-1){
					// ContentValues contentValues=new ContentValues();
					// contentValues.put("f0", openWeather.getF0());
					// contentValues.put("fb", openWeather.getFb());
					// contentValues.put("fd", openWeather.getFd());
					// contentValues.put("ff", openWeather.getFf());
					// contentValues.put("fh", openWeather.getFh());
					// db.update("OpenWeather", contentValues, whereClause,
					// whereArgs);
					// }
				}
			}
		}
	}

	public List loadOpenWeather() {
		List list = new ArrayList<OpenWeather>();
		OpenWeather openWeather;
		Cursor cursor = db.query("OpenWeather", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				openWeather = new OpenWeather();
				openWeather
						.setF0(cursor.getString(cursor.getColumnIndex("f0")));
				openWeather.setFa(cursor.getInt(cursor.getColumnIndex("fa")));
				openWeather.setFb(cursor.getInt(cursor.getColumnIndex("fb")));
				openWeather.setFc(cursor.getInt(cursor.getColumnIndex("fc")));
				openWeather.setFd(cursor.getInt(cursor.getColumnIndex("fd")));
				openWeather.setFe(cursor.getInt(cursor.getColumnIndex("fe")));
				openWeather.setFf(cursor.getInt(cursor.getColumnIndex("ff")));
				openWeather.setFg(cursor.getInt(cursor.getColumnIndex("fg")));
				openWeather.setFh(cursor.getInt(cursor.getColumnIndex("fh")));
				openWeather
						.setFi(cursor.getString(cursor.getColumnIndex("fi")));
				list.add(openWeather);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveOpenZhiShu(List list) {
		if (list.size() != 0) {
			del("OpenZhiShu");
			Log.i("RespDB", list.size() + "");
			for (int i = 0; i < list.size(); i++) {
				OpenZhiShu openZhiShu = (OpenZhiShu) list.get(i);
				if (openZhiShu != null) {
					ContentValues contentValues = new ContentValues();
					contentValues.put("name", openZhiShu.getName());
					contentValues.put("chineseName",
							openZhiShu.getChineseName());
					contentValues.put("value", openZhiShu.getValue());
					contentValues.put("advise", openZhiShu.getAdvise());
					contentValues.put("day", openZhiShu.getDay());
					db.insert("OpenZhiShu", null, contentValues);
				}
			}
		}
	}

	public List loadOpenZhiShu() {
		List list = new ArrayList<OpenZhiShu>();
		OpenZhiShu openZhiShu;
		Cursor cursor = db.query("OpenZhiShu", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				openZhiShu = new OpenZhiShu();
				openZhiShu.setName(cursor.getString(cursor
						.getColumnIndex("name")));
				openZhiShu.setChineseName(cursor.getString(cursor
						.getColumnIndex("chineseName")));
				openZhiShu.setValue(cursor.getString(cursor
						.getColumnIndex("value")));
				openZhiShu.setAdvise(cursor.getString(cursor
						.getColumnIndex("advise")));
				list.add(openZhiShu);
			} while (cursor.moveToNext());
		}

		return list;
	}

	public Weather loadNowDayWeather(String day) {
		Log.i("Resp", "loadNowDayWeather"+"*"+day);
		Weather weather = null;
		Cursor cursor = db
				.query("Forecast", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor
						.getColumnIndex("easyDate"));
				Pattern pattern = Pattern.compile(day + "(.*)");
				Matcher matcher = pattern.matcher(name);
				if (matcher.find()) {
					Log.i("Resp", "loadNowDayWeather"+"*"+name);
					weather = new Weather();
					weather.setEasyCity(cursor.getString(cursor
							.getColumnIndex("easyCity")));
					weather.setEasyDate(cursor.getString(cursor
							.getColumnIndex("easyDate")));
					weather.setEasyFengli(cursor.getString(cursor
							.getColumnIndex("easyFengLi")));
					weather.setEasyFengxiang(cursor.getString(cursor
							.getColumnIndex("easyFengXiang")));
					weather.setEasyHighTmp(cursor.getString(cursor
							.getColumnIndex("easyHigh")));
					weather.setEasyLowTmp(cursor.getString(cursor
							.getColumnIndex("easyLow")));
					weather.setEasyType(cursor.getString(cursor
							.getColumnIndex("easyType")));
					break;
				}else{
					weather=null;
				}
			} while (cursor.moveToNext());
		}
		return weather;
	}
}
