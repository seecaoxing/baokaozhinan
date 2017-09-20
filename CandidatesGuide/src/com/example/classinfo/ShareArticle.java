package com.example.classinfo;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

import android.widget.ImageView;

public class ShareArticle extends BmobObject implements Serializable {
	private int articleId;
	
	private String articleTitle;

	private String articleImg;

	private String articleIntro;

	private String articleContents;
	
	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}


	public String getArticleTitle() {
		return articleTitle;
	}

	public String getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}

	public String getArticleIntro() {
		return articleIntro;
	}

	public void setArticleIntro(String articleIntro) {
		this.articleIntro = articleIntro;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContents() {
		return articleContents;
	}

	public void setArticleContents(String articleContents) {
		this.articleContents = articleContents;
	}

}
