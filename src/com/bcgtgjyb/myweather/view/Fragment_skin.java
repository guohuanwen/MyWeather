package com.bcgtgjyb.myweather.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.model.UserDB;
import com.bcgtgjyb.myweather.tool.ReStart;
import com.bcgtgjyb.myweather.tool.XmlSelect;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Fragment_skin extends Fragment{
	private ListView listView;
	private View view;
	private Context context;
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=inflater.inflate(getXml(), null,false);
		context=getActivity();
		initList();
		return view;
	}
	
	private void initList(){
		listView=(ListView)view.findViewById(R.id.fragment_skin_list);
		ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map1=new HashMap<String,Object>();
		Map<String,Object> map2=new HashMap<String,Object>();
		map1.put("Image", R.drawable.blue);
		list.add(map1);
		
		map2.put("Image", R.drawable.orange);
		list.add(map2);
		
		SimpleAdapter adapter=new SimpleAdapter(context, list, R.layout.skin_list, new String[]{"Image"}, new int[]{R.id.skin_list_ib});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ListListener());
	}

	
	private int getXml() {
		return new XmlSelect().getSkinXml();
	}
	
	
	class ListListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.i("Fragment_skin", ""+position);
			UserDB userDB=UserDB.getInstance(context);
			userDB.setSkin((int)position);
			sendActivityMessage(3);
		}
		
	}
	
	private void sendActivityMessage(int i) {
		android.os.Handler handler = ((StartUI) getActivity()).handler;
		Message message = new Message();
		message.what = i;
		handler.sendMessage(message);
	}
	
	
	
	
	

}
