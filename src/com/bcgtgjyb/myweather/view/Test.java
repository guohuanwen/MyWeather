package com.bcgtgjyb.myweather.view;

import java.util.List;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.R.integer;
import com.bcgtgjyb.myweather.model.BigButton;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.TextID;
import com.bcgtgjyb.myweather.presenter.TestPresenter;
import com.bcgtgjyb.myweather.tool.MyAnimation;
import com.bcgtgjyb.myweather.tool.FourTextAdapter;
import com.bcgtgjyb.myweather.tool.Initialise;
import com.bcgtgjyb.myweather.tool.MyApplication;
import com.bcgtgjyb.myweather.tool.MyButton;
import com.bcgtgjyb.myweather.util.NetPresenter;
import com.bcgtgjyb.myweather.util.Utility;
import com.fourmob.colorpicker.ColorPickerDialog;
import com.fourmob.colorpicker.ColorPickerSwatch.OnColorSelectedListener;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class Test extends FragmentActivity{
	private Handler myHandler;
	private MyAnimation animation;
	private Button buttonLeft;
	private Button btn2;
	private Button btn3;
	private FourTextAdapter fourTextAdapter;
	private ListView leftDayList;
	private RelativeLayout relativeLayout;
	private MyWeatherCITYDB myWeatherCITYDB;
	private RespDB respDB;
	private UserDB buttonDB;
	private PopupWindow popupWindow;
	private Button bSwitch;
	private TestPresenter testPresenter;
	
	private ResideMenu resideMenu;
	private ResideMenuItem itemSettings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		new Initialise().copyDB();
		resideMenu=new ResideMenu(this);
		animation = new MyAnimation();
		buttonDB = UserDB.getInstance(this);
//		leftDayList = (ListView) findViewById(R.id.test_left_list);
		relativeLayout = (RelativeLayout) findViewById(R.id.content_frame);
		myWeatherCITYDB = MyWeatherCITYDB.getInstance(MyApplication
				.getContext());
		respDB=RespDB.getInstance(MyApplication
				.getContext());
//		bSwitch = (com.indris.material.RippleView) findViewById(R.id.test_left_swith);
		
//		animator();
//		longPush();
		analysisJson();

		
		
		//初始化
		setHandler();
		
		testPresenter=new TestPresenter(this, relativeLayout,myHandler,leftDayList,bSwitch);
		
//		new NetPresenter().getNetWeather();
		
		
		
	}
	
	
	private void setResideMenu(){
		
		resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.8f);
        
        //set item
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");
//        itemSettings.setOnClickListener();
        
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
	}
	
	
	public void onClick(View view) {

        if (view == itemSettings){
//            changeFragment(new SettingsFragment());
        }

        resideMenu.closeMenu();
}
	
//	public void setMylist() {
//		MyWeatherCITYDB myWeatherCITYDB = MyWeatherCITYDB.getInstance(this);
//		List list = myWeatherCITYDB.loadCity("wu");
//		Log.i("Test", list.toString());
//		final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.test_drawer_layout);
//		// Button buttonRight=(Button)fin
//		// Button button=(Button)findViewById(R.id.test_btnLeft);
//		ListView listLeft = (ListView) findViewById(R.id.test_left_list);
//		// ListView listRight=(ListView)findViewById(R.id.test_right_drawer);
//		ArrayAdapter<String> adapterLeft = new ArrayAdapter<String>(this,
//				R.layout.list_item, R.id.list_item_text, list);
//		listLeft.setAdapter(adapterLeft);
//		buttonLeft.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				drawerLayout.openDrawer(Gravity.LEFT);
//			}
//		});
//	}

	// json��ݽ���
	public void analysisJson() {
		Utility.AnalysisWeatherJson("");
	}

	private void next(Class param) {
		Intent intent = new Intent(Test.this, param);
		// intent.putExtra("URL",
		// "http://m.weather.com.cn/mweather15d/101200101.shtml");
		startActivity(intent);
	}

//	private void animator() {
//		final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content_frame);
//		DisplayMetrics dm = getResources().getDisplayMetrics();
//		final int screenWidth = dm.widthPixels;
//		final int screenHeight = dm.heightPixels;
//		
//		animation.setAnimation(buttonLeft);
//		animation.setAnimation(btn2);
//		animation.setAnimation(btn3);
//		
//		buttonLeft.setOnTouchListener(new touch());
//		btn2.setOnTouchListener(new touch());
//		btn3.setOnTouchListener(new touch());
//
//	}

	

	
	
	public void myColorPicker(final View view) {
		Log.i("Test", "myColorPicker");
		ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
		Log.i("Test", "myColorPicker");
		colorPickerDialog.initialize(R.string.dialog_title, new int[] {
				Color.CYAN, Color.LTGRAY, Color.BLACK, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.GRAY, Color.YELLOW
//				R.color.Myaliceblue,
//				R.color.Myantiquewhite,
//				R.color.Myaqua,
//				R.color.Myaquamarine,
//				R.color.Myazure,
//				R.color.Mybeige,
//				R.color.Mybisque,
//				R.color.Myblanchedalmond,
//				R.color.Myblue,
//				R.color.Myblueviolet,
//				R.color.Mybrown,
//				R.color.Myburlywood,
//				R.color.Mycadetblue,
//				R.color.Mychartreuse,
//				R.color.Mychocolate,
//				R.color.Mycoral,
//				R.color.Mycornflowerblue,
//				R.color.Mycornsilk,
//				R.color.Mycrimson,
//				R.color.Mycyan,
//				R.color.Mydeeppink,
//				R.color.Mydeepskyblue,
//				R.color.Mydimgray,
//				R.color.Mydodgerblue,
//				R.color.Myfirebrick,
//				R.color.Myfloralwhite,
//				R.color.Myforestgreen,
//				R.color.Myfuchsia,
//				R.color.Mygainsboro,
//				R.color.Myghostwhite,
//				R.color.Mygold,
//				R.color.Mygoldenrod,
//				R.color.Mygray,
//				R.color.Mygreen,
//				R.color.Mygreenyellow,
//				R.color.Mygrey,
//				R.color.Myhoneydew,
//				R.color.Myhotpink,
//				R.color.Myindianred,
//				R.color.Myindigo,
//				R.color.Myivory,
//				R.color.Mykhaki,
//				R.color.Mylavender,
//				R.color.Mylavenderblush,
//				R.color.Mylawngreen,
//				R.color.Mylemonchiffon,
				//R.color.Mylightblue,
				//R.color.Mylightcoral,
				//R.color.Mylightcyan,
				//R.color.Mylightgoldenrodyellow,
				//R.color.Mylightgray,
				//R.color.Mylightgreen,
				
//				R.color.Mylightgrey,
				
				//R.color.Mylightpink,
				//R.color.Mylightsalmon,
				//R.color.Mylightseagreen,
				//R.color.Mylightskyblue,
				
//				R.color.Mylightslategray,
				
				//R.color.Mylightsteelblue,
				//R.color.Mylightyellow,
				
				
				
//				R.color.Mylime,
//				R.color.Mylimegreen,
//				R.color.Mylinen,
//				R.color.Mymagenta,
//				R.color.Mymaroon,
//				R.color.Mymediumaquamarine,
//				R.color.Mymediumblue,
//				R.color.Mymediumorchid,
//				R.color.Mymediumpurple,
//				R.color.Mymediumseagreen
				},
				R.color.Mylightseagreen,3,2);
		Log.i("Test", "myColorPicker");
		colorPickerDialog.setOnColorSelectedListener(new OnColorSelectedListener() {
					@Override
					public void onColorSelected(int color) {
						view.setBackgroundColor(color);
					}
				});
		Log.i("Test", "myColorPicker");
		colorPickerDialog.show(getSupportFragmentManager(), "颜色选择");
		Log.i("Test", "myColorPicker");
	}
	
	
	
	
	private void setHandler(){
		myHandler=new Handler(){
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					myColorPicker(relativeLayout);
					break;

				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
	}
	
	
	
	
	
	
}
