/**
 * 
 */
package com.example.mmclient.calculator;

import java.math.BigDecimal;

/**
 * Holds member and the amount associated.
 * 
 * @author SWAPNIL
 * 
 */
public class MemberAndAmount {
	private Member member;
	private BigDecimal amount = BigDecimal.ZERO;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
