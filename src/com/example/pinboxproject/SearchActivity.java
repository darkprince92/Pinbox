package com.example.pinboxproject;

import com.example.pinboxproject.adapters.SearchPagerAdapter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

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
				
		String selectedCategory = "";
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			selectedCategory = bundle.getString("category");
		}
		
		Toast.makeText(this, "category: " + selectedCategory, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

}
