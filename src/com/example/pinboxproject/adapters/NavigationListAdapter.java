package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import com.example.pinboxproject.R;
import com.example.pinboxproject.R.id;
import com.example.pinboxproject.R.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationListAdapter extends BaseAdapter{
	
	private Activity activity;
	private String[] items;
	private ArrayList<String> itemList;
	private Context context;
	
	public NavigationListAdapter(Context context, String[] items){
		this.context = context;
		this.items = items;
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		
		itemList = new ArrayList<String>();
		for(int i = 0;i<items.length;i++){
			itemList.add(items[i]);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return itemList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String title = itemList.get(position);
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.drawer_item, null);
		}
		
		TextView textNavTitle = (TextView)convertView.findViewById(R.id.text_nav_title);
		textNavTitle.setText(title);
		
		ImageView navIcon = (ImageView)convertView.findViewById(R.id.image_nav_icon);
		switch (position) {
		case 0:
			//navIcon.setImageResource(R.drawable.ic_action_home);
			break;
		case 1:
			//navIcon.setImageResource(R.drawable.ic_av_make_available_offline);
			break;
		case 2:
			//navIcon.setImageResource(R.drawable.ic_nav_category);
			break;
		case 3:
			//navIcon.setImageResource(R.drawable.ic_nav_subscribe);
			break;
		case 4:
			//navIcon.setImageResource(R.drawable.ic_content_email);
			break;
		case 5:
			//navIcon.setImageResource(R.drawable.ic_action_about);
			break;
		case 6:
			//navIcon.setImageResource(R.drawable.ic_action_help);
			break;

		default:
			break;
		}
		
		return convertView;
	}
}
