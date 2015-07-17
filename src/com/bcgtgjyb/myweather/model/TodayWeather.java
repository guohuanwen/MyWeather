package com.bcgtgjyb.myweather.model;

public class TodayWeather {
	//天气：多云
	private String sky; 
	//运动指数
	private String run; 
	//温度
	private String tmp; 
	//风
	private String wind; 
	//时间，23 (时)
	private String hour; 
	
	public void setRun(String param){
		run=param;
	}
	public void setHour(String param){
		hour=param;
	}
	public void setWind(String param){
		wind=param;
	}
	public void setSky(String param){
		sky=param;
	}
	public void setTmp(String param){
		tmp=param;
	}
	
	public String getRun(){
		return run;
	}
	public String getWind(){
		return wind;
	}
	public String getHour(){
		return hour;
	}
	public String getSky(){
		return sky;
	}
	public String getTmp(){
		return tmp;
	}
	
	
}
