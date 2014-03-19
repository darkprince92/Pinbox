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
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CommentListAdapter extends BaseAdapter{
	
	private ArrayList<CommentData> commentData;
	//ArrayList<IncidentComment> comments;
	private Activity activity;
	
	public CommentListAdapter(Activity activity){
		//this.comments=comments;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return comments.size();
		return 5;
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
		TextView commentUserName,commentDesc;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.comment_list_row, null);
			
		}
		commentUserName=(TextView)convertView.findViewById(R.id.text_comment_name);
		commentDesc=(TextView)convertView.findViewById(R.id.text_view_comment_comment);
		
		//commentUserName.setText(comments.get(position).getAuthor());
		//commentDesc.setText(comments.get(position).getDescription());
		return convertView;
	}
	
	public class CommentData{
		public String name;
		public String comment;
	}

}
