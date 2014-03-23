package com.example.pinboxproject.adapters;

import com.example.pinboxproject.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryGridAdapter extends BaseAdapter{
	
	private Activity activity;
	private String[] categories;
	private Integer[] icons;
	
	ImageView catImage;
	TextView catName;
	
	public CategoryGridAdapter(Activity activity, String[] categories, Integer[] icons) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.categories = categories;
		this.icons = icons;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categories.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categories[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//ViewHolder viewHolder;
		
		
		
		if(convertView == null){
			//<-----With ViewHolder---->
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.category_grid_item, null);
			
			//viewHolder = new ViewHolder();
			catImage = (ImageView)convertView.findViewById(R.id.category_item_image);
			catName = (TextView)convertView.findViewById(R.id.category_item_title);
			
			//convertView.setTag(viewHolder);
			
		//}else{			
		//	viewHolder = (ViewHolder)convertView.getTag();
		}
		
		catImage.setImageDrawable(activity.getResources().getDrawable(icons[position]));
		catName.setText(categories[position]);
		
		return convertView;		
	}

}
