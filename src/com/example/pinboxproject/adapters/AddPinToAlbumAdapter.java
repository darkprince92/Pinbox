package com.example.pinboxproject.adapters;

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

public class AddPinToAlbumAdapter extends BaseAdapter{
	
	//private ArrayList<CommentData> commentData;
	//ArrayList<IncidentComment> comments;
	private Activity activity;
	
	public AddPinToAlbumAdapter(Activity activity){
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
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
		return convertView;
	}
	
	private class ViewHolder{
		ImageView imagePin;
		TextView pinTitle;
		TextView pinCategory;
		CheckBox checkBox;
	}

}
