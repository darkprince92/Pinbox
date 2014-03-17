package com.example.pinboxproject;

import com.example.pinboxproject.apputils.GPSTracker;
import com.example.pinboxproject.apputils.MapSetup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapActivity extends NavigationActivity {

	private GoogleMap map;
	private LatLng myLocation;
	private Marker selectedMarker;
	private Location location;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_map);
		MapSetup mapSetup = new MapSetup(this, R.id.map_activity_fragment);
		map = mapSetup.getMap();
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

}
