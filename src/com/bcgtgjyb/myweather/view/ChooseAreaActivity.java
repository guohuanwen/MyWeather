package com.bcgtgjyb.myweather.view;


import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.StringRequest;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.City;
import com.bcgtgjyb.myweather.model.County;
import com.bcgtgjyb.myweather.model.MyWeatherDB;
import com.bcgtgjyb.myweather.model.Province;
import com.bcgtgjyb.myweather.model.WeatherPlace;
import com.bcgtgjyb.myweather.presenter.ChooseAreaPresenter;
import com.bcgtgjyb.myweather.util.HttpCallbackListener;
import com.bcgtgjyb.myweather.util.HttpUtil;
import com.bcgtgjyb.myweather.util.Utility;

public class ChooseAreaActivity extends Activity{


private int lastNub;
private TextView place=null;
private ListView listView=null;
private ChooseAreaPresenter chooesArea=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_area);
		chooesArea=new ChooseAreaPresenter();
		place=(TextView)findViewById(R.id.title_text);
		place.setText(R.string.china);
		listView=(ListView)findViewById(R.id.list_view);
		chooesArea.setListObject("");
		chooesArea.setNameList(chooesArea.getProvinceNameList());
		loadList();
		
	}
	/**
	 * ����list
	 */
	public void loadList(){
		
		ListAdapter listAdapter=chooesArea.fillList(this);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new ListListener());
	}
	/**
	 * ����list����
	 * @author Administrator
	 *
	 */
	class ListListener implements OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {	
				int param=(int)id;
				switch (chooesArea.getPage()) {
				case 0:
					lastNub=param;
					chooesArea.setPage(1);
					Log.i("ChooseAcitvity", "listener");
					String name=(chooesArea.getProvinceNameList()).get(param);
					Log.i("ChooseAcitvity", name);
					place.setText(name);
					chooesArea.setListObject(name);
					chooesArea.setNameList(chooesArea.getCityNameList());
					Log.i("ChooseAcitvity", chooesArea.getNameList().toString());
					loadList();
					break;
				case 1:
					chooesArea.setPage(2);
					String name1=(chooesArea.getCityNameList()).get(param);
					place.setText(name1);
					chooesArea.setListObject(name1);
					chooesArea.setNameList(chooesArea.getCountyNameList());
					loadList();
					break;
				case 2:
					chooesArea.saveMyWeatherPlace(param);
				default:
					break;
				}
			}
			
		}
	
	
	private void setBack(int param){
		
		switch (param) {
		case 0:
			break;
		case 1:
			place.setText(R.string.china);
			chooesArea.setListObject("");
			chooesArea.setNameList(chooesArea.getProvinceNameList());
			loadList();
			break;
		case 2:
			chooesArea.setPage(1);
			Log.i("ChooseAcitvity", "listener");
			String name=(chooesArea.getProvinceNameList()).get(lastNub);
			Log.i("ChooseAcitvity", name);
			place.setText(name);
			chooesArea.setListObject(name);
			chooesArea.setNameList(chooesArea.getCityNameList());
			Log.i("ChooseAcitvity", chooesArea.getNameList().toString());
			loadList();
			break;
		default:
			break;
		}
		
		}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		int param=chooesArea.getPage();
		Log.i("ChoosrActivity", "onKeyDown");
		Log.i("ChoosrActivity", event.getDownTime()+"----"+event.getEventTime());
		if(keyCode==KeyEvent.KEYCODE_BACK){
		// TODO Auto-generated method stub
//			setBack(param);
			Log.i("ChoosrActivity", keyCode+"");
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		int param=chooesArea.getPage();
		Log.i("ChoosrActivity", "onKeyDown");
		Log.i("ChoosrActivity", event.getDownTime()+"----"+event.getEventTime());
		if(keyCode==KeyEvent.KEYCODE_BACK){
		// TODO Auto-generated method stub
			setBack(param);
		}
		return super.onKeyUp(keyCode, event);
	}
}
