package com.bcgtgjyb.myweather.tool;

import android.content.Context;
import android.widget.Button;

public class MyButton extends Button{
	private int textId;
	private int backgroundId;
	public MyButton(Context context) {
		super(context);
	}
	
	public void setTextId(int param){
		this.textId=param;
	}
	public void setBackgroundId(int param){
		this.backgroundId=param;
	}
	public int getTextId(){
		return textId;
	}
	public int getBackgroundId(){
		return backgroundId;
	}
	

}
