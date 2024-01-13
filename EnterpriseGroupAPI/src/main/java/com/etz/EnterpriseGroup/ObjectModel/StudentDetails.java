package com.etz.EnterpriseGroup.ObjectModel;

public class StudentDetails {

	private String Title;
	private String Name;
	private String Qualification;
	private String campusCode;
	private String campusName;
	private String offeringTypeName;
	private String offeringTypeCode;
	private String studentType;
	private String residence;
	private String amountDue;
	

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getQualification() {
		return Qualification;
	}
	public void setQualification(String qualification) {
		Qualification = qualification;
		
	}
	public String getCampusCode() {
		return campusCode;
	}
	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}
	public String getOfferingTypeName() {
		return offeringTypeName;
	}
	public void setOfferingTypeName(String offeringTypeName) {
		this.offeringTypeName = offeringTypeName;
	}
	public String getOfferingTypeCode() {
		return offeringTypeCode;
	}
	public void setOfferingTypeCode(String offeringTypeCode) {
		this.offeringTypeCode = offeringTypeCode;
	}
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}

	
}
