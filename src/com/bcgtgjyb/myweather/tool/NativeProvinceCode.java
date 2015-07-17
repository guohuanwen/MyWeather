package com.bcgtgjyb.myweather.tool;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.City;
import com.bcgtgjyb.myweather.model.County;
import com.bcgtgjyb.myweather.model.MyWeatherDB;
import com.bcgtgjyb.myweather.model.Province;

public class NativeProvinceCode {
	/**
	 * pull解析
	 * @param 需要获取的省，或者市
	 * @return 本地地点与代码的list
	 */
	public void getProvinceCodeText(String param){
		MyApplication myApplication=new MyApplication();
		Resources ass=myApplication.getContext().getResources();
		XmlResourceParser xrp = ass.getXml(R.xml.channel);
		List provinceList=new ArrayList<Province>();
		MyWeatherDB myWeatherDb=MyWeatherDB.getInstance(myApplication.getContext());
		try{
			Province province=null;
			City city=null;
			County county=null;
			String provinceName = "";
			 String cityName="";
			// 直到文档的结尾处
			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
			// 如果遇到了开始标签
			if (xrp.getEventType() == XmlResourceParser.START_TAG) {
				 String tagName = xrp.getName();// 获取标签的名字
				 
				 if (tagName.equals("Province")){
					  province=new Province();
			          provinceName = xrp.getAttributeValue(null, "name");// 通过属性名来获取属性值
			          province.setProvinceName(provinceName);
			          
//				      String code = xrp.getAttributeValue(null,"code");// 通过属性索引来获取属性值
				      
				  }else if(tagName.equals("City")){
					  city=new City();
					  cityName=xrp.getAttributeValue(null,"name");
					  city.setCityName(cityName);
					  city.setProvinceId(provinceName);
				  }else if(tagName.equals("County")){
					  county=new County();
					  String countyName=xrp.getAttributeValue(null,"name");
					  String countyCode=xrp.getAttributeValue(null,"code");
					  county.setCountyCode(countyCode);
					  county.setCountyName(countyName);
					  county.setCityId(cityName);
				  }
			}else if(xrp.getEventType() == XmlResourceParser.END_TAG){
				String tagName = xrp.getName();// 获取标签的名字
				 if (tagName.equals("Province")){
					  myWeatherDb.saveProvince(province);
					  province=null;
				  }else if(tagName.equals("City")){
					  myWeatherDb.saveCity(city);
					  city=null;
				  }else if(tagName.equals("County")){
					  myWeatherDb.saveCounty(county);
					  county=null;
				  }
			}
			xrp.next();// 获取解析下一个事件
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
}
}
				
			         
				     
		
		
	


