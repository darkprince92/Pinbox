package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinboxproject.adapters.StaggeredAdapter;
import com.example.pinboxproject.entity.Pin;
import com.origamilabs.library.views.StaggeredGridView;

public class ProfilePinsFragment extends Fragment{
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;
	
//	private Activity activity;
	ArrayList<Pin> pins;
	
	public ProfilePinsFragment(ArrayList<Pin> pins) {
		// TODO Auto-generated constructor stub
		this.pins=pins;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_profile_pins, container, false);
		
		screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		StaggeredGridView staggeredGrid = (StaggeredGridView)view.findViewById(R.id.profile_staggeredGridView);
		adapter = new StaggeredAdapter(getActivity(), screenWidth - 40, columnNumber,pins);
		staggeredGrid.setAdapter(adapter);
		
		return view;
	}

}
