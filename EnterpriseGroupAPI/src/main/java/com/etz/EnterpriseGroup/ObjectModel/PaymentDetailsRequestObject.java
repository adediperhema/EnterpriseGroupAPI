package com.etz.EnterpriseGroup.ObjectModel;

public class PaymentDetailsRequestObject {
	String partner_id;
	String partner_secret;
	String requestor;
	String payment_code;
	

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getPartner_secret() {
		return partner_secret;
	}

	public void setPartner_secret(String partner_secret) {
		this.partner_secret = partner_secret;
	}
	
	public String getRequestor() {
		return requestor;
	}

	

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getPayment_code() {
		return payment_code;
	}

	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}

	
	@Override
	public String toString() {
		return "PaymentDetailsRequestObject [partner_id=" + partner_id + ", partner_secret=" + partner_secret
				+ ", requestor=" + requestor + ", payment_code=" + payment_code + "]";
	}
	

}
