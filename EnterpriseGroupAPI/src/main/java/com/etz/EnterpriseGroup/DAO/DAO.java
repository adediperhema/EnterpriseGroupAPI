package com.etz.EnterpriseGroup.DAO;

//import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.Path;
import org.apache.log4j.Logger;

import com.etz.EnterpriseGroup.Httpclient.DoRequest;
import com.etz.EnterpriseGroup.ObjectModel.MakePaymentRequestObject;
import com.etz.EnterpriseGroup.ObjectModel.MakePaymentResponseObject;
import com.etz.EnterpriseGroup.ObjectModel.PaymentDetailsResponseObject;
import com.etz.EnterpriseGroup.Util.SochitelConfig;
import com.etz.vas.host.TPay;
import com.etz.vas.host.VASNode;
import com.google.gson.Gson;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DAO extends VASNode {

	Logger log;


	private static String url = SochitelConfig.getValue("api_url");
	private static String partner_id = SochitelConfig.getValue("partner_id");
	private static String partner_secret = SochitelConfig.getValue("partner_secret");
    private static String requestor = SochitelConfig.getValue("requestor");


	Document document;
	Path path;

	public DAO() {

		try {
			this.log = configure("ETL");


		} catch (Exception ex)

		{

		}
	}
//	static {
//		try {
//			// this.log = configure("SOCHITELVTU");
//
//			url = SochitelConfig.getValue("test_api_url");
//			partner_id = SochitelConfig.getValue("partner_id");
//			partner_secret = SochitelConfig.getValue("partner_secret");
//			payment_code = SochitelConfig.getValue("payment_code");
//			
//
//		} catch (Exception ex) {
//				ex.printStackTrace();l
//		}
//	}

	public DAO(String xmlString) throws Exception {

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlString));
		this.document = db.parse(is);
		this.document.getDocumentElement().normalize();
	}

	public Logger getLog() {
		return this.log;
	}

	
	public static void main(String[] args) throws Exception { // ////
//		System.out.println(new Gson().toJson(getPaymentDetails("Anne"))); //
//		System.out.println(new Gson().toJson(submitPaymentTransaction("KHYBRLK3GUN8", (float) 50.00, "Mary", "Accra"))); // PaymentDetailsResponseObject
//		paymentDetailsResponse = new PaymentDetailsResponseObject(); //
//		paymentDetailsResponse = getPaymentDetails("KHYBRLK3GUN8"); //
//		System.out.println(new Gson().toJson(paymentDetailsResponse)); //
//		System.out.println(paymentDetailsResponse.getAccount_number()); //
//		System.out.println(url);
	String json = "{\"otherinfo\":\"\", \"account\":\"HWNML4B4\"}";
	System.out.println(new DAO().query(json));
	}
	 

	public String query(String jsonData) {

		Map<String, String> jmap;
	

		jmap = (Map<String, String>) new Gson().fromJson(jsonData, (Class) Map.class);
		String otherInfo = (String) jmap.get("otherinfo");
		String account = (String) jmap.get("account");

	
		String payment_code = account;

		try {
			
			PaymentDetailsResponseObject paymentDetailsResponse = new PaymentDetailsResponseObject();
			paymentDetailsResponse = getPaymentDetails(payment_code);


			if (paymentDetailsResponse.getAccount_number() != null) {
				jmap.put("error", "00");
			
				String output = paymentDetailsResponse.getName_of_organization() + "#"
						+ paymentDetailsResponse.getAccount_number() + "#" + paymentDetailsResponse.getAmount_paid()+"#"+paymentDetailsResponse.getAmount_due();
				jmap.put("fault", output);

				// jmap.put("fault", new Gson().toJson(paymentDetailsResponse));
				jmap.put("otherInfo", "Query Successful");

			} else {
				jmap.put("error", "06");
				jmap.put("fault", "Query failed to get successful status");
				jmap.put("otherInfo", "Query failed");

			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.info((Object) ("URL::" + url));
			jmap.put("error", "99");
			jmap.put("fault", "Could not process query");
			jmap.put("otherInfo", "Exception:: " + e.getMessage());
		}

		// }
		// }
		// else if (otherInfo.startsWith("getVerification") ||
		// otherInfo.startsWith("getOperatorProducts")) {
//		else if (otherInfo.startsWith("getPaymentDetails")) {
////			String[] otherInfoCombination = otherInfo.split("#");
////			String operation = otherInfoCombination[0];
//			if (operation.equalsIgnoreCase("getPaymentDetails")) {
//				// String payment_code = otherInfoCombination[1];
//				// String productType = otherInfoCombination[2];
//
//				try {
//					PaymentDetailsResponseObject paymentDetailsResponse = new PaymentDetailsResponseObject();
//					paymentDetailsResponse = getPaymentDetails(payment_code);
//					//paymentDetailsResponse.getAccount_number()
//
//					if (paymentDetailsResponse.getAccount_number() != null) {
//						jmap.put("error", "00");
//						// jmap.put("fault", new
//						// Gson().toJson(getVerificationResponse.getAccount_number()));
//						jmap.put("fault", new Gson().toJson(paymentDetailsResponse));
//						jmap.put("otherInfo", "Query Successful");
//
//					} else {
//						jmap.put("error", "06");
//						jmap.put("fault", "Query failed to get successful status");
//						jmap.put("otherInfo", "Query failed");
//
//					}
//				 } catch (Exception e) {
//					jmap.put("error", "99");
//					jmap.put("fault", "Could process query");
//					jmap.put("otherInfo", "Exception:: " + e.getMessage());
//				}
//
//			}
//	}
		return new Gson().toJson(jmap);

	}

	public String process(String jsonData) {

		MakePaymentResponseObject paymentResponseObject;

		Map jmap = (Map) new Gson().fromJson(jsonData, Map.class);

		String otherInfo = (String) jmap.get("otherinfo");
		String reference = (String) jmap.get("reference");
		String account = (String) jmap.get("account");
		String subscriber = (String) jmap.get("subscriber");
		double amount = ((Double) jmap.get("amount"));
		String dateStr = (String) jmap.get("date");
		String mac = (String) jmap.get("mac");
		String mobile = (String) jmap.get("mobile");
//      String name = (String) jmap.get("name");
		String vasClient = (String) jmap.get("client");
		String alias = (String) jmap.get("alias");
		String bank = (String) jmap.get("bank");

//        double percent_amount = Double.parseDouble(profit_percent) * amount_with_profit;
//        double amount = amount_with_profit-percent_amount;

		if (account != null) {
//            if (msisdn.contains("#")) {
//
//                String formatted_msisdn;
//
//                if (accountCombined.length > 3) {
//                    formatted_msisdn = parseMsisdn(accountCombined[3], accountCombined[0]);
//                } else {
//                    formatted_msisdn = accountCombined[0];
//                }
//
//              percent_amount = Float.parseFloat(profit_percent) * amount;
//              amount = amount_with_profit-percent_amount;

			float amount_ = (float) amount;
			TPay tpay = new TPay();
//            String formatted_msisdn = parseMsisdn(accountCombined[3], accountCombined[0]);
			try {
				// loanRepaymentResponse = exectransaction(accountCombined[1], formatted_msisdn,
				// String.valueOf(amount), reference, accountCombined[2]);
				paymentResponseObject = submitPaymentTransaction(account, amount_);

				// System.out.println("PAYMENT RESPONSE :: " +
				// loanRepaymentResponse.getOperatorProducts());
				this.log.info((Object) ("PAYMENT RESPONSE:: " + new Gson().toJson(paymentResponseObject)));

				tpay.setAmount(amount);
				tpay.setChannel(getChannelCode(vasClient));
				tpay.setType("BILL");
				tpay.setMobile(mobile);
				tpay.setSubscriber(account);
				tpay.setMerchantCode(alias);
				tpay.setResponseDate(dateStr);
				tpay.setBatchNo(mac);
				tpay.setIssuerCode(bank);
				tpay.setUniqueId(reference);
				tpay.setMerchantId(alias);

				if (paymentResponseObject != null && paymentResponseObject.getStatus().equals("200")) {
					
					tpay.setProcessStatus("1");
					tpay.setStatus("00");
					tpay.setNote("Payment successfully completed");

					jmap.put("error", "00");
					jmap.put("fault", new Gson().toJson(paymentResponseObject));
					jmap.put("otherInfo", "Payment Successful");
				} else {
					tpay.setStatus("06");
					tpay.setProcessStatus("0");
					tpay.setNote("Transaction failed");
					jmap.put("error", "06");
					jmap.put("fault", new Gson().toJson(paymentResponseObject));
					jmap.put("otherInfo", "Payment failed");
				}
			} catch (NumberFormatException e) {
				tpay.setStatus("06");
				tpay.setProcessStatus("0");
				tpay.setNote("Transaction failed");
				
				jmap.put("error", "99");
				jmap.put("fault", "Payment couldn't process");
				jmap.put("otherInfo", "Exception:: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				tpay.setStatus("06");
				tpay.setProcessStatus("0");
				tpay.setNote("Transaction failed");
				jmap.put("error", "99");
				jmap.put("fault", "Payment couldn't process");
				jmap.put("otherInfo", "Exception:: " + e.getMessage());
			}
			save(tpay);
		}
		return new Gson().toJson(jmap);
    
	}

	public static PaymentDetailsResponseObject getPaymentDetails(String payment_code) throws Exception {

		PaymentDetailsResponseObject paymentDetailsResponse = new PaymentDetailsResponseObject();

		String payment_details = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:cip.enterprisegroup.net.gh\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n"
				+ "      <urn:getPaymentDetails soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\r\n"
				+ "         <partner_id xsi:type=\"xsd:string\">" + partner_id + "</partner_id>\r\n"
				+ "         <partner_secret xsi:type=\"xsd:string\">" + partner_secret + "</partner_secret>\r\n"
				+ "         <requestor xsi:type=\"xsd:string\">" + requestor + "</requestor>\r\n"
				+ "         <payment_code xsi:type=\"xsd:string\">" + payment_code + "</payment_code>\r\n"
				+ "      </urn:getPaymentDetails>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		if (payment_details != null) {
			// System.out.println("url:"+url);
			String message = DoRequest.sendXMLPost(url, payment_details);

			// System.out.println("GETPAYMENTDETAILS RESPONSE:: " + message);

			String xml = message;

			DAO dom = new DAO(xml);
			String account_fund_id = dom.getElementValue("account_fund_id");
			String name_of_organization = dom.getElementValue("name_of_organization");
			String account_number = dom.getElementValue("account_number");
			String collection_account_number = dom.getElementValue("collection_account_number");
			String amount_due = dom.getElementValue("amount_due");
			String amount_paid = dom.getElementValue("amount_paid");
			String payment_for = dom.getElementValue("payment_for");
			String status = dom.getElementValue("status");
			
			System.out.println(account_fund_id);

			// System.out.println("account_fund_id:"+" "+ account_fund_id);

			if (account_fund_id != null) {
				//ArrayList<String> arrlist = new ArrayList<String>();
				if(!account_fund_id.isEmpty()) {
					paymentDetailsResponse.setAccount_fund_id(account_fund_id);
					paymentDetailsResponse.setName_of_organization(name_of_organization);
					paymentDetailsResponse.setAccount_number(account_number);
					paymentDetailsResponse.setCollection_account_number(collection_account_number);
					paymentDetailsResponse.setAmount_due(amount_due);
					if(!amount_paid.isEmpty()) {
						paymentDetailsResponse.setAmount_paid(amount_paid);
					}else {
						paymentDetailsResponse.setAmount_paid("0.0");
					}
					paymentDetailsResponse.setPayment_for(payment_for);
					paymentDetailsResponse.setStatus(status);
				}
			}
		}
		return paymentDetailsResponse;
	}

	public static MakePaymentResponseObject submitPaymentTransaction(String account, float amount) throws Exception {

		MakePaymentResponseObject paymentResponseObject = new MakePaymentResponseObject();
		MakePaymentRequestObject paymentRequestObject = new MakePaymentRequestObject();
		String payee = "ETRANZACTGH";
		String branch = "Accra";
		
		String paymentRequest = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:cip.enterprisegroup.net.gh\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n"
				+ "      <urn:submitPaymentTransaction soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\r\n"
				+ "         <partner_id xsi:type=\"xsd:string\">" + partner_id + "</partner_id>\r\n"
				+ "         <partner_secret xsi:type=\"xsd:string\">" + partner_secret + "</partner_secret>\r\n"
				+ "         <requestor xsi:type=\"xsd:string\">" + requestor + "</requestor>\r\n"
				+ "         <payment_code xsi:type=\"xsd:string\">" + account + "</payment_code>\r\n"
				+ "         <amount xsi:type=\"xsd:float\">" + amount + "</amount>\r\n"
				+ "         <payee xsi:type=\"xsd:string\">" + payee + "</payee>\r\n"
				+ "         <branch xsi:type=\"xsd:string\">" + branch + "</branch>\r\n"
				+ "      </urn:submitPaymentTransaction>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		if (paymentRequestObject != null) {

			String response = DoRequest.sendXMLPost(url, paymentRequest);

			// System.out.println("GET_PAYMENT_TRANSACTION RESPONSE:: " + response);

			String xml = response;
			DAO dom = new DAO(xml);
			String message = dom.getElementValue("message");
			String status = dom.getElementValue("status");
			// System.out.println("message: " + status);

			if (status.equals("200") && message.equals("Transaction Successfull")) {

				paymentResponseObject.setMessage(message);
				paymentResponseObject.setStatus(status);

				// System.out.println(new Gson().toJson(paymentResponseObject));

			}
		}

		return paymentResponseObject;

	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument        (Document document) {
		this.document = document;
	}

	public NodeList getNodeList(String tag) {
		return this.document.getElementsByTagName(tag);
	}

	public Node getNode(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
		}
		return null;
	}

	public String getNodeValue(Node node) {
		NodeList childNodes = node.getChildNodes();
		for (int x = 0; x < childNodes.getLength(); x++) {
			Node data = childNodes.item(x);
			if (data.getNodeType() == Node.TEXT_NODE) {
				return data.getNodeValue();
			}
		}
		return "";
	}

	public String getNodeValue(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int y = 0; y < childNodes.getLength(); y++) {
					Node data = childNodes.item(y);
					if (data.getNodeType() == Node.TEXT_NODE) {
						return data.getNodeValue();
					}
				}
			}
		}
		return "";
	}

	public String getNodeAttr(String attrName, Node node) {
		NamedNodeMap attrs = node.getAttributes();
		for (int y = 0; y < attrs.getLength(); y++) {
			Node attr = attrs.item(y);
			if (attr.getNodeName().equalsIgnoreCase(attrName)) {
				return attr.getNodeValue();
			}
		}
		return "";
	}

	public String getNodeAttr(String tagName, String attrName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int y = 0; y < childNodes.getLength(); y++) {
					Node data = childNodes.item(y);
					if (data.getNodeType() == Node.ATTRIBUTE_NODE) {
						if (data.getNodeName().equalsIgnoreCase(attrName)) {
							return data.getNodeValue();
						}
					}
				}
			}
		}

		return "";
	}

	public String getElementValue(String tag) {
		return getNodeValue(tag, this.document.getElementsByTagName(tag));
	}

	public static String getChannelCode(final String channel) {
		String response;
		if (channel.equalsIgnoreCase("Payoutlet") || channel.equalsIgnoreCase("webconnectplus")) {
			response = "01";
		} else if (channel.equalsIgnoreCase("MobileGate") || channel.equalsIgnoreCase("Smsinterface")
				|| channel.equalsIgnoreCase("billprocessor")) {
			response = "02";
		} else if (channel.equalsIgnoreCase("POS")) {
			response = "03";
		} else if (channel.equalsIgnoreCase("Console")) {
			response = "05";
		} else if (channel.equalsIgnoreCase("FundGate") || channel.equalsIgnoreCase("FundGatePlus")) {
			response = "09";
		} else {
			response = "99";
		}
		return response;
	}

	public static String errorMatch(int errorCode) {
		String errorDescription = null;

		switch (errorCode) {
		case 1:
			errorDescription = "Unknown error";
			break;
		case 2:
			break;
		case 3:
			errorDescription = "Invalid destination";
			break;
		case 4:
			errorDescription = "Invalid amount";
			break;
		case 6:
			errorDescription = "Operator error";
			break;
		case 53:
			errorDescription = "Invalid product amount";
			break;
		case 57:
			errorDescription = "Invalid product";
			break;
		case 68:
			errorDescription = "Missing parameters";
			break;
		case 70:
			errorDescription = "Operator unreachable";
			break;
		}

		return errorDescription;
	}
	
}