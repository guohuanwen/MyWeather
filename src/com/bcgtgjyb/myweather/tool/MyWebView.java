package com.bcgtgjyb.myweather.tool;


import com.bcgtgjyb.myweather.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends Activity{
	
	private WebView webView=null;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		Intent intent=getIntent();
		String url=intent.getStringExtra("URL");
		webView=(WebView)findViewById(R.id.webview_web);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url); // 根据传入的参数再去加载新的网页
		return true; // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
		}
		});
		webView.loadUrl(url);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	

}
