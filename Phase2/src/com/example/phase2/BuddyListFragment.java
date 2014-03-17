package com.example.phase2;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.phase2.user.Buddy;

import android.app.ListFragment;

public class BuddyListFragment extends ListFragment {
	private BuddyListAdapter adapter;
	private ArrayList<Buddy> buddies;
	private ArrayList<HashMap<String, Object>> listData;
	
	
	public void populateList(ArrayList<Buddy> buddies) {
		this.buddies = buddies;
		listData = new ArrayList<HashMap<String, Object>>();
		for(Buddy b : this.buddies) {
			HashMap<String, Object> buddyMap = new HashMap<String, Object>();
			buddyMap.put("username", b.getUsername());
			buddyMap.put("distance", String.format("%f.2", b.getDistance()));
			buddyMap.put("online", true);
			listData.add(buddyMap);
		}
		adapter = new BuddyListAdapter(getActivity(), listData, R.layout.buddy_list_row,
				BuddyListAdapter.from, BuddyListAdapter.to);
		setListAdapter(adapter);
	}
}
