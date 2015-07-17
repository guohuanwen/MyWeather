package com.bcgtgjyb.myweather.view;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.c;
import com.android.volley.Cache;
import com.aps.m;
import com.aps.p;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.model.WeatherDB;
import com.bcgtgjyb.myweather.tool.LocationCallBack;
import com.bcgtgjyb.myweather.tool.MyLocation;
import com.bcgtgjyb.myweather.tool.MyDialog;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.XmlSelect;

public class Fragment_chooseCity extends Fragment {
	private  MyDialog myDialog;
	private MyLocation myLocation;
	private int erroeCode = 0;
	private String province;
	private String city;
	private View view;
	private ListView listView;
	private EditText editText;
	private Button button;
	private WeatherDB weatherDB;
	private UserDB userDB;
	private MyHandler myHandler;
	private List list;
	private ListAdapter adapter;
	private Context context;
	
	public Fragment_chooseCity() {

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = new MyApplication().getContext();
		view = inflater.inflate(getXML(), null, false);
		listView = (ListView) view.findViewById(R.id.framgent_choosecity_List);
		button = (Button) view.findViewById(R.id.fragment_choosecity_button);
		button.setOnClickListener(new ButtonListenr());
		editText = (EditText) view
				.findViewById(R.id.fragment_choosecity_editText);
		editText.addTextChangedListener(new EditTextListener());
		weatherDB = WeatherDB.getInstence(context);
		userDB = UserDB.getInstance(context);
		setDate(getList());
		myHandler = new MyHandler();
		setLocation();
		myDialog = new MyDialog();
		return view;
	}

	private int getXML() {
		
		return new XmlSelect().getChooseXml();
	}

	private List getList() {
		List list = userDB.loadCity();
		return list;
	}

	private void setDate(List list) {
			adapter = new ArrayAdapter<String>(getActivity(),
					R.layout.list_item, R.id.list_item_text, list);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new ListListener());
			listView.setOnItemLongClickListener(new ListLongClick());
		
	}

	class EditTextListener implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			Log.i("Fragment_chooseCity", "beforeTextChanged");
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			Log.i("Fragment_chooseCity", "onTextChanged");
		}

		@Override
		public void afterTextChanged(Editable s) {
			Log.i("Fragment_chooseCity", "afterTextChanged");
			Log.i("Fragment_chooseCity", "*" + s.toString());
			if (!s.toString().equals("")) {
				final String text = s.toString();
				new Thread(new Runnable() {
					public void run() {
						list = weatherDB.findCity(text);
						if (list.size() > 0) {
						}
						Message message = new Message();
						message.what = 0;
						myHandler.sendMessage(message);
					}
				}).start();
			} else {
				setDate(getList());
			}

		}

	}

	class ButtonListenr implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}
	}

	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				setDate(list);
				break;
			case 1:
				Log.i("Fragment_chooseCity", province+"");
				erroeCode=1;
				button.setText(city);
				new Thread(new Runnable() {
					public void run() {
						List reCity = weatherDB.getLocationCity(province, city);
						if (reCity.size() >= 2) {
							boolean re = userDB
									.isCityIn((String) reCity.get(0));
							if (re == false) {
								userDB.saveCity((String) reCity.get(0),
										(String) reCity.get(1), 0);
							}
						}
						Message message=new Message();
						message.what=2;
						myHandler.sendMessage(message);
					}
				}).start();
				break;
			case 2:
				editText.setText("");
				break;
			default:
				break;
			}

			super.handleMessage(msg);
		}

	}
	
	
	class ListLongClick implements OnItemLongClickListener{

		@SuppressLint("ResourceAsColor")
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			TextView textView=new TextView(context);
			textView.setText(R.string.pleaseDeltel);
			textView.setTextSize(18);
			textView.setTextColor(R.color.Mywhite);
			textView.setPadding(50, 50, 0, 100);
			final String cityName = adapter.getItem(position).toString();
			Log.i("Fragment_chooseCity","ListLongClick"+cityName);
			if(userDB.isCityIn(cityName)){
				Log.i("Fragment_chooseCity","ListLongClick"+userDB.isCityIn(cityName));
				myDialog.dialogShow(13, getActivity(),textView,
						new OnClickListener() {
							public void onClick(View v) {
								userDB.deleteCity(cityName);
								myDialog.cancel();
								Message message = new Message();
								message.what = 2;
								myHandler.sendMessage(message);
							}
						}, new OnClickListener() {
							public void onClick(View v) {
								myDialog.cancel();
							}
						});
				
				
			}
			return true;
		}

		
		
	}

	
	class ListListener implements OnItemClickListener {
		public void onItemClick(AdapterViewCompat<?> arg0, View arg1, int arg2,
				long arg3) {
		}

		@SuppressLint("ResourceAsColor")
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.i("Fragment_chooseCity", "onItemClick");
			TextView textView=new TextView(context);
			
			textView.setTextSize(18);
			textView.setTextColor(R.color.Mywhite);
			textView.setPadding(50, 50, 0, 100);
			final String cityName = adapter.getItem(position).toString();
			final String cityId = userDB.searchCityId(cityName);
			
			if (userDB.isCityIn(cityName)) {
				textView.setText(R.string.pleaseMain);
				// 设为主城市
				myDialog.dialogShow(13, getActivity(), textView,
						new OnClickListener() {

							public void onClick(View v) {
								userDB.setMainCity(cityName);
								myDialog.cancel();
								editText.setText("");
							}
						}, new OnClickListener() {
							public void onClick(View v) {
								myDialog.cancel();
							}
						});
			} else {
				// 添加到城市列表
				textView.setText(R.string.pleaseAdd);
				myDialog.dialogShow(13, getActivity(),textView,
						new OnClickListener() {

							public void onClick(View v) {
								userDB.saveCity(cityName, cityId, 0);
								myDialog.cancel();
								editText.setText("");
							}
						}, new OnClickListener() {
							public void onClick(View v) {
								myDialog.cancel();
							}
						});

			}
			
		}

	}
	

	/**
	 * 设置定位服务
	 */

	private void setLocation() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
				myLocation = new MyLocation(context);
				myLocation.init(new LocationCallBack() {
					public void onBack(String province, String city) {
						Fragment_chooseCity.this.province = province;
						Fragment_chooseCity.this.city = city;
						Message message=new Message();
						message.what=1;
						myHandler.sendMessage(message);
					}
				});
				}catch(Exception e){
					Log.i("Fragment_chooseCity", e.toString());
				}
			}
			
		}).start();
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	
	@Override
	public void onPause() {
//		myLocation.closeLocation();
		super.onPause();
	}

}
