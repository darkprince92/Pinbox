package com.example.pinboxproject.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pinboxproject.R;
import com.example.pinboxproject.entity.Pin;

public class GridPinAdapter extends BaseAdapter {

	ArrayList<Pin> pins;
	Activity a;
	public GridPinAdapter(Activity a,ArrayList<Pin> pins)
	{
		this.a=a;
		this.pins=pins;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pins.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pins.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return pins.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup Parent) {
		// TODO Auto-generated method stub
		View vi=convertView;
		TextView pinTitleText,pinCatText;
		if(convertView==null)
		{
			LayoutInflater inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.pin_grid_item, null);
			
		}
		pinTitleText=(TextView)vi.findViewById(R.id.pin_title_text);
		pinCatText=(TextView)vi.findViewById(R.id.pin_category_text);
		
		pinTitleText.setText(pins.get(position).getName());
		pinCatText.setText(pins.get(position).getCat().getCatName());
		return vi;
	}

}
