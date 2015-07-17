package com.bcgtgjyb.myweather.presenter;

import java.util.ArrayList;
import java.util.List;

import com.bcgtgjyb.myweather.R;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import com.bcgtgjyb.myweather.model.City;
import com.bcgtgjyb.myweather.model.County;
import com.bcgtgjyb.myweather.model.MyWeatherDB;
import com.bcgtgjyb.myweather.model.MyWeatherFiftenDB;
import com.bcgtgjyb.myweather.model.Province;
import com.bcgtgjyb.myweather.model.WeatherPlace;
import com.bcgtgjyb.myweather.tool.MyApplication;

public class ChooseAreaPresenter {
	private int page=0; 
	private List<String> provinceNameList;
	private List<String> cityNameList;
	private List<String> countyNameList;
	private List<String> countyCodeList;
	private List<String> nameList;
	private Context context=(new MyApplication()).getContext();
	private MyWeatherDB myWeatherDB=MyWeatherDB.getInstance(context);
	private MyWeatherFiftenDB myWeatherFiftenDB=MyWeatherFiftenDB.getInstance(context);
	
	public void setNameList(List<String> list){
		nameList=list;
	}
	public List<String> getNameList(){
		return nameList;
	}
	public void setPage(int i){
		page=i;
	}
	public int getPage(){
		return page;
	}
	public List<String> getProvinceNameList(){
		return provinceNameList;
	}
	public List<String> getCityNameList(){
		return cityNameList;
	}
	public List<String> getCountyNameList(){
		return countyNameList;
	}
	public List<String> getCountyCodeList(){
		return countyCodeList;
	}
	
	public void setListObject(String place){
		switch (page) {
		case 0:
			List<Province> province=myWeatherDB.loadProvinces();
			provinceNameList=new ArrayList<String>();
			for(Province object:province){
				provinceNameList.add(object.getProvinceName());
			}
			break;
		case 1:
			Log.i("ChoosePresenter", "setListObject");
			List<City> city=myWeatherDB.loadCities(place);
			cityNameList=new ArrayList<String>();
			for(City object:city){
				Log.i("ChoosePresenter", object.getCityName());
				cityNameList.add(object.getCityName());
			}
			Log.i("ChoosePresenter", cityNameList.toString());
			break;
		case 2:
			countyNameList=new ArrayList<String>();
			countyCodeList=new ArrayList<String>();
			List<County> county=myWeatherDB.loadCounties(place);
			for(County object:county){
				countyNameList.add(object.getCountyName());
				countyCodeList.add(object.getCountyCode());
			}
			break;
		default:
			break;
		}
		
	}
	
	public ListAdapter fillList(Context context){
		Log.i("ChooseAreaPresenter", "fillList");
		ListAdapter listAdapter=new ArrayAdapter<String>(context,R.layout.list_item,R.id.list_item_text,nameList);
		Log.i("ChooseAreaPresenter", nameList.toString());
		return listAdapter;
		
	}
	
	public void saveMyWeatherPlace(int param){
		setPage(0);
		WeatherPlace weatherPlace=new WeatherPlace();
		String name2=countyNameList.get(param);
		String code2=countyCodeList.get(param);
		weatherPlace.setWeatherPlace(name2);
		weatherPlace.setWeatherCode(code2);
		myWeatherFiftenDB.saveMyWeatherPlace(weatherPlace);
	}
	
	
	

}
