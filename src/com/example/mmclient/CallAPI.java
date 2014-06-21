package com.example.mmclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

public class CallAPI extends AsyncTask<Void, Void, String> {

	public final static String apiURL = "https://docs.google.com/forms/d/1Mt9GNWFZOOrW9EOCCS7IVWYKFwtg5FzCGqkm-V83kJA/formResponse?entry.1841478281=";
	public String amount = "0.0";

	public CallAPI(String amount) {
		this.amount = amount;
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = AndroidHttpClient.newInstance("Money Manager");
		HttpContext localContext = new BasicHttpContext();

		// gps.getLocation();
		String urlWithAmount = apiURL + amount;
		HttpGet httpGet = new HttpGet(urlWithAmount);
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();
			text = entity.toString();

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
		return text;
	}

}
