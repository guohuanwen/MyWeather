package com.bcgtgjyb.myweather.tool;

import com.bcgtgjyb.myweather.R;

public class WeatherPhoto {
	private MyPatten myPatten;
	public WeatherPhoto() {
		myPatten=new MyPatten();
	}
	public int getDayPhotoId(String photoName){
		int a=myPatten.getMath(photoName);
		int res=0;
		switch (a) {
		case 0:
			res=R.drawable.a00;
		case 1:
			res=R.drawable.a01;
		case 2:
			res=R.drawable.a02;
		case 3:
			res=R.drawable.a03;
		case 4:
			res=R.drawable.a04;
		case 5:
			res=R.drawable.a05;
		case 6:
			res=R.drawable.a06;
		case 7:
			res=R.drawable.a07;
		case 8:
			res=R.drawable.a08;
		case 9:
			res=R.drawable.a09;
		case 10:
			res=R.drawable.a10;
		case 11:
			res=R.drawable.a11;
		case 12:
			res=R.drawable.a12;
		case 13:
			res=R.drawable.a13;
		case 14:
			res=R.drawable.a14;
		case 15:
			res=R.drawable.a15;
		case 16:
			res=R.drawable.a16;
		case 17:
			res=R.drawable.a17;
		case 18:
			res=R.drawable.a18;
		case 19:
			res=R.drawable.a19;
		case 20:
			res=R.drawable.a20;
		case 21:
			res=R.drawable.a21;
			
		case 22:
			res=R.drawable.a22;
		case 23:
			res=R.drawable.a23;
		case 24:
			res=R.drawable.a24;
		case 25:
			res=R.drawable.a25;
		case 26:
			res=R.drawable.a26;
		case 27:
			res=R.drawable.a27;
		case 28:
			res=R.drawable.a28;
		case 29:
			res=R.drawable.a29;
		case 30:
			res=R.drawable.a30;
		case 31:
			res=R.drawable.a31;	
		case 53:
			res=R.drawable.a53;	
			break;

		default:
			break;
		}
		
		
		return res;
	}
	
	public int getNightPhotoId(String photoName){
		int a=myPatten.getMath(photoName);
		int res=0;
		switch (a) {
		case 0:
			res=R.drawable.d00;
		case 1:
			res=R.drawable.d01;
		case 2:
			res=R.drawable.d02;
		case 3:
			res=R.drawable.d03;
		case 4:
			res=R.drawable.d04;
		case 5:
			res=R.drawable.d05;
		case 6:
			res=R.drawable.d06;
		case 7:
			res=R.drawable.d07;
		case 8:
			res=R.drawable.d08;
		case 9:
			res=R.drawable.d09;
		case 10:
			res=R.drawable.d10;
		case 11:
			res=R.drawable.d11;
		case 12:
			res=R.drawable.d12;
		case 13:
			res=R.drawable.d13;
		case 14:
			res=R.drawable.d14;
		case 15:
			res=R.drawable.d15;
		case 16:
			res=R.drawable.d16;
		case 17:
			res=R.drawable.d17;
		case 18:
			res=R.drawable.d18;
		case 19:
			res=R.drawable.d19;
		case 20:
			res=R.drawable.d20;
		case 21:
			res=R.drawable.d21;
			
		case 22:
			res=R.drawable.d22;
		case 23:
			res=R.drawable.d23;
		case 24:
			res=R.drawable.d24;
		case 25:
			res=R.drawable.d25;
		case 26:
			res=R.drawable.d26;
		case 27:
			res=R.drawable.d27;
		case 28:
			res=R.drawable.d28;
		case 29:
			res=R.drawable.d29;
		case 30:
			res=R.drawable.d30;
		case 31:
			res=R.drawable.d31;	
		case 53:
			res=R.drawable.d53;	
			break;

		default:
			break;
		}
		
		
		return res;
	}
}
