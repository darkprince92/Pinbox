package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import com.example.pinboxproject.R;
import com.example.pinboxproject.R.drawable;
import com.example.pinboxproject.R.id;
import com.example.pinboxproject.R.layout;
import com.example.pinboxproject.entity.Album;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAlbumAdapter extends BaseAdapter{
	
	private Activity activity;
	private ArrayList<Album> albums;
	public GridAlbumAdapter(Activity activity,ArrayList<Album> albums) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.albums=albums;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return albums.size()+1;
	}

	@Override
	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return item;
	}

	@Override
	public long getItemId(int itemId) {
		// TODO Auto-generated method stub
		return itemId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		
		if(convertView == null){
			//<-----With ViewHolder---->
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.grid_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.album_grid_image);
			viewHolder.titleText=(TextView)convertView.findViewById(R.id.album_grid_title);
			
			
			
			convertView.setTag(viewHolder);
			
		}else{			
			viewHolder = (ViewHolder)convertView.getTag();
		}
		if(position == 0)
		{
			viewHolder.imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_add));
			viewHolder.titleText.setText("");
		}
		else
		{
			viewHolder.titleText.setText(albums.get(position-1).getTitle());
			
		}
		return convertView;
	}
	
	private class ViewHolder{
		TextView titleText;
		ImageView imageView;
	}

}
