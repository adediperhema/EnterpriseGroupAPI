package com.etz.EnterpriseGroup.ObjectModel;

//import org.json.JSONArray;

public class PaymentTypesObject {

	private static String value;
	private static String title;
	
	public static String getValue() {
		return value;
	}
	public void setValue(String value) {
		PaymentTypesObject.value = value;
	}
	public static String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		PaymentTypesObject.title = title;
	}
}
