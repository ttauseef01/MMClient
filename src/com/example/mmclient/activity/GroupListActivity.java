package com.example.mmclient.activity;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mmclient.R;
import com.example.mmclient.domain.Group;
import com.example.mmclient.domain.Member;

public class GroupListActivity extends ListActivity {
	Group group = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] groupMembers = getResources().getStringArray(
				R.array.group_members);
		group = new Group(groupMembers);

		this.setListAdapter(new GroupMemberAdaptor(this, R.layout.list_view,
				R.id.label, group.get()));

		ListView lv = getListView();

		// TODO use below listener to find details of each member
		// lv.setOnItemClickListener(new OnItemClickListener() {
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		//
		// // When clicked, show a toast with the TextView text
		// Member mem = (Member) parent.getItemAtPosition(position);
		// Toast.makeText(getApplicationContext(),
		// "Clicked on Row: " + mem.getFirstName(),
		// Toast.LENGTH_LONG).show();
		//
		// // Launching new Activity on selecting single List Item
		// Intent i = new Intent(getApplicationContext(),
		// GroupMemberActivity.class);
		// // sending data to new activity
		// i.putExtra("member", mem.getFirstName());
		// startActivity(i);
		//
		// }
		// });
	}

	private class GroupMemberAdaptor extends ArrayAdapter<Member> {

		private List<Member> members = null;

		public GroupMemberAdaptor(GroupListActivity context, int resource,
				int textViewResourceId, List<Member> groupMembers) {
			super(context, resource, textViewResourceId, groupMembers);
			this.members = groupMembers;
		}

		private class ViewHolder {
			TextView code;
			CheckBox name;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));
			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.group_member, null);

				holder = new ViewHolder();
				holder.code = (TextView) convertView.findViewById(R.id.name);
				holder.name = (CheckBox) convertView
						.findViewById(R.id.checkbox);
				convertView.setTag(holder);
				holder.name.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Member member = (Member) cb.getTag();
						Toast.makeText(
								getApplicationContext(),
								"Clicked on Checkbox: " + cb.getText() + " is "
										+ cb.isChecked(), Toast.LENGTH_LONG)
								.show();
						member.setInvolvedInExpense(cb.isSelected());
					}
				});
			}

			Member currentMember = members.get(position);
			holder.code.setText(currentMember.getFirstName() + ":"
					+ currentMember.getLastName());
			holder.name.setText(currentMember.getFirstName());
			holder.name.setChecked(currentMember.isInvolvedInExpense());
			holder.name.setTag(currentMember);
			return convertView;
		}
	}
}
