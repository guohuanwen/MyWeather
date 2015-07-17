package com.bcgtgjyb.myweather.tool;

import java.util.List;

import com.bcgtgjyb.myweather.model.BackgroundId;
import com.bcgtgjyb.myweather.model.BigButton;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.model.SmallButton;
import com.bcgtgjyb.myweather.model.TextID;
import com.bcgtgjyb.myweather.presenter.TestPresenter;
import com.bcgtgjyb.myweather.view.Test;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Initialise {
	private Context context=new MyApplication().getContext();
	private MyAnimation animation;
	public void copyDB(){
		AssetsDatabaseManager.initManager(context);
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
		SQLiteDatabase db1 = mg.getDatabase("city");
		SQLiteDatabase db2 = mg.getDatabase("My_weather");
		mg.getDatabase("weather");
		mg.getDatabase("user");
//		db1.execSQL("insert into tb([ID],[content]) values(null, 'db1');");
	}
	
	
	
	
	
	
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
			return true;
		}

	}

}
