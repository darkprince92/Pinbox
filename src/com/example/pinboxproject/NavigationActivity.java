package com.example.pinboxproject;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pinboxproject.adapters.NavigationListAdapter;

public abstract class NavigationActivity extends FragmentActivity {
	
	protected abstract void createView();
	protected abstract void createMenu();
	
	protected ActionBar actionBar;
	protected Bundle bundle;
	protected String[] mItems = {"Home", "Category", "Map", "Add Pin", "Search", "Help", "About Us"};
	protected DrawerLayout mDrawerLayout;
	protected ListView mDrawerList;
	protected CharSequence appTitle;
	protected CharSequence menuTitle;
	protected ActionBarDrawerToggle mDrawerToggle;
	
	protected SearchView searchView;
	
	protected Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_navigation);
		createView();
		//ActionBar codes
		
		actionBar = getActionBar();
		//actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_back));
		//actionBar.setLogo(R.drawable.logo);
		//Navigation Drawer Codes
		appTitle = "PinBox";
		menuTitle = "Menu";
						
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList = (ListView)findViewById(R.id.left_drawer);
		mDrawerLayout.setScrimColor(Color.TRANSPARENT);
		NavigationListAdapter drawerAdapter = new NavigationListAdapter(this, mItems);
		mDrawerList.setAdapter(drawerAdapter);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//"Home", "Category", "Map", "Add Pin", "Search", "Help", "About Us"
				switch(position){
				case 0:
					gotoActivity(MainActivity.class);
					break;
				case 1:
					gotoActivity(CategoryActivity.class);
					break;
				case 2:
					gotoActivity(MapActivity.class);	
					break;
				case 3:
					gotoActivity(AddPinActivity.class);
					break;
				case 4:
					gotoActivity(SearchActivity.class);
					break;
				case 5:
					
					break;					
				}
				
			}
		});
		
		mDrawerToggle = new ActionBarDrawerToggle(this, 
				mDrawerLayout, 
				R.drawable.ic_action_navigation,
				R.string.drawer_open, 
				R.string.drawer_close){
			
			@Override
	        public void onDrawerSlide ( View drawerView, float slideOffset )
	        {
	            bringDrawerToFront();
	            super.onDrawerSlide( drawerView, slideOffset );
	        }

	        @Override
	        public void onDrawerStateChanged ( int newState )
	        {
	            bringDrawerToFront();
	            super.onDrawerStateChanged( newState );
	        }
	        private void bringDrawerToFront()
	        {
	            mDrawerList.bringToFront();
	            mDrawerLayout.requestLayout();
	        }           
			
			public void onDrawerClosed(View view){
				getActionBar().setTitle(appTitle);
			}
			
			public void onDrawerOpened(View drawerView){
				getActionBar().setTitle(menuTitle);
			}
		};
		
		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		//mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.navigation, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setSubmitButtonEnabled(true);
		//suggestion adapter
		//searchView.setSuggestionsAdapter(adapter);

		createMenu();
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(mDrawerToggle.onOptionsItemSelected(item)){
			return super.onOptionsItemSelected(item);
		}								
		else {
			switch (item.getItemId()) {
			case R.id.action_profile:
				gotoActivity(UserProfileActivity.class);
			
			default:
				return super.onOptionsItemSelected(item);
			}
		}		
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void setTitle(CharSequence title){
		getActionBar().setTitle(title);
	}
	
	private void gotoActivity(Class<?> activityTo){
		Intent intent = new Intent(NavigationActivity.this, activityTo);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}
