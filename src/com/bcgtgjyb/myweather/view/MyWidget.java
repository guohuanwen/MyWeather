package com.bcgtgjyb.myweather.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.Weather;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyPatten;
import com.bcgtgjyb.myweather.tool.MyTime;
import com.bcgtgjyb.myweather.tool.WeatherDistinguish;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MyWidget extends AppWidgetProvider {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.i("MyWidget", "onReceive");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.i("MyWidget", "onDeleted");
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		Log.i("MyWidget", "onDisabled");
	}

	@Override
	public void onRestored(Context context, int[] oldWidgetIds,
			int[] newWidgetIds) {
		// TODO Auto-generated method stub
		super.onRestored(context, oldWidgetIds, newWidgetIds);
		Log.i("MyWidget", "onRestored");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.i("MyWidget", "onEnabled");

	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		try {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new MyUpTime(context, appWidgetManager),
					1, 30 * 60 * 1000);
		} catch (Exception e) {
			Log.e("MyWidget", e.toString());
		}
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.widget);
		Intent fullIntent = new Intent(context, StartUI.class);
		PendingIntent Pfullintent = PendingIntent.getActivity(context, 0,
				fullIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.widget_weather, Pfullintent);
		appWidgetManager.updateAppWidget(appWidgetIds, views);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("MyWidget", "onUpdate");
	}

	public class MyUpTime extends TimerTask {
		RemoteViews remoteViews;
		AppWidgetManager appWidgetManager;
		ComponentName thisWidget;

		public MyUpTime(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetManager = appWidgetManager;
			remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);

			thisWidget = new ComponentName(context, MyWidget.class);
		}

		public void run() {
			List imgList = new ArrayList<String>();
			List tvList1 = new ArrayList<String>();
			List tvList2 = new ArrayList<String>();
			List tvList3 = new ArrayList<String>();
			try {
				RespDB respDB = RespDB.getInstance(MyApplication.getContext());
				Date date = new Date();
				List weatherList = respDB.loadEasyWeather();
				MyPatten myPatten = new MyPatten();
				MyTime myTime = new MyTime();
				WeatherDistinguish weatherDistinguish = new WeatherDistinguish();
				if (weatherList.size() == 5) {
					for (int i = 0; i < 5; i++) {
						String high = ((Weather) weatherList.get(i))
								.getEasyHighTmp();
						String low = ((Weather) weatherList.get(i))
								.getEasyLowTmp();
						int dateDay = myPatten.getMath(((Weather) weatherList
								.get(i)).getEasyDate());
						String weather = ((Weather) weatherList.get(i))
								.getEasyType();
						int myHigh = myPatten.getMath(high);
						int myLow = myPatten.getMath(low);
						imgList.add(weatherDistinguish.distinguish(weather)
								+ "");
						tvList1.add(weather);
						tvList2.add(myLow + "°/" + myHigh + "°");
						if ((dateDay + "").equals(myTime.getTodayDay())) {
							tvList3.add(MyApplication.getContext()
									.getResources().getText(R.string.today));
						} else {
							String a = dateDay + "";
							tvList3.add(a + "日");
						}

					}
				}

				if (imgList.size() == 5 && imgList.size() == 5
						&& tvList2.size() == 5 && tvList3.size() == 5) {

					remoteViews.setImageViewResource(R.id.widget_img1,
							Integer.valueOf((String) imgList.get(0)));
					remoteViews.setImageViewResource(R.id.widget_img2,
							Integer.valueOf((String) imgList.get(1)));
					remoteViews.setImageViewResource(R.id.widget_img3,
							Integer.valueOf((String) imgList.get(2)));
					remoteViews.setImageViewResource(R.id.widget_img4,
							Integer.valueOf((String) imgList.get(3)));
					remoteViews.setImageViewResource(R.id.widget_img5,
							Integer.valueOf((String) imgList.get(4)));
					remoteViews.setTextViewText(R.id.widget_tv1_1,
							(String) tvList1.get(0));
					remoteViews.setTextViewText(R.id.widget_tv1_2,
							(String) tvList2.get(0));
					remoteViews.setTextViewText(R.id.widget_tv1_3,
							(String) tvList3.get(0));
					remoteViews.setTextViewText(R.id.widget_tv2_1,
							(String) tvList1.get(1));
					remoteViews.setTextViewText(R.id.widget_tv2_2,
							(String) tvList2.get(1));
					remoteViews.setTextViewText(R.id.widget_tv2_3,
							(String) tvList3.get(1));
					remoteViews.setTextViewText(R.id.widget_tv3_1,
							(String) tvList1.get(2));
					remoteViews.setTextViewText(R.id.widget_tv3_2,
							(String) tvList2.get(2));
					remoteViews.setTextViewText(R.id.widget_tv3_3,
							(String) tvList3.get(2));
					remoteViews.setTextViewText(R.id.widget_tv4_1,
							(String) tvList1.get(3));
					remoteViews.setTextViewText(R.id.widget_tv4_2,
							(String) tvList2.get(3));
					remoteViews.setTextViewText(R.id.widget_tv4_3,
							(String) tvList3.get(3));
					remoteViews.setTextViewText(R.id.widget_tv5_1,
							(String) tvList1.get(4));
					remoteViews.setTextViewText(R.id.widget_tv5_2,
							(String) tvList2.get(4));
					remoteViews.setTextViewText(R.id.widget_tv5_3,
							(String) tvList3.get(4));
					appWidgetManager.updateAppWidget(thisWidget, remoteViews);
					Log.i("MyWidget", "MyUpTime");
				}
			} catch (Exception e) {
				Log.e("MyWidget", e.toString());
			}
		}
	}
}
