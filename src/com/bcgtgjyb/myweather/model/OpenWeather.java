package com.bcgtgjyb.myweather.model;

public class OpenWeather {
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getF0() {
		return f0;
	}
	public void setF0(String f0) {
		this.f0 = f0;
	}
	public int getFa() {
		return fa;
	}
	public void setFa(int fa) {
		this.fa = fa;
	}
	public int getFb() {
		return fb;
	}
	public void setFb(int fb) {
		this.fb = fb;
	}
	public int getFc() {
		return fc;
	}
	public void setFc(int fc) {
		this.fc = fc;
	}
	public int getFd() {
		return fd;
	}
	public void setFd(int fd) {
		this.fd = fd;
	}
	public int getFe() {
		return fe;
	}
	public void setFe(int fe) {
		this.fe = fe;
	}
	public int getFf() {
		return ff;
	}
	public void setFf(int ff) {
		this.ff = ff;
	}
	public int getFg() {
		return fg;
	}
	public void setFg(int fg) {
		this.fg = fg;
	}
	public int getFh() {
		return fh;
	}
	public void setFh(int fh) {
		this.fh = fh;
	}
	public String getFi() {
		return fi;
	}
	public void setFi(String fi) {
		this.fi = fi;
	}
	//f0预报发不时间
	private String f0;
	//fa 白天天气现象编号 01 
	private int fa;
	//fb 晚上天气现象编号 01 
	private int fb;
	//fc 白天天气温度(摄氏度) 11 
	private int fc;
	//fd 晚上天气温度(摄氏度) 0 
	private int fd;
	//fe 白天风向编号 4 
	private int fe;
	//ff 晚上风向编号 4 
	private int ff;
	//fg 白天风力编号 1 
	private int fg;
	//fh 晚上风力编号 0 
	private int fh;
	//fi 日出日落时间(中间用|分割) 06:44|18:21 
	private String fi;
	//时间
	private int day;

}
