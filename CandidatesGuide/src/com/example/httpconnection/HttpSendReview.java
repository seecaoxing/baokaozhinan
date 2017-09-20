package com.example.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.integer;

public class HttpSendReview extends Thread {

	private int flagId;
	private String reviewString;
	private String urlString;
	private String typeString;
	private int reviewId;
	private String content;

	public HttpSendReview(String urlString, int flagId, String reviewString,String typeString) {
		this.flagId = flagId;
		this.reviewString = reviewString;
		this.urlString = urlString;
		this.typeString = typeString;
		content = "flagId=" + flagId + "&review=" + reviewString + "&type="	+ typeString;
	}

	public HttpSendReview(String urlString, int reviewId, int flagId,
			String typeString) {
		this.flagId = flagId;
		this.typeString = typeString;
		this.urlString = urlString;
		this.reviewId = reviewId;
		content = "flagId=" + flagId + "&reviewId=" + reviewId + "&type="+ typeString;
	}

	@Override
	public void run() {

		doPost();

	}

	private void doPost() {

		// Properties properties = System.getProperties();
		// properties.list(System.out);
		try {
			// ��������
			URL httpuUrl = new URL(urlString);
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpuUrl
					.openConnection();
			// ������������
			httpURLConnection.setDoOutput(true);// ʹ��URL���ӽ������
			httpURLConnection.setDoInput(true);// ʹ��URL���ӽ�������
			// httpURLConnection.setUseCaches(false);//���Ի���
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("POST");

			OutputStream outputStream = httpURLConnection.getOutputStream();

			byte[] contentBytes = content.getBytes("UTF-8");
			outputStream.write(contentBytes);
			outputStream.close();

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream()));

			StringBuffer stringBuffer = new StringBuffer();
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				stringBuffer.append(string);
			}
			System.out.println("httpsendreview_________________stringbuffer"
					+ stringBuffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
