package com.example.news;

import com.example.candidatesguide.R;
import com.example.classinfo.NewsClass;
import com.example.httpconnection.HttpNews;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class NewsOne extends Activity{
	private WebView webView;
	private Handler handler = new Handler();
	private String content ="text/html";
	private String charset="UTF-8";
	private NewsClass newsClass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsone);
		newsClass = (NewsClass) getIntent().getSerializableExtra("newsClass");
		webView = (WebView) findViewById(R.id.newsone_web);
		new HttpNews(newsClass.getUrl(), handler, webView,content,charset).start();
	}

}
