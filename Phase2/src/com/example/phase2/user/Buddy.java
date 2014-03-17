package com.example.phase2.user;

public class Buddy {
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
}
