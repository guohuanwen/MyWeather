package com.bcgtgjyb.myweather.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bcgtgjyb.myweather.R;

import android.util.Log;

public class WeatherDistinguish {
	// 晴
	private String a0 = "晴";
	// 多云
	private String a1 = "多云";
	// 阴
	private String a2 = "阴";
	// 阵
	private String a3 = "阵";
	// 雷阵雨
	// 冰雹
	private String a4 = "冰雹";
	// 雨夹雪
	private String a5 = "夹雪";
	// 小雨
	private String a6 = "小";
	// 中雨
	private String a7 = "中";
	// 大雨
	private String a8 = "大";
	// 暴雨
	private String a9 = "暴";
	// 大暴雨
	private String a10 = "大暴";
	// 特大暴雨
	private String a11 = "特大暴";
	// 阵雪
//	private String a13 = "阵";
	// 小雪
//	private String a14 = "小";
	// 中雪
//	private String a15 = "中";
	// 大雪
//	private String a16 = "大";
	// 暴雪
//	private String a17 = "暴";
	// 雾
	private String a12 = "雾";
	// 冻雨
	private String a13 = "冻";
	// 沙尘暴
	private String a14 = "沙尘暴";
	// 浮尘
	private String a15 = "浮尘";
	// 扬沙
	private String a16 = "扬沙";
	// 龙卷风
	private String a17 = "龙卷风";
	// 飑
	private String a18 = "飑";
	// 霾
	private String a19 = "霾";
	// 雨
	private String a20 = "雨";
	//雪
	private String a21 = "雪";
	//雷
	private String a22 = "雷";
	//部
	private String a23 = "部";

	public int distinguish(String param) {
		 Log.i("WeatherDistinguish",param);
		int res = 0;
		List list = getListBasisWeatehr();
		List sky = new ArrayList<String>();
		int i = 0;
		for (Object obj : list) {
			String weather = (String) obj;
			Pattern pattern = Pattern.compile("(.*)" + weather + "(.*)");
			Matcher matcher = pattern.matcher(param);
			if (matcher.find()) {
				sky.add(i + "");
				Log.i("WeatherDistinguish", obj + "" + "---" + i);
			}
			i++;
		}
		res = distinguishInt(sky);
		return res;
	}

	private List getListBasisWeatehr() {
		List list = new ArrayList<String>();
		list.add(a0);
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		list.add(a5);
		list.add(a6);
		list.add(a7);
		list.add(a8);
		list.add(a9);
		list.add(a10);
		list.add(a11);
		list.add(a12);
		list.add(a13);
		list.add(a14);
		list.add(a15);
		list.add(a16);
		list.add(a17);
		list.add(a18);
		list.add(a19);
		list.add(a20);
		list.add(a21);
		list.add(a22);
		list.add(a23);
		return list;
	}

	private int distinguishInt(List list) {
		int res = 0;
		if(list.size()==0){
			res=R.drawable.touming;
		}
		else if (list.size() == 1) {
			res =oneNum(list);

		} else if (list.size() == 2) {
			res=twoNum(list);
			
		} else if(list.size()==3){
			res=threeNum(list);
			
		}else if(list.size()==4){
			//雷阵雨并伴有冰雹
			if (((String) list.get(0)).equals("3")
					&& ((String) list.get(1)).equals("4")
					&& ((String) list.get(2)).equals("20")
					&& ((String) list.get(2)).equals("22")) {
				res = R.drawable.a05;
			}
		}
		return res;
	}
	
	private int oneNum(List list){
		int res;
		//雨
		if (((String) list.get(0)).equals("20")) {
			res = R.drawable.a03;
		}
		//晴
		else if (((String) list.get(0)).equals("0")) {
			res = R.drawable.a00;
		} 
		//多云
		else if (((String) list.get(0)).equals("1")) {
			res = R.drawable.a01;
		} 
		//阴
		else if (((String) list.get(0)).equals("2")) {
			res = R.drawable.a02;
		} 
		//冰雹
		else if (((String) list.get(0)).equals("4")) {
			res = R.drawable.a19;
		}  
		//雾
		else if (((String) list.get(0)).equals("12")) {
			res = R.drawable.a18;
		}  
		//沙尘暴
		else if (((String) list.get(0)).equals("14")) {
			res = R.drawable.a20;
		} 
		//浮尘
		else if (((String) list.get(0)).equals("15")) {
			res = R.drawable.a29;
		} 
		//扬沙
		else if (((String) list.get(0)).equals("16")) {
			res = R.drawable.a30;
		} 
		//龙卷风
		else if (((String) list.get(0)).equals("17")) {
			res = R.drawable.a32;
		} 
		//飑
		else if (((String) list.get(0)).equals("18")) {
			res = R.drawable.a32;
		} 
		//霾
		else if (((String) list.get(0)).equals("19")) {
			res = R.drawable.a53;
		} else {
			res = R.drawable.touming;
		}
		
		return res;
	}
	
	private int twoNum(List list){
		int res;
		
		//阵雨
		if(((String) list.get(0)).equals("3")
				&& ((String) list.get(1)).equals("20")){
			res = R.drawable.a03;
		}
		//雷雨
	    else if (((String) list.get(0)).equals("20")
				&& ((String) list.get(1)).equals("22")) {
			res = R.drawable.a04;
		}
		//小雨
	    else if (((String) list.get(0)).equals("6")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a07;
		}
		//中雨
	    else if (((String) list.get(0)).equals("7")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a08;
		}
		//大雨
	    else if (((String) list.get(0)).equals("8")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a09;
		}
		//暴雨
	    else if (((String) list.get(0)).equals("9")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a10;
		}
		//大暴雨
	    else if (((String) list.get(0)).equals("10")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a11;
		}
		//特大暴雨
	    else if (((String) list.get(0)).equals("11")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a12;
		}
		//冻雨
	    else if (((String) list.get(0)).equals("13")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a19;
		}
		//阵雪
	    else if (((String) list.get(0)).equals("3")
				&& ((String) list.get(1)).equals("21")) {
			res = R.drawable.a13;
		}
		//小雪
	    else if (((String) list.get(0)).equals("6")
				&& ((String) list.get(1)).equals("22")) {
			res = R.drawable.a14;
		}
		//中雪
	    else if (((String) list.get(0)).equals("7")
				&& ((String) list.get(1)).equals("22")) {
			res = R.drawable.a15;
		}
		//大雪
	    else if (((String) list.get(0)).equals("8")
				&& ((String) list.get(1)).equals("22")) {
			res = R.drawable.a16;
		}
		//暴雪
	    else if (((String) list.get(0)).equals("9")
				&& ((String) list.get(1)).equals("22")) {
			res = R.drawable.a17;
		}
		//晴间多云
	    else if (((String) list.get(0)).equals("0")
				&& ((String) list.get(1)).equals("1")) {
			res = R.drawable.a01;
		}else {
			res = R.drawable.touming;
		}
		return res;
	}
	
	private int threeNum(List list){
		int res;
		//大部**
		if(((String) list.get(1)).equals("8")
				&& ((String) list.get(2)).equals("23"))
		{
			List list1=new ArrayList<String>();
			list1.add(list.get(0));
			res=oneNum(list1);
		}
		//雷阵雨
		else if (((String) list.get(0)).equals("3")
				&& ((String) list.get(1)).equals("20")
				&& ((String) list.get(1)).equals("22")) {
			res = R.drawable.a04;
		}
		//小雨-中雨
		else if (((String) list.get(0)).equals("6")
				&& ((String) list.get(1)).equals("7")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a21;
		}
		//中雨-大雨
		else if (((String) list.get(0)).equals("7")
				&& ((String) list.get(1)).equals("8")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a22;
		}
		//大雨-暴雨
		else if (((String) list.get(0)).equals("8")
				&& ((String) list.get(1)).equals("9")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a23;
		}
		//暴雨-大暴雨
		else if (((String) list.get(0)).equals("9")
				&& ((String) list.get(1)).equals("10")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a24;
		}
		//大暴雨-特大暴雨
		else if (((String) list.get(0)).equals("10")
				&& ((String) list.get(1)).equals("11")
				&& ((String) list.get(1)).equals("20")) {
			res = R.drawable.a25;
		}
		//小雪-中雪
		else if (((String) list.get(0)).equals("6")
				&& ((String) list.get(1)).equals("7")
				&& ((String) list.get(1)).equals("21")) {
			res = R.drawable.a26;
		}
		//中雪-大雪
		else if (((String) list.get(0)).equals("7")
				&& ((String) list.get(1)).equals("8")
				&& ((String) list.get(1)).equals("21")) {
			res = R.drawable.a27;
		}
		//大雪-暴雪
		else if (((String) list.get(0)).equals("8")
				&& ((String) list.get(1)).equals("9")
				&& ((String) list.get(1)).equals("21")) {
			res = R.drawable.a28;
		}else{
			res= R.drawable.touming;
		}
		 return res;
	}
}
