package com.example.phase2.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import android.location.Location;

public class User {
	private static String username;
	private static Location location;
	private static ArrayList<Buddy> buddies = new ArrayList<Buddy>();
	private static boolean loggedIn = true;
	
	// currently just prints the output of the web service with the parameter user-id 2
	public static void requestBuddiesFromServer() {
		try {
			URL url = new URL("http://plato.cs.virginia.edu/~cs4720s14beans/api/users/friends/2");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			readStream(con.getInputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readStream(InputStream input) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(input));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
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
