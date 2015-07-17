package com.bcgtgjyb.myweather.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.bcgtgjyb.myweather.R;
import com.bcgtgjyb.myweather.tool.MyApplication;

public class OpenWeatherCode {
	public OpenWeatherCode() {
	}

	/**
	 * 风向编号对应
	 */
	public int getFengXiang(int param) {
		int fengxiang = 0;
		switch (param) {
		case 0:
			fengxiang = R.string.fengxiang0;

			break;
		case 1:
			fengxiang = R.string.fengxiang1;

			break;
		case 2:
			fengxiang = R.string.fengxiang2;

			break;
		case 3:
			fengxiang = R.string.fengxiang3;

			break;
		case 4:
			fengxiang = R.string.fengxiang4;

			break;
		case 5:
			fengxiang = R.string.fengxiang5;

			break;
		case 6:
			fengxiang = R.string.fengxiang6;

			break;
		case 7:
			fengxiang = R.string.fengxiang7;

			break;
		case 8:
			fengxiang = R.string.fengxiang8;

			break;
		case 9:
			fengxiang = R.string.fengxiang9;

			break;

		default:
			break;
		}
		return fengxiang;
	}

	/**
	 * 白天天气现象编号
	 */
	public int[] getDayWeather(int param) {
		int picture = 0;
		int weather = 0;
		int[] re = { picture, weather };
		switch (param) {
		case 0:
			picture = R.drawable.a00;
			weather = R.string.weather0;
			break;
		case 1:
			picture = R.drawable.a01;
			weather = R.string.weather1;
			break;
		case 2:
			picture = R.drawable.a02;
			weather = R.string.weather2;
			break;
		case 3:
			picture = R.drawable.a03;
			weather = R.string.weather3;
			break;
		case 4:
			picture = R.drawable.a04;
			weather = R.string.weather4;
			break;
		case 5:
			picture = R.drawable.a05;
			weather = R.string.weather5;
			break;
		case 6:
			picture = R.drawable.a06;
			weather = R.string.weather6;
			break;
		case 7:
			picture = R.drawable.a07;
			weather = R.string.weather7;
			break;
		case 8:
			picture = R.drawable.a08;
			weather = R.string.weather8;
			break;
		case 9:
			picture = R.drawable.a09;
			weather = R.string.weather9;
			break;
		case 10:
			picture = R.drawable.a10;
			weather = R.string.weather10;
			break;
		case 11:
			picture = R.drawable.a11;
			weather = R.string.weather11;
			break;
		case 12:
			picture = R.drawable.a12;
			weather = R.string.weather12;
			break;
		case 13:
			picture = R.drawable.a13;
			weather = R.string.weather13;
			break;
		case 14:
			picture = R.drawable.a14;
			weather = R.string.weather14;
			break;
		case 15:
			picture = R.drawable.a15;
			weather = R.string.weather15;
			break;
		case 16:
			picture = R.drawable.a16;
			weather = R.string.weather16;
			break;
		case 17:
			picture = R.drawable.a17;
			weather = R.string.weather17;
			break;
		case 18:
			picture = R.drawable.a18;
			weather = R.string.weather18;
			break;
		case 19:
			picture = R.drawable.a19;
			weather = R.string.weather19;
			break;
		case 20:
			picture = R.drawable.a20;
			weather = R.string.weather20;
			break;
		case 21:
			picture = R.drawable.a21;
			weather = R.string.weather21;
			break;
		case 22:
			picture = R.drawable.a22;
			weather = R.string.weather22;
			break;
		case 23:
			picture = R.drawable.a23;
			weather = R.string.weather23;
			break;
		case 24:
			picture = R.drawable.a24;
			weather = R.string.weather24;
			break;
		case 25:
			picture = R.drawable.a25;
			weather = R.string.weather25;
			break;
		case 26:
			picture = R.drawable.a26;
			weather = R.string.weather26;
			break;
		case 27:
			picture = R.drawable.a27;
			weather = R.string.weather27;
			break;
		case 28:
			picture = R.drawable.a28;
			weather = R.string.weather28;
			break;
		case 29:
			picture = R.drawable.a29;
			weather = R.string.weather29;
			break;
		case 30:
			picture = R.drawable.a30;
			weather = R.string.weather30;
			break;
		case 31:
			picture = R.drawable.a31;
			weather = R.string.weather31;
			break;
		case 53:
			picture = R.drawable.a53;
			weather = R.string.weather53;
			break;
		case 99:
			picture = R.drawable.touming;
			weather = R.string.weather99;
			break;
		default:
			break;
		}
		re[0] = picture;
		re[1] = weather;

		return re;

	}

	/**
	 * 夜晚天气编号
	 */
	public int[] getNightWeather(int param) {
		int picture = 0;
		int weather = 0;
		int[] re={picture,weather}; 
		switch (param) {
		case 0:
			picture = R.drawable.d00;
			weather = R.string.weather0;
			break;
		case 1:
			picture = R.drawable.d01;
			weather = R.string.weather1;
			break;
		case 2:
			picture = R.drawable.d02;
			weather = R.string.weather2;
			break;
		case 3:
			picture = R.drawable.d03;
			weather = R.string.weather3;
			break;
		case 4:
			picture = R.drawable.d04;
			weather = R.string.weather4;
			break;
		case 5:
			picture = R.drawable.d05;
			weather = R.string.weather5;
			break;
		case 6:
			picture = R.drawable.d06;
			weather = R.string.weather6;
			break;
		case 7:
			picture = R.drawable.d07;
			weather = R.string.weather7;
			break;
		case 8:
			picture = R.drawable.d08;
			weather = R.string.weather8;
			break;
		case 9:
			picture = R.drawable.d09;
			weather = R.string.weather9;
			break;
		case 10:
			picture = R.drawable.d10;
			weather = R.string.weather10;
			break;
		case 11:
			picture = R.drawable.d11;
			weather = R.string.weather11;
			break;
		case 12:
			picture = R.drawable.d12;
			weather = R.string.weather12;
			break;
		case 13:
			picture = R.drawable.d13;
			weather = R.string.weather13;
			break;
		case 14:
			picture = R.drawable.d14;
			weather = R.string.weather14;
			break;
		case 15:
			picture = R.drawable.d15;
			weather = R.string.weather15;
			break;
		case 16:
			picture = R.drawable.d16;
			weather = R.string.weather16;
			break;
		case 17:
			picture = R.drawable.d17;
			weather = R.string.weather17;
			break;
		case 18:
			picture = R.drawable.d18;
			weather = R.string.weather18;
			break;
		case 19:
			picture = R.drawable.d19;
			weather = R.string.weather19;
			break;
		case 20:
			picture = R.drawable.d20;
			weather = R.string.weather20;
			break;
		case 21:
			picture = R.drawable.d21;
			weather = R.string.weather21;
			break;
		case 22:
			picture = R.drawable.d22;
			weather = R.string.weather22;
			break;
		case 23:
			picture = R.drawable.d23;
			weather = R.string.weather23;
			break;
		case 24:
			picture = R.drawable.d24;
			weather = R.string.weather24;
			break;
		case 25:
			picture = R.drawable.d25;
			weather = R.string.weather25;
			break;
		case 26:
			picture = R.drawable.d26;
			weather = R.string.weather26;
			break;
		case 27:
			picture = R.drawable.d27;
			weather = R.string.weather27;
			break;
		case 28:
			picture = R.drawable.d28;
			weather = R.string.weather28;
			break;
		case 29:
			picture = R.drawable.d29;
			weather = R.string.weather29;
			break;
		case 30:
			picture = R.drawable.d30;
			weather = R.string.weather30;
			break;
		case 31:
			picture = R.drawable.d31;
			weather = R.string.weather30;
			break;
		case 53:
			picture = R.drawable.d53;
			weather = R.string.weather53;
			break;
		case 99:
			picture = R.drawable.touming;
			weather = R.string.weather99;
			break;
		default:
			break;
		}
		re[0]=picture;
		re[1]=weather;
		return re;

	}

	/**
	 * 白天风力编号
	 */
	public int getFengLi(int param) {
		int fengliText=0;
		switch (param) {
		case 0:
			fengliText = R.string.fengli0;
			break;
		case 1:
			fengliText = R.string.fengli1;
			break;
		case 2:
			fengliText = R.string.fengli2;
			break;
		case 3:
			fengliText = R.string.fengli3;
			break;
		case 4:
			fengliText = R.string.fengli4;
			break;
		case 5:
			fengliText = R.string.fengli5;
			break;
		case 6:
			fengliText = R.string.fengli6;
			break;
		case 7:
			fengliText = R.string.fengli7;
			break;
		case 8:
			fengliText = R.string.fengli8;
			break;
		case 9:
			fengliText = R.string.fengli9;
			break;

		default:
			break;
		}
		return fengliText;
	}

}
