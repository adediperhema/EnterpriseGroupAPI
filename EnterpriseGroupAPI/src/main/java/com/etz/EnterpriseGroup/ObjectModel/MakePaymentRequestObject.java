package com.etz.EnterpriseGroup.ObjectModel;

public class MakePaymentRequestObject {

	String partner_id;
	String partner_secret;
	String payment_code;
	String requestor;
	Double amount;
	String payee;
	String branch;
    
	
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

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

	public String getPayment_code() {
		return payment_code;
	}

	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}