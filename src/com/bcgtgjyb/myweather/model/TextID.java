package com.bcgtgjyb.myweather.model;

import java.util.Calendar;
import java.util.List;
import android.R.bool;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyTime;

public class TextID {
	private MyWeatherCITYDB myWeatherCITYDB;
	private MyTime myTime;

	public String getText(int i) {
		String retn=null;
		myTime=new MyTime();
		myWeatherCITYDB = MyWeatherCITYDB.getInstance(new MyApplication()
				.getContext());
		List list = myWeatherCITYDB.loadEveryDayWeather();
		String date = myTime.getTodayMonth() + "/" + myTime.getTodayDay();
		EveryDayWeather everyDayWeather = myWeatherCITYDB.getToday(date);
		if (null != everyDayWeather) {
			switch (i) {
			case 0:
				String today=everyDayWeather.getDate();
				retn=today;
				break;
			case 1:
				String tmp=everyDayWeather.getMinTmp()+"-"+everyDayWeather.getMaxTmp()+"℃";
				retn=tmp;
				break;
			case 2:
				String run=everyDayWeather.getRun();
				retn=run;
				break;
			case 3:
				String wind=everyDayWeather.getWind();
				retn=wind;
				break;
			case 4:
				String sky=everyDayWeather.getSky();
				retn=sky;
				break;
			default:
				break;
			}
		}
		return retn;
	}

	
}
