package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pinboxproject.R;
import com.example.pinboxproject.apputils.ThumbnailConverter;
import com.example.pinboxproject.entity.Pin;

public class StaggeredAdapter extends BaseAdapter{
	
	private Activity activity;
	private int screenWidth;
	private int columnNum;
	
	private ImageView imageView;
	private ArrayList<Bitmap> imageThumbList;
	private ArrayList<Pin> pins;
	
	
	public StaggeredAdapter(Activity activity, int screenWidth, int colNum,ArrayList<Pin> pins){
		this.activity = activity;
		this.screenWidth = screenWidth;
		this.columnNum = colNum;
		this.pins=pins;
		
		
		int i;
		imageThumbList = new ArrayList<Bitmap>();
		
		Bitmap image = BitmapFactory.decodeResource(activity.getResources(), mThumbIds[0]);
		imageThumbList.add(ThumbnailConverter.CreateThumbnail(image, screenWidth/columnNum)) ;
		image = BitmapFactory.decodeResource(activity.getResources(), mThumbIds[3]);
		imageThumbList.add(ThumbnailConverter.CreateThumbnail(image, screenWidth/columnNum)) ;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pins.size();
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
			LayoutInflater inflater2 = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater2.inflate(R.layout.staggered_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.image_item);
			viewHolder.textTitle = (TextView)convertView.findViewById(R.id.text_item_title);
			viewHolder.textCategory = (TextView)convertView.findViewById(R.id.text_item_category);
			
			convertView.setTag(viewHolder);
			
		}else{			
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		Bitmap imageThumb = imageThumbList.get(position%2);
		viewHolder.imageView.setImageBitmap(imageThumb);
		String size = imageThumb.getHeight() + " " + imageThumb.getWidth();
		viewHolder.textCategory.setText(pins.get(position).getCat().getCatName());	
		viewHolder.textTitle.setText(pins.get(position).getName());
		
		return convertView;		
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,         
            R.drawable.sample_7, R.drawable.sample_0,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_1,
            R.drawable.sample_7
    };
    
    private static class ViewHolder{
    	ImageView imageView;
    	TextView textTitle;
    	TextView textCategory;
    }
}
