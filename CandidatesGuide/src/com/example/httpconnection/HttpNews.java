package com.example.httpconnection;

import com.example.candidatesguide.R;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class HttpNews extends Thread{

	private WebView webView;
	private Handler handler;
	private String urlString;
	private String content;
	private String charset;
	private String resultString;
	public HttpNews(String url,Handler handler,WebView webView,String content,String charset){
		this.webView = webView;
		this.handler = handler;
		this.urlString = url;
		this.content = content;
		this.charset = charset;
	}
	@Override
	public void run() {
		System.out.println("��������  webview");
		HttpConnection httpConnection = new HttpConnection(urlString,charset);
		httpConnection.start();
	/*	try {
			httpConnection.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		resultString = httpConnection.getResult();
		handler.post(new Runnable() {
			@SuppressLint("ResourceAsColor") 
			@Override
			public void run() {
				// TODO Auto-generated method stub
                //		//webView.loadData(resultString, content, charset);	
				 webView.getSettings().setSupportZoom(true);//���ſ���
				 webView.getSettings().setBuiltInZoomControls(true);// �����Ƿ������
				 webView.getSettings().setUseWideViewPort(true);//���ô����ԣ�������������š�
				 webView.setInitialScale(0);//��ʼ����ֵ����
				 //webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);//��webview��ʾͼƬ�����������
				 //webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);//������Ӧ��Ļ,�����Զ�����
				 webView.setBackgroundColor(R.color.wheat);
				 //v.loadUrl("about:blank");

				 webView.clearCache(true);
				 webView.getSettings().setDefaultTextEncodingName("utf-8");
				 webView.loadDataWithBaseURL(null,resultString, "text/html", "utf-8",null);
			}
		});
	}
}
