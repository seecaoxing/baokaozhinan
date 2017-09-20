package com.example.classinfo;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class SchoolInfo extends BmobObject implements Serializable{
	
	private String school_NameString;
	
	private String school_AddressString;
	
	private String school_IntroductionString;
	
	private String school_Code;
	private String school_ImageUrlString;
	
	public String getSchool_Code() {
		return school_Code;
	}

	public void setSchool_Code(String school_Code) {
		this.school_Code = school_Code;
	}

	
	public void setSchool_imageurl(String url){
		this.school_ImageUrlString = url;
	} 
	
	public String getSchool_imageurl(){
		return school_ImageUrlString;
	}
	
	public void setSchool_name(String schoolNameString){
		this.school_NameString = schoolNameString;
	}
	
	public String getSchool_name(){
		return school_NameString;
	}
	
	public void setSchool_address(String schoolAddressString){
		this.school_AddressString = schoolAddressString;
	}
	
	public String getSchool_adderss(){
		return school_AddressString;
	}
	
	public void setSchool_introduction(String schoolIntroductionString){
		this.school_IntroductionString =schoolIntroductionString;
	}
	
	public String getSchool_introduction(){
		return school_IntroductionString;
	}

}
