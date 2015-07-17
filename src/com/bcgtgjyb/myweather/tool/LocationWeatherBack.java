package com.bcgtgjyb.myweather.tool;

import com.bcgtgjyb.myweather.model.NowWeather;

public interface LocationWeatherBack {
	void onNowWeatherBack(NowWeather nowWeather);
	void onNowWeatherError(int i);
}
