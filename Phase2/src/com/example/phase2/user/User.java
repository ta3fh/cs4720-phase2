package com.example.phase2.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONObject;


import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class User {
	private static String username;
	private static Location location;
	private static ArrayList<Buddy> buddies = new ArrayList<Buddy>();
	private static boolean loggedIn = true;
	
	// currently just prints the output of the web service with the parameter user-id 2
	public static String requestBuddiesFromServer(String url_string) {
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL url = new URL(url_string);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			try {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
					//System.out.println(line);
				}
			} catch(Exception e) {
				Log.d("requestBuddiesFromServer", e.getLocalizedMessage());
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch(Exception e) {
						Log.d("requestBuddiesFromServer", e.getLocalizedMessage());
						//e.printStackTrace();
					}
				}
			}
		} catch(Exception e) {
			Log.d("requestBuddiesFromServer", e.getLocalizedMessage());
		}
		return stringBuilder.toString();
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
