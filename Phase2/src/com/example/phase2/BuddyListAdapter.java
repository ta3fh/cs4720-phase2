package com.example.phase2;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

public class BuddyListAdapter extends SimpleAdapter {
	
	public static final String[] from = {"username", "distance"};
	public static final int[] to = {R.id.buddy_row_username, R.id.buddy_row_distance};
	
	public BuddyListAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}
}
