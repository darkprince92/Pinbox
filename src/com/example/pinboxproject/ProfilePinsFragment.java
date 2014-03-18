package com.example.pinboxproject;

import com.example.pinboxproject.adapters.StaggeredAdapter;
import com.origamilabs.library.views.StaggeredGridView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfilePinsFragment extends Fragment{
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;
	
	private Activity activity;
	
	public ProfilePinsFragment(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_profile_pins, container, false);
		
		screenWidth = this.activity.getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		StaggeredGridView staggeredGrid = (StaggeredGridView)view.findViewById(R.id.profile_staggeredGridView);
		adapter = new StaggeredAdapter(activity, screenWidth - 40, staggeredGrid.getColumnCount());
		staggeredGrid.setAdapter(adapter);
		
		return view;
	}

}
