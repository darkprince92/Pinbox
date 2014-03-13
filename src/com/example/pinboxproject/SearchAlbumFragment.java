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

public class SearchAlbumFragment extends Fragment{
	
	private ListView albumListView;
	private Spinner sortSpinner;
	private Activity activity;
	private ArrayList<String> sortSpinnerList;
	
	public SearchAlbumFragment(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		spinnerItemInit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_search_album, container, false);
	    
	    //<-------sorting spinner--------->
	    sortSpinner = (Spinner)view.findViewById(R.id.search_album_spinner_sort);
	    ArrayAdapter<String> sortSpinnerAdapter = new ArrayAdapter<String>(activity, 
	    		android.R.layout.simple_spinner_dropdown_item, 
	    		sortSpinnerList);
	    sortSpinner.setAdapter(sortSpinnerAdapter);
	    
	    //<-------list view--------->
	    albumListView = (ListView)view.findViewById(R.id.search_album_list);
	    SearchAlbumAdapter albumAdapter = new SearchAlbumAdapter(activity);
	    albumListView.setAdapter(albumAdapter);
	    
	    return view;
	}
	
	private void spinnerItemInit(){
		sortSpinnerList = new ArrayList<String>();
		sortSpinnerList.add("Date Added");
		sortSpinnerList.add("Up Vote");
		sortSpinnerList.add("Down Vote");
		sortSpinnerList.add("Comments");
		
	}
}
