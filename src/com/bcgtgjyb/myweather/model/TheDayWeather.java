package com.bcgtgjyb.myweather.model;

public class TheDayWeather {
	private String city;
	private String updatetime;
	private String wendu;
	private String fengli;
	private String shidu;
	private String fengxiang;
	private String sunrise;
	private String sunset;
	
	public void setCity(String city){
		this.city=city;
	}
	
	public void setUpdatetime(String updatetime){
		this.updatetime=updatetime;
	}
	
	public void setWendu(String wendu){
		this.wendu=wendu;
	}
	
	public void setFengli(String fengli){
		this.fengli=fengli;
	}
	
	public void setShidu(String shidu){
		this.shidu=shidu;
	}
	
	public void setFengxiang(String fengxiang){
		this.fengxiang=fengxiang;
	}
	
	public void setSunrise(String sunrise){
		this.sunrise=sunrise;
	}
	
	public void setSunset(String sunset){
		this.sunset=sunset;
	}
	
	public String getCity(){
		return this.city;
	}
	public String getUpdatetime(){
		return this.updatetime;
	}
	public String getWendu(){
		return this.wendu;
	} 
	public String getFengxiang(){
		return this.fengxiang;
	}
	public String getFengli(){
		return this.fengli;
	}
	public String getShidu(){
		return this.shidu;
	}
	public String getSunrise(){
		return this.sunrise;
	}
	public String getSunset(){
		return this.sunset;
	}
	

}
