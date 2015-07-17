package com.bcgtgjyb.myweather.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import com.amap.api.location.c;
import com.aps.u;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyDialog;
import com.bcgtgjyb.myweather.tool.XmlSelect;
import com.zcw.togglebutton.ToggleButton;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Fragment_setting extends Fragment {
	private View view;
	private com.zcw.togglebutton.ToggleButton toggleButton;
	private RelativeLayout skin;
	private RelativeLayout about;
	private UserDB userDB;
	private Context context;
	private MyDialog myDialog;
	private LinearLayout layout;
	private RelativeLayout explain;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(getXML(), container, false);
		context = new MyApplication().getContext();
		userDB = UserDB.getInstance(context);
		myDialog=new MyDialog();
		init();
		return view;
	}

	private void init() {
		layout=(LinearLayout)view.findViewById(R.id.fragment_setting_max);
		
		layout.setOnClickListener(new LayoutListener());
		
		toggleButton = (ToggleButton) view
				.findViewById(R.id.fragment_setting_tb);
		skin = (RelativeLayout) view.findViewById(R.id.fragment_setting_skin);
		about = (RelativeLayout) view.findViewById(R.id.fragment_setting_about);
		explain=(RelativeLayout)view.findViewById(R.id.fragment_setting_explain);
		
		
		skin.setOnClickListener(new SkinListener());
		about.setOnClickListener(new AboutListener());
		explain.setOnClickListener(new ExplainListener());
	}

	public void onResume() {
		// setWaveView();
		Log.i("Fragment_setting", "onResume");
		super.onResume();
		Log.i("Fragment_setting", userDB.getWaveHeight()+"");
		
		if (userDB.getWaveHeight() == 0) {
			toggleButton.toggleOff();
		} else {
			toggleButton.toggleOn();
		}
	}

	@Override
	public void onDestroy() {
		Log.i("Fragment_setting", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onPause() {
		Log.i("Fragment_setting", "onPause");
		super.onPause();
		boolean a = toggleButton.getToggle();
		if (a) {
			userDB.setWaveHeight(1);
		} else {
			userDB.setWaveHeight(0);
		}
	}

	@Override
	public void onStart() {
		Log.i("Fragment_setting", "onStart");
		super.onStart();
	}

	@Override
	public void onStop() {
		Log.i("Fragment_setting", "onStop");
		super.onStop();
	}

	public void onDetach() {
		Log.i("Fragment_main", "onDetach");
		super.onDetach();
	}

	private int getXML() {
		return new XmlSelect().getSettingXml();
	}

	class ExplainListener implements OnClickListener{
		public void onClick(View v) {
			ListView listView=new ListView(getActivity().getBaseContext());
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Map<String,Object> map1=new HashMap<String,Object>();
			Map<String,Object> map2=new HashMap<String,Object>();
			Map<String,Object> map3=new HashMap<String,Object>();
			Map<String,Object> map4=new HashMap<String,Object>();
			map1.put("image", R.drawable.explain1);
			map2.put("image", R.drawable.explain2);
			map3.put("image", R.drawable.explain3);
			map4.put("image", R.drawable.explain4);
			list.add(map1);
			list.add(map2);
			list.add(map3);
			list.add(map4);
			ListAdapter adapter=new SimpleAdapter(getActivity(), list, R.layout.image_list, new String[]{"image"}, new int[]{R.id.image_list_im});
			listView.setAdapter(adapter);
		
			
			myDialog.dialogShow(5, getActivity(), listView,
					new OnClickListener() {

						public void onClick(View v) {
							myDialog.cancel();
						}
					}, new OnClickListener() {
						public void onClick(View v) {
							myDialog.cancel();
						}
					});
			
		}
		
	}
	
	class AboutListener implements OnClickListener{

		@SuppressLint("ResourceAsColor")
		@Override
		public void onClick(View v) {
			TextView textView=new TextView(getActivity().getBaseContext());
			textView.setTextSize(18);
			textView.setTextColor(R.color.Mywhite);
			textView.setPadding(10, 10, 10, 10);
			textView.setText(R.string.about);
			myDialog.dialogShow(13, getActivity(), textView,
					new OnClickListener() {

						public void onClick(View v) {
							myDialog.cancel();
						}
					}, new OnClickListener() {
						public void onClick(View v) {
							myDialog.cancel();
						}
					});
		}
		
	}
	
	class SkinListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			sendActivityMessage(2);
		}
		
	}
	
	class LayoutListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
		}
		
	}
	

	
	private void sendActivityMessage(int i) {
		android.os.Handler handler = ((StartUI) getActivity()).handler;
		Message message = new Message();
		message.what = i;
		handler.sendMessage(message);
	}
}
