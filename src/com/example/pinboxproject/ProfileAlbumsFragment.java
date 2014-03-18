package com.example.pinboxproject;

import com.example.pinboxproject.adapters.GridAlbumAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ProfileAlbumsFragment extends Fragment{
	
	private Activity activity;
	private GridView gridView;
	private GridAlbumAdapter gridAdapter;
	
	public ProfileAlbumsFragment(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_profile_albums, container, false);
		
		gridView = (GridView)view.findViewById(R.id.profile_grid_album);
		gridAdapter = new GridAlbumAdapter(activity);
		gridView.setAdapter(gridAdapter);
		
		return view;
	}

}
