package com.bcgtgjyb.myweather.view;


import com.bcgtgjyb.myweather.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_sky extends Fragment{
	private View myView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sky, container, false);
	}

	

}
