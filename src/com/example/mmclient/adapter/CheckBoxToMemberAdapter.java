/**
 * 
 */
package com.example.mmclient.adapter;

import java.util.ArrayList;
import java.util.List;

import android.widget.CheckBox;

import com.example.mmclient.domain.Member;

/**
 * @author SWAPNIL
 * 
 */
public class CheckBoxToMemberAdapter {
	public static List<Member> toMembers(List<CheckBox> checkBoxs) {
		List<Member> members = new ArrayList<Member>();
		for (CheckBox checkBox : checkBoxs) {
			members.add(new Member(checkBox.getText().toString(), null,
					checkBox.isChecked()));
		}
		return members;
	}
}
