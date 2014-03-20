package com.example.phase2;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSLocation extends Service implements LocationListener {
	private final Context context;
    protected LocationManager locationManager;
    private static final long update_distance = 10; // 10 meters
    private static final long update_time = 1000 * 60 * 1; // 1 minute
    Location location; 
    double latitude;
    double longitude; 
    boolean GPSEnabled = false;
  
    public GPSLocation(Context context) {
        this.context = context;
        getLocation();
    }
 
    public Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            GPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (GPSEnabled) {
            	locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        update_time,
                        update_distance, this);
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
        } catch (Exception e) {
            Log.d("getLocation", e.getLocalizedMessage());
        }
        return location;
    }
    
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
         
        // return latitude
        return latitude;
    }
    
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
         
        // return latitude
        return longitude;
    }
    
    public boolean isGPSEnabled() {
        return this.GPSEnabled;
    }

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
