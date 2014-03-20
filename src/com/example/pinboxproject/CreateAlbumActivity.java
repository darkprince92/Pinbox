package com.example.pinboxproject;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.pinboxproject.entity.Pin;

public class CreateAlbumActivity extends FragmentActivity {
	
	private EditText editTitle;
	private EditText editDescription;
	
	private Button buttonAddPins;
	private Button buttonSave;
	
	private GridView gridPins;
	
	private ArrayList<Pin> addedPinId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_album);
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_album, menu);
		return true;
	}

	private void init(){
		editTitle = (EditText)findViewById(R.id.create_album_edittext_title);
		editDescription = (EditText)findViewById(R.id.create_album_edittext_description);
		
		buttonAddPins = (Button)findViewById(R.id.create_album_button_app_pins);
		buttonSave = (Button)findViewById(R.id.create_album_button_save);
		
		addedPinId = new ArrayList<Pin>();
		
		buttonAddPins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addPinToArray();
			}
		});
		
		buttonSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				save();
			}
		} );
	}
	
	private void addPinToArray(){
		AddPinToAlbumFragment addPinDialog = new AddPinToAlbumFragment(this);
		addPinDialog.show(getSupportFragmentManager(), "Add Pin");
	}
	
	private void save(){
		
	}
}
