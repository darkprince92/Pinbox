package com.example.pinboxproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pinboxproject.AddPinFragment1.DataHandler;
import com.example.pinboxproject.entity.Location;

public class AddPinFragment2 extends Fragment{
	
	private Activity activity;
	
	private String title,imageStr;
	int catId;
	Location loc; 
	View view ;
	private EditText editTextDesc,editTextTags;
	private Button buttonCamera,buttonAdd;
	private static final int CAMERA_REQUEST = 1888;
	SendHandler sendObj;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view= inflater.inflate(R.layout.fragment_add_pin_2, container, false);
		init();
		return view;
	}
	private void init()
	{
		editTextDesc=(EditText)view.findViewById(R.id.add_pin_edittext_description);
		editTextTags=(EditText)view.findViewById(R.id.add_pin_edittext_tags);
		buttonCamera=(Button)view.findViewById(R.id.add_pin_button_add_image);
		buttonAdd=(Button)view.findViewById(R.id.add_pin_button_add_2);
		buttonCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
	            getActivity().startActivityForResult(cameraIntent, CAMERA_REQUEST);
			}
		});
		buttonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendFinal();
			}
		});
	}
	void sendFinal()
	{
		String desc=editTextDesc.getText().toString();
		String tags=editTextTags.getText().toString();
		sendObj.sendDataWithAdditional(title, catId, loc, desc, tags, imageStr);
		
	}
	public void getDataFromfirst(String title, int catId, Location loc)
	{
		this.title=title;
		this.catId=catId;
		this.loc=loc;
	}
	public void getImageData(String imageStr)
	{
		this.imageStr=imageStr;
	}
	public interface SendHandler
	{
		public void sendDataWithAdditional(String title, int catId, Location loc,String desc,String tags,String imageStr);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (activity instanceof SendHandler) {
			sendObj = (SendHandler) activity;
		    } else {
		      throw new ClassCastException(activity.toString()
		          + " must implemenet AddpinFragment2.SendHandler");
		    }
	}
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDetach()
	 */
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		sendObj=null;
	}

}
