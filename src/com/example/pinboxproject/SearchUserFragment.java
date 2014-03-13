package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class SearchUserFragment extends Fragment {
	
	private ListView pinListView;
	private Spinner locationSpinner;
	private Spinner categorySpinner;
	private Activity activity;
	private ArrayList<String> locationSpinnerList;
	private ArrayList<String> categorytSpinnerList;
	
	public SearchUserFragment(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		spinnerItemInit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_search_user, container, false);
	    
	    //<-------sorting spinner--------->
	    locationSpinner = (Spinner)view.findViewById(R.id.search_user_spinner_location);
	    ArrayAdapter<String> locationSpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, 
	    		locationSpinnerList);
	    locationSpinner.setAdapter(locationSpinnerAdapter);
	    
	    //<-------select category spinner--------->
	    /*categorySpinner = (Spinner)view.findViewById(R.id.search_pin_spinner_category);
	    ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, categorytSpinnerList);
	    categorySpinner.setAdapter(categorySpinnerAdapter);*/
	    
	    //<-------list view--------->
	    pinListView = (ListView)view.findViewById(R.id.search_user_list);
	    SearchUserAdapter userAdapter = new SearchUserAdapter(activity);
	    pinListView.setAdapter(userAdapter);
	    
	    return view;
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
		
		/*categorytSpinnerList = new ArrayList<String>();
		categorytSpinnerList.add("All");
		categorytSpinnerList.add("Restaurant");
		categorytSpinnerList.add("Bank");
		categorytSpinnerList.add("Education");
		categorytSpinnerList.add("Hospital");*/
	}

}
