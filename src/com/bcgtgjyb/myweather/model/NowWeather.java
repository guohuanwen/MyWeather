package com.bcgtgjyb.myweather.model;

public class NowWeather {
	public int getImg1() {
		return img1;
	}
	public void setImg1(int img1) {
		this.img1 = img1;
	}
	public int getImg2() {
		return img2;
	}
	public void setImg2(int img2) {
		this.img2 = img2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWindDir() {
		return windDir;
	}
	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}
	public String getWindPower() {
		return windPower;
	}
	public void setWindPower(String windPower) {
		this.windPower = windPower;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	private String city;
	private String weather;
	private String temperature;
	private String windDir;
	private String windPower;
	private String humidity;
	private String reportTime;
	private int img1;
	private int img2;
	

}
