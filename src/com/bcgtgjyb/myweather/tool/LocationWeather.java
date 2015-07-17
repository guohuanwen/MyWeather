package com.bcgtgjyb.myweather.tool;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.NowWeather;

/**
 * 实时天气示例 获取当前地区天气情况
 * */
public class LocationWeather {
	
	private Context context;
	private LocationManagerProxy mLocationManagerProxy;
	private String picture;
//	private String city;
//	private String weather;
//	private String temperature;
//	private String windDir;
//	private String windPower;
//	private String humidity;
//	private String reportTime;
	private NowWeather nowWeather;

	public LocationWeather(Context context,LocationWeatherBack locationWeatherBack) {
		this.context = context;
		nowWeather=new NowWeather();
		init(locationWeatherBack);
	}
	

	private void init(final LocationWeatherBack locationWeatherBack) {
		mLocationManagerProxy = LocationManagerProxy.getInstance(context);
		// 获取实时天气预报
		// 如果需要同时请求实时、未来三天天气，请确保定位获取位置后使用,分开调用，可忽略本句。
		mLocationManagerProxy.requestWeatherUpdates(
				LocationManagerProxy.WEATHER_TYPE_LIVE,new AMapLocalWeatherListener() {
					
					@Override
					public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
						if (aMapLocalWeatherLive != null
								&& aMapLocalWeatherLive.getAMapException().getErrorCode() == 0) {
							// 天气预报成功回调 设置天气信息
							Log.i("LocationWeather", "WeatherRepose");
							nowWeather.setCity(aMapLocalWeatherLive.getCity());// 地点
							String weather=aMapLocalWeatherLive.getWeather();
							nowWeather.setWeather(weather);// 天气
							nowWeather.setTemperature(aMapLocalWeatherLive.getTemperature());// 气温
							nowWeather.setWindDir(aMapLocalWeatherLive.getWindDir());// 风向
							nowWeather.setWindPower(aMapLocalWeatherLive.getWindPower());// / 风力
							nowWeather.setHumidity(aMapLocalWeatherLive.getHumidity()+"%");// 空气湿度
							nowWeather.setReportTime(aMapLocalWeatherLive.getReportTime());// 发布时间
							nowWeather.setImg1(matchDayPicture(weather));
							nowWeather.setImg2(matchNightPicture(weather));
							locationWeatherBack.onNowWeatherBack(nowWeather);
						} else {
							locationWeatherBack.onNowWeatherError(0);
							// 获取天气预报失败
						}
						
					}
					
					@Override
					public void onWeatherForecaseSearched(AMapLocalWeatherForecast arg0) {
						// TODO Auto-generated method stub
						
					}
				});

	}



	public void onPause() {
		// 销毁定位
		mLocationManagerProxy.destroy();
	}


	private int matchDayPicture(String weather) {
		int res=0;
		if ("晴".equals(weather)) {
			res=R.drawable.a00;
		}
		if ("多云".equals(weather)) {
			res=R.drawable.a01;
		}
		if ("阴".equals(weather)) {
			res=R.drawable.a02;
		}
		if ("阵雨".equals(weather)) {
			res=R.drawable.a03;
		}
		if ("雷阵雨".equals(weather)) {
			res=R.drawable.a04;
		}
		if ("雷阵雨并伴有冰雹".equals(weather)) {
			res=R.drawable.a05;
		}
		if ("雨夹雪".equals(weather)) {
			res=R.drawable.a06;
		}
		if ("小雨".equals(weather)) {
			res=R.drawable.a07;
		}
		if ("中雨".equals(weather)) {
			res=R.drawable.a08;
		}
		if ("大雨".equals(weather)) {
			res=R.drawable.a09;
		}
		if ("暴雨".equals(weather)) {
			res=R.drawable.a10;
		}
		if ("大暴雨".equals(weather)) {
			res=R.drawable.a11;
		}
		if ("特大暴雨".equals(weather)) {
			res=R.drawable.a12;
		}
		if ("阵雪".equals(weather)) {
			res=R.drawable.a13;
		}
		if ("小雪".equals(weather)) {
			res=R.drawable.a14;
		}
		if ("中雪".equals(weather)) {
			res=R.drawable.a15;
		}
		if ("大雪".equals(weather)) {
			res=R.drawable.a16;
		}
		if ("暴雪".equals(weather)) {
			res=R.drawable.a17;
		}
		if ("雾".equals(weather)) {
			res=R.drawable.a18;
		}
		if ("冻雨".equals(weather)) {
			res=R.drawable.a19;
		}
		if ("沙尘暴".equals(weather)) {
			res=R.drawable.a20;
		}
		
		if ("小雨-中雨".equals(weather)) {
			res=R.drawable.a21;
		}
		if ("中雨-大雨".equals(weather)) {
			res=R.drawable.a22;
		}
		if ("大雨-暴雨".equals(weather)) {
			res=R.drawable.a23;
		}
		if ("暴雨-大暴雨".equals(weather)) {
			res=R.drawable.a24;
		}
		if ("大暴雨-特大暴雨".equals(weather)) {
			res=R.drawable.a25;
		}
		if ("小雪-中雪".equals(weather)) {
			res=R.drawable.a26;
		}
		if ("中雪-大雪".equals(weather)) {
			res=R.drawable.a27;
		}
		if ("大雪-暴雪".equals(weather)) {
			res=R.drawable.a28;
		}
		if ("浮尘".equals(weather)) {
			res=R.drawable.a29;
		}
		if ("扬沙".equals(weather)) {
			res=R.drawable.a30;
		}
		if ("强沙尘暴".equals(weather)) {
			res=R.drawable.a31;
		}
		if ("飑".equals(weather)) {
			res=R.drawable.a32;
		}
		if ("龙卷风".equals(weather)) {
			res=R.drawable.a32;
		}
		if ("弱高吹雪".equals(weather)) {
			res=R.drawable.a32;
		}
		if ("轻霾".equals(weather)) {
			res=R.drawable.a53;
		}
		if ("霾".equals(weather)) {
			res=R.drawable.a53;
		}
		return res;

	}
	
	
	private int matchNightPicture(String weather) {
		int res=0;
		if ("晴".equals(weather)) {
			res=R.drawable.d00;
		}
		if ("多云".equals(weather)) {
			res=R.drawable.d01;
		}
		if ("阴".equals(weather)) {
			res=R.drawable.d02;
		}
		if ("阵雨".equals(weather)) {
			res=R.drawable.d03;
		}
		if ("雷阵雨".equals(weather)) {
			res=R.drawable.d04;
		}
		if ("雷阵雨并伴有冰雹".equals(weather)) {
			res=R.drawable.d05;
		}
		if ("雨夹雪".equals(weather)) {
			res=R.drawable.d06;
		}
		if ("小雨".equals(weather)) {
			res=R.drawable.d07;
		}
		if ("中雨".equals(weather)) {
			res=R.drawable.d08;
		}
		if ("大雨".equals(weather)) {
			res=R.drawable.d09;
		}
		if ("暴雨".equals(weather)) {
			res=R.drawable.d10;
		}
		if ("大暴雨".equals(weather)) {
			res=R.drawable.d11;
		}
		if ("特大暴雨".equals(weather)) {
			res=R.drawable.d12;
		}
		if ("阵雪".equals(weather)) {
			res=R.drawable.d13;
		}
		if ("小雪".equals(weather)) {
			res=R.drawable.d14;
		}
		if ("中雪".equals(weather)) {
			res=R.drawable.d15;
		}
		if ("大雪".equals(weather)) {
			res=R.drawable.d16;
		}
		if ("暴雪".equals(weather)) {
			res=R.drawable.d17;
		}
		if ("雾".equals(weather)) {
			res=R.drawable.d18;
		}
		if ("冻雨".equals(weather)) {
			res=R.drawable.d19;
		}
		if ("沙尘暴".equals(weather)) {
			res=R.drawable.d20;
		}
		
		if ("小雨-中雨".equals(weather)) {
			res=R.drawable.d21;
		}
		if ("中雨-大雨".equals(weather)) {
			res=R.drawable.d22;
		}
		if ("大雨-暴雨".equals(weather)) {
			res=R.drawable.d23;
		}
		if ("暴雨-大暴雨".equals(weather)) {
			res=R.drawable.d24;
		}
		if ("大暴雨-特大暴雨".equals(weather)) {
			res=R.drawable.d25;
		}
		if ("小雪-中雪".equals(weather)) {
			res=R.drawable.d26;
		}
		if ("中雪-大雪".equals(weather)) {
			res=R.drawable.d27;
		}
		if ("大雪-暴雪".equals(weather)) {
			res=R.drawable.d28;
		}
		if ("浮尘".equals(weather)) {
			res=R.drawable.d29;
		}
		if ("扬沙".equals(weather)) {
			res=R.drawable.d30;
		}
		if ("强沙尘暴".equals(weather)) {
			res=R.drawable.d31;
		}
		if ("飑".equals(weather)) {
			res=R.drawable.d32;
		}
		if ("龙卷风".equals(weather)) {
			res=R.drawable.d32;
		}
		if ("弱高吹雪".equals(weather)) {
			res=R.drawable.d32;
		}
		if ("轻霾".equals(weather)) {
			res=R.drawable.d53;
		}
		if ("霾".equals(weather)) {
			res=R.drawable.d53;
		}
		return res;
	}
	
	
}
