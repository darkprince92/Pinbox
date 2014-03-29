package com.example.pinboxproject;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.pinboxproject.adapters.StaggeredAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.PinCursorLoader;
import com.example.pinboxproject.apputils.Utils;
import com.example.pinboxproject.entity.Pin;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class MainActivity extends NavigationActivity implements LoaderCallbacks<Cursor>{
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;
	private Button buttonAddPin,buttonMap,buttonSearch;
	private MyPrePopulatedDBHelper mdh;
	private ArrayList<Pin> pins;
	private StaggeredGridView staggeredGrid;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);	
		
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
	
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		pullData();
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		staggeredGrid = (StaggeredGridView)findViewById(R.id.staggeredGridView1);
		getSupportLoaderManager().initLoader(0, null, this);
		
	}

	void init()
	{
		
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
//				Toast.makeText(getApplicationContext(), "Position "+position, Toast.LENGTH_LONG).show();
			}
		});
	}
	private void pullData()
	{
		mdh=new MyPrePopulatedDBHelper(getApplicationContext(), "tik");
		
	}
	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		pullData();
		return new PinCursorLoader(this, mdh,"Home");
	}


	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		pins=Utils.cursorToPinList(arg1);
//		arg1.close();
		for(int i=0;i<pins.size();i++)
		{
			System.out.println(pins.get(i).toString());
		}
		this.mdh.database.close();
		init();
		
	}


	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
