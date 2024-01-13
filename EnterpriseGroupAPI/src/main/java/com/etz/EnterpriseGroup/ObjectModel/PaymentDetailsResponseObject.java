package com.etz.EnterpriseGroup.ObjectModel;

public class PaymentDetailsResponseObject {

	String account_fund_id;
	String name_of_organization;
	String account_number;
	String collection_account_number;
	String amount_due;
	String amount_paid;
	String payment_for;
	String status;

	public String getAccount_fund_id() {
		return account_fund_id;
	}

	public void setAccount_fund_id(String account_fund_id) {
		this.account_fund_id = account_fund_id;
	}

	public String getName_of_organization() {
		return name_of_organization;
	}

	public void setName_of_organization(String name_of_organization) {
		this.name_of_organization = name_of_organization;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getCollection_account_number() {
		return collection_account_number;
	}

	public void setCollection_account_number(String collection_account_number) {
		this.collection_account_number = collection_account_number;
	}

	public String getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(String amount_due) {
		this.amount_due = amount_due;
	}

	public String getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(String amount_paid) {
		this.amount_paid = amount_paid;
	}

	public String getPayment_for() {
		return payment_for;
	}

	public void setPayment_for(String payment_for) {
		this.payment_for = payment_for;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
