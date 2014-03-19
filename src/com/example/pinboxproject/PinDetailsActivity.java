package com.example.pinboxproject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.pinboxproject.apputils.MapSetup;
import com.google.android.gms.maps.GoogleMap;

public class PinDetailsActivity extends NavigationActivity {
	
	private GoogleMap map;
	private Button buttonComment;
	private Button buttonReport;
	private TextView textTitle;
	private TextView textCategory;
	private TextView textUser;
	private TextView textNoPhoto;

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pin_details, menu);
		return true;
	}*/

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_pin_details);
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}
	
	private void init(){

		MapSetup mapSetup = new MapSetup(this, R.id.pin_details_map_fragment);
		map = mapSetup.getMap();
		buttonComment = (Button)findViewById(R.id.pin_button_comment);
		buttonReport = (Button)findViewById(R.id.pin_button_report);
		textTitle = (TextView)findViewById(R.id.pin_text_title);
		textCategory = (TextView)findViewById(R.id.pin_text_category);
		textUser = (TextView)findViewById(R.id.pin_text_user);
		textNoPhoto = (TextView)findViewById(R.id.pin_text_no_photo);
		
		buttonComment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewComments();
			}
		});
	}
	
	private void viewComments(){
		CommentDialogFragment dialog = new CommentDialogFragment(this);
		dialog.show(getSupportFragmentManager(), "Comments");
	}

}
