package com.example.phase2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.example.phase2.user.Buddy;
import com.example.phase2.user.User;

import android.app.ListFragment;

public class BuddyListFragment extends ListFragment {
	private BuddyListAdapter adapter;
	private ArrayList<Buddy> buddies;
	private ArrayList<HashMap<String, Object>> listData;
	
	
	public void populateList(ArrayList<Buddy> buddies) {
		this.buddies = User.sortBuddiesByDistance(buddies);;
		listData = new ArrayList<HashMap<String, Object>>();
		for(Buddy b : this.buddies) {
			HashMap<String, Object> buddyMap = new HashMap<String, Object>();
			buddyMap.put("username", b.getUsername());
			if(b.isOnline()) {
				buddyMap.put("distance", String.format("%.2f", b.getDistance()));
			} else {
				buddyMap.put("distance", "--");
			}
			buddyMap.put("online", b.isOnline());
			listData.add(buddyMap);
		}
		adapter = new BuddyListAdapter(getActivity(), listData, R.layout.buddy_list_row,
				BuddyListAdapter.from, BuddyListAdapter.to);
		setListAdapter(adapter);
	}
	
	public void addTestData() {
		ArrayList<Buddy> testBuddies = new ArrayList<Buddy>();
		testBuddies.add(new Buddy("Jonathan"));
		testBuddies.add(new Buddy("Mary"));
		testBuddies.add(new Buddy("Shauna"));
		testBuddies.add(new Buddy("Alex"));
		testBuddies.add(new Buddy("Elliot"));
		testBuddies.add(new Buddy("Benedict"));
		testBuddies.add(new Buddy("Susan"));
		testBuddies.add(new Buddy("Archibald"));
		testBuddies.add(new Buddy("Elizabeth"));
		testBuddies.add(new Buddy("Christopher"));
		testBuddies.add(new Buddy("Allison"));
		testBuddies.add(new Buddy("Agnes"));
		Random r = new Random(12);
		for(Buddy t : testBuddies) {
			t.setDistance(r.nextDouble() * 100);
			t.setOnline(r.nextBoolean());
		}
		populateList(testBuddies);
	}
}
