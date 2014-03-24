package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pinboxproject.R;
import com.example.pinboxproject.entity.Pin;

public class AddPinToAlbumAdapter extends BaseAdapter{
	
	//private ArrayList<CommentData> commentData;
	//ArrayList<IncidentComment> comments;
	private Activity activity;
	private ArrayList<Pin> userPins;
	private ArrayList<Boolean> pinCheck;
	
	public AddPinToAlbumAdapter(Activity activity, ArrayList<Pin> pins){
		this.activity = activity;
		this.userPins = pins;
		pinCheck=new ArrayList<Boolean>();
		init();
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
		
//		final ViewHolder viewHolder;
		View vi=convertView;
		final int childPos=position;
		final PinFilterer filterer = (PinFilterer)activity;
		ImageView imagePin;
		TextView pinTitle;
		TextView pinCategory;
		final CheckBox checkBox;
		
		if(convertView == null){
			//<-----With ViewHolder---->
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.add_pin_item, null);
			
//			viewHolder = new ViewHolder();
//			viewHolder.imagePin = (ImageView)convertView.findViewById(R.id.add_pin_to_album_image);
//			viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.add_pin_to_album_check_box);
//			viewHolder.pinTitle = (TextView)convertView.findViewById(R.id.add_pin_to_album_text_title);
//			viewHolder.pinCategory = (TextView)convertView.findViewById(R.id.add_pin_to_album_text_category);
			
			
			
//			convertView.setTag(viewHolder);
			
		}else{			
//			viewHolder = (ViewHolder)convertView.getTag();
		}
		imagePin = (ImageView)vi.findViewById(R.id.add_pin_to_album_image);
		checkBox = (CheckBox)vi.findViewById(R.id.add_pin_to_album_check_box);
		pinTitle = (TextView)vi.findViewById(R.id.add_pin_to_album_text_title);
		pinCategory = (TextView)vi.findViewById(R.id.add_pin_to_album_text_category);
		
//		viewHolder.checkBox.setChecked(pinCheck.get(position));
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				System.out.println(childPos);
				pinCheck.set(childPos, isChecked);
				
				filterer.filterPin(pinCheck);
				
			}
		});
		vi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(pinCheck.get(childPos) == true)
				{
					checkBox.setChecked(false);
					pinCheck.set(childPos, false);
				}
				else if(pinCheck.get(childPos) == false)
				{
					checkBox.setChecked(true);
					pinCheck.set(childPos, true);
				}
				
				
			}
		});
		
		pinTitle.setText(userPins.get(position).getName());
		pinCategory.setText(userPins.get(position).getCat().getCatName());
		
		
		
		return vi;
	}
	
	private void init()
	{
		int s=userPins.size();
		for(int i=0;i<s;i++)
		{
			pinCheck.add(i, false);
		}
	}
	private class ViewHolder{
		ImageView imagePin;
		TextView pinTitle;
		TextView pinCategory;
		CheckBox checkBox;
	}
	public static interface PinFilterer{
		public void filterPin(ArrayList<Boolean> pinCheckList);
	}

}
