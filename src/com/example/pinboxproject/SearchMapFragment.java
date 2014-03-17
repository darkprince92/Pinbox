package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pinboxproject.apputils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchMapFragment extends Fragment{
	
	private Activity activity;
	private Spinner locationSpinner;
	private Spinner categorySpinner;
	private ArrayList<String> locationSpinnerList;
	private ArrayList<String> categorySpinnerList;
	
	private GoogleMap map;
	private LatLng myLocation;
	private static final LatLng DHAKA = new LatLng(23.709921, 90.407143);
	private Marker selectedMarker;
	
	private LocationManager locationManager;
	private Location location;
	private GPSTracker gpsTracker;
	
	private double currentLat;
	private double currentLng;
	
	public SearchMapFragment(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		spinnerItemInit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_search_map, container, false);
	    
	    //<-------sorting spinner--------->
	    locationSpinner = (Spinner)view.findViewById(R.id.search_map_spinner_location);
	    ArrayAdapter<String> sortSpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, 
	    		locationSpinnerList);
	    locationSpinner.setAdapter(sortSpinnerAdapter);
	    
	  //<-------sorting spinner--------->
	    categorySpinner = (Spinner)view.findViewById(R.id.search_map_spinner_category);
	    ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, 
	    		categorySpinnerList);
	    categorySpinner.setAdapter(categorySpinnerAdapter);
	    
	    setupMap();
	   
	    return view;
	}
	
	private void setupMap(){
		map = ((MapFragment)activity.getFragmentManager().findFragmentById(R.id.search_map_fragment))
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
				
		map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
			
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
		});
	}
	
	private void spinnerItemInit(){
		locationSpinnerList = new ArrayList<String>();
		locationSpinnerList.add("Dhaka");
		locationSpinnerList.add("Chittagong");
		locationSpinnerList.add("Sylhet");
		locationSpinnerList.add("Comilla");
		locationSpinnerList.add("Khulna");
		locationSpinnerList.add("Borishal");
		locationSpinnerList.add("Rajshahi");
		
		categorySpinnerList = new ArrayList<String>();
		categorySpinnerList.add("All");
		categorySpinnerList.add("Restaurant");
		categorySpinnerList.add("Bank");
		categorySpinnerList.add("Education");
		categorySpinnerList.add("Hospital");
	}

}
