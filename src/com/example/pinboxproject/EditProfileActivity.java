package com.example.pinboxproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends Activity {
	
	private EditText editName;
	private EditText editEmail;
	private EditText editLocation;
	private EditText editAboutMe;
	
	private Button buttonChangePic;
	private Button buttonSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

	private void init(){
		
		buttonChangePic = (Button)findViewById(R.id.edit_profile_button_change_pic);
		buttonSave = (Button)findViewById(R.id.edit_profile_button_save);
		
		editName = (EditText)findViewById(R.id.edit_profile_edittext_name);
		editEmail = (EditText)findViewById(R.id.edit_profile_edittext_email);
		editLocation = (EditText)findViewById(R.id.edit_profile_edittext_location);
		editAboutMe = (EditText)findViewById(R.id.edit_profile_edittext_about);
		
		buttonSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
