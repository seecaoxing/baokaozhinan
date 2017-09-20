package com.example.jsons;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.classinfo.NewsClass;

public class JsonNews {

	private List<NewsClass> list_newsClasses;

	public JsonNews(String resultString) {
		list_newsClasses = parseJson(resultString);
	}

	public List<NewsClass> getList_newsClasses() {
		return list_newsClasses;
	}

	public List<NewsClass> parseJson(String result) {

		try {
			JSONArray jsonArray = new JSONArray(result);
			List<NewsClass> list_news = new ArrayList<NewsClass>();
			NewsClass newsClass = new NewsClass();
			for (int i = 0; i < jsonArray.length(); i++) {
			//	NewsClass newsClass = new NewsClass();//放在里面会浪费内存
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				newsClass.setPicUrl(jsonObject.getString("picUrl"));
				newsClass.setUrl(jsonObject.getString("url"));
				list_news.add(newsClass);
			}
			return list_news;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

}
