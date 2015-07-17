package com.bcgtgjyb.myweather.tool;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.TheDayWeather;
import com.bcgtgjyb.myweather.model.Weather;
import com.bcgtgjyb.myweather.model.Zhishu;

public class SAXHandler extends DefaultHandler{
	private Weather weather=null;
	private Weather yesterday=null;
	private Zhishu zhishu=null;
	private com.bcgtgjyb.myweather.model.Environment environment=null;
	private TheDayWeather theDayWeather=null;
	private String nowTag;
	private String twoTag;
	private String oneTag;
	private List weatherList=new ArrayList<Weather>();
	private List zhishuList=new ArrayList<Weather>();
	private RespDB respDB;
	
	public TheDayWeather getTheDayWeather(){
		return this.theDayWeather;
	}
	public Weather getYeaterday(){
		return this.yesterday;
	}
	public List getWeatherList(){
		return weatherList;
	}
	public List getZhiShuList(){
		return zhishuList;
	}
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String data=new String(ch,start,length);
		Log.i("SAXHandler", "characters："+data);
		if(theDayWeather!=null){
			Log.i("SAXHandler", "theDayWeather");
			if("city".equals(nowTag)){
				Log.i("SAXHandler", "setCity");
				theDayWeather.setCity(data);
			}
			if("updatetime".equals(nowTag)){
				theDayWeather.setUpdatetime(data);
			}
			if("wendu".equals(nowTag)){
				theDayWeather.setWendu(data);
			}
			if("fengli".equals(nowTag)){
				theDayWeather.setFengli(data);
			}
			if("shidu".equals(nowTag)){
				theDayWeather.setShidu(data);
			}
			if("fengxiang".equals(nowTag)){
				theDayWeather.setFengxiang(data);
			}
			if("sunrise_1".equals(nowTag)){
				theDayWeather.setSunrise(data);
			}
			if("sunset_1".equals(nowTag)){
				theDayWeather.setSunset(data);
			}
		}
		if(environment!=null){
			if("api".equals(nowTag)){
			environment.setApi(data);
			}
			if("pm25".equals(nowTag)){
				environment.setPm25(data);
			}
			if("suggest".equals(nowTag)){
				environment.setSuggest(data);
			}
			if("quality".equals(nowTag)){
				environment.setQuality(data);
			}
			if("o3".equals(nowTag)){
				environment.setO3(data);
			}
			if("co".equals(nowTag)){
				environment.setCo(data);
			}
			if("pm10".equals(nowTag)){
				environment.setPm10(data);
			}
			if("so2".equals(nowTag)){
				environment.setSo2(data);
			}
			if("no2".equals(nowTag)){
				environment.setNo2(data);
			}
			if("time".equals(nowTag)){
				environment.setTime(data);
			}
		}
		
		if(yesterday!=null&&"yesterday".equals(oneTag)){
			if("date_1".equals(nowTag)){
				yesterday.setDate(data);
			}
			if("high_1".equals(nowTag)){
				yesterday.setHigh(data);
			}
			if("low_1".equals(nowTag)){
				yesterday.setLow(data);
			}
			if("type_1".equals(nowTag)&&"day_1".equals(twoTag)){
				yesterday.setDay_type(data);
			}
			if("fx_1".equals(nowTag)&&"day_1".equals(twoTag)){
				yesterday.setDay_fx(data);
			}
			if("fl_1".equals(nowTag)&&"day_1".equals(twoTag)){
				yesterday.setDay_fl(data);
			}
			if("type_1".equals(nowTag)&&"night_1".equals(twoTag)){
				yesterday.setNight_type(data);
			}
			if("fx_1".equals(nowTag)&&"night_1".equals(twoTag)){
				yesterday.setNight_fx(data);
			}
			if("fl_1".equals(nowTag)&&"night_1".equals(twoTag)){
				yesterday.setNight_fl(data);
			}
		}
		
		if(weather!=null&&"forecast".equals(oneTag)){
			if("date".equals(nowTag)){
				weather.setDate(data);
			}
			if("high".equals(nowTag)){
				weather.setHigh(data);
			}
			if("low".equals(nowTag)){
				weather.setLow(data);
			}
			if("type".equals(nowTag)&&"day".equals(twoTag)){
				weather.setDay_type(data);
			}
			if("fengxiang".equals(nowTag)&&"day".equals(twoTag)){
				weather.setDay_fx(data);
			}
			if("fengli".equals(nowTag)&&"day".equals(twoTag)){
				weather.setDay_fl(data);
			}
			if("type".equals(nowTag)&&"night".equals(twoTag)){
				weather.setNight_type(data);
			}
			if("fengxiang".equals(nowTag)&&"night".equals(twoTag)){
				weather.setNight_fx(data);
			}
			if("fengli".equals(nowTag)&&"night".equals(twoTag)){
				weather.setNight_fl(data);
			}
			
		}
		
		if(zhishu!=null&&"zhishus".equals(oneTag)){
			if("name".equals(nowTag)){
				zhishu.setName(data);
			}
			if("value".equals(nowTag)){
				zhishu.setValue(data);
			}
			if("detail".equals(nowTag)){
				zhishu.setDetail(data);
			}
		}
	}

	@Override
	public void startDocument() throws SAXException {
		Log.i("SAXHandler", "startDocument");
		Context context=new MyApplication().getContext();
		respDB=RespDB.getInstance(context);
		theDayWeather=new TheDayWeather();
	}

	public void endDocument() throws SAXException {
		Log.i("SAXHandler", "endDocument");
		respDB.del("Weather");
		for(Object obj:weatherList){
			respDB.saveWeather((Weather)obj);
		}
		respDB.del("Zhishu");
		for(Object obj:zhishuList){
			respDB.saveZhishu((Zhishu)obj);
		}
		
		
	}

	
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		Log.i("SAXHandler", "startElement"+"**nowTag:"+qName);
		if("environment".equals(qName)){
			environment=new com.bcgtgjyb.myweather.model.Environment();
		}
		if("yesterday".equals(qName)){
			yesterday=new Weather();
			oneTag=qName;
		}
		if("day_1".equals(qName)){
			twoTag=qName;
		}
		if("night_1".equals(qName)){
			twoTag=qName;
		}
		if("forecast".equals(qName)){
			oneTag=qName;
		}
		if("weather".equals(qName)){
			weather= new Weather();
		}
		if("day".equals(qName)){
			twoTag=qName;
		}
		if("night".equals(qName)){
			twoTag=qName;
		}
		if("zhishus".equals(qName)){
			oneTag=qName;
		}
		if("zhishu".equals(qName)){
			zhishu=new Zhishu();
		}
		nowTag=qName;
		
		
	}


	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		Log.i("SAXHandler", "endElement");
		nowTag="";
		if("sunset_1".equals(qName)){
			Log.i("SAXHandler", "respDB.saveTheDayWeather");
			respDB.saveTheDayWeather(theDayWeather);
			theDayWeather=null;
		}
		if("environment".equals(qName)){
			respDB.saveEnvironment(environment);
			environment=null;
		}
		if("yesterday".equals(qName)){
			respDB.saveYesterday(yesterday);
			oneTag="";
		}
		if("day_1".equals(qName)){
			twoTag="";
		}
		if("night_1".equals(qName)){
			twoTag="";
		}
		if("day".equals(qName)){
			twoTag="";
		}
		if("night".equals(qName)){
			twoTag="";
		}
		if("weather".equals(qName)){
			weatherList.add(weather);
			weather=null;
		}
		if("zhishu".equals(qName)){
			zhishuList.add(zhishu);
			zhishu=null;
		}
	}
		
	
	
	
}
