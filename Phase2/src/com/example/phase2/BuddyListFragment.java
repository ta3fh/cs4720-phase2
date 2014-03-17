package com.example.phase2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.example.phase2.user.Buddy;
import com.example.phase2.user.User;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BuddyListFragment extends ListFragment {
	private BuddyListAdapter adapter;
	private ArrayList<Buddy> buddies;
	private ArrayList<HashMap<String, Object>> listData;
	
	public void populateFullList() {
		adapter = formAdapter(this.buddies);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	public void filterListByOnline(boolean on) {
		ArrayList<Buddy> toAddBuddies = new ArrayList<Buddy>();
		for(Buddy b : this.buddies) {
			if(b.isOnline()) {
				if(on) {
					toAddBuddies.add(b);
				}
			} else if(!on) {
				toAddBuddies.add(b);
			}
		}
		adapter = formAdapter(toAddBuddies);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	public void setBuddies(ArrayList<Buddy> buddies) {
		if(buddies.size() == 0) {
			setEmptyText();
		}
		this.buddies = User.sortBuddiesByDistance(buddies);
	}
	
	private BuddyListAdapter formAdapter(ArrayList<Buddy> buddyList) {
		listData = new ArrayList<HashMap<String, Object>>();
		for(Buddy b : buddyList) {
			HashMap<String, Object> buddyMap = new HashMap<String, Object>();
			buddyMap.put("username", b.getUsername());
			buddyMap.put("distance", String.format("%.2f", b.getDistance()));
			if(b.isOnline()) {
				buddyMap.put("online", "online");
			} else {
				buddyMap.put("online", "offline");
			}
			listData.add(buddyMap);
		}
		return new BuddyListAdapter(getActivity(), listData, R.layout.buddy_list_row,
				BuddyListAdapter.from, BuddyListAdapter.to);
	}
	
	private void setEmptyText() {
		if(User.isLoggedIn()) {
			setEmptyText(getString(R.string.no_registered_friends));
		} else {
			setEmptyText(getString(R.string.login_to_view_friends));
		}
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
		setBuddies(testBuddies);
		populateFullList();
	}
}
