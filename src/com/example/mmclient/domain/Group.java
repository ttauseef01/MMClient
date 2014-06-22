package com.example.mmclient.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private List<Member> members = new ArrayList<Member>();

	public Group(String[] members) {
		for (String member : members) {
			this.members.add(new Member(member, "Unkown", false));
		}
	}

	public List<Member> get() {
		return members;
	}
}
