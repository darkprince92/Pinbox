package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import com.example.pinboxproject.R;
import com.example.pinboxproject.R.id;
import com.example.pinboxproject.R.layout;
import com.example.pinboxproject.adapters.SearchPinListAdapter.PinData;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchAlbumAdapter extends BaseAdapter{
	
	private ArrayList<AlbumData> albumData;
	private Activity activity;
	
	public SearchAlbumAdapter(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
ViewHolder viewHolder;
		
		if(convertView == null){
			//<-----With ViewHolder---->
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.search_album_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.search_album_image);
			viewHolder.textTitle = (TextView)convertView.findViewById(R.id.search_album_text_title);
			viewHolder.textAddress = (TextView)convertView.findViewById(R.id.search_album_text_address);
			
			convertView.setTag(viewHolder);
			
		}else{			
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		return convertView;
	}
	
	private static class ViewHolder{
		ImageView imageView;
		TextView textTitle;
		TextView textAddress;
	}	
	
	public static class AlbumData{
		String albumTitle;
		String address;
		int commentCount;
		int upCount;
		int downCount;
		Bitmap albumImage;
	}

}
