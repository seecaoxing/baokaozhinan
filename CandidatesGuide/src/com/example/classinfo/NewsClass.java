package com.example.classinfo;

import java.io.Serializable;

import android.widget.ImageView;

public class NewsClass implements Serializable{
	private String url;
	private String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
