package com.bcgtgjyb.myweather.view;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.c;
import com.aps.n;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.NowWeather;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.TheDayWeather;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.model.WeatherDB;
import com.bcgtgjyb.myweather.tool.Initialise;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.ReStart;
import com.bcgtgjyb.myweather.tool.Test5;
import com.bcgtgjyb.myweather.tool.XmlSelect;
import com.bcgtgjyb.myweather.util.Utility;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class StartUI extends ActionBarActivity implements View.OnClickListener {
	private String cityText;
	private String skyText;
	private String shiduText;
	private String fengxiangText;
	private String fengliText;

	private ResideMenu resideMenu;
	private ResideMenuItem itemSettings;
	private ResideMenuItem itemChoose;
	private ResideMenuItem itemCity;
	private ResideMenuItem itemShidu;
	private ResideMenuItem itemFengXiang;
	private ResideMenuItem itemFengLi;
	private ResideMenuItem itemWeather;
	private RespDB respDB;
	private UserDB userDB;
	private Fragment fragment_main;
	private Fragment fragment_choose;
	private Fragment fragment_setting;
	private Fragment fragment_skin;

	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(getXML());
		new Initialise().copyDB();
		Log.i("StartUI", "1");
		respDB = RespDB.getInstance(this);
		userDB = UserDB.getInstance(this);
		Log.i("StartUI", "2");
		fragment_main = new Fragment_main();
		fragment_choose = new Fragment_chooseCity();
		fragment_setting = new Fragment_setting();
		fragment_skin = new Fragment_skin();

		setRediseMenu();

		replaceFragment(fragment_main);
		Log.i("StartUI", "3");
		if(!(new Test5().getSHA1())){
//			finish();
		};
	}

	private int getXML() {

		return new XmlSelect().getStartUIXml();
	}

	private void setRediseMenu() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.day1);
		resideMenu.attachToActivity(this);
		resideMenu.setScaleValue(0.6f);

		NowWeather nowWeather = respDB.loadNowWeather();
		String weather = "";
		String city = "";
		String humidy = "";
		String windDir = "";
		String windPower = "";
		if (nowWeather != null) {
			weather = nowWeather.getWeather();
			city = nowWeather.getCity();
			humidy = nowWeather.getHumidity();
			windDir = nowWeather.getWindDir();
			windPower = nowWeather.getWindPower();

		}
		// set item
		itemWeather = new ResideMenuItem(this, R.drawable.weather, weather);
		itemCity = new ResideMenuItem(this, R.drawable.location, city);
		itemShidu = new ResideMenuItem(this, R.drawable.shidu, humidy);
		itemFengXiang = new ResideMenuItem(this, R.drawable.fengxiang, windDir);
		itemFengLi = new ResideMenuItem(this, R.drawable.fengli, windPower);
		itemChoose = new ResideMenuItem(this, R.drawable.find, R.string.choose);
		itemSettings = new ResideMenuItem(this, R.drawable.icon_settings,
				R.string.Settings);
		itemSettings.setOnClickListener(this);
		itemChoose.setOnClickListener(this);

		resideMenu.addMenuItem(itemCity, ResideMenu.DIRECTION_RIGHT);
		resideMenu.addMenuItem(itemWeather, ResideMenu.DIRECTION_RIGHT);
		resideMenu.addMenuItem(itemShidu, ResideMenu.DIRECTION_RIGHT);
		resideMenu.addMenuItem(itemFengXiang, ResideMenu.DIRECTION_RIGHT);
		resideMenu.addMenuItem(itemFengLi, ResideMenu.DIRECTION_RIGHT);

		resideMenu.addMenuItem(itemChoose, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);

	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			addFragment(fragment_setting);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void replaceFragment(Fragment targetFragment) {
		Log.i("StartUI", "replaceFragment");
		if (resideMenu.isOpened()) {
			resideMenu.closeMenu();
		}

		resideMenu.clearIgnoredViewList();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// ft.add(targetFragment, "fragment");
		ft.replace(R.id.main_fragment, targetFragment, "fragment");
		ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
		onWindowFocusChanged(true);
		nowFragment = targetFragment;
		Log.i("StartUi", nowFragment.toString());
	}

	private void addFragment(Fragment targetFragment) {
		Log.i("StartUI", "addFragment");
		if (resideMenu.isOpened()) {
			resideMenu.closeMenu();
		}

		if (nowFragment == fragment_main) {
			addFrag(targetFragment);
		} else if (nowFragment == fragment_choose) {
			if (targetFragment == fragment_choose) {

			} else {
				removeFragment(nowFragment);
				addFrag(targetFragment);
			}
		} else if (nowFragment == fragment_setting) {
			if (targetFragment == fragment_setting) {

			} else {
				removeFragment(nowFragment);
				addFrag(targetFragment);
			}
		}

	}

	private void addFrag(Fragment targetFragment) {
		resideMenu.clearIgnoredViewList();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.main_fragment, targetFragment, "fragment");
		// ft.replace(R.id.main_fragment, targetFragment, "fragment");
		ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
		onWindowFocusChanged(true);
		nowFragment = targetFragment;
		Log.i("StartUi", nowFragment.toString());
	}

	private Fragment nowFragment;

	private void removeFragment(Fragment targetFragment) {
		Log.i("StartUI", "removeFragment");
		if (resideMenu.isOpened()) {
			resideMenu.closeMenu();
		}

		resideMenu.clearIgnoredViewList();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// ft.add(targetFragment, "fragment");
		// ft.replace(R.id.main_fragment, targetFragment, "fragment");
		ft.remove(nowFragment);
		ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
		onWindowFocusChanged(true);
		Log.i("StartUi", nowFragment.toString());
		nowFragment = fragment_main;
	}

	@Override
	public void onClick(View v) {
		if (v == itemSettings) {
			addFragment(fragment_setting);

		} else if (v == itemChoose) {
			addFragment(fragment_choose);
		}

	}

	private void setNoTitle() {

	}

	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				setRediseMenu();
				break;
			case 1:
				Log.i("StartUI", "setleft");
				NowWeather nowWeather = respDB.loadNowWeather();
				if(nowWeather!=null){
				itemCity.setTitle(nowWeather.getCity());
				itemFengLi.setTitle(nowWeather.getWindPower());
				itemFengXiang.setTitle(nowWeather.getWindDir());
				itemShidu.setTitle(nowWeather.getHumidity());
				itemWeather.setTitle(nowWeather.getWeather());}
				break;
			case 2:
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.add(R.id.main_fragment, fragment_skin, "fragment");
				// ft.replace(R.id.main_fragment, targetFragment, "fragment");
				ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.commit();
				onWindowFocusChanged(true);
				nowFragment = fragment_skin;
				break;
			case 3:
				Log.i("StartUI", "handler3");
				removeFragment(fragment_skin);
				Context context = new MyApplication().getContext();
				Intent i = context.getPackageManager()
						.getLaunchIntentForPackage(context.getPackageName());
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				break;
			default:
				break;
			}

			super.handleMessage(msg);
		}

	};

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (nowFragment == fragment_main) {
				if (resideMenu.isOpened()) {
					resideMenu.closeMenu();
				}else{
				finish();}
			} else if (nowFragment == fragment_skin) {
				removeFragment(nowFragment);
				nowFragment = fragment_setting;
			} else {
				removeFragment(nowFragment);
			}
		}

		return false;
	}

	public void onWindowFocusChanged(boolean hasWindowFocus) {
		Log.i("StartUI", "onWindowFocusChanged" + hasWindowFocus);
		super.onWindowFocusChanged(hasWindowFocus);
		if (hasWindowFocus) {
		}
	}

	public void setLeftUI(String city, String sky, String shidu,
			String fengxiang, String fengli) {
		
		this.cityText = city;
		this.skyText = sky;
		this.shiduText = shidu;
		this.fengxiangText = fengxiang;
		this.fengliText = fengli;
	}

	public void retry() {
		removeFragment(fragment_main);
		replaceFragment(fragment_main);
	}

}
