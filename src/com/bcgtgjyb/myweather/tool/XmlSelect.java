package com.bcgtgjyb.myweather.tool;

import com.bcgtgjyb.myweather.R;
import android.content.Context;
import android.util.Log;
import android.util.Xml;
import com.bcgtgjyb.myweather.model.UserDB;

public class XmlSelect {
	private int skin;
	private int xml;
	public XmlSelect() {
		Context context=new MyApplication().getContext();
		UserDB userDB=UserDB.getInstance(context);
		skin=userDB.getSkin();
		Log.i("XmlSelect", skin+"");
	}
	
	
	public  int getMainXml(){
		switch (skin) {
		case 0:
			xml=R.layout.fragment_main;
			break;
		case 1:
			xml=R.layout.fragment_main_two;
			break;
		default:
			break;
		}
		return xml;
	}
	
	public  int getChooseXml(){
		switch (skin) {
		case 0:
			xml=R.layout.fragment_choose_city;
			break;
		case 1:
			xml=R.layout.fragment_choose_city_two;
			break;
		default:
			break;
		}
		return xml;
	}
	public  int getSettingXml(){
		
		switch (skin) {
		case 0:
			xml=R.layout.fragment_setting;
			break;
		case 1:
			xml=R.layout.fragment_setting_two;
			break;
		default:
			break;
		}
		return xml;
	}
	
	public  int getStartUIXml(){
		
		switch (skin) {
		case 0:
			xml=R.layout.start_ui;
			break;
		case 1:
			xml=R.layout.start_ui_two;
			break;
		default:
			break;
		}
		return xml;
	}
	
public  int getFutureXml(){
		
		switch (skin) {
		case 0:
			xml=R.layout.fragment_future;
			break;
		case 1:
			xml=R.layout.fragment_future_two;
			break;
		default:
			break;
		}
		return xml;
	}

public  int getSkinXml(){
	
	switch (skin) {
	case 0:
		xml=R.layout.fragment_skin;
		break;
	case 1:
		xml=R.layout.fragment_skin_two;
		break;
	default:
		break;
	}
	return xml;
}

}
