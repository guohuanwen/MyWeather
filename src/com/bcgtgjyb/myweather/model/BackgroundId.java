package com.bcgtgjyb.myweather.model;

import com.bcgtgjyb.myweather.R;

public class BackgroundId {
	public int getBackgroundId(int i){
		int retn = 0;
		switch (i) {
		case -1:
			retn=R.drawable.aaa;
			break;
		case 0:
			retn=R.drawable.qipao;
			break;
		case 1:
			retn=R.drawable.qipao2;
			break;
		case 2:
			retn=R.drawable.qipao3;
			break;
		case 3:
			retn=R.drawable.qipao4;
			break;
		case 4:
			retn=R.drawable.qipao5;
			break;
		default:
			break;
		}
		return retn;
	}
}
