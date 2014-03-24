package com.example.pinboxproject;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.pinboxproject.AddPinToAlbumFragment.PinHandler;
import com.example.pinboxproject.adapters.AddPinToAlbumAdapter.PinFilterer;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.MyThread;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;

public class CreateAlbumActivity extends FragmentActivity implements PinFilterer,PinHandler{
	
	private EditText editTitle;
	private EditText editDescription;
	
	private Button buttonAddPins;
	private Button buttonSave;
	
	private GridView gridPins;
	
	String title,desc,pinList;
	private ArrayList<Pin> addedPinId;
	private AddPinToAlbumFragment addPinDialog;
	ProgressDialog pd;

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
		addPinDialog = new AddPinToAlbumFragment(this);
		addPinDialog.show(getSupportFragmentManager(), "Add Pin");
	}
	
	
	private void save(){
		
	}

	@Override
	public void filterPin(ArrayList<Boolean> pinCheckList) {
		// TODO Auto-generated method stub
		addPinDialog.getPins(pinCheckList);
	}

	private void getData()
	{
		title=editTitle.getText().toString();
		desc=editDescription.getText().toString();
				
	}
	private void sendData()
	{
		insertAlbumLocally();
		pd=ProgressDialog.show(getApplicationContext(), "Creating album", null);
		ArrayList<NameValuePair> dataToSend=new ArrayList<NameValuePair>();
		dataToSend.add(new BasicNameValuePair("user_id", ""+Settings.loggedUser.getId()));
		dataToSend.add(new BasicNameValuePair("locs", ""+pinList));
		dataToSend.add(new BasicNameValuePair("title", title));
		dataToSend.add(new BasicNameValuePair("desc", desc));
		
		MyThread myThread=new MyThread(handler, dataToSend, Settings.BASE_URL+"addAlbum");
		myThread.start();
		
	}
	private void insertAlbumLocally()
	{
		MyPrePopulatedDBHelper mdh=new MyPrePopulatedDBHelper(getApplicationContext(), "tik");
		mdh.insertAlbum(title, desc, pinList);
	}
	@Override
	public void getAddedPins(String pins) {
		// TODO Auto-generated method stub
		pinList=pins;
		getData();
		sendData();
	}
	
	Handler handler=new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			JSONObject responseObj=null;
			pd.dismiss();
			Bundle b=msg.getData();
			String status=null;
			System.out.println(b.toString());
			String response=b.getString("response");
			if(response.equals("success"))
			{
				Toast.makeText(getApplicationContext(), "Album Added successfully", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(CreateAlbumActivity.this,UserProfileActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		}
	};
}
