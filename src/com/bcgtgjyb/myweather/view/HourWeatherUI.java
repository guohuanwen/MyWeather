package com.bcgtgjyb.myweather.view;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.model.TodayWeather;
import com.bcgtgjyb.myweather.myclass.HorizontalListView;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyListAdapter;

public class HourWeatherUI extends Activity {
	private List hourList;
	private List skyList;
	private List windList;
	private MyWeatherCITYDB myWeatherCITYDB = MyWeatherCITYDB
			.getInstance(MyApplication.getContext());
	private  HorizontalListView listView = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hour_weather_list);
		listView = (HorizontalListView) findViewById(R.id.hourList);
		List hourWeather = myWeatherCITYDB.loadTodayWeather();
		MyListAdapter myListAdapter = new MyListAdapter(this, hourWeather);
		listView.setAdapter(myListAdapter);
	}

}
