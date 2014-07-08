package com.example.mmclient.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mmclient.R;
import com.example.mmclient.adapter.CheckBoxToMemberAdapter;
import com.example.mmclient.domain.Member;
import com.example.mmclient.domain.MemberAndAmount;
import com.example.mmclient.domain.SimpleCalculator;
import com.example.mmclient.service.CallAPI;
import com.example.mmclient.service.GetGoogleAccount;

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
		TextView welcomeTextView = (TextView) findViewById(R.id.welcomeMessageId);
		welcomeTextView.setText("Welcome !");
		String[] groupMembers = getResources().getStringArray(
				R.array.group_members);
		LinearLayout grpMembers = (LinearLayout) findViewById(R.id.grpMembers);
		for (int i = 0; i < groupMembers.length; i++) {
			CheckBox cb = new CheckBox(this);
			cb.setText(groupMembers[i]);
			cb.setTag(groupMembers[i]);
			//cb.setId(i + 10);
			grpMembers.addView(cb);
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
		process(amount);
		amountEditText.setText("");

		TextView amountAddedTextView = (TextView) findViewById(R.id.amountAddedMessageId);
		amountAddedTextView.setText("Added Amount Rs" + amount
				+ " in google sheet");
	}

	/**
	 * Process the given Amount.
	 * 
	 * @param amount
	 */
	private void process(String amount) {
		List<CheckBox> checkBoxs = new ArrayList<CheckBox>();
		LinearLayout grpMembers = (LinearLayout) findViewById(R.id.grpMembers);
		String[] groupMembers = getResources().getStringArray(
				R.array.group_members);
		for (int i = 0; i < groupMembers.length; i++)
		{
			checkBoxs.add((CheckBox)grpMembers.findViewWithTag(groupMembers[i]));
		}

		List<Member> members = CheckBoxToMemberAdapter.toMembers(checkBoxs);
		List<MemberAndAmount> memberAndAmounts = SimpleCalculator.calculate(
				members, new BigDecimal(amount));

		BigDecimal anupamAmount = BigDecimal.ZERO;
		BigDecimal anuragAmount = BigDecimal.ZERO;
		BigDecimal arpanaAmount = BigDecimal.ZERO;
		for (MemberAndAmount memberAndAmount : memberAndAmounts) {
			System.out.println(memberAndAmount.getAmount());
			if (memberAndAmount.getMember().getFirstName()
					.equalsIgnoreCase("anupam")) {
				anupamAmount = memberAndAmount.getAmount();
			}
			if (memberAndAmount.getMember().getFirstName()
					.equalsIgnoreCase("anurag")) {
				anuragAmount = memberAndAmount.getAmount();
			}
			if (memberAndAmount.getMember().getFirstName()
					.equalsIgnoreCase("arpana")) {
				arpanaAmount = memberAndAmount.getAmount();
			}
		}

		TextView expense = (TextView) findViewById(R.id.expenseId);
		String name = new GetGoogleAccount(getSystemService(ACCOUNT_SERVICE))
				.getName();
		Spinner memberDropDown = (Spinner) findViewById(R.id.spendById);
		new CallAPI(new BigDecimal(amount), anupamAmount, anuragAmount,
				arpanaAmount, expense.getText().toString(), name,
				memberDropDown.getSelectedItem().toString()).execute();
		resetUIFields(checkBoxs, expense,
				memberDropDown);
	}

	/**
	 * Reset the UI fields to default.
	 * 
	 * @param anupamCheckBox
	 * @param anuragCheckBox
	 * @param arpanaCheckBox
	 * @param expense
	 * @param spinner
	 */
	private void resetUIFields(List<CheckBox> checkBoxs, TextView expense,
			Spinner memberDropDown) {
		expense.setText("");
		for(int i=0; i<checkBoxs.size();i++)
		{
			checkBoxs.get(i).setChecked(false);
		}
		memberDropDown.setSelection(0);
	}

	@Override
	public void onBackPressed() {
		// DO nothing
	}
}
