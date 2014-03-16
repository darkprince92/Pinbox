package com.example.pinboxproject;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;


public class UserProfileActivity extends NavigationActivity {
	
	private Button buttonSelectPins;
	private Button buttonSelectAlbums;
	private ViewPager pager;
	private PagerAdapter pagerAdapter;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user_profile);
		
		pager = (ViewPager)findViewById(R.id.profile_pager);
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new ProfilePinsFragment(this));
		fragments.add(new ProfileAlbumsFragment(this));
		pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, fragments);
		pager.setAdapter(pagerAdapter);
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

}
