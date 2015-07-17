package com.bcgtgjyb.myweather.view;


import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.tool.FourTextAdapter;

public class Fragment_everyDayList extends Fragment{
private View view;
private ListView listView;
private Context context;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.fragment_everydaylist, null, false);
		listView=(ListView)view.findViewById(R.id.fragment_everyDay_listView);
		context=view.getContext();
		initListDate();
		return view;
	}
	
	private void initListDate(){
		
	}
	

}
