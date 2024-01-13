package com.etz.EnterpriseGroup.ObjectModel;

import java.util.ArrayList;

public class PaymentTypeResponseObject {
 
	PaymentTypesObject paymentTypesObject;
   String message;
  int status;
  ArrayList<String> paymentTypes;
	
	public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
	
	

	public ArrayList<String> getPaymentTypes() {
		return paymentTypes;
	}
	public void setPaymentTypes(ArrayList<String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	public PaymentTypesObject getPaymentTypesObject() {
		return paymentTypesObject;
	}
	public void setPaymentTypesObject(PaymentTypesObject paymentTypesObject) {
		this.paymentTypesObject = paymentTypesObject;
	}
	
	
}
