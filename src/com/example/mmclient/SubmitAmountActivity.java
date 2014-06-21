package com.example.mmclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
		String username = (String) getIntent().getExtras().get("USERNAME");
		setContentView(R.layout.activity_submit_amount);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit_amount, menu);
		return true;
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
		new CallAPI(amount).execute();

	}

}
