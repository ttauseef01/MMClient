package com.example.mmclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity class for Submit Amount screen.
 * 
 * @author SWAPNIL
 * 
 */
public class SubmitAmountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_amount);
		String username = (String) getIntent().getExtras().get("USERNAME");
		if (null != username) {
			TextView welcomeTextView = (TextView) findViewById(R.id.welcomeMessageId);
			welcomeTextView.setText("Welcome " + username + "!");
		}
	}

	/**
	 * This method will be called when user enters amount and clicks on submit
	 * button.
	 * 
	 * @param view
	 */
	public void submitAmount(View view) {
		EditText amountEditText = (EditText) findViewById(R.id.amountId);
		String amount = amountEditText.getText().toString();
		System.out.println(amount);
		String name = new GetGoogleAccount(getSystemService(ACCOUNT_SERVICE))
				.getName();
		new CallAPI(amount, name).execute();
		amountEditText.setText("");

		TextView amountAddedTextView = (TextView) findViewById(R.id.amountAddedMessageId);
		amountAddedTextView.setText("Added Amount Rs" + amount
				+ " in google sheet");
	}

	@Override
	public void onBackPressed() {
		// DO nothing
	}

}
