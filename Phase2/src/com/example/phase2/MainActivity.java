package com.example.phase2;

import com.example.phase2.user.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class MainActivity extends Activity {
	private BuddyListFragment buddyList;
	private Switch onlineSwitch;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buddyList = (BuddyListFragment) getFragmentManager().findFragmentById(R.id.main_buddy_list);
        onlineSwitch = (Switch) findViewById(R.id.online_friends_switch);
        onlineSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton view, boolean isChecked) {
				populateBuddyList();
			}
        	
        });
        buddyList.addTestData();
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
			buddyList.filterListByOnline(true);
		} else {
			buddyList.populateFullList();
		}
    }
    
    public void refreshBuddyList() {
    	User.requestBuddiesFromServer();
    	buddyList.setBuddies(User.getBuddies());
    	populateBuddyList();
    }
    
}
