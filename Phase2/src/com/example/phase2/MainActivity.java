package com.example.phase2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
				if(isChecked) {
					buddyList.filterListByOnline(true);
				} else {
					buddyList.populateFullList();
				}
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
    
}
