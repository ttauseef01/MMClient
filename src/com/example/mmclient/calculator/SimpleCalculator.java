/**
 * 
 */
package com.example.mmclient.calculator;

import java.math.BigDecimal;
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
				: totalAmount.divide(new BigDecimal(memberAndAmounts.size()));
		for (MemberAndAmount memberAndAmount : memberAndAmounts) {
			memberAndAmount.setAmount(dividedAmount);
		}
		return memberAndAmounts;
	}
}
