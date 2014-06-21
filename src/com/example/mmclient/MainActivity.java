package com.example.mmclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mmclient.service.AuthenticationService;
import com.example.mmclient.service.GoogleAuthenticationService;

public class MainActivity extends Activity {

	public final static String apiURL = "http://172.19.4.63:8080/test";

	MMLocationService gps;

	private AuthenticationService googleAuthenticationService = new GoogleAuthenticationService();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This method will be called when user will click login button from login
	 * page.
	 * 
	 * @param view
	 */
	public void login(View view) {
		EditText usernameEditText = (EditText) findViewById(R.id.usernameId);
		EditText passwordEditText = (EditText) findViewById(R.id.passwordId);
		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		boolean authenticate = googleAuthenticationService.authenticate(
				username, password);
		if (authenticate) {
			// Authentication is successful, redirecting to submit amount
			// screen.
			Intent intent = new Intent(this, SubmitAmountActivity.class);
			startActivity(intent);
		} else {
			// TODO
			// user authentication failed
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public class CallAPI extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			HttpClient httpClient = AndroidHttpClient
					.newInstance("Money Manager");
			HttpContext localContext = new BasicHttpContext();

			// gps.getLocation();
			String urlWithLatitude = apiURL + "?latitude" + gps.getLatitude()
					+ "&longitude" + gps.getLongitude();
			gps.getLatitude();
			HttpGet httpGet = new HttpGet(urlWithLatitude);
			String text = null;
			try {
				HttpResponse response = httpClient.execute(httpGet,
						localContext);

				HttpEntity entity = response.getEntity();
				text = entity.toString();

			} catch (Exception e) {
				return e.getLocalizedMessage();
			}
			return text;
		}

	}

	// This is the method that is called when the submit button is clicked
	public void hitAPI(View view) {
		new CallAPI().execute();
	}

}
