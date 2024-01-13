package com.etz.adaallianz.process;

import com.etz.adaallianz.om.CallBackResquestObjectModel;
import com.etz.adaallianz.om.CallbackResponseObjectModel;
import com.etz.adaallianz.om.LookupRequestObjectModel;
import com.etz.adaallianz.om.LookupResponseObjectModel;
import com.etz.adaallianz.utils.AllianzConfig;
import com.etz.adaallianz.utils.DoRequest;
import com.google.gson.Gson;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

//java -cp lib\*;cfg\*;. com.etz.adaallianz.process.Allianz
public class Allianz {

    //	public Logger getLog() {
//		return this.log;
//	}
    public static void main(String[] args) throws Exception {

        // System.out.println(getLookup("String bankToken"));
//        System.out.println(getCallback("AHCP1960200", "200", "01", "2021-07-0 02:26:01", "987729938"));
        // System.out.println(json_exmple());
        System.out.println(getLookup("0244229547"));

    }

    public static String getLookup(String msisdn) throws Exception {

        // String msisdn = "0244229547";
        LookupRequestObjectModel lookupRequestObject = new LookupRequestObjectModel();
        LookupResponseObjectModel lookupResponseObject = new LookupResponseObjectModel();

        lookupRequestObject.setMsisdn(msisdn);
        lookupRequestObject.setApiKey(AllianzConfig.getValue("api_key"));

        Gson gson = new Gson();
        String dd = gson.toJson(lookupRequestObject);

        if (dd != null) {
            System.out.println("URL::" + AllianzConfig.getValue("config1.url"));
            String message2 = DoRequest.sendGetData(AllianzConfig.getValue("config1.url"), dd);

            System.out.println("RESPONSE:: " + message2);
            JSONObject mainJson = new JSONObject(message2);
            System.out.println("message::" + message2);

            // JSONObject response = mainJson.getJSONObject("Response");
            String response_code = mainJson.getString("responseCode");
            String response_message = mainJson.getString("responseMessage");
            // String trnsaction_id = response.getString("trnsaction_id");

            // List<LookupResponseObjectModel> response_list = new ArrayList<>();
//            List<LookupResponseObjectModel> responselist = new ArrayList<>();

            if (response_code.equalsIgnoreCase("01") && response_message.equalsIgnoreCase("SUCCESS")) {

                // Getting languages JSON array from the JSON object
                JSONArray jsonArray = mainJson.getJSONArray("data");

                ArrayList<Object> listdata = new ArrayList<>();
                // Checking whether the JSON array has some value or not
                if (jsonArray != null) {

                    // Iterating JSON array
                    for (int i = 0; i < jsonArray.length(); i++) {

                        // Adding each element of JSON array into ArrayList
                        listdata.add(jsonArray.get(i));
                    }
                }

                lookupResponseObject.setData(listdata);
            }
            lookupResponseObject.setResponseCode(response_code);
            lookupResponseObject.setResponseMessage(response_message);
            // responselist.add(listdata);

            System.out.println(new Gson().toJson(lookupResponseObject));
        }

        return new Gson().toJson(lookupResponseObject);
    }

    public static String getCallback(String policy_number, String amount, String responseCode,
                                                          String timestamp, String receipt_no) throws Exception {

        CallBackResquestObjectModel callupRequestObject = new CallBackResquestObjectModel();
        CallbackResponseObjectModel callupResponseObject = new CallbackResponseObjectModel();

        callupRequestObject.setPolicy_number(policy_number);
        callupRequestObject.setAmount(amount);
        callupRequestObject.setApiKey(AllianzConfig.getValue("api_key"));
        callupRequestObject.setResponseCode(responseCode);
        callupRequestObject.setTimestamp(timestamp);
        callupRequestObject.setReceipt_no(receipt_no);

        Gson gson = new Gson();
        String dd = gson.toJson(callupRequestObject);

        if (dd != null) {

            String message2 = DoRequest.sendPost(AllianzConfig.getValue("config2.url"), dd);
            /*
             * String message2 = " {\r\n" + "    \"responseCode\": \"01\",\r\n" +
             * "    \"responseMessage\": \"SUCCESS\",\r\n" +
             * "    \"trnsaction_id\": \"202170003\"\r\n" + "}";
             */
            // List<PaymentTypesObject> paymentTypeList = new ArrayList<>();

            JSONObject mainJson = new JSONObject(message2);

            // JSONObject response = mainJson.getJSONObject("Response");
            String response_code = mainJson.getString("responseCode");

            String response_message = mainJson.getString("responseMessage");
            String transaction_id = mainJson.getString("trnsaction_id");

            if (response_code.equalsIgnoreCase("01") && response_message.equalsIgnoreCase("SUCCESS")) {

                callupResponseObject.setResponseCode(response_code);
                callupResponseObject.setResponseMessage(response_message);
                callupResponseObject.setTransaction_id(transaction_id);
            }

            System.out.println(new Gson().toJson(callupResponseObject));

        }
        return new Gson().toJson(callupResponseObject);

    }

}
