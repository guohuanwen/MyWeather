package com.bcgtgjyb.myweather.model;

public class EveryDayWeather {
	//�˶�ָ��
	private String run;
	//ʱ�䣺2015/03/05
	private String date;
	//����
	private String wind;
	//���������ѩ
	private String sky;
	//����¶�
	private String minTmp;
	//����¶�
	private String maxTmp;
	public void setRun(String param){
		run=param;
	}
	public void setDate(String param){
		date=param;
	}
	public void setWind(String param){
		wind=param;
	}
	public void setSky(String param){
		sky=param;
	}
	public void setMinTmp(String param){
		minTmp=param;
	}
	public void setMaxTmp(String param){
		maxTmp=param;
	}
	public String getRun(){
		return run;
	}
	public String getWind(){
		return wind;
	}
	public String getDate(){
		return date;
	}
	public String getSky(){
		return sky;
	}
	public String getMinTmp(){
		return minTmp;
	}
	public String getMaxTmp(){
		return maxTmp;
	}
	
	

}
