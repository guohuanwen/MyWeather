package com.bcgtgjyb.myweather.tool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;

public class Test5 {
	private static Context context = MyApplication.getContext();
	
	private Signature[] signatures;

	private StringBuilder builder = new StringBuilder();

	public String Equal() {
		String signatureString = "";
		/** 通过包管理器获得指定包名包含签名的包信息 **/
		PackageInfo packageInfo;
		try {
			packageInfo = MyApplication
					.getContext()
					.getPackageManager()
					.getPackageInfo("com.bcgtgjyb.myweather",
							PackageManager.GET_SIGNATURES);

			/******* 通过返回的包信息获得签名数组 *******/
			signatures = packageInfo.signatures;
			for (Signature signature : signatures) {
				builder.append(signature.toCharsString());
			}
			/************** 得到应用签名 **************/
			signatureString = builder.toString();
			Log.i("md5", signatureString);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signatureString;

	}

	public void getmd() {

	}

	public void getSingInfo() {
		try {
			PackageInfo packageInfo = MyApplication
					.getContext()
					.getPackageManager()
					.getPackageInfo("com.bcgtgjyb.myweather",
							PackageManager.GET_SIGNATURES);
			Signature[] signs = packageInfo.signatures;
			Signature sign = signs[0];
			parseSignature(sign.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseSignature(byte[] signature) {
		try {
			CertificateFactory certFactory = CertificateFactory
					.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certFactory
					.generateCertificate(new ByteArrayInputStream(signature));
			String pubKey = cert.getPublicKey().toString();
			String signNumber = cert.getSerialNumber().toString();
			Log.i("signName", cert.getSigAlgName());
			System.out.println("signName:" + cert.getSigAlgName());
			System.out.println("pubKey:" + pubKey);
			System.out.println("signNumber:" + signNumber);
			System.out.println("subjectDN:" + cert.getSubjectDN().toString());
		} catch (CertificateException e) {
			e.printStackTrace();
		}
	}

	public void getmm() throws NameNotFoundException {
		PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
				"com.bcgtgjyb.myweather", PackageManager.GET_SIGNATURES);
		Signature[] signs = packageInfo.signatures;
		Signature sign = signs[0];
		boolean checkright = false;
		int code = sign.hashCode();
		System.out.println(code + "+++++++");
	}

	/**
	 * 获取文件的md5值
	 * 
	 * @param path
	 *            文件的全路径名称
	 * @return
	 */
	private String getFileMd5(String path) {
		try {
			// 获取一个文件的特征信息，签名信息。
			File file = new File(path);
			// md5
			MessageDigest digest = MessageDigest.getInstance("md5");
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			byte[] result = digest.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				// System.out.println(str);
				if (str.length() == 1) {
					sb.append("0");
				}
				sb.append(str);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	//这个是获取SHA1的方法
	public  boolean getSHA1( ) {
	                //获取包管理器
			PackageManager pm = context.getPackageManager();
	                //获取当前要获取SHA1值的包名，也可以用其他的包名，但需要注意，
	                //在用其他包名的前提是，此方法传递的参数Context应该是对应包的上下文。
			String packageName = context.getPackageName();
	                //返回包括在包中的签名信息
			int flags = PackageManager.GET_SIGNATURES;
			PackageInfo packageInfo = null;
			try {
	                //获得包的所有内容信息类
				packageInfo = pm.getPackageInfo(packageName, flags);
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}
	                //签名信息
			Signature[] signatures = packageInfo.signatures;
			byte[] cert = signatures[0].toByteArray();
	                //将签名转换为字节数组流
			InputStream input = new ByteArrayInputStream(cert);
	                //证书工厂类，这个类实现了出厂合格证算法的功能
			CertificateFactory cf = null;
			try {
				cf = CertificateFactory.getInstance("X509");
			} catch (CertificateException e) {
				e.printStackTrace();
			}
	                //X509证书，X.509是一种非常通用的证书格式
			X509Certificate c = null;
			try {
				c = (X509Certificate) cf.generateCertificate(input);
			} catch (CertificateException e) {
				e.printStackTrace();
			}
			String hexString = null;
			try {
	                        //加密算法的类，这里的参数可以使MD4,MD5等加密算法
				MessageDigest md = MessageDigest.getInstance("SHA1");
	                        //获得公钥
				byte[] publicKey = md.digest(c.getEncoded());
	                        //字节到十六进制的格式转换
				hexString = byte2HexFormatted(publicKey);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (CertificateEncodingException e) {
				e.printStackTrace();
			}
			return hexString.equals(getsha());
		}
	//这里是将获取到得编码进行16进制转换
		private static String byte2HexFormatted(byte[] arr) {
			StringBuilder str = new StringBuilder(arr.length * 2);
			for (int i = 0; i < arr.length; i++) {
				String h = Integer.toHexString(arr[i]);
				int l = h.length();
				if (l == 1)
					h = "0" + h;
				if (l > 2)
					h = h.substring(l - 2, l);
				str.append(h.toUpperCase());
				if (i < (arr.length - 1))
					str.append(':');
			}
			return str.toString();
		}
		
		private native  String getsha();
		static {
			 System.loadLibrary("md");
			 }
		
	 

}
