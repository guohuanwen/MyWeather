package com.bcgtgjyb.myweather.model;


public class BigButton {
	private int id;
	private int x;
	private int y;
	private int width;
	private int height;
	private int text;
	private int picture;
	public int getId(){
		return id;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getText(){
		return text;
	}
	public int getPicture(){
		return picture;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setWidth(int width){
		this.width=width;
	}
	public void setHeight(int height){
		this.height=height;
	}
	public void setText(int text){
		this.text=text;
	}
	public void setPicture(int picture){
		this.picture=picture;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
}
