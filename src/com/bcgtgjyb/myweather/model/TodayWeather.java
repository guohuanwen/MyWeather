package com.bcgtgjyb.myweather.model;

public class TodayWeather {
	//����������
	private String sky; 
	//�˶�ָ��
	private String run; 
	//�¶�
	private String tmp; 
	//��
	private String wind; 
	//ʱ�䣬23 (ʱ)
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
