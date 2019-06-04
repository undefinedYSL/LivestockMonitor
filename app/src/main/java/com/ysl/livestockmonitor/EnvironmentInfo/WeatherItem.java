package com.ysl.livestockmonitor.EnvironmentInfo;

public class WeatherItem {

	private int imgID;
	private String info;

	public WeatherItem(int imgID, String info) {
		this.imgID = imgID;
		this.info = info;
	}

	public int getImgID() {
		return imgID;
	}

	public void setImgID(int imgID) {
		this.imgID = imgID;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
