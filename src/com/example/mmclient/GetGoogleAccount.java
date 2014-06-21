package com.example.mmclient;

import android.accounts.Account;
import android.accounts.AccountManager;

public class GetGoogleAccount {

	Account gmail = null;

	public GetGoogleAccount(AccountManager manager) {
		Account[] list = manager.getAccounts();
		for (Account account : list) {
			if (account.type.equalsIgnoreCase("com.google")) {
				gmail = account;
				break;
			}
		}
	}

	public String getName() {
		return gmail.name;
	}

}
