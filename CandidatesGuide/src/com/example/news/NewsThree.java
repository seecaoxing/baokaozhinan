package com.example.news;


import java.net.ContentHandler;

import com.example.candidatesguide.R;
import com.example.classinfo.NewsClass;
import com.example.httpconnection.HttpNews;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class NewsThree extends Activity{
	private Handler handler = new Handler();
	private WebView webView;
	private String content = "text/html";
	//private String charset = "gb2312";
	private String charset="UTF-8";
	private NewsClass newsClass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsthree);
		webView = (WebView) findViewById(R.id.newsthree_web);
		newsClass = (NewsClass) getIntent().getSerializableExtra("newsClass");
		System.out.println("NewsThree"+newsClass.getUrl());
		new HttpNews(newsClass.getUrl().toString(), handler, webView,content,charset).start();
	}

}
