package com.bcgtgjyb.myweather.tool;

import java.util.List;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.TodayWeather;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater listContainer;
	private List<TodayWeather> listItems;
	
	public final class ListItemView{
		public TextView hour;
		public TextView sky;
		public TextView wind;
	} 
	public MyListAdapter(Context context,List<TodayWeather> listIten){
		Log.i("MyListAdapter","MyListAdapter");
		this.context=context;
		listContainer=LayoutInflater.from(context);
		this.listItems=listIten;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("MyListAdapter", "getView");
		ListItemView listItemView=null;
		if(convertView==null){
			listItemView=new ListItemView();
			convertView=listContainer.inflate(R.layout.hour_list_item, null);
			listItemView.hour=(TextView)convertView.findViewById(R.id.hour_list_item_date);
			listItemView.sky=(TextView)convertView.findViewById(R.id.hour_list_item_sky);
			listItemView.wind=(TextView)convertView.findViewById(R.id.hour_list_item_wind);
			convertView.setTag(listItemView);
		}else {
			listItemView=(ListItemView)convertView.getTag();
		}
			listItemView.hour.setText(listItems.get(position).getHour());
			listItemView.sky.setText(listItems.get(position).getSky());
			listItemView.wind.setText(listItems.get(position).getWind());
		return convertView;
	}
	
}
