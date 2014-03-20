package com.example.phase2;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.phase2.user.Buddy;
import com.example.phase2.user.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class MainActivity extends Activity {
	private BuddyListFragment buddyList;
	private Switch onlineSwitch;
	private EditText radius;
	private double dist = 10E6;
	private String default_url = "http://plato.cs.virginia.edu/~cs4720s14beans/api/users/friends/2";
	GPSLocation gps;
	
	public void onBackgroundTaskDataObtained(ArrayList<Buddy> buddiesToAdd) {
		buddyList.setBuddies(buddiesToAdd);
		populateBuddyList();
	}
	
	public class readJSON extends AsyncTask<String, Void, String> {
		
		protected String doInBackground(String... urls) {
			return User.requestBuddiesFromServer(urls[0]);
		}
		
		
		protected void onPostExecute(String result) {
    		ArrayList<Buddy> buddiesToAdd = new ArrayList<Buddy>();
            try {
            	JSONArray jsonArray = new JSONArray(result);
            	for (int i = 0; i < jsonArray.length(); i++) {
            		JSONObject user_info = new JSONObject(jsonArray.getJSONObject(i).getString("User"));
            		Buddy b = new Buddy(user_info.getString("username"));
            		if (user_info.getString("online").equals("true")) {
            			b.setOnline(true);
            			//Toast.makeText(getBaseContext(), user_info.getString("username") + ": " + user_info.getString("online"), Toast.LENGTH_SHORT).show();
            		} else {
            			b.setOnline(false);
            			//Toast.makeText(getBaseContext(), user_info.getString("username") + ": " + user_info.getString("online"), Toast.LENGTH_SHORT).show();
            		}
        			b.setDistance(Double.parseDouble(user_info.getString("distance")));
            	}
            } catch (Exception e) {
                Log.d("readJSON", e.getLocalizedMessage());
            }  
            MainActivity.this.onBackgroundTaskDataObtained(buddiesToAdd);       
        }
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buddyList = (BuddyListFragment) getFragmentManager().findFragmentById(R.id.main_buddy_list);
        onlineSwitch = (Switch) findViewById(R.id.online_friends_switch);
        radius = (EditText) findViewById(R.id.radius);
        onlineSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton view, boolean isChecked) {
				try {
		        new readJSON().execute(default_url+ "/" + dist);        
				populateBuddyList();
				} catch(Exception e) {
	                Log.d("readJSON", e.getLocalizedMessage());
				}
			}
        	
        });
        try {
        	refreshBuddyList();
        } catch(Exception e) {
        	Log.d("Refresh", e.getLocalizedMessage());
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.refresh_buddy_list:
    		refreshBuddyList();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    private void populateBuddyList() {
		if(onlineSwitch.isChecked()) {
			//buddyList.filterListByOnline(true);
			buddyList.displayOnline(true);
		} else {
			buddyList.populateFullList();
		}
    }
    
    public void refreshBuddyList() {
//    	gps = new GPSLocation(MainActivity.this);
//    	if(gps.isGPSEnabled()){
//            double latitude = gps.getLatitude();
//            double longitude = gps.getLongitude();
//            Toast.makeText(getApplicationContext(), "Your current location \nLatitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_SHORT).show();    
//        } else {
//            Toast.makeText(getApplicationContext(), "GPS is not enabled.", Toast.LENGTH_SHORT).show();    
//        }
    	if(radius != null) {
    		if(radius.getText().toString() != "") {
    			try {
    				dist = Double.parseDouble(radius.getText().toString());
    			} catch (NumberFormatException n) {
    				dist = 10E6;
    			}
    		}
    	}
    	try {
    		new readJSON().execute(default_url + "/" + dist);
	    	//buddyList.setBuddies(User.getBuddies());
	    	populateBuddyList();
    	} catch(Exception e) {
            Log.d("Refresh", e.getLocalizedMessage());
    	}
    }
    
}
