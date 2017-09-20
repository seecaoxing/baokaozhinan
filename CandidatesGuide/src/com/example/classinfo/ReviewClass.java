package com.example.classinfo;

import android.R.string;
import cn.bmob.v3.BmobObject;

import com.example.candidatesguide.R.id;
/**
 * ÆÀÂÛµÄÀà
 * @author see
 *
 */
public class ReviewClass extends BmobObject{

	private int id;
	private int flagId;
	//private String objectId;
	private String reviewString;
	private String typeString;
	private String timeString;
	

	/*public String getobjectId() {
		return objectId;
	}

	public void setobjectId(String objectId) {
		this.objectId = objectId;
	}
*/
	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}


	public int getId() {
		return id;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlagId() {
		return flagId;
	}

	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}

	public String getReviewString() {
		return reviewString;
	}

	public void setReviewString(String reviewString) {
		this.reviewString = reviewString;
	}
}
