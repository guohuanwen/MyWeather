package com.bcgtgjyb.myweather.view;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.xmlpull.v1.XmlPullParser;

import android.R.bool;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.JetPlayer.OnJetEventListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.toolbox.ClearCacheRequest;
import com.aps.l;
import com.aps.m;
import com.aps.w;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.db.MyWeatherCITY;
import com.bcgtgjyb.myweather.model.EveryDayWeather;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.model.MyWeatherDB;
import com.bcgtgjyb.myweather.model.NowWeather;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.TheDayWeather;
import com.bcgtgjyb.myweather.model.TodayWeather;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.model.Weather;
import com.bcgtgjyb.myweather.model.WeatherDB;
import com.bcgtgjyb.myweather.tool.LocationCallBack;
import com.bcgtgjyb.myweather.tool.LocationWeather;
import com.bcgtgjyb.myweather.tool.LocationWeatherBack;
import com.bcgtgjyb.myweather.tool.MyAnimation;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyLocation;
import com.bcgtgjyb.myweather.tool.MyPatten;
import com.bcgtgjyb.myweather.tool.MyTime;
import com.bcgtgjyb.myweather.tool.WeatherDistinguish;
import com.bcgtgjyb.myweather.tool.WeatherPhoto;
import com.bcgtgjyb.myweather.tool.XmlSelect;
import com.bcgtgjyb.myweather.util.NetPresenter;
import com.bcgtgjyb.myweather.view.MyWidget.MyUpTime;
import com.db.chart.Tools;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.db.chart.view.XController;
import com.db.chart.view.YController;
import com.db.chart.view.animation.style.DashAnimation;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.indris.material.RippleView;
import com.john.waveview.WaveView;
import com.special.ResideMenu.ResideMenuItem;

public class Fragment_main extends Fragment {
	private TextView city;
	private TextView main_text;
	private MyWeatherCITYDB myWeatherCITYDB;
	private MyPatten myPatten;
	private Button button;
	private View view;
	private WaveView waveView;
	private PullToRefreshScrollView mPullRefreshScrollView;
	private ScrollView mScrollView;
	public Handler handler;
	private static LineChartView mLineChart;
	private final static String[] lineLabels = { "", "ANT", "GNU", "OWL",
			"APE", "JAY", "ANT", "GNU", "OWL", "APE", "JAY", "" };
	private final static float[][] lineValues = {
			{ -5f, 6f, 2f, 9f, 0f, 1f, 5f, -9f, -2f, -4f, -5f, -3f },
			{ -9f, -2f, -4f, -3f, -7f, -5f, -3f, -5f, -6f, -3f, -1f, -1f } };
	private final static float[][] FiveWeather = {
			{ 20f, 20f, 20f, 20f, 20f, 20f, 20f },
			{ -10f, -10f, -10f, -10f, -10f, -10f, -10f } };
	private final static float[][] TenWeather = {
			{ -5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, -3f },
			{ -9f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f } };
	private final static String[] FiveLabels = { "", "一", "二", "三", "四", "五",
			"" };
	private final static String[] TenLabels = { "", "", "", "", "", "", "", "",
			"", "", "", "" };
	private final static String[] FiveWea = { "", "", "", "", "", "", "" };
	private final static String[] TenWea = { "", "", "", "", "", "", "", "",
			"", "", "", "" };
	private Paint mLineGridPaint;
	private static int LINE_MAX = 45;
	private static int LINE_MIN = -45;
	private TextView mLineTooltip;
	private final TimeInterpolator enterInterpolator = new DecelerateInterpolator(
			1.5f);
	private final TimeInterpolator exitInterpolator = new AccelerateInterpolator();
	private MyAnimation myAnimation;
	private Context context;
	private LayoutInflater inflater;
	public MyHandler myHandler;
	private RelativeLayout relativeLayout2;
	private UserDB userDB;
	private RespDB respDB;
	private WeatherDB weatherDB;
	private NetPresenter netPresenter;
	private RippleView changeButton;
	private Fragment_future fragment_future;

	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Fragment_main", "onCreate");

	}

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.i("Fragment_main", "onCreateView");
		view = inflater.inflate(getXML(), null, false);
		this.inflater = inflater;
		setLocation();

		context = view.getContext();
		userDB = UserDB.getInstance(context);
		respDB = RespDB.getInstance(context);
		weatherDB = WeatherDB.getInstence(context);
		myWeatherCITYDB = MyWeatherCITYDB.getInstance(context);

		myAnimation = new MyAnimation();
		fragment_future = new Fragment_future();
		myHandler = new MyHandler();
		main_text = (TextView) view.findViewById(R.id.fragment_main_TV);
		city=(TextView)view.findViewById(R.id.fragment_main_TV1);
		
		netPresenter = new NetPresenter();
		myPatten = new MyPatten();

		setPull();
		setPaint(FiveWeather, FiveLabels, lineEntryListener);
		setRelHeight();
		getIButton();
		getChangeButton();
		setWave();

		sendMessage(3);

		return view;
	}

	private int getXML() {
		XmlSelect xmlSelect = new XmlSelect();
		return xmlSelect.getMainXml();
	}

	private void setWave() {
		iButtonNow = userDB.getWaveHeight();
		if (iButtonNow == 0) {
			setWaveView(19);
		} else {
			setWaveView(98);
		}
	}

	private void setTMPText() {
		MyTime myTime = new MyTime();
		MyPatten myPatten = new MyPatten();
		Weather weather = respDB
				.loadNowDayWeather(myTime.getTodayDayWithOut0());
		if (weather != null) {
			main_text.setText(myPatten.getMath(weather.getEasyLowTmp()) + "°/"
					+ myPatten.getMath(weather.getEasyHighTmp()) + "°");
		}
		List list=userDB.loadCity();
		if(list.size()!=0){
			city.setText((String)list.get(0));
		}
	}

	private int change = 1;

	private void getChangeButton() {
		changeButton = (RippleView) view.findViewById(R.id.fragment_main_btn);
		changeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (change == 0) {
					setTenLables();
					changeButton.setText(R.string.change2);
					change = 1;
				} else {
					setFiveLabels();
					changeButton.setText(R.string.change1);
					change = 0;
				}
			}
		});

	}

	private int iButtonNow = 0;
	private int inChange = 0;
	private int waveI;

	private void getIButton() {
		button = (Button) view.findViewById(R.id.fragment_main_Ib);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// fragment_future.setUI();
				if (inChange == 0) {
					inChange = 1;
					new Thread(new Runnable() {
						public void run() {
							if (iButtonNow == 0) {
								for (int i = 19; i <= 98; i++) {
									try {
										Thread.sleep(20);
									} catch (Exception e) {
										// TODO: handle exception
									}

									waveI = i;
									sendMessage(4);
								}
								inChange = 0;
								iButtonNow = 1;
							} else {
								for (int i = 98; i >= 19; i--) {
									try {
										Thread.sleep(20);
									} catch (Exception e) {
										// TODO: handle exception
									}
									waveI = i;
									sendMessage(4);
								}
								inChange = 0;
								iButtonNow = 0;
							}
						}
					}).start();

					// sendMessage(3);
					Log.i("Fragment_main", "onClick" + button.requestFocus()
							+ waveView.requestFocus() + view.requestFocus());
					// getActivity().onWindowFocusChanged(true);
					Log.i("Fragment_main", "onClick" + button.requestFocus()
							+ waveView.requestFocus() + view.requestFocus());
					// setWaveView();
				}
			}
		});
	}

	private void setPull() {
		// TODO Auto-generated method stub
		mPullRefreshScrollView = (PullToRefreshScrollView) view
				.findViewById(R.id.pull_refresh_scrollview);
		mPullRefreshScrollView.setScrollingWhileRefreshingEnabled(true);
		mPullRefreshScrollView
				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
					public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
						new GetDataTask().execute();
					}
				});

		mScrollView = mPullRefreshScrollView.getRefreshableView();
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				if (netPresenter.isConnect() == 1) {
				getDate();
				}else{
					sendMessage(14);
				}
			} catch (Exception e) {
				Log.i("Fragment_main", e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			// Do some stuff here
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshScrollView.onRefreshComplete();

			setUiDate(1);

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}

	private void setWaveView(int i) {
		waveView = (WaveView) view.findViewById(R.id.wave_view);
		waveView.setProgress(i);
	}

	private void setPaint(float[][] lineValues, String[] lineLabels,
			OnEntryClickListener lineEntryListener) {
		Log.i("Fragment_main", "setPaint");
		mLineChart = (LineChartView) view.findViewById(R.id.linechart);
		mLineGridPaint = new Paint();
		mLineGridPaint.setColor(context.getResources().getColor(
				R.color.line_grid));
		mLineGridPaint
				.setPathEffect(new DashPathEffect(new float[] { 5, 5 }, 0));
		mLineGridPaint.setStyle(Paint.Style.STROKE);
		mLineGridPaint.setAntiAlias(true);
		mLineGridPaint.setStrokeWidth(Tools.fromDpToPx(.75f));
		updateLineChart(lineValues, lineLabels);
		mLineChart.setOnEntryClickListener(lineEntryListener);
		mLineChart.setOnClickListener(lineClickListener);
	}

	private void updateLineChart(float[][] lineValues, String[] lineLabels) {
		Log.i("Fragment_main", "updateLineChart");
		mLineChart.reset();
		LineSet dataSet = new LineSet();
		dataSet.addPoints(lineLabels, lineValues[0]);
		dataSet.setDots(true)
				.setDotsColor(context.getResources().getColor(R.color.line_bg))
				.setDotsRadius(Tools.fromDpToPx(6))
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(
						context.getResources().getColor(R.color.line))
				.setLineColor(context.getResources().getColor(R.color.line))
				.setLineThickness(Tools.fromDpToPx(3)).beginAt(1)
				.endAt(lineLabels.length - 1);
		mLineChart.addData(dataSet);

		dataSet = new LineSet();
		dataSet.addPoints(lineLabels, lineValues[1]);
		dataSet.setDots(true)
				.setDotsColor(context.getResources().getColor(R.color.line_bg))
				.setDotsRadius(Tools.fromDpToPx(6))
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(
						context.getResources().getColor(R.color.line))
				.setLineColor(context.getResources().getColor(R.color.line))
				.setLineThickness(Tools.fromDpToPx(3)).beginAt(1)
				.endAt(lineLabels.length - 1);
		mLineChart.addData(dataSet);

		// dataSet = new LineSet();
		// dataSet.addPoints(lineLabels, lineValues[1]);
		// dataSet.setLineColor(this.getResources().getColor(R.color.line))
		// .setLineThickness(Tools.fromDpToPx(3))
		// .setSmooth(true)
		// .setDashed(true);
		// mLineChart.addData(dataSet);

		float max = myPatten.getMax(lineValues);
		float min = myPatten.getMin(lineValues);
		LINE_MIN = (int) min - 1;
		LINE_MAX = (int) max + 1;
		int degree = (LINE_MAX - LINE_MIN) / 5;
		LINE_MAX = LINE_MAX + degree - LINE_MAX % degree;
		LINE_MIN = LINE_MIN - LINE_MIN % degree;
		mLineChart.setBorderSpacing(Tools.fromDpToPx(10))
				.setGrid(LineChartView.GridType.HORIZONTAL, mLineGridPaint)
				.setXAxis(false).setXLabels(XController.LabelPosition.OUTSIDE)
				.setYAxis(false).setYLabels(YController.LabelPosition.OUTSIDE)
				.setAxisBorderValues(LINE_MIN, LINE_MAX, degree)
				.setLabelsFormat(new DecimalFormat("##'°'"))

				// .show(getAnimation(true).setEndAction(mEnterEndAction))
				.show();

		mLineChart.animateSet(1, new DashAnimation());
	}

	// private Animation getAnimation(boolean newAnim){
	// if(newAnim)
	// return new Animation()
	// .setAlpha(mCurrAlpha)
	// .setEasing(mCurrEasing)
	// .setOverlap(mCurrOverlapFactor, mCurrOverlapOrder)
	// .setStartPoint(mCurrStartX, mCurrStartY);
	// else
	// return new Animation()
	// .setAlpha(mOldAlpha)
	// .setEasing(mOldEasing)
	// .setOverlap(mOldOverlapFactor, mOldOverlapOrder)
	// .setStartPoint(mOldStartX, mOldStartY);
	// }
	// private final Runnable mEnterEndAction = new Runnable() {
	// @Override
	// public void run() {
	// mPlayBtn.setEnabled(true);
	// }
	// };
	private final OnEntryClickListener tenEntryListener = new OnEntryClickListener() {
		@Override
		public void onClick(int setIndex, int entryIndex, Rect rect) {

			if (mLineTooltip == null)
				showTenLineTooltip(setIndex, entryIndex, rect);
			else
				dismissTenLineTooltip(setIndex, entryIndex, rect);
		}
	};

	private final OnEntryClickListener lineEntryListener = new OnEntryClickListener() {
		@Override
		public void onClick(int setIndex, int entryIndex, Rect rect) {

			if (mLineTooltip == null)
				showLineTooltip(setIndex, entryIndex, rect);
			else
				dismissLineTooltip(setIndex, entryIndex, rect);
		}
	};

	private final OnClickListener lineClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mLineTooltip != null)
				dismissLineTooltip(-1, -1, null);
		}
	};

	@SuppressLint("NewApi")
	private void showLineTooltip(int setIndex, int entryIndex, Rect rect) {
		mLineTooltip = (TextView) this.inflater.inflate(
				R.layout.circular_tooltip, null);
		String text = Integer.toString((int) FiveWeather[setIndex][entryIndex]);
		mLineTooltip.setText(text + "°" + "\n" + FiveWea[entryIndex]);

		LayoutParams layoutParams = new LayoutParams(
				(int) Tools.fromDpToPx(70), (int) Tools.fromDpToPx(70));
		layoutParams.leftMargin = rect.centerX() - layoutParams.width / 2;
		layoutParams.topMargin = rect.centerY() - layoutParams.height / 2;
		mLineTooltip.setLayoutParams(layoutParams);

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			mLineTooltip.setPivotX(layoutParams.width / 2);
			mLineTooltip.setPivotY(layoutParams.height / 2);
			mLineTooltip.setAlpha(0);
			mLineTooltip.setScaleX(0);
			mLineTooltip.setScaleY(0);
			mLineTooltip.animate().setDuration(150).alpha(1).scaleX(1)
					.scaleY(1).rotation(360).setInterpolator(enterInterpolator);
		}

		mLineChart.showTooltip(mLineTooltip);
	}

	@SuppressLint("NewApi")
	private void dismissLineTooltip(final int setIndex, final int entryIndex,
			final Rect rect) {

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			mLineTooltip.animate().setDuration(100).scaleX(0).scaleY(0)
					.alpha(0).setInterpolator(exitInterpolator)
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							mLineChart.removeView(mLineTooltip);
							mLineTooltip = null;
							if (entryIndex != -1)
								showLineTooltip(setIndex, entryIndex, rect);
						}
					});
		} else {
			mLineChart.dismissTooltip(mLineTooltip);
			mLineTooltip = null;
			if (entryIndex != -1)
				showLineTooltip(setIndex, entryIndex, rect);
		}
	}

	@SuppressLint("NewApi")
	private void showTenLineTooltip(int setIndex, int entryIndex, Rect rect) {
		mLineTooltip = (TextView) this.inflater.inflate(
				R.layout.circular_tooltip, null);
		String text = Integer.toString((int) TenWeather[setIndex][entryIndex]);
		mLineTooltip.setText(text + "°" + "\n" + TenWea[entryIndex]);

		LayoutParams layoutParams = new LayoutParams(
				(int) Tools.fromDpToPx(70), (int) Tools.fromDpToPx(70));
		layoutParams.leftMargin = rect.centerX() - layoutParams.width / 2;
		layoutParams.topMargin = rect.centerY() - layoutParams.height / 2;
		mLineTooltip.setLayoutParams(layoutParams);

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			mLineTooltip.setPivotX(layoutParams.width / 2);
			mLineTooltip.setPivotY(layoutParams.height / 2);
			mLineTooltip.setAlpha(0);
			mLineTooltip.setScaleX(0);
			mLineTooltip.setScaleY(0);
			mLineTooltip.animate().setDuration(150).alpha(1).scaleX(1)
					.scaleY(1).rotation(360).setInterpolator(enterInterpolator);
		}

		mLineChart.showTooltip(mLineTooltip);
	}

	@SuppressLint("NewApi")
	private void dismissTenLineTooltip(final int setIndex,
			final int entryIndex, final Rect rect) {

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			mLineTooltip.animate().setDuration(100).scaleX(0).scaleY(0)
					.alpha(0).setInterpolator(exitInterpolator)
					.withEndAction(new Runnable() {
						public void run() {
							mLineChart.removeView(mLineTooltip);
							mLineTooltip = null;
							if (entryIndex != -1)
								showTenLineTooltip(setIndex, entryIndex, rect);
						}
					});
		} else {
			mLineChart.dismissTooltip(mLineTooltip);
			mLineTooltip = null;
			if (entryIndex != -1)
				showTenLineTooltip(setIndex, entryIndex, rect);
		}
	}

	private void setRelHeight() {
		relativeLayout2 = (RelativeLayout) view
				.findViewById(R.id.fragment_main_Rel2);
		int screenWidth, screenHeight;
		WindowManager windowManager = getActivity().getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();

		// 状态栏
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Calculate ActionBar height
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
				tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					context.getResources().getDisplayMetrics());
		}

		Log.i("Fragment_main", screenHeight + "*" + actionBarHeight + "*" + x
				+ "*" + sbar);
		relativeLayout2.setLayoutParams(new LayoutParams(screenWidth,
				screenHeight - actionBarHeight - sbar));
	}

	public class MyHandler extends Handler {
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 10:
				Toast.makeText(getActivity(), "今日概况与20日预测天气数据异常，请1分钟后重试",
						Toast.LENGTH_SHORT).show();
				break;
			case 11:
				Toast.makeText(getActivity(), "实时天气数据异常，请1分钟后重试",
						Toast.LENGTH_SHORT).show();
				break;
			case 12:
				Toast.makeText(getActivity(), "生活指数数据异常，请1分钟后重试",
						Toast.LENGTH_SHORT).show();
				break;
			case 13:
				Toast.makeText(getActivity(), "未来五日预测数据异常，请1分钟后重试",
						Toast.LENGTH_SHORT).show();
				break;
			case 14:
				Toast.makeText(getActivity(), "网络故障，请检查网络连接",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				try {
					waveView.onWindowFocusChanged(true);
				} catch (Exception e) {
					Log.i("Fragment_main", e.toString());
				}
				break;
			case 2:
				setTenLables();
				break;
			case 3:
				try {
					setUiDate(0);
				} catch (Exception e) {
					Log.e("Fragment_main", e.toString());
				}
				break;
			case 4:
				waveView.setProgress(waveI);
				break;
			case 5:
				Log.i("Fragment_main", "5");
				// 图标中的五天天气
				try {
					// setFiveLabels();
				} catch (Exception e) {
					Log.e("Fragment_main", "5" + e.toString());
				}
				break;
			case 6:
				Log.i("Fragment_main", "6");
				// 左边的定位天气情况
				
				break;
			case 7:
				Log.i("Fragment_main", "7");
				// 同一数据源 URL3
				try {
					// setIButton();
					setTenLables();
				} catch (Exception e) {
					Log.e("Fragment_main", "7" + e.toString());
				}
				break;
			case 8:
				Log.i("Fragment_main", "8");
				// 中间五天天气
				try {
					Fragment_future.Myhandler future_handler = new Fragment_future().new Myhandler();
					Message message = new Message();
					message.what = 1;
					future_handler.sendMessage(message);
				} catch (Exception e) {
					Log.e("Fragment_main", "8" + e.toString());
				}
				break;
			case 9:
				Log.i("Fragment_main", "9");
				// 中间三个指数
				try {
					Fragment_future.Myhandler future_handler1 = new Fragment_future().new Myhandler();
					Message message1 = new Message();
					message1.what = 1;
					future_handler1.sendMessage(message1);
				} catch (Exception e) {
					Log.e("Fragment_main", "9" + e.toString());
				}
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}

	}

	private List cityName;
	private List cityCode;
	private int img1;
	private int img2;

	private void getDate() {
		Log.e("Fragment_main", "getDate");
		List name = userDB.loadCity();
		cityCode = new ArrayList<String>();
		cityName = new ArrayList<String>();
		if (name.size() > 0) {

		} else {
			setLocation();
			name = userDB.loadCity();
		}
		for (Object obj : name) {

			String cityId = userDB.searchCityId((String) obj);
			String oneCityName = weatherDB.findCityName(cityId);
			cityCode.add(cityId);
			cityName.add(oneCityName);
		}
		int re1 = 0, re2 = 0, re3 = 0, re4 = 0;

		
			try {
				re4 = netPresenter.getOpenWeatherNet((String) cityCode.get(0));
			} catch (Exception e) {
				Log.e("Fragment_main", e.toString());
			}
			try {
				re2 = netPresenter.getResp((String) cityCode.get(0));
			} catch (Exception e) {
				Log.e("Fragment_main", e.toString());
			}

			try {
				re3 = netPresenter.getWeatherJson((String) cityName.get(0));
			} catch (Exception e) {
				Log.e("Fragment_main", e.toString());
			}
			if (re4 == 0) {
				sendMessage(12);
			}
			if (re2 == 0) {
				sendMessage(13);
			} 
			if (re3 == 0) {
				sendMessage(10);
			}
			if(re2==1||re3==1||re4==1){
				getNetNowWeather();
			}
//				if(re1==1){
//					NowWeather nowWeather = respDB.loadNowWeather();
//					setStartUiDate(nowWeather.getCity(), nowWeather.getWeather(),
//							nowWeather.getHumidity(), nowWeather.getWindDir(),
//							nowWeather.getWindPower());
//				}
			
			
	} 
	



	private void setIButton() {
		MyTime myTime = new MyTime();
		WeatherDistinguish weatherDistinguish = new WeatherDistinguish();
		Log.i("Fragment_main", myTime.getTodayDayWithOut0() + "");
		Weather weather = respDB
				.loadNowDayWeather(myTime.getTodayDayWithOut0());

		String name = weather.getEasyType();
		int res = weatherDistinguish.distinguish(name);
		button.setBackgroundResource(res);
	}

	private void setUiDate(int i) {
		Log.i("Fragment_main", "setUiDate1");
		try {
			sendActivityMessage(1);
			changeButton.setText(R.string.change2);
			if (i == 1) {
				fragment_future = new Fragment_future();
				replaceFragment(fragment_future);
			} else if (i == 0) {
				replaceFragment(fragment_future);
			}

			MyTime myTime = new MyTime();

			// sendMessage(8);
			// sendMessage(9);

			Log.i("Fragment_main", "setUiDate4");
			// MyTime myTime = new MyTime();
			//
			// TheDayWeather theDayWeather = respDB.loadTheDayWeather();
			// String sunSet = theDayWeather.getSunset();
			// String sunRise = theDayWeather.getSunrise();
			//
			// Log.i("Fragment_main", "setUiDate3" + sunSet + sunRise);
			//
			// int sunSetHour = myPatten.getHour(sunSet);
			// int sunSetMunite = myPatten.getMinute(sunSet);
			// int sunRiseHour = myPatten.getHour(sunRise);
			// int sunRiseMunite = myPatten.getMinute(sunRise);
			//
			// Log.i("Fragment_main", "setUiDate3");
			//
			// int nowHour = Integer.valueOf(myTime.getTodayHour());
			// int nowMunite = Integer.valueOf(myTime.getTodayMinute());
			//
			// Log.i("Fragment_main", "setUiDate4");
			//
			// img1 = nowWeather.getImg1();
			// img2 = nowWeather.getImg2();
			//
			// Log.i("Fragment_main", "setUiDate5");
			// Log.i("Fragment_main", nowHour + sunRiseHour + nowMunite
			// + sunRiseMunite + "");
			// if (nowHour >= sunRiseHour && nowMunite >= sunRiseMunite
			// && nowHour <= sunSetHour && nowMunite <= sunSetMunite) {
			// button.setBackgroundResource(img1);
			// } else {
			// button.setBackgroundResource(img1);
			// }
			// Log.i("Fragment_main", "setUiDate");

		} catch (Exception e) {
			Log.i("Fragment_main", e.toString());
		}

		try {
			setTenLables();
		} catch (Exception e) {
			Log.i("Fragment_main", e.toString());
		}

		try {
			setIButton();
			setTMPText();
			updateWidget();

		} catch (Exception e) {
			Log.i("Fragment_main", e.toString());
		}

	}

	private void updateWidget() {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget);
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
					String low = ((Weather) weatherList.get(i)).getEasyLowTmp();
					int dateDay = myPatten.getMath(((Weather) weatherList
							.get(i)).getEasyDate());
					String weather = ((Weather) weatherList.get(i))
							.getEasyType();
					int myHigh = myPatten.getMath(high);
					int myLow = myPatten.getMath(low);
					imgList.add(weatherDistinguish.distinguish(weather) + "");
					tvList1.add(weather);
					tvList2.add(myLow + "°/" + myHigh + "°");
					if ((dateDay + "").equals(myTime.getTodayDay())) {
						tvList3.add(MyApplication.getContext().getResources()
								.getText(R.string.today));
					} else {
						String a = dateDay + "";
						tvList3.add(a + "日");
					}

				}
			}
			if (imgList.size() == 5 && imgList.size() == 5
					&& tvList2.size() == 5 && tvList3.size() == 5) {
				Log.i("MyWidget", "2");
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
				Log.i("MyWidget", (String) tvList3.get(1));
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
				AppWidgetManager.getInstance(getActivity()).updateAppWidget(
						new ComponentName(getActivity(), MyWidget.class),
						remoteViews);
			}
		} catch (Exception e) {
			Log.e("MyWidget", e.toString());
		}
	}

	private void sendMessage(int i) {
		Message message = new Message();
		message.what = i;
		myHandler.sendMessage(message);
	}

	private void sendActivityMessage(int i) {
		handler = ((StartUI) getActivity()).handler;
		Message message = new Message();
		message.what = i;
		handler.sendMessage(message);
	}

	@Override
	public void onResume() {
		super.onResume();
		// setWaveView();
		// getActivity().onWindowFocusChanged(true);
		Log.i("Fragment_main", "onResume");
		sendMessage(2);

	}

	@Override
	public void onDestroy() {
		Log.i("Fragment_main", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("Fragment_main", "onPause");
		myLocation.closeLocation();
		// myLocation.closeLocation();
		// locationWeather.onPause();

	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("Fragment_main", "onStart");
	}

	@Override
	public void onStop() {
		Log.i("Fragment_main", "onStop");
		super.onStop();
	}

	@Override
	public void onDetach() {
		Log.i("Fragment_main", "onDetach");
		super.onDetach();
	}

	
		
	

	private int re;
	private LocationWeather locationWeather;

	private int getNetNowWeather() {
		Log.i("Fragment_main", "getNetNowWeather");
		new Thread(new Runnable() {
			public void run() {
				re = 0;
				locationWeather = new LocationWeather(context,
						new LocationWeatherBack() {
							public void onNowWeatherBack(NowWeather nowWeather) {
								respDB.saveNowWeather(nowWeather);
								re = 1;
								sendActivityMessage(1);
							}

							public void onNowWeatherError(int i) {
								if (i == 0) {
									re = 0;
								}

							}

						});

			}
		}).start();

		return re;

	}

	private void setTenLables() {
		try {
			Log.i("Fragment_main", "setTenLables");
			List weatherList = myWeatherCITYDB.loadTodayWeather();
			if (weatherList.size() > 0) {
				for (int i = 0; i < 20; i++) {
					int n = i % 2;
					if (n == 0) {
						String tem = ((TodayWeather) weatherList.get(i))
								.getTmp();
						String date = ((TodayWeather) weatherList.get(i))
								.getHour();
						String weather = ((TodayWeather) weatherList.get(i))
								.getSky();
						TenWeather[0][i / 2 + 1] = Integer.valueOf(tem);
						TenWeather[1][i / 2 + 1] = Integer.valueOf(tem);
						TenLabels[i / 2 + 1] = date;
						TenWea[i / 2 + 1] = weather;
					}
				}
				TenWeather[0][0] = TenWeather[0][1];
				TenWeather[0][11] = TenWeather[0][1];
				TenWeather[1][0] = TenWeather[1][1];
				TenWeather[1][11] = TenWeather[1][1];
				setPaint(TenWeather, TenLabels, tenEntryListener);

			}
		} catch (Exception e) {
			Log.e("Fragment_main", e.toString());
		}
	}

	private void setFiveLabels() {
		try {
			List weatherList = respDB.loadEasyWeather();
			for (int i = 0; i < weatherList.size(); i++) {
				String high = ((Weather) weatherList.get(i)).getEasyHighTmp();
				String low = ((Weather) weatherList.get(i)).getEasyLowTmp();
				String date = ((Weather) weatherList.get(i)).getEasyDate();
				String weather = ((Weather) weatherList.get(i)).getEasyType();
				int myHigh = myPatten.getMath(high);
				int myLow = myPatten.getMath(low);
				FiveWeather[0][i + 1] = myHigh;
				FiveWeather[1][i + 1] = myLow;

				if (myPatten.getMath(date) == Integer.valueOf(new MyTime()
						.getTodayDay())) {

					FiveLabels[i + 1] = context.getString(R.string.today);
				} else {
					FiveLabels[i + 1] = myPatten.getMath(date)
							+ context.getString(R.string.day);
				}

				FiveWea[i + 1] = weather;
			}
			FiveWeather[0][0] = FiveWeather[0][1];
			FiveWeather[0][weatherList.size()] = FiveWeather[0][1];
			FiveWeather[1][0] = FiveWeather[1][1];
			FiveWeather[1][weatherList.size()] = FiveWeather[1][1];
			setPaint(FiveWeather, FiveLabels, lineEntryListener);
		} catch (Exception e) {
			Log.e("Fragment_main", e.toString());
		}
	}

	private MyLocation myLocation;

	private void setLocation() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					myLocation = new MyLocation(context);
					myLocation.init(new LocationCallBack() {
						public void onBack(String province, String city) {
							List reCity = weatherDB.getLocationCity(province,
									city);
							if (reCity.size() >= 2) {
								boolean re = userDB.isCityIn((String) reCity
										.get(0));
								if (re == false) {
									userDB.saveCity((String) reCity.get(0),
											(String) reCity.get(1), 1);
								} else {
									userDB.setMainCity((String) reCity.get(0));
								}
							}
						}
					});
				} catch (Exception e) {
					Log.i("Fragment_chooseCity", e.toString());
				}

			}
		}).start();
	}

	private void replaceFragment(Fragment targetFragment) {
		Log.i("StartUI", "replaceFragment");
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// ft.add(targetFragment, "fragment");
		ft.replace(R.id.fragment_main_frameLayout, targetFragment, "fragment");
		ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
	}

}
