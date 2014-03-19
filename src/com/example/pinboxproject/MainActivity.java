package com.example.pinboxproject;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.pinboxproject.adapters.StaggeredAdapter;
import com.origamilabs.library.views.StaggeredGridView;

public class MainActivity extends NavigationActivity {
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;
	private Button buttonAddPin,buttonMap,buttonSearch;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		StaggeredGridView staggeredGrid = (StaggeredGridView)findViewById(R.id.staggeredGridView1);
		adapter = new StaggeredAdapter(this, screenWidth - 40, staggeredGrid.getColumnCount());
		staggeredGrid.setAdapter(adapter);
		
		buttonSearch = (Button)findViewById(R.id.home_button_search);
		buttonSearch.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(MainActivity.this, SearchActivity.class);
				Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);		
				
				MainActivity.this.startActivity(intent);
			}
		});
	
		
		buttonMap = (Button)findViewById(R.id.home_button_map);
		buttonMap.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, MapActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
		buttonAddPin=(Button)findViewById(R.id.home_button_add_pin);
		buttonAddPin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,AddPinActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				startActivity(intent);
			}
		});
		
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

	

}
