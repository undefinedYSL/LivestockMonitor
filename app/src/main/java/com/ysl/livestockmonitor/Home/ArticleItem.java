package com.ysl.livestockmonitor.Home;

public class ArticleItem {

	private String title;
	private String synopsis;
	private String date;
	private int imgID;

	private String key;
	private String value;

	public ArticleItem(String synopsis,String date,int imgID){
		this.synopsis = synopsis;
		this.date = date;
		this.imgID = imgID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getImgID() {
		return imgID;
	}

	public void setImgID(int imgID) {
		this.imgID = imgID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
