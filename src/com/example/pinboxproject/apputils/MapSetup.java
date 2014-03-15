package com.example.pinboxproject.apputils;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;

import com.example.pinboxproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapSetup {
	
	private Activity activity;
	private GoogleMap map;
	private LatLng myLocation;
	private final LatLng DHAKA = new LatLng(23.709921, 90.407143);
	private Marker selectedMarker;
	
	private LocationManager locationManager;
	private Location location;
	private GPSTracker gpsTracker;
	
	private double currentLat;
	private double currentLng;
	
	private int mapResourceId;
	
	public MapSetup(Activity activity, int mapResourceId){
		this.mapResourceId = mapResourceId;
		this.activity = activity;
		setupMap();
	}
	
	public void setupMap(){
		map = ((MapFragment)activity.getFragmentManager().findFragmentById(mapResourceId))
				.getMap();
	    //map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map_add_report)).getMap();
		if(map==null){
			//Toast.makeText(activity, "Map not Loaded", Toast.LENGTH_LONG).show();
			System.out.println("Map not Loaded");	 
		}else {
			//Toast.makeText(activity, "Map Loaded", Toast.LENGTH_LONG).show();
			System.out.println("Map Loaded");
		}
		
		gpsTracker = new GPSTracker(activity, activity);
		location = gpsTracker.getLocation();
		
		if(location!=null){
			currentLat = location.getLatitude();
			currentLng = location.getLongitude();
		}
		else {
			currentLat = DHAKA.latitude;
			currentLng = DHAKA.longitude;
		}
		
		map.setMyLocationEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		
		myLocation = new LatLng(currentLat,currentLng);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));
		selectedMarker = map.addMarker(new MarkerOptions().position(myLocation)
				.title("Your Report Location")
				.draggable(true)); // disturb here!!!
				
		/*map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
			
			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub				
			}
		});*/
	}
	
	public GoogleMap getMap(){
		return map;
	}

}
