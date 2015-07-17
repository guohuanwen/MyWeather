package com.bcgtgjyb.myweather.presenter;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.R.integer;
import com.bcgtgjyb.myweather.model.BackgroundId;
import com.bcgtgjyb.myweather.model.BigButton;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.model.MyWeatherCITYDB;
import com.bcgtgjyb.myweather.model.RespDB;
import com.bcgtgjyb.myweather.model.SmallButton;
import com.bcgtgjyb.myweather.model.TextID;
import com.bcgtgjyb.myweather.model.Weather;
import com.bcgtgjyb.myweather.tool.MyAnimation;
import com.bcgtgjyb.myweather.tool.FourTextAdapter;
import com.bcgtgjyb.myweather.tool.MyButton;
import com.bcgtgjyb.myweather.util.NetPresenter;
import com.bcgtgjyb.myweather.view.Test;
import com.fourmob.colorpicker.ColorPickerDialog;
import com.fourmob.colorpicker.ColorPickerSwatch.OnColorSelectedListener;

public class TestPresenter {
	private RespDB respDB;
	private MyWeatherCITYDB myWeatherCITYDB;
	private MyAnimation animation;
	private MyButton touchButton;
	private boolean disPlayer = true;
	private PopupWindow popupWindow;
	private long upTime = 0;
	private long downTime = 0;
	private UserDB buttonDB;
	private Context context;
	private int x;
	private int y;
	private RelativeLayout relativeLayout;
	private static FragmentManager fragmentManager;
	private Handler myHandler;
	private int net1;
	private int net2;
	private FourTextAdapter fourTextAdapter;
	private int mySwitch = 1;
	private Button bSwitch;
	private ListView leftDayList;
	
	
	
	public TestPresenter() {

	}

	public TestPresenter(Context context, RelativeLayout relativeLayout,Handler handler,//
			ListView leftDayList,Button bSwitch) {
		this.bSwitch=bSwitch;
		this.leftDayList=leftDayList;
		this.myHandler=handler;
		this.context = context;
		this.relativeLayout = relativeLayout;
		bSwitch.setOnClickListener(new switchListener());
		respDB=RespDB.getInstance(context);
		myWeatherCITYDB=MyWeatherCITYDB.getInstance(context);
		buttonDB = UserDB.getInstance(context);
		animation = new MyAnimation();
		fragmentManager = new android.support.v4.app.FragmentActivity()
				.getSupportFragmentManager();
		
		longPush();
		bSwitch.setOnClickListener(new switchListener());
		getNet();
		
		
		uiIntialise();
	}

	/**
	 * 初始化
	 */
	private void uiIntialise() {
		Log.i("TestPresenter", "uiIntialise");
		UserDB buttonDB = UserDB.getInstance(context);
		List<BigButton> bigList = buttonDB.loadBigButton();
		List<SmallButton> smallList = buttonDB.loadSmallButton();
		Log.i("TestPresenter",
				"uiIntialise" + bigList.size() + "*" + smallList.size());
		int backGround, x, y, width, height, id;
		int text;
		for (int i = 0; i < bigList.size(); i++) {
			id = ((BigButton) bigList.get(i)).getId();
			backGround = ((BigButton) bigList.get(i)).getPicture();
			x = ((BigButton) bigList.get(i)).getX();
			y = ((BigButton) bigList.get(i)).getY();
			width = ((BigButton) bigList.get(i)).getWidth();
			height = ((BigButton) bigList.get(i)).getHeight();
			text = ((BigButton) bigList.get(i)).getText();
			addMyView(context, relativeLayout, backGround, x, y, width, height,
					text, id, 1);
		}
		for (int i = 0; i < smallList.size(); i++) {
			id = ((SmallButton) smallList.get(i)).getId();
			backGround = ((SmallButton) smallList.get(i)).getPicture();
			x = ((SmallButton) smallList.get(i)).getX();
			y = ((SmallButton) smallList.get(i)).getY();
			width = ((SmallButton) smallList.get(i)).getWidth();
			height = ((SmallButton) smallList.get(i)).getHeight();
			text = ((SmallButton) smallList.get(i)).getText();
			addMyView(context, relativeLayout, backGround, x, y, width, height,
					text, id, 0);
		}
		setEveryLeft();
	}

	/**
	 * 添加按钮
	 * 
	 * @param context
	 * @param linear
	 * @param backGround
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @param id
	 * @param i
	 */
	private void addMyView(Context context,
			RelativeLayout linear,//
			int backGround, int x, int y, int width, int height, int text,
			int id, int i) {
		Log.i("TestPresenter", "addMyView");
		RelativeLayout layout = linear;
		MyButton myButton;
		myButton = new MyButton(context);
		myButton.setId(id);
		myButton.setBackgroundResource(new BackgroundId()
				.getBackgroundId(backGround));
		myButton.setX(x);
		myButton.setY(y);
		myButton.setWidth(width);
		myButton.setHeight(height);
		myButton.setText(new TextID().getText(text));
		if (i == 0) {
			myButton.setOnLongClickListener(new smallButtonLongClick());
		} else if (i == 1) {
			myButton.setOnLongClickListener(new bigButtonLongClick());
		}
		linear.addView(myButton);

		animation.setAnimation(myButton);
		myButton.setOnTouchListener(new touch());
	}

	/**
	 * listItem监听
	 */
	class onItemListener implements OnItemClickListener {
		private int i;

		public onItemListener() {

		}

		public onItemListener(int i) {
			this.i = i;
		}

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			popupWindow.dismiss();
			switch (i) {
			case 1:
				Log.i("Test", "Item" + "*" + position);
				// popupWindow.dismiss();
				if (position == 0) {
					String[] preference = { "大", "小" };
					ListView listView = getAListView(preference, 11);
					setMyPreference(listView);
				} else if (position == 1) {
					String[] preference = { "背景色" };
					ListView listView = getAListView(preference, 12);
					setMyPreference(listView);
				}
				break;
			case 2:
				//大按钮
				if (position == 0) {
					String[] param = { "时间", "温度", "运动指数", "风向","天气"};
					ListView listView = getAListView(param, 21);
					setMyPreference(listView);
				} else if (position == 1) {

				}
				break;
			case 3:
				//小按钮
				if (position == 0) {
					String[] param = { "时间", "温度", "运动指数", "风向","天气"};
					ListView listView = getAListView(param, 21);
					setMyPreference(listView);
				} else if (position == 1) {
					String[] param = { "蓝", "红", "粉", "紫", "黄" };
					ListView listView = getAListView(param, 14);
					setMyPreference(listView);
				} else if (position == 2) {
					
				}
				break;
			case 11:// 添加大 小按钮
				if (position == 0) {
					// 大
					String[] param = { "蓝", "红", "粉", "紫", "黄" };
					ListView listView = getAListView(param, 13);
					setMyPreference(listView);
				} else if (position == 1) {
					// 小
					addButton(0, -1, 0);
				}
				break;
			case 13:
				addButton(1, position, position);
			case 12:
				Message message=new Message();
				message.what=0;
				myHandler.sendMessage(message);
				break;
			case 14:
				touchButton.setBackgroundResource(new BackgroundId().getBackgroundId(position));
				break;
			case 21:
				touchButton.setTextId(position);
				touchButton.setText(new TextID().getText(position));
				break;
			default:
				break;
			}
		}

	}

	/**
	 * 小按钮的长按监听
	 */
	public class smallButtonLongClick implements OnLongClickListener {

		@Override
		public boolean onLongClick(View v) {
			touchButton = (MyButton) v;
			String[] list = { "更改内容", "删除按钮" };
			ListView listView = getAListView(list, 2);
			setMyPreference(listView);
			return false;
		}
	}

	/**
	 * 大按钮的长按监听
	 */
	public class bigButtonLongClick implements OnLongClickListener {

		@Override
		public boolean onLongClick(View v) {
			touchButton = (MyButton) v;
			String[] list = { "更改内容", "更换背景", "删除按钮" };
			ListView listView = getAListView(list,3);
			setMyPreference(listView);
			return false;
		}
	}

	/**
	 * 获取按钮的宽高
	 * 
	 * @param button
	 * @return
	 */
	private List getXY(final Button button) {
		List list = new ArrayList<integer>();
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		button.measure(w, h);
		int height = button.getMeasuredHeight();
		int width = button.getMeasuredWidth();
		list.add(width);
		list.add(height);
		Log.i("Test", x + "****" + y);
		return list;
	}

	/**
	 * 设置弹出   位子   内容
	 * 
	 * @param 弹出框的内容
	 */
	private void setMyPreference(ListView view) {
		// ListView view = getAListView(list,1);
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.update();
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);
		popupWindow.showAtLocation(relativeLayout, Gravity.BOTTOM, 0, 0);
		
	}

	/**
	 * 传入字符串，返回一个ListView
	 * 
	 * @param list
	 * @param i
	 * @return
	 */
	private ListView getAListView(String[] list, int i) {
		ListView listView = new ListView(context);
		ArrayAdapter<String> myAdapter = new ArrayAdapter<>(context,
				R.layout.menu_list, R.id.menu_list, list);
		listView.setAdapter(myAdapter);
		listView.setOnItemClickListener(new onItemListener(i));
		return listView;

	}

	/**
	 * 添加一个按钮
	 * 
	 * @param id
	 * @param 背景图片id
	 * @param 内容id
	 */
	private void addButton(int i, int background, int textId) {
		UserDB buttonDB = UserDB.getInstance(context);
		final MyButton myButton;
		myButton = new MyButton(context);
		if (i == 0) {
			myButton.setBackgroundResource(R.drawable.aaa);
			myButton.setBackgroundId(background);
			myButton.setTextId(textId);
			myButton.setText(new TextID().getText(textId));
			myButton.setWidth(100);
			myButton.setHeight(100);
			myButton.setOnLongClickListener(new smallButtonLongClick());
			int small = buttonDB.getSmallNub();
			myButton.setId(small + 1);
			Log.i("Test", "id***" + myButton.getId());
			saveSmallButton(findSmallButton(myButton));
		} else if (i == 1) {
			int small = buttonDB.getBigNub();
			myButton.setId(small + 1);
			myButton.setBackgroundId(background);
			myButton.setBackgroundResource(new BackgroundId()
					.getBackgroundId(background));
			myButton.setTextId(textId);
			myButton.setOnLongClickListener(new bigButtonLongClick());
			myButton.setText(new TextID().getText(textId));
			saveBigButton(findBigButton(myButton));

			// myButton.setWidth(363);
			// myButton.setHeight(363);
		}

		relativeLayout.addView(myButton);
		animation.setAnimation(myButton);
		myButton.setOnTouchListener(new touch());
	}

	/**
	 * 获取小按钮的内容，宽高等数据
	 * 
	 * @param 传入一个按钮
	 * @return
	 */
	private SmallButton findSmallButton(MyButton button) {
		List list = getXY(button);
		Log.i("Test", list.get(0) + "*" + list.get(1));
		SmallButton smallButton = new SmallButton();
		smallButton.setPicture(button.getBackgroundId());
		smallButton.setHeight((int) list.get(1));
		smallButton.setId(button.getId());
		smallButton.setText(button.getTextId());
		smallButton.setWidth((int) list.get(0));
		smallButton.setX((int) button.getX());
		smallButton.setY((int) button.getY());
		Log.i("Test", "findSmallButton" + button.getMeasuredWidth());
		return smallButton;
	}

	/**
	 * 获取大按钮的内容，宽高等数据
	 * 
	 * @param 传入一个按钮
	 * @return
	 */
	private BigButton findBigButton(MyButton button) {
		List list = getXY(button);
		Log.i("Test", list.get(0) + "*" + list.get(1));
		BigButton bigButton = new BigButton();
		bigButton.setPicture(button.getBackgroundId());
		bigButton.setHeight((int) list.get(1));
		bigButton.setId(button.getId());
		bigButton.setText(button.getTextId());
		bigButton.setWidth((int) list.get(0));
		bigButton.setX((int) button.getX());
		bigButton.setY((int) button.getY());
		Log.i("Test", "findBigButton" + button.getMeasuredWidth());
		return bigButton;
	}

	/**
	 * 保存按钮到数据库
	 * 
	 * @param smallButton
	 */
	private void saveSmallButton(SmallButton smallButton) {
		buttonDB.saveSmallButton(smallButton);
	}

	/**
	 * 保存按钮到数据库
	 * 
	 * @param smallButton
	 */
	private void saveBigButton(BigButton bigButton) {
		buttonDB.saveBigButton(bigButton);
	}

	/**
	 * 长按监听
	 * 
	 * @author Administrator
	 * 
	 */
	class longClickListenre implements OnLongClickListener {
		private String[] list;

		public longClickListenre() {
		}

		public longClickListenre(String[] list) {
			this.list = list;
		}

		@Override
		public boolean onLongClick(View v) {
			ListView listView = getAListView(list, 1);
			// listView.getChildAt(0).setBackgroundResource(R.color.beige);
			setMyPreference(listView);
			return false;
		}

	}

	private void longPush() {
		String[] list = { "添加按钮", "属性" };
		relativeLayout.setOnLongClickListener(new longClickListenre(list));
	}

	/**
	 * 触摸设置
	 * 
	 * @author Administrator
	 * 
	 */
	class touch implements OnTouchListener {
		private int _xDelta;
		private int _yDelta;

		public boolean onTouch(View v, MotionEvent event) {
			final int X = (int) event.getRawX();
			final int Y = (int) event.getRawY();
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v
						.getLayoutParams();
				_xDelta = X - lParams.leftMargin;
				_yDelta = Y - lParams.topMargin;
				break;
			case MotionEvent.ACTION_UP:
				// animation=new Animation();
				// animation.setAnimation((Button)v);
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				break;
			case MotionEvent.ACTION_POINTER_UP:
				break;
			case MotionEvent.ACTION_MOVE:
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
						.getLayoutParams();
				layoutParams.leftMargin = X - _xDelta;
				layoutParams.topMargin = Y - _yDelta;
				layoutParams.rightMargin = -250;
				layoutParams.bottomMargin = -250;
				v.setLayoutParams(layoutParams);
				break;
			}
			return false;
		}

	}
	
	private int getNet(){
		new Thread(new Runnable() {
			public void run() {
				NetPresenter netPresenter= new NetPresenter();
				net1=netPresenter.getResp("101010100");
				net2=netPresenter.getWeatherJson("北京");
			}
		}).start();
		return net1+net2;
	}
	
	private void setEveryLeft() {
		List eList = myWeatherCITYDB.loadTodayWeather();
		fourTextAdapter = new FourTextAdapter(context, eList, mySwitch);
		leftDayList.setAdapter(fourTextAdapter);
	}

	class switchListener implements OnClickListener {
		public void onClick(View v) {
			Swith();
		}
	}

	private void Swith() {
		switch (mySwitch) {
		case 1:
			List eList = loadMyWeather();
			fourTextAdapter = new FourTextAdapter(context, eList, 2);
			leftDayList.setAdapter(fourTextAdapter);
			mySwitch = 2;
			break;
		case 2:
			List oList = myWeatherCITYDB.loadTodayWeather();
			fourTextAdapter = new FourTextAdapter(context, oList, 1);
			leftDayList.setAdapter(fourTextAdapter);
			mySwitch = 1;
			break;
		default:
			break;
		}
	}
	public List loadMyWeather() {
		Log.i("RespDB", "loadMyWeather");
		Weather yesterday = respDB.loadYesterday();
		Log.i("RespDB", yesterday.getDate());
		List list = new ArrayList<Weather>();
		list.add(yesterday);
		Log.i("RespDB", respDB.loadWeather().toString());
		for (Object obj : respDB.loadWeather()) {
			list.add((Weather) obj);
		}
		return list;

	}
}
