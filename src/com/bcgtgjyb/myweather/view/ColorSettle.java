package com.bcgtgjyb.myweather.view;


import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.bcgtgjyb.myweather.R;

public class ColorSettle extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color_settle);
		LinearLayout layout=(LinearLayout)findViewById(R.id.color_settle_linear);
//		ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
//		SVBar svBar = (SVBar) findViewById(R.id.svbar);
//		OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
//		SaturationBar saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
//		ValueBar valueBar = (ValueBar) findViewById(R.id.valuebar);
//		picker.addSVBar(svBar);
//		picker.addOpacityBar(opacityBar);
//		picker.addSaturationBar(saturationBar);
//		picker.addValueBar(valueBar);
//
//		//To get the color
//		picker.getColor();
//
//		//To set the old selected color u can do it like this
//		picker.setOldCenterColor(picker.getColor());
//		// adds listener to the colorpicker which is implemented
//		//in the activity
////		picker.setOnColorChangedListener((OnColorChangedListener) this);
//
//		//to turn of showing the old color
//		picker.setShowOldCenterColor(false);

		//adding onChangeListeners to bars
//		opacitybar.setOnOpacityChangeListener(new OnOpacityChangeListener …)
//		valuebar.setOnValueChangeListener(new OnValueChangeListener …)
//		saturationBar.setOnSaturationChangeListener(new OnSaturationChangeListener …)
	}
	

}
