package com.bcgtgjyb.myweather.tool;



import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

public class MyLocation {
	
	private int errorCode=0;
	private String province;
	private String city;
	private LocationManagerProxy mLocationManagerProxy;
	private Context context;

	public MyLocation() {
		// TODO Auto-generated constructor stub
	}

	public MyLocation(Context context) {
		this.context = context;
	}

	
	/**
	 * 获取响应码
	 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}
/**
 * 获取所在省份
 * @return
 */
	public String getProvince() {
		return province;
	}
/**
 * 获取所在城市
 * @return
 */
	public String getCity() {
		return city;
	}
	/**
	 * 初始化定位
	 */
	public void init(final LocationCallBack locationCallBack) {
		Log.i("MyLocation", "init");
		// 初始化定位，只采用网络定位
		mLocationManagerProxy = LocationManagerProxy.getInstance(context);
		mLocationManagerProxy.setGpsEnable(false);
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用destroy()方法
		// 其中如果间隔时间为-1，则定位只定一次,
		// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, -1, 15,
				new AMapLocationListener() {

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub

					}

					public void onLocationChanged(MyLocation location) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLocationChanged(AMapLocation amapLocation) {
						Log.i("MyLocation", "onLocationChanged");
						if (amapLocation != null
								&& amapLocation.getAMapException()
										.getErrorCode() == 0) {
							// 定位成功回调信息，设置相关消息
							// SimpleDateFormat df = new
							// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							// Date date = new Date(amapLocation.getTime());
							if (amapLocation.getProvince() == null) {
								province = "";
								errorCode = 100;
							} else {
								province = amapLocation.getProvince();
								city = amapLocation.getCity();
								locationCallBack.onBack(amapLocation.getProvince(), amapLocation.getCity());
								Log.i("MyLocation", "onLocationChanged"+"**"+province);
								errorCode = 200;
							}
						} else {
							errorCode = 101;
							Log.e("AmapErr", "Location ERR:"
									+ amapLocation.getAMapException()
											.getErrorCode());
						}

					}

					@Override
					public void onLocationChanged(
							android.location.Location location) {
						// TODO Auto-generated method stub

					}
				});
	}
	
	
	public  void closeLocation() {
		// 移除定位请求
		mLocationManagerProxy.removeUpdates(new AMapLocationListener(){

			@Override
			public void onLocationChanged(android.location.Location location) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLocationChanged(AMapLocation arg0) {
				// TODO Auto-generated method stub
				
			}});
		// 销毁定位
		mLocationManagerProxy.destroy();
	}

	
}
