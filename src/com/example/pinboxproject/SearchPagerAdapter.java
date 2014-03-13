package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SearchPagerAdapter extends FragmentPagerAdapter{
	
	private ArrayList<Fragment> fragments;
	
	private Fragment pinListFragment;
	private Fragment albumFragment;
	private Fragment userFragment;
	private Fragment mapFragment;
	
	private Activity activity;

	public SearchPagerAdapter(FragmentManager fm, Activity activity) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.activity = activity;
		
		pinListFragment = new SearchPinListFragment(activity);
		albumFragment = new SearchAlbumFragment(activity);
		userFragment = new SearchUserFragment(activity);
		mapFragment = new SearchMapFragment(activity);
		
		/*fragments = new ArrayList<Fragment>();
		fragments.add(pinListFragment);
		fragments.add(albumFragment);
		fragments.add(albumFragment);
		fragments.add(mapFragment);*/
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		
		switch (position) {
		case 0:
			return pinListFragment;
			//break;
			
		case 1:
			return albumFragment;
			//break;
		case 2:
			return userFragment;
			//break;
		case 3:
			return mapFragment;
			//break;

		default:
			return null;
			//break;
		}		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

}
