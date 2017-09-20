package com.example.classinfo;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class SchoolFind extends BmobObject implements Serializable{
	private String schoolName;
	private Integer lowScore;
	private Integer highScore;
	private Integer lowRanked;
	private Integer highRanked;
	private String studentAddress;
	private String subject;
	private String batch;
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getLowScore() {
		return lowScore;
	}

	public void setLowScore(Integer lowScore) {
		this.lowScore = lowScore;
	}

	public Integer getHighScore() {
		return highScore;
	}

	public void setHighScore(Integer highScore) {
		this.highScore = highScore;
	}

	public Integer getLowRanked() {
		return lowRanked;
	}

	public void setLowRanked(Integer lowRanked) {
		this.lowRanked = lowRanked;
	}

	public Integer getHighRanked() {
		return highRanked;
	}

	public void setHighRanked(Integer highRanked) {
		this.highRanked = highRanked;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

}
