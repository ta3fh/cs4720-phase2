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
			buddyMap.put("distance", b.getDistance());
			buddyMap.put("online", true);
			listData.add(buddyMap);
		}
		adapter = new BuddyListAdapter(getActivity(), listData, 0,
				BuddyListAdapter.from, BuddyListAdapter.to);
		setListAdapter(adapter);
	}
}
