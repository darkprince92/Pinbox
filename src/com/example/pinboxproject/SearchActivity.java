package com.example.pinboxproject;

import android.support.v4.view.ViewPager;

public class SearchActivity extends NavigationActivity {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}*/
	
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
