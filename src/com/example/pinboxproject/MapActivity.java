package com.example.pinboxproject;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.Toast;

import com.example.pinboxproject.apputils.MapSetup;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.PinCursorLoader;
import com.example.pinboxproject.apputils.SimpleCursorLoader;
import com.example.pinboxproject.apputils.Utils;
import com.example.pinboxproject.entity.Category;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Pin;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends NavigationActivity implements LoaderCallbacks<Cursor>{

	private GoogleMap map;
	private LatLng myLocation;
	private Marker selectedMarker;
	private Location location;
	
	private MyPrePopulatedDBHelper mdh;
	ArrayList<Pin> allPins;
	private HashMap<Integer, Marker> visibleMarkers;
	private HashMap<Marker, Integer> reverseList;
	private HashMap<Integer, Pin> pinMap;
	private ProgressDialog pd;
	

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_map);
		MapSetup mapSetup = new MapSetup(this, R.id.map_activity_fragment);
		map = mapSetup.getMap();
		visibleMarkers = new HashMap<Integer, Marker>();
		reverseList=new HashMap<Marker, Integer>();
		pinMap=new HashMap<Integer, Pin>();
		
		init();
	}
	private void init()
	{
		getSupportLoaderManager().initLoader(0, null, this);
		
//		pd=ProgressDialog.show(MapActivity.this,"","Loading Pins...",true,false);
//		threading();
		
		
		
		
		
		
	}
	
	private void mapPopulate()
	{
		map.clear();
		map.setOnCameraChangeListener(getCameraChangeListener());
		
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker arg0) {
				// TODO Auto-generated method stub
				Log.d("marker", arg0.getId());
				
				Toast.makeText(getApplicationContext(), arg0.getId(), Toast.LENGTH_LONG).show();
				
				int id=reverseList.get(arg0);
				Intent intent=new Intent(MapActivity.this,PinDetailsActivity.class);
				intent.putExtra("pin_data",pinMap.get(id));
				startActivity(intent);
			}
		});
	}
	public OnCameraChangeListener getCameraChangeListener()
	{
	    return new OnCameraChangeListener() 
	    {
	        @Override
	        public void onCameraChange(CameraPosition position) 
	        {
	            addItemsToMap(allPins);
	        }
	    };
	}
	
	
	private void addItemsToMap(ArrayList<Pin> pins)
	{
	    if(this.map != null)
	    {
	        //This is the current user-viewable region of the map
	        LatLngBounds bounds = this.map.getProjection().getVisibleRegion().latLngBounds;
	 
	        //Loop through all the items that are available to be placed on the map
	        for(Pin pin : pins) 
	        {
	 
	            //If the item is within the the bounds of the screen
	            if(bounds.contains(new LatLng(pin.getLoc().getLatitude(), pin.getLoc().getLongitude())))
	            {
	                //If the item isn't already being displayed
	                if(!visibleMarkers.containsKey(pin.getId()))
	                {
	                    //Add the Marker to the Map and keep track of it with the HashMap
	                    //getMarkerForItem just returns a MarkerOptions object
	                	Marker m=this.map.addMarker(getMarkerForPin(pin));
	                    this.visibleMarkers.put(pin.getId(), m);
	                    this.reverseList.put(m, pin.getId());
	                    this.pinMap.put(pin.getId(), pin);
	                }
	            }
	 
	            //If the marker is off screen
	            else
	            {
	                //If the course was previously on screen
	                if(visibleMarkers.containsKey(pin.getId()))
	                {
	                    //1. Remove the Marker from the GoogleMap
	                	Marker m=visibleMarkers.get(pin.getId());
	                	reverseList.remove(m);
	                    visibleMarkers.get(pin.getId()).remove();
	                    
	                    //2. Remove the reference to the Marker from the HashMap
	                    visibleMarkers.remove(pin.getId());
	                    pinMap.remove(pin.getId());
	                }
	            }
	        }
	    }
	}
	
	MarkerOptions getMarkerForPin(Pin pin)
	{
		LatLng myLocation=new LatLng(pin.getLoc().getLatitude(), pin.getLoc().getLongitude());
		return new MarkerOptions()
					.position(myLocation)
					.title(pin.getName())
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker));

				
	}

//	private void threading()
//	{
//		new Thread(new Runnable() {
//            public void run() {
//                allPins=mdh.getAllLocations(2000);
////                pd.dismiss();
//            }
//        }).start();
//		
//	}
	
	
	
	
//	Handler handler1=new Handler(){
//		public void handleMessage(android.os.Message msg)
//		{
//			JSONObject responseObj=null;
//			pd.dismiss();
//			Bundle b=msg.getData();
//			String status=null;
//			System.out.println(b.toString());
//			String response=b.getString("response");
//			if(response.equals("ok"))
//			{
//				mapPopulate();
//				addItemsToMap(allPins);
//			}
//		}
//	};
	
	private MyPrePopulatedDBHelper getHelper() {
		if (mdh == null) {
			mdh = MyPrePopulatedDBHelper.getInstance(getApplicationContext(), "tik");
		}
		return mdh;
	}
	

		
		
	
	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		pd=ProgressDialog.show(MapActivity.this,"","Loading Pins...",true,false);
		return new PinCursorLoader(this, getHelper(),"All");
	}
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		pd.dismiss();
		allPins=Utils.cursorToPinList(arg1);
		this.mdh.database.close();
		
		System.out.println("Pins count "+allPins.size());
		
		mapPopulate();
		addItemsToMap(allPins);
		
	}
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

}
