package com.bcgtgjyb.myweather.tool;


import com.bcgtgjyb.myweather.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ReStart extends Activity{

	private Context context; 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		reStart();
	}







	public  void reStart(){
		context =new MyApplication().getContext();
		Intent i = context.getPackageManager()
				.getLaunchIntentForPackage(context.getPackageName());
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}



	
	
	
}
