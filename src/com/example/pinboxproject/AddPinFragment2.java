package com.example.pinboxproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddPinFragment2 extends Fragment{
	
	private Activity activity;
	
	public AddPinFragment2(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_add_pin_2, container, false);
		
		return view;
	}

}
