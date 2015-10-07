package com.my.shili.bean;

import android.app.Activity;

public class MenuBean<T> {
	private String menuName;
	private String menuCode;
	private int imgRes;
	private Class<? extends Activity> clazz;
	private int index;
	private T otherValue;
	private boolean clickable;

	public MenuBean(String menuName,int imgRes,boolean clickable){
		this.menuName=menuName;
		this.imgRes=imgRes;
		this.clickable=clickable;
	}
	
	public MenuBean(String menuName,int imgRes,boolean clickable,String menuCode){
		this( menuName, imgRes, clickable);
		this.menuCode=menuCode;
	}
	
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz){
		this( menuName, imgRes, clickable);
		this.clazz=clazz;
	}
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz,String menuCode){
		this( menuName, imgRes, clickable,clazz);
		this.menuCode=menuCode;
	}
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz,int index){
		this( menuName, imgRes, clickable,clazz);
		this.index=index;
	}
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz,int index,String menuCode){
		this( menuName, imgRes, clickable,clazz,index);
		this.menuCode=menuCode;
	}
	
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz,int index,String menuCode,T otherValue){
		this( menuName, imgRes, clickable,clazz,index,menuCode);
		this.otherValue=otherValue;
	}
	
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz,int index,T otherValue){
		this( menuName, imgRes, clickable,clazz,index);
		this.otherValue=otherValue;
	}
	public MenuBean(String menuName,int imgRes,boolean clickable,Class<? extends Activity> clazz,T otherValue){
		this( menuName, imgRes, clickable,clazz);
		this.otherValue=otherValue;
	}
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getImgRes() {
		return imgRes;
	}

	public void setImgRes(int imgRes) {
		this.imgRes = imgRes;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends Activity> clazz) {
		this.clazz = clazz;
	}

	public T getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(T otherValue) {
		this.otherValue = otherValue;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}


	public String getMenuCode() {
		return menuCode;
	}


	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
}