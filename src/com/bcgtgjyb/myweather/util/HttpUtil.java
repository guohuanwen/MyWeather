package com.bcgtgjyb.myweather.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import android.util.Log;

public class HttpUtil {
	/**
	 * @get����
	 * @param Url
	 * @param HttpCallbackListener
	 */
	public static void GetNet(final String Url,
			final HttpCallbackListener listener) {
		HttpURLConnection connection = null;
		try {
			Log.i("HttpUtil", "url=" + Url + ";");
			URL myUrl = new URL(Url);
			connection = (HttpURLConnection) myUrl.openConnection();
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("host","weather.51juzhai.com");
			// connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			// connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			// connection.setRequestProperty("Accept-Encoding","gzip,deflate,sdch");
			// connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
			// connection.setRequestProperty("Transfer-Encoding","chunked");
			// connection.setReadTimeout(8000);
			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			StringBuilder response = new StringBuilder();
			while (reader.ready()) {
				// Log.i("HttpUtil","--"+response.toString());
				response.append(reader.readLine() + "\n");
			}
			Log.i("HttpUtil", "NetData=" + response.toString());
			listener.onFinish(response.toString());
		} catch (Exception e) {
			Log.i("HttpUtil", e.toString());
			listener.onError(e);
		}

	}

	/**
	 * 
	 */
	public static void getHttp(final String param,
			final HttpCallbackListener listener) {
		Log.i("HttpUtil", "url=" + param);
		try {
			HttpClient httpClient = new DefaultHttpClient();
			// // 请求超时
			 httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
			 2000);
			// // 读取超时
			 httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
			 2000);

			HttpParams params = httpClient.getParams();

			HttpConnectionParams.setConnectionTimeout(params, 2000);
			HttpConnectionParams.setSoTimeout(params, 2000);

			HttpGet httpGet = new HttpGet(param);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 对象呼叫中止
				Log.i("HttpUtil",httpResponse.getStatusLine().getStatusCode()+"");
				InputStream inputStream = httpResponse.getEntity().getContent();
				InputStreamReader inputRead = new InputStreamReader(
						inputStream, "utf-8");
				BufferedReader bufferReader = new BufferedReader(inputRead);
				String data = "";
				StringBuffer stringBuffer = new StringBuffer();
				while ((data = bufferReader.readLine()) != null) {
					stringBuffer.append(data);
				}
				Log.i("HttpUtil", stringBuffer.toString());
				listener.onFinish(stringBuffer.toString());
			} else {
				httpGet.abort(); 
				listener.onError(new Exception());
			}
		} catch (Exception e) {
			Log.i("HttpUtil", e.toString());
			listener.onError(e);
		}

	}

	/**
	 * 返回gzip的get请求
	 * 
	 * @param url
	 * @return
	 */
	public static void getGzipHttp(final String url,
			final HttpCallbackListener httpCallbackListener) {
		// final String resultString="";
		HttpGet get = new HttpGet(url);
		// get.setHeader("RANGE","bytes="+(startPos + compeleteSize) + "-" +
		// endPos);
		// System.setProperty("http.keepAlive", "false");
		// 在包头中添加gzip格式
		// get.addHeader("(Request-Line)","GET /WeatherApi?citykey=101200401 HTTP/1.1");
		get.addHeader("Accept", "text/html, application/xhtml+xml, */*");
		get.addHeader("Accept-encoding", "gzip, deflate");
		get.addHeader("Accept-Language", "zh-CN");
		get.addHeader("Connection", "Keep-Alive");
		get.addHeader("Host", "wthrcdn.etouch.cn");
		get.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");

		HttpClient client = new DefaultHttpClient();
		Log.i("HttpUtil", url);

		HttpResponse response = null;
		ByteArrayBuffer bt = new ByteArrayBuffer(4096);

		try {
			Log.i("HttpUtil", url);
			response = client.execute(get);

			Log.i("HttpUtil", url);
			// 执行Get方法
			HttpEntity he = response.getEntity();
			Log.i("HttpUtil", url);
			// 以下是解压缩的过程
			GZIPInputStream gis = new GZIPInputStream(he.getContent());
			Log.i("HttpUtil", url);
			int l;
			byte[] tmp = new byte[4096];
			while ((l = gis.read(tmp)) != -1) {
				bt.append(tmp, 0, l);
			}
			String resultString = "";
			resultString = new String(bt.toByteArray(), "utf-8");
			// 后面的参数换成网站的编码一般来说都是UTF-8
			httpCallbackListener.onFinish(resultString);
		} catch (Exception ioe) {
			httpCallbackListener.onError(ioe);
			Log.i("ERR", ioe.toString()); // 抛出处理中的异常
		}

		// return resultString;
	}

	private static DefaultHttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();

		// 设置 连接超时时间
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 5000);
		// 设置 读数据超时时间
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				5000);
		// 设置 字符集
		httpClient.getParams().setParameter("http.protocol.content-charset",
				"UTF_8");
		return httpClient;
	}

	private static HttpPost getHttpPost(String url) {
		HttpPost httpPost = new HttpPost(url);
		// 设置 请求超时时间
		httpPost.getParams()
				.setParameter(HttpConnectionParams.SO_TIMEOUT, 5000);
		httpPost.setHeader("Connection", "Keep-Alive");
		httpPost.addHeader("Accept-Encoding", "gzip");
		return httpPost;
	}

	public static InputStream http_post_return_byte(String url,
			Map<String, String> params) {

		DefaultHttpClient httpclient = null;
		HttpPost post = null;
		HttpResponse response = null;
		StringBuilder sb = null;
		StringEntity stringEntity = null;
		try {
			httpclient = getHttpClient();
			post = getHttpPost(url);
			sb = new StringBuilder();
			if (params != null && !params.isEmpty()) {

				for (Entry<String, String> entry : params.entrySet()) {
					sb.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(),
									HTTP.UTF_8)).append("&");
				}
				sb.deleteCharAt(sb.lastIndexOf("&"));

				stringEntity = new StringEntity(sb.toString());
				stringEntity
						.setContentType("application/x-www-form-urlencoded");
				post.setEntity(stringEntity);
			}

			response = httpclient.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {

			}

			InputStream is = response.getEntity().getContent();

			Header contentEncoding = response
					.getFirstHeader("Content-Encoding");
			if (contentEncoding != null
					&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
				is = new GZIPInputStream(new BufferedInputStream(is));
			}
			Log.i("HttpUtil", is.toString());
			return is;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			/*
			 * if (!post.isAborted()) {
			 * 
			 * post.abort(); } httpclient = null;
			 */

		}
		return null;

	}

	public static void httpGet2(String url2,
			HttpCallbackListener httpCallbackListener) {
		URL url;
		try {
			url = new URL(url2);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(2000);
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				// 将输入流转换成字符串
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				String json = baos.toString();
				baos.close();
				is.close();
				httpCallbackListener.onFinish(json);
			}
		} catch (Exception e) {
			Log.i("HttpUtil", e.toString());
			httpCallbackListener.onError(e);
			e.printStackTrace();
		}
	}

}
