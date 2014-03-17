package com.example.phase2.user;

public class Buddy implements Comparable<Buddy> {
	private String username;
	private boolean online;
	private double distance;
	
	public Buddy(String username) {
		this.username = username;
	}
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public String getUsername() {
		return username;
	}

	@Override
	public int compareTo(Buddy b2) {
		// First compare distances
		if(this.getDistance() == b2.getDistance()) {
			// If they have the same distance, compare usernames
			return this.getUsername().compareTo(b2.getUsername());
		} else {
			// The greater one is the one that is closer (lower distance)
			// Don't compare with subtraction because we need to return an int
			if(this.getDistance() > b2.getDistance()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
