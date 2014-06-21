package com.example.mmclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.mmclient.R;
import com.example.mmclient.R.id;
import com.example.mmclient.R.layout;
import com.example.mmclient.R.menu;
import com.example.mmclient.service.AuthenticationService;
import com.example.mmclient.service.GoogleAuthenticationService;

public class MainActivity extends Activity {

	// MMLocationService gps;

	private AuthenticationService googleAuthenticationService = new GoogleAuthenticationService();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// gps = new MMLocationService(this);
		// gps.scanLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * This method will be called when user will click login button from login
	 * page.
	 * 
	 * @param view
	 */
	public void login(View view) {
		EditText usernameEditText = (EditText) findViewById(R.id.usernameId);
		String username = usernameEditText.getText().toString();
		boolean authenticate = googleAuthenticationService
				.authenticate(username);
		if (authenticate) {
			// Authentication is successful, redirecting to submit amount
			// screen.
			Intent intent = new Intent(this, SubmitAmountActivity.class);
			intent.putExtra("USERNAME", username);
			startActivity(intent);
		} else {
			// TODO
			// user authentication failed
		}
	}

}
