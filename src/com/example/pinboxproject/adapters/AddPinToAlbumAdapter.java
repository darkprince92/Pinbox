package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pinboxproject.R;
import com.example.pinboxproject.entity.Pin;

public class AddPinToAlbumAdapter extends BaseAdapter{
	
	//private ArrayList<CommentData> commentData;
	//ArrayList<IncidentComment> comments;
	private Activity activity;
	private ArrayList<Pin> userPins;
	
	public AddPinToAlbumAdapter(Activity activity, ArrayList<Pin> pins){
		this.activity = activity;
		this.userPins = pins;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return userPins.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return userPins.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return userPins.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder viewHolder;
		
		if(convertView == null){
			//<-----With ViewHolder---->
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.add_pin_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.imagePin = (ImageView)convertView.findViewById(R.id.add_pin_to_album_image);
			viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.add_pin_to_album_check_box);
			viewHolder.pinTitle = (TextView)convertView.findViewById(R.id.add_pin_to_album_text_title);
			viewHolder.pinCategory = (TextView)convertView.findViewById(R.id.add_pin_to_album_text_category);
			
			convertView.setTag(viewHolder);
			
		}else{			
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		viewHolder.pinTitle.setText(userPins.get(position).getName());
		viewHolder.pinCategory.setText(userPins.get(position).getCat().getCatName());
		
		return convertView;
	}
	
	private class ViewHolder{
		ImageView imagePin;
		TextView pinTitle;
		TextView pinCategory;
		CheckBox checkBox;
	}

}
