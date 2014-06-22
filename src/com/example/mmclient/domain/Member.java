/**
 * 
 */
package com.example.mmclient.domain;

/**
 * @author SWAPNIL
 * 
 */
public class Member {
	private String firstName;
	private String lastName;
	private boolean involvedInExpense;

	public Member(String firstName, String lastName, boolean involvedInExpense) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.involvedInExpense = involvedInExpense;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isInvolvedInExpense() {
		return involvedInExpense;
	}

	public void setInvolvedInExpense(boolean involvedInExpense) {
		this.involvedInExpense = involvedInExpense;
	}

}
