package com.bcgtgjyb.myweather.model;

import java.util.List;

public class Weather {
	public String getEasyCity() {
		return easyCity;
	}
	public void setEasyCity(String easyCity) {
		this.easyCity = easyCity;
	}
	public String getEasyFengxiang() {
		return easyFengxiang;
	}
	public void setEasyFengxiang(String easyFengxiang) {
		this.easyFengxiang = easyFengxiang;
	}
	public String getEasyFengli() {
		return easyFengli;
	}
	public void setEasyFengli(String easyFengli) {
		this.easyFengli = easyFengli;
	}
	public String getEasyHighTmp() {
		return easyHighTmp;
	}
	public void setEasyHighTmp(String easyHighTmp) {
		this.easyHighTmp = easyHighTmp;
	}
	public String getEasyLowTmp() {
		return easyLowTmp;
	}
	public void setEasyLowTmp(String easyLowTmp) {
		this.easyLowTmp = easyLowTmp;
	}
	public String getEasyType() {
		return easyType;
	}
	public void setEasyType(String easyType) {
		this.easyType = easyType;
	}
	public String getEasyDate() {
		return easyDate;
	}
	public void setEasyDate(String easyDate) {
		this.easyDate = easyDate;
	}
	public String getDay_type() {
		return day_type;
	}
	public void setDay_type(String day_type) {
		this.day_type = day_type;
	}
	public String getDay_fx() {
		return day_fx;
	}
	public void setDay_fx(String day_fx) {
		this.day_fx = day_fx;
	}
	public String getDay_fl() {
		return day_fl;
	}
	public void setDay_fl(String day_fl) {
		this.day_fl = day_fl;
	}
	public String getNight_type() {
		return night_type;
	}
	public void setNight_type(String night_type) {
		this.night_type = night_type;
	}
	public String getNight_fx() {
		return night_fx;
	}
	public void setNight_fx(String night_fx) {
		this.night_fx = night_fx;
	}
	public String getNight_fl() {
		return night_fl;
	}
	public void setNight_fl(String night_fl) {
		this.night_fl = night_fl;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	
	private String date;
	private String high;
	private String low;
	private String day_type;
	private String day_fx;
	private String day_fl;
	private String night_type;
	private String night_fx;
	private String night_fl;
	private String easyFengxiang;
	private String easyFengli;
	private String easyHighTmp;
	private String easyLowTmp;
	private String easyType;
	private String easyDate;
	private String easyCity;
}
