package com.bcgtgjyb.myweather.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.aps.o;
import com.bcgtgjyb.myweather.model.EveryDayWeather;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.model.MyWeatherDB;
import com.bcgtgjyb.myweather.model.MyWeatherFiftenDB;
import com.bcgtgjyb.myweather.model.OpenWeather;
import com.bcgtgjyb.myweather.model.OpenZhiShu;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.TheDayWeather;
import com.bcgtgjyb.myweather.model.TodayWeather;
import com.bcgtgjyb.myweather.model.Weather;
import com.bcgtgjyb.myweather.model.WeatherFifteen;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyTime;
import com.bcgtgjyb.myweather.tool.SAXHandler;

public class Utility {
	private static Context context = new MyApplication().getContext();

	/**
	 * 解析十五天的天气
	 * 
	 * @param html
	 */
	public static void AnalysisWeatherHtml(String html) {
		ArrayList<String> list = new ArrayList<String>();
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		Elements note = doc.select("tbody");
		for (org.jsoup.nodes.Element link : note) {
			for (org.jsoup.nodes.Element link1 : link.select("span")) {
				String text = link1.text().toString();
				if (!text.equals("")) {
					list.add(text);
				}
			}
		}
		int i = 1;
		Log.i("list", list.toString());
		MyWeatherFiftenDB myWeatherFiftenDB = MyWeatherFiftenDB
				.getInstance(context);
		Log.i("db", myWeatherFiftenDB.toString());
		WeatherFifteen weatherFifteen1 = new WeatherFifteen();
		WeatherFifteen weatherFifteen2 = new WeatherFifteen();
		for (String parme : list) {
			Log.i("prame", parme);
			switch (i % 7) {
			case 1:
				weatherFifteen1.setTime(parme);
				break;
			case 2:
				weatherFifteen1.setDay(parme);
				weatherFifteen2.setDay(parme);
				break;
			case 3:
				weatherFifteen2.setTime(parme);
				break;
			case 4:
				weatherFifteen1.setTemperature(parme);
				break;
			case 5:
				weatherFifteen1.setWeather(parme);
				break;
			case 6:
				weatherFifteen2.setTemperature(parme);
				break;
			case 0:
				weatherFifteen2.setWeather(parme);
				myWeatherFiftenDB.saveWeather(weatherFifteen1);
				myWeatherFiftenDB.saveWeather(weatherFifteen2);
				break;
			default:
				break;
			}
			i++;
		}

	}

	/**
	 * 解析
	 * 
	 * @param json
	 */
	public static String[] AnalysisUrl2(String json) {
		String[] param = new String[2];
		try {
			JSONObject myjson = new JSONObject(json);
			param[0] = myjson.getString("result");
			param[1] = myjson.getString("success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return param;
	}

	/**
	 * 解析今天每小时天气
	 * 
	 * @param xml
	 * @return
	 */

	public static List AnalysisWeatherJson(String json) {
		Log.i("Utility", "AnalysisWeatherJson" + "-----" + json);
		MyWeatherCITYDB myWeatherCITYDB = MyWeatherCITYDB.getInstance(context);
		try {
			AssetManager assetManager = context.getAssets();
			InputStream in = assetManager.open("441.txt");
			InputStreamReader inRead = new InputStreamReader(in, "gbk");
			BufferedReader buffRead = new BufferedReader(inRead);
			StringBuffer sBuff = new StringBuffer();
			String data = "";
			while ((data = buffRead.readLine()) != null) {
				sBuff.append(data);
			}
			// Log.i("Utility", sBuff.toString());
			JSONObject jsonObj = new JSONObject(json.toString());
			JSONObject result = jsonObj.getJSONObject("result");
			JSONArray days = result.getJSONArray("days");
			JSONArray hours = result.getJSONArray("hours");

			List eList = new ArrayList<EveryDayWeather>();
			for (int i = 0; i < days.length(); i++) {
				EveryDayWeather everyDayWeather = new EveryDayWeather();
				JSONObject jsob = (JSONObject) days.get(i);
				String maxTmp = jsob.getString("maxTmp");
				String minTmp = jsob.getString("minTmp");
				String sky = jsob.getString("sky");
				String run = jsob.getString("run");
				String wind = jsob.getString("wind");
				String date = jsob.getString("date");
				Log.i("Utilty", maxTmp);
				Log.i("Utilty", minTmp);
				Log.i("Utilty", sky);
				Log.i("Utilty", run);
				Log.i("Utilty", wind);
				Log.i("Utilty", date);

				if (sky.equals("N/A")) {
					everyDayWeather = myWeatherCITYDB.findEveryDayWeather(date);
				} else {
					everyDayWeather.setMaxTmp(maxTmp);
					everyDayWeather.setMinTmp(minTmp);
					everyDayWeather.setSky(sky);
					everyDayWeather.setRun(run);
					everyDayWeather.setWind(wind);
					everyDayWeather.setDate(date);
				}

				eList.add(everyDayWeather);
			}
			myWeatherCITYDB.saveEveryDayWeather(eList);

			List tList = new ArrayList<TodayWeather>();
			for (int i = 0; i < hours.length(); i++) {
				TodayWeather todayWeather = new TodayWeather();
				JSONObject jsob = (JSONObject) hours.get(i);
				Log.i("Utility", jsob.toString());
				String sky = jsob.getString("sky");
				String run = jsob.getString("run");
				String wind = jsob.getString("wind");
				String hour = jsob.getString("hour");
				String tmp = jsob.getString("tmp");
				Log.i("Utilty", tmp);
				Log.i("Utilty", sky);
				Log.i("Utilty", run);
				Log.i("Utilty", wind);
				Log.i("Utilty", hour);
				todayWeather.setSky(sky);
				todayWeather.setRun(run);
				todayWeather.setWind(wind);
				todayWeather.setHour(hour);
				todayWeather.setTmp(tmp);
				tList.add(todayWeather);
			}
			myWeatherCITYDB.saveTodayWeather(tList);
			// Log.i("Utility", hourJsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// /**
	// * 解析今天的天气图标
	// */
	//
	// public void analisisIcon(String json){
	// try{
	// AssetManager assetManager = context.getAssets();
	// InputStream in = assetManager.open("icon.txt");
	// InputStreamReader inRead = new InputStreamReader(in, "gbk");
	// BufferedReader buffRead = new BufferedReader(inRead);
	// StringBuffer sBuff = new StringBuffer();
	// String data = "";
	// while ((data = buffRead.readLine()) != null) {
	// sBuff.append(data);
	// }
	// RespDB respDB=RespDB.getInstance(context);
	// JSONObject jsonObj = new JSONObject(sBuff.toString());
	// JSONObject weatherinfo=jsonObj.getJSONObject("weatherinfo");
	// String img1=weatherinfo.getString("img1");
	// String img2=weatherinfo.getString("img2");
	// if(img1!=null&&img2!=null){
	// respDB.del("Icon");
	// respDB.saveIcon(img1, img2);
	// }
	//
	//
	//
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// }

	/**
	 * 解析未来的天气json
	 */

	public void analysisWeatherJson(String json) {
		RespDB respDB = RespDB.getInstance(context);
		AssetManager assetManager = context.getAssets();
		InputStream in;
		try {
			in = assetManager.open("200.txt");
			InputStreamReader inRead = new InputStreamReader(in, "gbk");
			BufferedReader buffRead = new BufferedReader(inRead);
			StringBuffer sBuff = new StringBuffer();
			String data = "";
			while ((data = buffRead.readLine()) != null) {
				sBuff.append(data);
			}
			Log.i("Utility", sBuff.toString());
			JSONObject jsonObj = new JSONObject(json);
			JSONObject result = jsonObj.getJSONObject("data");
			JSONArray forecast = result.getJSONArray("forecast");
			String city = result.getString("city");
			List list = new ArrayList<Weather>();
			for (int i = 0; i < forecast.length(); i++) {
				Weather weather = new Weather();
				JSONObject jsonObject = (JSONObject) forecast.get(i);
				weather.setEasyFengxiang((jsonObject.getString("fengxiang")));
				weather.setEasyFengli(jsonObject.getString("fengli"));
				weather.setEasyHighTmp(jsonObject.getString("high"));
				weather.setEasyType(jsonObject.getString("type"));
				weather.setEasyLowTmp(jsonObject.getString("low"));
				weather.setEasyDate(jsonObject.getString("date"));
				weather.setEasyCity(city);
				list.add(weather);
			}
			respDB.saveEasyForeCast(list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解析xml
	 */
	public void analysisWeatherXml(InputStream inputSource) {
		Log.i("Utility", "analysisWeatherXml");
		AssetManager assetManager = new MyApplication().getContext()
				.getAssets();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			//InputStream in = assetManager.open("xml.txt");
			parser.parse(inputSource, handler);
			// TheDayWeather theDayWeather = handler.getTheDayWeather();
			// Log.i("Utility", "*"+theDayWeather.getCity());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 解析openWeather网站数据
	 */
	public void analysisOpenWeather(String json) {
		RespDB respDB = RespDB.getInstance(context);
		AssetManager assetManager = context.getAssets();
		InputStream in;
		List list = new ArrayList<OpenWeather>();
		try {
			in = assetManager.open("101.txt");
			InputStreamReader inRead = new InputStreamReader(in, "gbk");
			BufferedReader buffRead = new BufferedReader(inRead);
			StringBuffer sBuff = new StringBuffer();
			String data = "";
			while ((data = buffRead.readLine()) != null) {
				sBuff.append(data);
			}
			Log.i("Utility", sBuff.toString());
			JSONObject jsonObj = new JSONObject(json);
			JSONObject f = jsonObj.getJSONObject("f");
			String f0 = f.getString("f0");
			OpenWeather openWeather;
			JSONArray f1 = f.getJSONArray("f1");
			MyTime myTime = new MyTime();
			for (int i = 0; i < f1.length(); i++) {
				openWeather = new OpenWeather();
				JSONObject now = (JSONObject) f1.get(i);
				openWeather.setF0(f0);
				openWeather.setFa(Integer.valueOf(now.getString("fa")));
				openWeather.setFb(Integer.valueOf(now.getString("fb")));
				openWeather.setFc(Integer.valueOf(now.getString("fc")));
				openWeather.setFd(Integer.valueOf(now.getString("fd")));
				openWeather.setFe(Integer.valueOf(now.getString("fe")));
				openWeather.setFf(Integer.valueOf(now.getString("ff")));
				openWeather.setFg(Integer.valueOf(now.getString("fg")));
				openWeather.setFh(Integer.valueOf(now.getString("fh")));
				openWeather.setFi(now.getString("fi"));
				openWeather.setDay(Integer.valueOf(myTime.getFuture(i)));
				list.add(openWeather);
			}
			respDB.saveOpenWeather(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析openWeather网站数据
	 */
	public void analysisOpenZhishu(String json) {
		RespDB respDB = RespDB.getInstance(context);
		AssetManager assetManager = context.getAssets();
		InputStream in;
		List list = new ArrayList<OpenZhiShu>();
		try {
			in = assetManager.open("102.txt");
			InputStreamReader inRead = new InputStreamReader(in, "gbk");
			BufferedReader buffRead = new BufferedReader(inRead);
			StringBuffer sBuff = new StringBuffer();
			String data = "";
			while ((data = buffRead.readLine()) != null) {
				sBuff.append(data);
			}
			Log.i("Utility", sBuff.toString());
			JSONObject jsonObj = new JSONObject(json);
			JSONArray i = jsonObj.getJSONArray("i");
			OpenZhiShu openZhiShu;
			MyTime myTime = new MyTime();
			for (int n = 0; n < i.length(); n++) {
				openZhiShu = new OpenZhiShu();
				JSONObject now = i.getJSONObject(n);
				openZhiShu.setName(now.getString("i1"));
				openZhiShu.setChineseName(now.getString("i2"));
				openZhiShu.setValue(now.getString("i4"));
				openZhiShu.setAdvise(now.getString("i5"));
				openZhiShu.setDay(Integer.valueOf(myTime.getFuture(n)));
				list.add(openZhiShu);
			}
			respDB.saveOpenZhiShu(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
