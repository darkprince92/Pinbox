package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pinboxproject.adapters.SearchAlbumAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.Utils;
import com.example.pinboxproject.entity.Album;

public class SearchAlbumFragment extends Fragment {
	
	private ListView albumListView;
	private Spinner sortSpinner;
	private FragmentActivity activity;
	private ArrayList<String> sortSpinnerList;
	private String searchTag;
	private ArrayList<Album> albums;
	MyPrePopulatedDBHelper mdh;
	
	public SearchAlbumFragment(Activity activity,String searchTag) {
		// TODO Auto-generated constructor stub
		this.activity = (FragmentActivity)activity;
		this.searchTag=searchTag;
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
	    
	    pullData();
	    return view;
	}
	//Pull Data from database and populating list
	void pullData()
	{
		mdh=MyPrePopulatedDBHelper.getInstance(activity, "tik");
		albums=new ArrayList<Album>();
		albums=Utils.cursorToAlbums(mdh.searchAlbumCursor(this.searchTag));
		SearchAlbumAdapter albumAdapter = new SearchAlbumAdapter(activity,albums);
	    albumListView.setAdapter(albumAdapter);
	    
	    albumListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(activity,AlbumDetailsActivity.class);
				intent.putExtra("album",albums.get(arg2));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				activity.startActivity(intent);
				
			}
		});
	}
	private void spinnerItemInit(){
		sortSpinnerList = new ArrayList<String>();
		sortSpinnerList.add("Date Added");
		sortSpinnerList.add("Up Vote");
		sortSpinnerList.add("Down Vote");
		sortSpinnerList.add("Comments");
		
	}

	
}
