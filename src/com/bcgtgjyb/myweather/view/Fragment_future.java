package com.bcgtgjyb.myweather.view;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aps.m;
import com.aps.o;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.EveryDayWeather;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.model.OpenZhiShu;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.Weather;
import com.bcgtgjyb.myweather.model.Zhishu;
import com.bcgtgjyb.myweather.tool.FourTextAdapter;
import com.bcgtgjyb.myweather.tool.MyDialog;
import com.bcgtgjyb.myweather.tool.MyPatten;
import com.bcgtgjyb.myweather.tool.MyTime;
import com.bcgtgjyb.myweather.tool.WeatherDistinguish;
import com.bcgtgjyb.myweather.tool.XmlSelect;

@SuppressLint("HandlerLeak")
public class Fragment_future extends Fragment {
	private Myhandler myhandler;
	private View view;
	private ImageView im1;
	private ImageView im2;
	private ImageView im3;
	private ImageView im4;
	private ImageView im5;
	private TextView tv1_1;
	private TextView tv1_2;
	private TextView tv1_3;
	private TextView tv2_1;
	private TextView tv2_2;
	private TextView tv2_3;
	private TextView tv3_1;
	private TextView tv3_2;
	private TextView tv3_3;
	private TextView tv4_1;
	private TextView tv4_2;
	private TextView tv4_3;
	private TextView tv5_1;
	private TextView tv5_2;
	private TextView tv5_3;
	private Context context;
	private List pictureList;
	private List tv1List;
	private List tv2List;
	private List tv3List;
	private TextView value1;
	private TextView value2;
	private TextView value3;
	private TextView amvise1;
	private TextView amvise2;
	private TextView amvise3;
	private LinearLayout weatherLinear;
	private RespDB respDB;

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(getXML(), null, false);
		context = view.getContext();
		respDB = RespDB.getInstance(context);
		initUI();
		initUIList();

		setUiDate();
		setZhiShuDate();
		
		return view;
	}

	private int getXML() {

		return new XmlSelect().getFutureXml();
	}

	private void initUI() {

		im1 = (ImageView) view.findViewById(R.id.fragment_future_img1);
		im2 = (ImageView) view.findViewById(R.id.fragment_future_img2);
		im3 = (ImageView) view.findViewById(R.id.fragment_future_img3);
		im4 = (ImageView) view.findViewById(R.id.fragment_future_img4);
		im5 = (ImageView) view.findViewById(R.id.fragment_future_img5);

		tv1_1 = (TextView) view.findViewById(R.id.fragment_future_tv1_1);
		tv1_2 = (TextView) view.findViewById(R.id.fragment_future_tv1_2);
		tv1_3 = (TextView) view.findViewById(R.id.fragment_future_tv1_3);
		tv2_1 = (TextView) view.findViewById(R.id.fragment_future_tv2_1);
		tv2_2 = (TextView) view.findViewById(R.id.fragment_future_tv2_2);
		tv2_3 = (TextView) view.findViewById(R.id.fragment_future_tv2_3);
		tv3_1 = (TextView) view.findViewById(R.id.fragment_future_tv3_1);
		tv3_2 = (TextView) view.findViewById(R.id.fragment_future_tv3_2);
		tv3_3 = (TextView) view.findViewById(R.id.fragment_future_tv3_3);
		tv4_1 = (TextView) view.findViewById(R.id.fragment_future_tv4_1);
		tv4_2 = (TextView) view.findViewById(R.id.fragment_future_tv4_2);
		tv4_3 = (TextView) view.findViewById(R.id.fragment_future_tv4_3);
		tv5_1 = (TextView) view.findViewById(R.id.fragment_future_tv5_1);
		tv5_2 = (TextView) view.findViewById(R.id.fragment_future_tv5_2);
		tv5_3 = (TextView) view.findViewById(R.id.fragment_future_tv5_3);

		value1 = (TextView) view.findViewById(R.id.fragment_future_value1);
		value2 = (TextView) view.findViewById(R.id.fragment_future_value2);
		value3 = (TextView) view.findViewById(R.id.fragment_future_value3);

		amvise1 = (TextView) view.findViewById(R.id.fragment_future_advise1);
		amvise2 = (TextView) view.findViewById(R.id.fragment_future_advise2);
		amvise3 = (TextView) view.findViewById(R.id.fragment_future_advise3);

		weatherLinear = (LinearLayout) view
				.findViewById(R.id.fragment_future_weather);
		weatherLinear.setOnClickListener(new OnListener());
	}

	private void initUIList() {
		pictureList = new ArrayList<ImageView>();
		tv1List = new ArrayList<TextView>();
		tv2List = new ArrayList<TextView>();
		tv3List = new ArrayList<TextView>();

		pictureList.add(im1);
		pictureList.add(im2);
		pictureList.add(im3);
		pictureList.add(im4);
		pictureList.add(im5);

		tv1List.add(tv1_1);
		tv1List.add(tv2_1);
		tv1List.add(tv3_1);
		tv1List.add(tv4_1);
		tv1List.add(tv5_1);

		tv2List.add(tv1_2);
		tv2List.add(tv2_2);
		tv2List.add(tv3_2);
		tv2List.add(tv4_2);
		tv2List.add(tv5_2);

		tv3List.add(tv1_3);
		tv3List.add(tv2_3);
		tv3List.add(tv3_3);
		tv3List.add(tv4_3);
		tv3List.add(tv5_3);
	}

	private void setUiDate() {
		try {
			List weatherList = respDB.loadEasyWeather();
			MyPatten myPatten = new MyPatten();
			MyTime myTime = new MyTime();
			WeatherDistinguish weatherDistinguish = new WeatherDistinguish();
			if (weatherList.size() == 5) {
				for (int i = 0; i < 5; i++) {
					String high = ((Weather) weatherList.get(i))
							.getEasyHighTmp();
					String low = ((Weather) weatherList.get(i)).getEasyLowTmp();
					int date = myPatten.getMath(((Weather) weatherList.get(i))
							.getEasyDate());
					String weather = ((Weather) weatherList.get(i))
							.getEasyType();
					int myHigh = myPatten.getMath(high);
					int myLow = myPatten.getMath(low);

					((ImageView) pictureList.get(i))
							.setBackgroundResource(weatherDistinguish
									.distinguish(weather));
					((TextView) tv1List.get(i)).setText(weather);
					((TextView) tv2List.get(i)).setText(myPatten.getMath(low)
							+ "°/" + myPatten.getMath(high) + "°");
					if ((date + "").equals(myTime.getTodayDay())) {
						((TextView) tv3List.get(i)).setText(R.string.today);
					} else {
						String a = date + "";
						((TextView) tv3List.get(i)).setText(a+"日");
					}

				}
			}
		} catch (Exception e) {
			Log.e("Fragment_future", e.toString());
		}

	}

	private void setZhiShuDate() {
		RespDB respDB = RespDB.getInstance(context);
		List list = respDB.loadOpenZhiShu();
		Log.i("Fragment_future", "setZhiShuDate" + list.size());
		try {
			for (Object object : list) {
				OpenZhiShu zhishu = (OpenZhiShu) object;
				if ("cl".equals(zhishu.getName())) {
					value1.setText(zhishu.getValue());
					amvise1.setText(zhishu.getAdvise());

				} else if ("co".equals(zhishu.getName())) {
					value2.setText(zhishu.getValue());
					amvise2.setText(zhishu.getAdvise());

				} else if ("ct".equals(zhishu.getName())) {
					value3.setText(zhishu.getValue());
					amvise3.setText(zhishu.getAdvise());
				}
			}
		} catch (Exception e) {
			Log.i("Fragment_future", e.toString());
		}
	}

	public void setUI() {
		Message message = new Message();
		message.what = 0;
		myhandler.sendMessage(message);

	}

	class OnListener implements OnClickListener {
		public void onClick(View v) {
			final MyDialog myDialog = new MyDialog();
			myDialog.dialogShow(6, context, getListView(),
					new OnClickListener() {
						public void onClick(View v) {
							myDialog.cancel();
						}
					}, new OnClickListener() {

						@Override
						public void onClick(View v) {
							myDialog.cancel();
						}
					});
		}

	}

	private ListView getListView() {
		ListView listView = new ListView(context);
		MyWeatherCITYDB myWeatherCITYDB = MyWeatherCITYDB.getInstance(context);
		List list = myWeatherCITYDB.loadEveryDayWeather();
		FourTextAdapter fourTextAdapter = new FourTextAdapter(context, list, 2);
		listView.setAdapter(fourTextAdapter);
		return listView;
	}

	class Myhandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				setUiDate();
				setZhiShuDate();
				break;
			case 1:
				setUiDate();
				break;
			case 2:
				setZhiShuDate();
				break;
			default:
				break;
			}

		}

	}

}
