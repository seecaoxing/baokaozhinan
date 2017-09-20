package com.example.classinfo;

import java.io.Serializable;

import org.apache.http.entity.SerializableEntity;

import cn.bmob.v3.BmobObject;

import android.R.integer;

public class OtherArticle extends BmobObject implements Serializable {
	private int otherArticleId;
	private String otherArticleTitle;
	private String otherArticleConent;
	private String otherArticleIntro;
	private String otherArticleImg;

	public int getOtherArticleId() {
		return otherArticleId;
	}

	public void setOtherArticleId(int otherArticleId) {
		this.otherArticleId = otherArticleId;
	}

	public String getOtherArticleIntro() {
		return otherArticleIntro;
	}

	public void setOtherArticleIntro(String otherArticleIntro) {
		this.otherArticleIntro = otherArticleIntro;
	}

	public String getOtherArtivleImg() {
		return otherArticleImg;
	}

	public void setOtherArtivleImg(String otherArtivleImg) {
		this.otherArticleImg = otherArtivleImg;
	}

	public String getOtherArticleTitle() {
		return otherArticleTitle;
	}

	public void setOtherArticleTitle(String otherArticleTitle) {
		this.otherArticleTitle = otherArticleTitle;
	}

	public String getOtherArticleConent() {
		return otherArticleConent;
	}

	public void setOtherArticleConent(String otherArticleConent) {
		this.otherArticleConent = otherArticleConent;
	}

}
