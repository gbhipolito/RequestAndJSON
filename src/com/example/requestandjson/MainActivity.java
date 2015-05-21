package com.example.requestandjson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private static final String REQUEST_URL = "http://noah-moses.cloudapp.net:8000/getRegions";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new RequestTask().execute();
	}

	
	private class RequestTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
//				sendGet();
//				sendPost();
				sendOther();
			} catch (IOException e) {
				e.printStackTrace();
				Log.e("asdf", "ERROR! " + e.getMessage());
			}
			return null;
		} // doInBackground
		
	} // RequestTask
	
	private void sendGet() throws IOException {
		URL url = new URL(REQUEST_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("GET");
		int code = con.getResponseCode();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputline;
		StringBuilder response = new StringBuilder();
		
		while((inputline = in.readLine()) != null) {
			response.append(inputline);
		}
		in.close();
		
		String strResponse = response.toString();
		Log.e("asdf", "GET Response: " + strResponse);
		Log.e("asdf", "GET code: " + code);
	} // sendGet
	
	private void sendPost() throws IOException {
		URL url = new URL("https://selfsolve.apple.com/wcResults.do");
		String urlParams = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");
		
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(urlParams);
		out.flush();
		out.close();
		
		int code = con.getResponseCode();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputline;
		StringBuilder response = new StringBuilder();
		while( (inputline = in.readLine()) != null ) {
			response.append(inputline);
		}
		in.close();
		
		String strResponse = response.toString();
		
		Log.e("asdf", "POST response: " + strResponse);
		Log.e("asfd", "POST code: " + code);
	} // sendPost
	
	private void sendOther() throws IOException {
		URL url = new URL(REQUEST_URL);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
		String inputline;
		StringBuilder response = new StringBuilder();
		while( (inputline = in.readLine()) != null ) {
			response.append(inputline);
		}
		in.close();
		
		String strResponse = response.toString();
		
		Log.e("asdf", "OTHER response: " + strResponse);
	} // sendOther
} // MainActivity