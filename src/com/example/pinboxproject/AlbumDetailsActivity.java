package com.example.pinboxproject;

import com.origamilabs.library.views.StaggeredGridView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AlbumDetailsActivity extends NavigationActivity {
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_album_details);
		
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		StaggeredGridView staggeredGrid = (StaggeredGridView)findViewById(R.id.staggeredGridView1);
		adapter = new StaggeredAdapter(this, screenWidth - 40, staggeredGrid.getColumnCount());
		staggeredGrid.setAdapter(adapter);
		
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

}
