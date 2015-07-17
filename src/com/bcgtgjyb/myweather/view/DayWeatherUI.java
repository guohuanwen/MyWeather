package com.bcgtgjyb.myweather.view;


import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.tool.FourTextAdapter;
import com.bcgtgjyb.myweather.tool.MyApplication;

public class DayWeatherUI extends Activity{
private MyWeatherCITYDB myWeatherCITYDB;
private ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.fragment_day_weather_two);
		list=(ListView)findViewById(R.id.day_list);
		myWeatherCITYDB=MyWeatherCITYDB.getInstance(new MyApplication().getContext());
		List eList=myWeatherCITYDB.loadEveryDayWeather();
		FourTextAdapter fourTextAdapter=new FourTextAdapter(this, eList,2);
		list.setAdapter(fourTextAdapter);
		
	}

}
