package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import com.example.pinboxproject.R;
import com.example.pinboxproject.R.id;
import com.example.pinboxproject.R.layout;
import com.example.pinboxproject.apputils.*;
import com.example.pinboxproject.entity.Pin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchPinListAdapter extends BaseAdapter {
	
	private ArrayList<Pin> pins;
	private Activity activity;
	
	
	public SearchPinListAdapter(Activity activity,ArrayList<Pin> pins) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.pins=pins;
		//this.pinData = pinData;
	}	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return pinData.size();
		return pins.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		//return pinData.get(arg0);
		return pins.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return pins.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		
		if(convertView == null){
			//<-----With ViewHolder---->
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.search_pin_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.search_pin_image);
			viewHolder.textTitle = (TextView)convertView.findViewById(R.id.search_pin_text_title);
			viewHolder.textCategory = (TextView)convertView.findViewById(R.id.search_pin_text_category);
			viewHolder.textAddress = (TextView)convertView.findViewById(R.id.search_pin_text_address);
			
			convertView.setTag(viewHolder);
			
		}else{			
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.textTitle.setText(pins.get(position).getName());
		viewHolder.textCategory.setText(pins.get(position).getCat().getCatName());
		viewHolder.textAddress.setText(pins.get(position).getLoc().getDistrict());
		
		return convertView;
	}
	
	public static class PinData{
		String pinTitle;
		String pinCategory;
		int commentCount;
		String address;
		int upCount;
		int downCount;
		Bitmap pinImage;
	}
	
	private static class ViewHolder{
		ImageView imageView;
		TextView textTitle;
		TextView textCategory;
		TextView textAddress;
	}
}
