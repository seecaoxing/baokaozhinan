package com.example.jsons;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.classinfo.OtherArticle;
import com.example.classinfo.ShareArticle;

public class JsonShareArticle {

	private String dataString;
	private List<ShareArticle> shareArticleList = new ArrayList<ShareArticle>();
    private List<OtherArticle> otherArticleList = new ArrayList<OtherArticle>();
    
	public JsonShareArticle(String dataString) {
		this.dataString = dataString;
	}

	public List<ShareArticle> getShareData() {
		return shareArticleList;
	}
    public List<OtherArticle> getShareOther(){
    	return otherArticleList;
    }
	public void toParseData() {
		try {
			JSONObject jsonObject = new JSONObject(dataString);
			
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				ShareArticle shareArticle = new ShareArticle();
				JSONObject jsObject = jsonArray.getJSONObject(i);
				shareArticle.setArticleId(jsObject.getInt("id"));
				shareArticle.setArticleImg(jsObject.getString("picUrl"));
				shareArticle.setArticleTitle(jsObject.getString("title"));
				shareArticle.setArticleIntro(jsObject.getString("introduc"));
				shareArticle.setArticleContents(jsObject.getString("content"));
				shareArticleList.add(shareArticle);
				
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void toParseOther() {
		try {
			JSONObject jsonObject = new JSONObject(dataString);
			
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				OtherArticle otherArticle = new OtherArticle();
				JSONObject jsObject = jsonArray.getJSONObject(i);
				otherArticle.setOtherArticleId(jsObject.getInt("id"));
				otherArticle.setOtherArtivleImg(jsObject.getString("picUrl"));
				otherArticle.setOtherArticleTitle(jsObject.getString("title"));
				otherArticle.setOtherArticleIntro(jsObject.getString("introduc"));
				otherArticle.setOtherArticleConent(jsObject.getString("content"));
				otherArticleList.add(otherArticle);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
