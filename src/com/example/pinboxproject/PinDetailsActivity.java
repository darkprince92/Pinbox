package com.example.pinboxproject;

import com.example.pinboxproject.apputils.MapSetup;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PinDetailsActivity extends NavigationActivity {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pin_details, menu);
		return true;
	}*/

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_pin_details);
		MapSetup mapSetup = new MapSetup(this, R.id.pin_details_map_fragment);
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

}
