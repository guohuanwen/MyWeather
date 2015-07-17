package com.bcgtgjyb.myweather.tool;

import java.lang.reflect.Method;
import java.util.List;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.EveryDayWeather;
import com.bcgtgjyb.myweather.model.TodayWeather;
import com.bcgtgjyb.myweather.model.Weather;

import android.R.bool;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FourTextAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater listContainer;
	private List<Object> listItems;
	private int n;
	private boolean mNotifyOnChange = true;
	
	public final class ListItemView{
		public TextView date;
		public TextView sky;
		public TextView tmp;
//		public TextView xingqi;
	} 
	
	public FourTextAdapter(){
		
	}
	public FourTextAdapter(Context context,List<Object> listIten,int n){
		Log.i("MyListAdapter","MyListAdapter");
		this.context=context;
		listContainer=LayoutInflater.from(context);
		this.listItems=listIten;
		this.n=n;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mNotifyOnChange = true;
    }
	
	public void refresh(){
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("MyListAdapter", "getView");
		ListItemView listItemView=null;
		if(convertView==null){
			listItemView=new ListItemView();
			convertView=listContainer.inflate(R.layout.day_list_item, null);
			listItemView.date=(TextView)convertView.findViewById(R.id.day_list_item_date);
			listItemView.tmp=(TextView)convertView.findViewById(R.id.day_list_item_tmp);
			listItemView.sky=(TextView)convertView.findViewById(R.id.day_list_item_sky);
//			listItemView.xingqi=(TextView)convertView.findViewById(R.id.day_list_item_xingqi);
			convertView.setTag(listItemView);
		}else {
			listItemView=(ListItemView)convertView.getTag();
		}
//			Object myList=listItems.get(position);
		String date="";
		String sky="";
		String tmp="";
		String xingqi="";
		switch (n) {
		case 1:
			TodayWeather todayWeather=(TodayWeather)listItems.get(position);
			date=todayWeather.getHour()+"时";
			sky=todayWeather.getSky();
			tmp=todayWeather.getTmp()+"℃";
//			xingqi=
//			wind=todayWeather.getWind();
			break;
		case 2:
			GetNubFormString getNubFormString=new GetNubFormString();
			EveryDayWeather weather=(EveryDayWeather)listItems.get(position);
			date=weather.getDate()+"日";
					
			sky=weather.getSky();
			tmp=getNubFormString.getNumber(weather.getMinTmp())+"°"+"/"+getNubFormString.getNumber(weather.getMaxTmp())+"°";
//			wind=weather.getDay_fx()+weather.getDay_fl();
			break;
		default:
			break;
		}
			listItemView.date.setText(date);
			listItemView.sky.setText(sky);
			listItemView.tmp.setText(tmp);
//			listItemView.xingqi.setText(xingqi);
		return convertView;
	}
	
}

