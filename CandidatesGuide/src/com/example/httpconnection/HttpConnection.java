package com.example.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.integer;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.classinfo.NewsClass;
import com.example.classinfo.findClass;

public class HttpConnection extends Thread {

	private String urlString;
	private String result;
	private String charset;
	private findClass person = new findClass(null, null, null, null, null);

	public HttpConnection(String urlString, String charset) {
		this.urlString = urlString;
		this.charset = charset;
	}

	public HttpConnection(String urlString) {
		this.urlString = urlString;
	}

	public HttpConnection(String urlString, findClass person, String charset) {
		this.person = person;
		this.charset = charset;
		this.urlString = urlString;
	}

	public String getResult() {
		return result;
	}

	@Override
	public void run() {
		System.out.println("进入doGet();");
		doGet();
	}

	private void doGet() {
		try {
			System.out.println("zheng");
			URL httpUrl = new URL(urlString);
			System.out.println("已进入doget()");
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl
					.openConnection();
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("GET");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream(),
							charset));
			final StringBuffer stringBuffer = new StringBuffer();
			String string;
			while ((string = bufferedReader.readLine()) != null) {

				stringBuffer.append(string);

			}
			result = stringBuffer.toString();
			System.out.println(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doPost() {

		try {
			URL httpuUrl = new URL(urlString);
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpuUrl
					.openConnection();
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("POST");
			OutputStream outputStream = httpURLConnection.getOutputStream();
			String content = "point="
					+ Integer.parseInt(person.getScoreString()) + "&type="
					+ person.getSubjectString() + "&province="
					+ person.getAddressString() + "&batch="
					+ person.getBatchString() + "&posion="
					+ Integer.parseInt(person.getPositionString());
			outputStream.write(content.getBytes());
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream()));

			StringBuffer stringBuffer = new StringBuffer();
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				stringBuffer.append(string);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void volleyLoader(Context context, String url) {
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							JSONArray jsonArray = response.getJSONArray("");
							NewsClass newsClass = new NewsClass();
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject obj = jsonArray.getJSONObject(i);
								// newsClass.setUrl(obj.get(""));
							}
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}

				});
		requestQueue.add(jsonObjectRequest);
	}

}
