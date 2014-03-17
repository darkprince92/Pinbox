package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

public class PagerAdapter extends FragmentPagerAdapter{
	
	private ArrayList<Fragment> fragments;
	private Activity activity;

	public PagerAdapter(FragmentManager fm, Activity activity, ArrayList<Fragment> fragments) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int item) {
		// TODO Auto-generated method stub
		System.out.println("Profle pager fragment get item: ");
		System.out.println(fragments.get(item));
		return fragments.get(item);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}
