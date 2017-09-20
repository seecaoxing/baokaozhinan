package com.example.jsons;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.classinfo.SchoolInfoFindShow;

public class JsonFindClass {
	private List<SchoolInfoFindShow> listFindShows;
	
	public JsonFindClass(String resultString) {

	listFindShows =	parse(resultString);
	}

	public List<SchoolInfoFindShow> getResult() {
		return listFindShows;
	}

	public List<SchoolInfoFindShow> parse(String result) {

		try {
			JSONArray jsonArray = new JSONArray(result);
			List<SchoolInfoFindShow> listSchoolInfoFindShows = new ArrayList<SchoolInfoFindShow>();
			for (int i = 0; i < jsonArray.length(); i++) {
				SchoolInfoFindShow schoolInfoFindShow = new SchoolInfoFindShow();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				schoolInfoFindShow.setUniversityString(jsonObject.getString("university"));
				schoolInfoFindShow.setUniversityIdString(jsonObject.getString("universityId"));
				schoolInfoFindShow.setTypeString(jsonObject.getString("type"));
				schoolInfoFindShow.setMinPosionString(jsonObject.getString("minPosion"));
				listSchoolInfoFindShows.add(schoolInfoFindShow);
			}
			return listSchoolInfoFindShows;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	
	
	
	

}
