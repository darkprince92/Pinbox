package com.example.pinboxproject;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.pinboxproject.adapters.GridAlbumAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.PinCursorLoader;
import com.example.pinboxproject.apputils.Utils;
import com.example.pinboxproject.entity.Album;

public class ProfileAlbumsFragment extends Fragment implements LoaderCallbacks<Cursor>{
	
	private FragmentActivity activity;
	private GridView gridView;
	private GridAlbumAdapter gridAdapter;
	private MyPrePopulatedDBHelper mdh;
	private ArrayList<Album> albums;
	View view ;
	
	
	public ProfileAlbumsFragment(FragmentActivity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		
		mdh=new MyPrePopulatedDBHelper(activity, "tik");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.fragment_profile_albums, container, false);
		
		gridView = (GridView)view.findViewById(R.id.profile_grid_album);
		activity.getSupportLoaderManager().initLoader(0, null, (android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>) this);
		
		
		return view;
	}
	void init()
	{
		gridAdapter = new GridAlbumAdapter(activity,albums);
		gridView.setAdapter(gridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(position==0)
				{
					Intent intent=new Intent(activity,CreateAlbumActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					activity.startActivity(intent);
				}
				else
				{
					Intent intent=new Intent(activity,AlbumDetailsActivity.class);
					intent.putExtra("album",albums.get(position-1));
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					activity.startActivity(intent);
				}
			}
			
		});
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0,
			Bundle arg1) {
		// TODO Auto-generated method stub
		return new PinCursorLoader(activity, mdh, "UserAlbums");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0,
			Cursor arg1) {
		// TODO Auto-generated method stub
		albums=Utils.cursorToAlbums(arg1);
		
		for(int i=0;i<albums.size();i++)
		{
			System.out.println(albums.get(i).toString());
		}
		this.mdh.database.close();
		init();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
