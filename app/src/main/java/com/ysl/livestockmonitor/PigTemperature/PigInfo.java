package com.ysl.livestockmonitor.PigTemperature;

public class PigInfo {
	private String time;
	private float temp;

	public PigInfo(String time,float temp){
		this.time = time;
		this.temp = temp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}
}
