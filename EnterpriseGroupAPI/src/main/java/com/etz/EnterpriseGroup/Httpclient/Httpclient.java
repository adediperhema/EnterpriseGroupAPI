package com.etz.EnterpriseGroup.Httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Httpclient {

	public static String post(String URL, String jsonData) {
		StringBuilder response = new StringBuilder();
		try {
			URL url = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// con.setDoInput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.writeBytes(jsonData);
				wr.flush();
			}

			int responseCode = con.getResponseCode();

			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return response.toString();
	}
	

	public String post(String URL, String jsonData, String authToken) {
		StringBuilder response = new StringBuilder();
		try {
			URL url = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Content-Type", "application/json");
//		con.setRequestProperty("Authorization", "Bearer " + authToken);
			con.setDoOutput(true);
			// con.setDoInput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.writeBytes(jsonData);
				wr.flush();
			}

			int responseCode = con.getResponseCode();

			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return response.toString();
	}

}