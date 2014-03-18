package com.example.pinboxproject;

import com.example.pinboxproject.apputils.MapSetup;
import com.google.android.gms.maps.GoogleMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddPinFragment1 extends Fragment{
	
	private GoogleMap map;
	private Activity activity;
	
	public AddPinFragment1(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_add_pin_1, container, false);
		MapSetup mapSetup = new MapSetup(activity, R.id.add_pin_map_fragment);
		map = mapSetup.getMap();
		
		return view;
	}

}
