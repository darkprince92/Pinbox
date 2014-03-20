package com.example.pinboxproject;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.pinboxproject.adapters.StaggeredAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.entity.Pin;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class MainActivity extends NavigationActivity {
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;
	private Button buttonAddPin,buttonMap,buttonSearch;
	private MyPrePopulatedDBHelper mdh;
	private ArrayList<Pin> pins;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		pullData();
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		StaggeredGridView staggeredGrid = (StaggeredGridView)findViewById(R.id.staggeredGridView1);
		adapter = new StaggeredAdapter(this, screenWidth - 40, staggeredGrid.getColumnCount(),pins);
		staggeredGrid.setAdapter(adapter);
		staggeredGrid.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(StaggeredGridView parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Bundle b=new Bundle();
//				b.putParcelable("pin_data", pins.get(position));
				Intent intent=new Intent(MainActivity.this,PinDetailsActivity.class);
				intent.putExtra("pin_data",pins.get(position));
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Position "+position, Toast.LENGTH_LONG).show();
			}
		});
		
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
	private void pullData()
	{
		mdh=new MyPrePopulatedDBHelper(getApplicationContext(), "tik");
		pins=mdh.getAllLocations();
		for(int i=0;i<pins.size();i++)
		{
			System.out.println(pins.get(i).toString());
		}
	}
	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

	

}
