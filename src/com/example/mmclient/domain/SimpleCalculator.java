/**
 * 
 */
package com.example.mmclient.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Calculator to calculate and distribute amount.
 * 
 * @author SWAPNIL
 * 
 */
public class SimpleCalculator {
	/**
	 * Calculates and distribute amount among given members.
	 * 
	 * @param members
	 * @param totalAmount
	 * @return
	 */
	public static List<MemberAndAmount> calculate(List<Member> members,
			BigDecimal totalAmount) {
		List<MemberAndAmount> memberAndAmounts = new ArrayList<MemberAndAmount>();
		for (Member member : members) {
			if (member.isInvolvedInExpense()) {
				MemberAndAmount memberAndAmount = new MemberAndAmount();
				memberAndAmount.setMember(member);
				memberAndAmounts.add(memberAndAmount);
			}
		}
		BigDecimal dividedAmount = memberAndAmounts.isEmpty() ? BigDecimal.ZERO
				: totalAmount.divide(new BigDecimal(memberAndAmounts.size()),
						new MathContext(7, RoundingMode.FLOOR));
		for (MemberAndAmount memberAndAmount : memberAndAmounts) {
			memberAndAmount.setAmount(dividedAmount);
		}
		return memberAndAmounts;
	}
}
