package com.example.mmclient.service;

import java.math.BigDecimal;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

public class CallAPI extends AsyncTask<Void, Void, String> {

	public final static String apiURL = "https://docs.google.com/forms/d/1ofJB5V1sugFMkdUvaarPo-8uo0j_oBNTZFSWEg_b3QQ/formResponse?";

	private BigDecimal totalAmount = BigDecimal.ZERO;
	private BigDecimal anupamAmount = BigDecimal.ZERO;
	private BigDecimal anuragAmount = BigDecimal.ZERO;
	private BigDecimal arpanaAmount = BigDecimal.ZERO;

	public String name = "Unkown";
	public String expense = "Unkown";
	public String spentBy = "Unkown";

	public CallAPI(BigDecimal totalAmount, BigDecimal anupamAmount,
			BigDecimal anuragAmount, BigDecimal arpanaAmount, String expense,
			String name, String spentBy) {
		this.totalAmount = totalAmount;
		this.anupamAmount = anupamAmount;
		this.anuragAmount = anuragAmount;
		this.arpanaAmount = arpanaAmount;
		this.expense = expense;
		this.name = name;
		this.spentBy = spentBy;
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = AndroidHttpClient.newInstance("Money Manager");
		HttpContext localContext = new BasicHttpContext();

		String amountEntry = "entry_1044836361=" + totalAmount.doubleValue();
		String anupamEntry = "entry.1196913981=" + anupamAmount.doubleValue();
		String anuragEntry = "entry.342210625=" + anuragAmount.doubleValue();
		String arpanaEntry = "entry.86740398=" + arpanaAmount.doubleValue();
		String nameEntry = "entry.895685265=" + name;
		String expenseEntry = "entry_230100034=" + expense;
		String spentByEntry = "entry.1818080019=" + spentBy;

		String urlWithAmount = apiURL + amountEntry + "&" + anupamEntry + "&"
				+ anuragEntry + "&" + arpanaEntry + "&" + nameEntry + "&"
				+ expenseEntry + "&" + spentByEntry;
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
