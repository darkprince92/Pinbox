package com.example.pinboxproject;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.pinboxproject.adapters.SearchPagerAdapter;

public class SearchActivity extends NavigationActivity {

	
	private SearchPagerAdapter pagerAdapter;
	private ViewPager pager;
	private String searchText;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_search);
		handleIntent(getIntent());
		
		
		pagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(), this,searchText);
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
	
	@Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }
 
    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
 
            /**
             * Use this query to display search results like 
             * 1. Getting the data from SQLite and showing in listview 
             * 2. Making webrequest and displaying the data 
             * For now we just display the query only
             */
            searchText = query;
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
 
        }
 
    }

}
