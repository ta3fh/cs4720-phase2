package com.example.phase2.user;

import java.util.ArrayList;

import android.location.Location;

public class User {
	private static String username;
	private static Location location;
	private static ArrayList<Buddy> buddies;
	
	public static void requestBuddiesFromServer() {
		
	}
	
	public static ArrayList<Buddy> getBuddies() {
		return buddies;
	}
	
	public static ArrayList<Buddy> getOnlineBuddies() {
		ArrayList<Buddy> result = new ArrayList<Buddy>();
		for(Buddy b : buddies) {
			if(b.isOnline()) {
				result.add(b);
			}
		}
		return result;
	}
	
	public static ArrayList<Buddy> sortBuddiesByLocation() {
		//TODO: Implement this
		return buddies;
	}
}
