package com.example.pinboxproject;

import android.support.v4.view.ViewPager;

public class SearchActivity extends NavigationActivity {

	
	private SearchPagerAdapter pagerAdapter;
	private ViewPager pager;
	

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_search);
		
		pagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(), this);
		pager = (ViewPager)findViewById(R.id.search_pager);
		pager.setAdapter(pagerAdapter);
		
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

}
