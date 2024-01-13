package com.etz.EnterpriseGroup.ObjectModel;

public class StudentInfoRequestObject {
	private  String bankToken;
	private  String bankSecret;
	private  String studentID;
	private  String paymentType;
	
	public String getBankToken() {
		return bankToken;
	}
	public void setBankToken(String bankToken) {
		this.bankToken = bankToken;
	}
	public String getBankSecret() {
		return bankSecret;
	}
	public void setBankSecret(String bankSecret) {
		this.bankSecret = bankSecret;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	
}
