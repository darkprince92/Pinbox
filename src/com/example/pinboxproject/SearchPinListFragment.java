package com.example.pinboxproject;


import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pinboxproject.adapters.SearchPinListAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.PinCursorLoader;
import com.example.pinboxproject.apputils.Utils;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;

public class SearchPinListFragment extends Fragment implements LoaderCallbacks<Cursor>{
	
	private ListView pinListView;
	private Spinner sortSpinner;
	private Spinner categorySpinner;
	private FragmentActivity activity;
	private ArrayList<String> sortSpinnerList;
	private ArrayList<String> categorytSpinnerList;
	ArrayList<Pin> pins;
	ArrayList<Pin> selectedPins;
	private String searchTag;
	ProgressDialog pd;
	MyPrePopulatedDBHelper mdh;
	
	public SearchPinListFragment(FragmentActivity activity,String searchTag) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.searchTag=searchTag;
		spinnerItemInit();
	}
	public SearchPinListFragment()
	{}
	
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
	    
	    categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2!=0)
				{
					if(pins!=null)
					{
						selectedPins=new ArrayList<Pin>();
						for(int i=0;i<pins.size();i++)
						{
							if(pins.get(i).getCat().getId()==arg2)
							{
								selectedPins.add(pins.get(i));
							}
						}
						
						SearchPinListAdapter pinAdapter = new SearchPinListAdapter(activity,selectedPins);
					    pinListView.setAdapter(pinAdapter);
					}
				}
				else
				{
					if(pins!=null)
					{
						selectedPins=new ArrayList<Pin>();
						selectedPins=pins;
						SearchPinListAdapter pinAdapter = new SearchPinListAdapter(activity,pins);
					    pinListView.setAdapter(pinAdapter);
					}
					
				}
				
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});	    
	    //<-------list view--------->
	    pinListView = (ListView)view.findViewById(R.id.search_pin_list);
	    pinListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Pin p=selectedPins.get(arg2);
				Intent intent=new Intent(activity,PinDetailsActivity.class);
				intent.putExtra("pin_data",p);
				startActivity(intent);
				
			}
		});
	    
	    activity.getSupportLoaderManager().initLoader(0, null, this);
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
		System.out.println("categorie status : "+Settings.categories);
		if(Settings.categories==null)
		{
			mdh= MyPrePopulatedDBHelper.getInstance(activity, "tik");
			Settings.categories=mdh.getAllCats();
		}
			
		for(int i=0;i<Settings.categories.size();i++)
		{
			categorytSpinnerList.add(Settings.categories.get(i).getCatName());
		}
		
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		mdh=MyPrePopulatedDBHelper.getInstance(activity, "tik");
		pd=ProgressDialog.show(activity, null, "searching..."); 
		return new PinCursorLoader(activity, mdh, "SearchPin",searchTag);
		
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		pins=new ArrayList<Pin>(); 
		pins=Utils.cursorToPinList(arg1);
//		if(arg1 != null && !arg1.isClosed()){
//			arg1.close();
//	    }
		
		
		pd.dismiss();
		selectedPins=pins;
		SearchPinListAdapter pinAdapter = new SearchPinListAdapter(activity,pins);
	    pinListView.setAdapter(pinAdapter);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
