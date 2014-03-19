package com.example.pinboxproject;


import java.util.ArrayList;

import com.example.pinboxproject.adapters.SearchPinListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class SearchPinListFragment extends Fragment{
	
	private ListView pinListView;
	private Spinner sortSpinner;
	private Spinner categorySpinner;
	private Activity activity;
	private ArrayList<String> sortSpinnerList;
	private ArrayList<String> categorytSpinnerList;
	
	public SearchPinListFragment(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		spinnerItemInit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_search_pin_list, container, false);
	    
	    //<-------sorting spinner--------->
	    sortSpinner = (Spinner)view.findViewById(R.id.search_pin_spinner_sort);
	    ArrayAdapter<String> sortSpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, 
	    		sortSpinnerList);
	    sortSpinner.setAdapter(sortSpinnerAdapter);
	    
	    //<-------select category spinner--------->
	    categorySpinner = (Spinner)view.findViewById(R.id.search_pin_spinner_category);
	    ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, categorytSpinnerList);
	    categorySpinner.setAdapter(categorySpinnerAdapter);
	    
	    //<-------list view--------->
	    pinListView = (ListView)view.findViewById(R.id.search_pin_list);
	    SearchPinListAdapter pinAdapter = new SearchPinListAdapter(activity);
	    pinListView.setAdapter(pinAdapter);
	    
	    return view;
	}
	
	private void spinnerItemInit(){
		sortSpinnerList = new ArrayList<String>();
		sortSpinnerList.add("Up Vote");
		sortSpinnerList.add("Down Vote");
		sortSpinnerList.add("Comments");
		sortSpinnerList.add("Date Added");
		
		categorytSpinnerList = new ArrayList<String>();
		categorytSpinnerList.add("All");
		categorytSpinnerList.add("Restaurant");
		categorytSpinnerList.add("Bank");
		categorytSpinnerList.add("Education");
		categorytSpinnerList.add("Hospital");
	}
}
