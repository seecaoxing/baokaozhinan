package com.example.jsons;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.classinfo.ReviewClass;

public class JsonReview {

	private String result;
	private List<ReviewClass> reviewclassList = new ArrayList<ReviewClass>();
    private int flagId;
	public JsonReview(String result,int id) {
		this.result = result;
		this.flagId = id;
	}

	public  List<ReviewClass> getData() {
		return reviewclassList;
	}

	public void toParseData() {

		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				ReviewClass reviewClass = new ReviewClass();
				JSONObject jsObject = jsonArray.getJSONObject(i);
				reviewClass.setId(jsObject.getInt("id"));
				reviewClass.setFlagId(jsObject.getInt("itid"));
				reviewClass.setReviewString(jsObject.getString("review"));
				if (flagId==jsObject.getInt("itid")) {
					reviewclassList.add(reviewClass);
				}else{}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toParseOther() {

		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				ReviewClass reviewClass = new ReviewClass();
				JSONObject jsObject = jsonArray.getJSONObject(i);
				reviewClass.setId(jsObject.getInt("id"));
				reviewClass.setFlagId(jsObject.getInt("otherid"));
				reviewClass.setReviewString(jsObject.getString("review"));
				if (flagId==jsObject.getInt("otherid")) {
					reviewclassList.add(reviewClass);
					
				}else{}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
