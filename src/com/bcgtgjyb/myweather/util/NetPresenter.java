package com.bcgtgjyb.myweather.util;

import com.aps.m;
import com.bcgtgjyb.myweather.view.Fragment_main;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class NetPresenter {
private Fragment_main.MyHandler fragment_main_handler;
	
	public NetPresenter() {
		Fragment_main fragment_main=new Fragment_main();
		fragment_main_handler=fragment_main.new MyHandler();
	}
private final String Url1="http://weather.51juzhai.com/data/getHttpUrl?cityName=";//101010100


private final  String Url3="http://weather.51juzhai.com/data/otherWeather?city=";//北京*


private final String Url4="http://wthrcdn.etouch.cn/weather_mini?citykey=";//101010100*


private final String Url5="http://www.baidu.com";

int isCon=0;
public int isConnect(){
	HttpUtil.getHttp(Url5, new HttpCallbackListener() {
		public void onFinish(String response) {
			isCon=1;
		}
		
		@Override
		public void onError(Exception e) {
			isCon=0;
		}
	});
	return isCon;
}
//public void getNetWeather(){
//		HttpUtil.GetNet("http://m.weather.com.cn/mweather15d/101200101.shtml", new HttpCallbackListener() {
//			
//			@Override
//			public void onFinish(String response) {
//				// TODO Auto-generated method stub
//				Log.i("NetPrresenter", response);
//				Utility.AnalysisWeatherHtml(response);
//			}
//			
//			@Override
//			public void onError(Exception e) {
//				// TODO Auto-generated method stub
//				e.printStackTrace();
//			}
//		});
//	}
	
	
	private void sendUIMessage(int i){
		Message msg=new Message();
		msg.what=i;
		fragment_main_handler.sendMessage(msg);
	}
	
	private int getWeatherJsonReturn=0;
	@SuppressWarnings("static-access")
	public int getWeatherJson(String cityName){
		getWeatherJsonReturn=0;
		Log.i("NetPresenter","getWeatherJson--"+cityName);
		HttpUtil.getHttp(Url3+cityName,new HttpCallbackListener() {
			public void onFinish(String response) {
				Utility.AnalysisWeatherJson(response);
				Log.i("NetPresenter", response);
				getWeatherJsonReturn=1;
				
				
			}
			
			@Override
			public void onError(Exception e) {
				Log.i("NetPresenter", e.toString());
				
			}
		});
		
//		AsyncHttpClient client = new AsyncHttpClient();
//		client.get(Url3+cityName, new AsyncHttpResponseHandler() {
//
//		    @Override
//		    public void onStart() {
//		    	Log.i("NetPresenter", "onStart");
//		    }
//
//		    @Override
//		    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//		    	Log.i("NetPresenter", "onSuccess"+new String(response));
//		    	Utility.AnalysisWeatherJson(new String(response));
//				
//				getWeatherJsonReturn=1;
//		    	// called when response HTTP status is "200 OK"
//		    }
//
//		    @Override
//		    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//		       Log.e("NetPresenter", new String(errorResponse));
//		    	// called when response HTTP status is "4XX" (eg. 401, 403, 404)
//		    }
//
//		    @Override
//		    public void onRetry(int retryNo) {
//		        // called when request is retried
//			}
//		});
		return getWeatherJsonReturn;
	}
	
	
	private int getReturn=0;
	public int getResp(String cityId){
		Log.i("NetPresenter","getResp--"+cityId);
		getReturn=0;
		HttpUtil.httpGet2(Url4+cityId, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				getReturn=1;
				new Utility().analysisWeatherJson(response);
			}
			public void onError(Exception e) {
				getReturn=0;
			}
		});
//		new Utility().analysisWeatherXml(HttpUtil.http_post_return_byte(Url4+cityId,null));
//		HttpUtil.getGzipHttp(Url4+cityId, new HttpCallbackListener() {
//			public void onFinish(String response) {
//				
//				Log.i("NetPresenter", response);
//				new Utility().analysisWeatherXml(new InputSource(new StringReader(response)));
//				getReturn=1;
//			}
//			public void onError(Exception e) {
//			}
//		});
		return getReturn;
	}
	
	
	
//	private int iconReturn=0;
//	public int getIcon(String cityId){
//		iconReturn=0;
//		HttpUtil.getHttp(Url5+cityId+".html", new HttpCallbackListener() {
//			
//			@Override
//			public void onFinish(String response) {
//				new Utility().analisisIcon(response);
//				iconReturn=1;
//			}
//			
//			@Override
//			public void onError(Exception e) {
//				iconReturn=0;
//			}
//		});
//		return iconReturn;
//		
//	}
	private int openRe=0;
	public int getOpenWeatherNet(String areaid){
		Log.i("NetPresenter","getOpenWeatherNet--"+areaid);
		//基础天气数据
//		String url1=new OpenWeatherEncrypt().getString(areaid, "forecast_f");
//		HttpUtil.getHttp(url1, new HttpCallbackListener() {
//			public void onFinish(String response) {
////				new Utility().analysisOpenWeather(response);
////				sendUIMessage();
//			}
//			
//			@Override
//			public void onError(Exception e) {
//				Log.i("NetPresenter", e.toString());
//				sendUIMessage(0);
//			}
//		});
		//指数
		String url2=new OpenWeatherEncrypt().getString(areaid, "index_f");
		HttpUtil.getHttp(url2, new HttpCallbackListener() {
			public void onFinish(String response) {
				new Utility().analysisOpenZhishu(response);
				openRe=1;
			}
			
			@Override
			public void onError(Exception e) {
				openRe=0;
				Log.i("NetPresenter", e.toString());
			}
		});
		return openRe;
	}
	
	

}
