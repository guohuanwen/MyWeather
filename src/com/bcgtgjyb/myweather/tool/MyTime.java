package com.bcgtgjyb.myweather.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyTime {
	
	public String getTodayDay() {
		Calendar cal = Calendar.getInstance();
		String retn;
		// int time = cal.get(Calendar.MONTH);
		int time = cal.get(Calendar.DAY_OF_MONTH);
		retn = time + "";
		if (time < 10) {
			retn = "0" + retn;
		}
		return retn;
	}
	public String getTodayDayWithOut0() {
		Calendar cal = Calendar.getInstance();
		String retn;
		// int time = cal.get(Calendar.MONTH);
		int time = cal.get(Calendar.DAY_OF_MONTH);
		retn = time + "";
		return retn;
	}

	public String getTodayMonth() {
		Calendar cal = Calendar.getInstance();
		String retn;
		int time = cal.get(Calendar.MONTH) + 1;
		// int time = cal.get(Calendar.DAY_OF_MONTH);
		retn = time + "";
		if (time < 10) {
			retn = "0" + retn;
		}
		return retn;
	}
	
	public String getTodayHour() {
		Calendar cal = Calendar.getInstance();
		String retn;
		int time = cal.get(Calendar.HOUR);
		retn = time + "";
		if (time < 10) {
			retn = "0" + retn;
		}
		return retn;
	}
	
	public String getTodayMinute() {
		Calendar cal = Calendar.getInstance();
		String retn;
		int time = cal.get(Calendar.MINUTE);
		retn = time + "";
		if (time < 10) {
			retn = "0" + retn;
		}
		return retn;
	}
	
	public  String getToDayTime(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");//设置日期格式
    	String re=df.format(new Date());
    	return re;
    }
	
	
	// 05 日
	public String getFuture(int param){
		Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,param);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("dd");
		 String dateString = formatter.format(date);
		 System.out.println(dateString);
		 return dateString;
	}
	
	
	
	public String getFutureTime(int param){
		Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,param);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("MM"+"/"+"dd");
		 String dateString = formatter.format(date);
		 System.out.println(dateString);
		 return dateString;
	}
}
