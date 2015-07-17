package com.bcgtgjyb.myweather.model;

import java.util.ArrayList;
import java.util.List;

public class County {
	private int id;
	private String countyName;
	private String countyCode;
	private String cityId;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getCountyName(){
		return countyName;
	}
	public void setCountyName(String countyName){
		this.countyName=countyName;
	}
	
	public String getCountyCode(){
		return countyCode;
	}
	
	public void setCountyCode(String countyCode){
		this.countyCode=countyCode;
	}
	public String getCityId(){
		return cityId;
	}
	public void setCityId(String cityId){
		this.cityId=cityId;
	}
}
