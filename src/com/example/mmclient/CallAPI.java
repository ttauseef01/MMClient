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

	public final static String apiURL = "https://docs.google.com/forms/d/1Mt9GNWFZOOrW9EOCCS7IVWYKFwtg5FzCGqkm-V83kJA/formResponse?";
	public String amount = "0.0";
	public String name = "Unkown";

	public CallAPI(String amount, String name) {
		this.amount = amount;
		this.name = name;
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = AndroidHttpClient.newInstance("Money Manager");
		HttpContext localContext = new BasicHttpContext();

		// gps.getLocation();
		String amountEntry = "entry.1841478281=" + amount;
		String nameEntry = "entry.1271618268=" + name;
		String urlWithAmount = apiURL + amountEntry + "&" + nameEntry;
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
