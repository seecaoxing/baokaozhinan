package com.example.jsons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.JsonSchoolAdapter;
import com.example.candidatesguide.IntroductionActivity;
import com.example.classinfo.SchoolInfo;

public class JsonSchoolInfo extends Thread {

	private String url;
	private List<SchoolInfo> schList = new ArrayList<SchoolInfo>();
	private JsonSchoolAdapter jsonSchoolAdapter;
	private Context context;
	private ProgressDialog progressDialog;
	private ListView listView;
	private Handler handler;
	private List<SchoolInfo> data = new  ArrayList<SchoolInfo>();
	// private List<SchoolInfo> schList = new ArrayList<SchoolInfo>();
	public JsonSchoolInfo(String urlString,
			JsonSchoolAdapter jsonSchoolAdapter, Handler handler,
			ListView listView, Context context, ProgressDialog progressDialog,
			List<SchoolInfo> schoolInfos) {
		this.url = urlString;
		this.jsonSchoolAdapter = jsonSchoolAdapter;
		this.handler = handler;
		this.listView = listView;
		this.context = context;
		this.progressDialog = progressDialog;
	}

	
	@Override
	public void run() {

		try {

			URL httpuUrl = new URL(url);

			HttpURLConnection httpURLConnection = (HttpURLConnection) httpuUrl
					.openConnection();

			httpURLConnection.setReadTimeout(5000);

			httpURLConnection.setRequestMethod("GET");

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream()));

			StringBuffer stringBuffer = new StringBuffer();

			String string;

			while ((string = bufferedReader.readLine()) != null) {
				stringBuffer.append(string);
			}

			System.out.println(stringBuffer.toString());
			if (stringBuffer.toString().equals("[]")) {
				System.out.println("error");
				Message msg = new Message();
				msg.what = 0x12;
				handler.sendMessage(msg);
			}

			data =	parseJson(stringBuffer.toString());
			setSchList(data);
			IntroductionActivity.schoolInfos_list = data;
			SchoolInfo sInfo = new SchoolInfo();
			sInfo = schList.get(0);
			Message message = new Message();
			message.what = 0x11;
			handler.sendMessage(message);
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					jsonSchoolAdapter.setData(data);
					listView.setAdapter(jsonSchoolAdapter);
					handler.sendEmptyMessage(0x15);
				}
			});

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void setSchList(List<SchoolInfo> schList) {
		this.schList = schList;
	}


	public List<SchoolInfo> parseJson(String result) {

		try {
			// JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = new JSONArray(result);
			List<SchoolInfo> schools = new ArrayList<SchoolInfo>();
			// JSONArray schoolaArray = jsonObject.getJSONArray(result);
			for (int i = 0; i < jsonArray.length(); i++) {
				SchoolInfo sInfo = new SchoolInfo();
				JSONObject sJsonObject = jsonArray.getJSONObject(i);
				String address = sJsonObject.getString("address");
				String describe = sJsonObject.getString("describe");
				String name = sJsonObject.getString("name");
				String imageUrl = sJsonObject.getString("picurl");
				sInfo.setSchool_address(address);
				sInfo.setSchool_introduction(describe);
				sInfo.setSchool_name(name);
				sInfo.setSchool_imageurl(imageUrl);
				schools.add(sInfo);
			}

			return schools;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}


	public List<SchoolInfo> getSchList() {
		// TODO Auto-generated method stub
		 return schList;
	}

}
