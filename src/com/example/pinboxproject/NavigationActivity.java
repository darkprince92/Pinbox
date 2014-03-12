package com.example.pinboxproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

public abstract class NavigationActivity extends FragmentActivity {
	
	protected abstract void createView();
	protected abstract void createMenu();
	
	protected ActionBar actionBar;
	protected Bundle bundle;
	protected String[] mItems;
	protected DrawerLayout mDrawerLayout;
	protected ListView mDrawerList;
	protected CharSequence appTitle;
	protected CharSequence menuTitle;
	protected ActionBarDrawerToggle mDrawerToggle;
	
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
		mDrawerList.setAdapter(new NavigationListAdapter(this));
		
		
		mDrawerToggle = new ActionBarDrawerToggle(this, 
				mDrawerLayout, 
				R.drawable.ic_navigation_drawer,
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
}
