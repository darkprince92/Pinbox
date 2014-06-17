package com.example.pinboxproject;


import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.TextView;

import com.example.pinboxproject.adapters.StaggeredAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.PinCursorLoader;
import com.example.pinboxproject.apputils.Utils;
import com.example.pinboxproject.entity.Album;
import com.example.pinboxproject.entity.Pin;
import com.origamilabs.library.views.StaggeredGridView;

public class AlbumDetailsActivity extends NavigationActivity implements LoaderCallbacks<Cursor>{
	
	private StaggeredAdapter adapter;
	private static int screenWidth;
	private static int columnNumber = 2;
	ArrayList<Pin> pins;
	MyPrePopulatedDBHelper mdh;
	StaggeredGridView staggeredGrid;
	
	private TextView albumTitleText,albumDescText;
	
	Album a;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_album_details);
		
		Bundle b = this.getIntent().getExtras();
		a=this.getIntent().getParcelableExtra("album");
		
		mdh=MyPrePopulatedDBHelper.getInstance(getApplicationContext(), "tik");
		
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
			columnNumber = 2;
		}
		else columnNumber = 3;
		
		staggeredGrid = (StaggeredGridView)findViewById(R.id.album_staggeredGridView);
		
		albumTitleText=(TextView)findViewById(R.id.album_details_text_title);
		albumDescText=(TextView)findViewById(R.id.album_text_description);
		
		albumTitleText.setText(a.getTitle());
		albumDescText.setText(a.getDesc());
		getSupportLoaderManager().initLoader(0, null, this);
		
		
	}
	
	void init()
	{
		adapter = new StaggeredAdapter(this, screenWidth - 40, staggeredGrid.getColumnCount(),pins);
		staggeredGrid.setAdapter(adapter);
	}
	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new PinCursorLoader(this, mdh, "UserAlbum","pin_location.LOCATION_ID IN ("+a.getLocation_ids()+")");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		pins=Utils.cursorToPinList(arg1);
		this.mdh.database.close();
		init();
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

}
