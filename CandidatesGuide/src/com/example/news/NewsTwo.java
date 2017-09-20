package com.example.news;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import com.example.candidatesguide.R;
import com.example.classinfo.NewsClass;
import com.example.httpconnection.HttpNews;

public class NewsTwo extends Activity {
	private WebView webView;
	private Handler handler = new Handler();
	private String content = "text/html";
	// private String charset="gb2312";
	private String charset = "UTF-8";
	private NewsClass newsClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newstwo);
		webView = (WebView) findViewById(R.id.newstwo_web);
		newsClass = (NewsClass) getIntent().getSerializableExtra("newsClass");
		new HttpNews(newsClass.getUrl().toString(), handler, webView, content,
				charset).start();
	}

}
