package com.bcgtgjyb.myweather.tool;

public class GetNubFormString {
	
	public int getNumber(String str){
		str=str.trim();
		String str2="";
		if(str != null && !"".equals(str)){
			for(int i=0;i<str.length();i++){
				if(str.charAt(i)>=48 && str.charAt(i)<=57){
					str2+=str.charAt(i);
				}
			}
		}
		return Integer.valueOf(str2);
	}
}
