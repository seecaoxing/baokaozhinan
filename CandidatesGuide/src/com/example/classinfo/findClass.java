package com.example.classinfo;

import java.io.Serializable;

public class findClass implements Serializable{

	private String addressString;
	private String subjectString;
	private String scoreString;
	private String positionString;
	private String batchString;
	public findClass(String address,String subject, String score,String position,String batch) {

		this.addressString = address;
		this.subjectString = subject;
		this.scoreString = score;
		this.positionString = position;
		this.batchString  = batch;
		
	}	
	
	public String getBatchString() {
		return batchString;
	}

	public void setBatchString(String batchString) {
		this.batchString = batchString;
	}

	public String getAddressString() {
		return addressString;
	}
	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}
	public String getSubjectString() {
		return subjectString;
	}
	public void setSubjectString(String subjectString) {
		this.subjectString = subjectString;
	}
	public String getScoreString() {
		return scoreString;
	}
	public void setScoreString(String scoreString) {
		this.scoreString = scoreString;
	}
	public String getPositionString() {
		return positionString;
	}
	public void setPositionString(String positionString) {
		this.positionString = positionString;
	}

}
