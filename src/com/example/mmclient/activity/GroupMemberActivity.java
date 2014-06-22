package com.example.mmclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mmclient.R;

public class GroupMemberActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.group_member);

		TextView txtProduct = (TextView) findViewById(R.id.name);

		Intent i = getIntent();
		// getting attached intent data
		String product = i.getStringExtra("member");
		// displaying selected product name
		txtProduct.setText(product);

	}
}
