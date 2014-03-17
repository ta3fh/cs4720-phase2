package com.example.phase2.user;

import java.util.ArrayList;
import java.util.Collections;

import android.location.Location;

public class User {
	private static String username;
	private static Location location;
	private static ArrayList<Buddy> buddies;
	private static boolean loggedIn = true;
	
	public static void requestBuddiesFromServer() {
		
	}
	
	public static ArrayList<Buddy> getBuddies() {
		return buddies;
	}
	
	public static boolean isLoggedIn() {
		return loggedIn;
	}

	public static void setLoggedIn(boolean loggedIn) {
		User.loggedIn = loggedIn;
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
	
	public static ArrayList<Buddy> sortBuddiesByDistance(ArrayList<Buddy> toSort) {
		Collections.sort(toSort);
		return toSort;
	}
}
