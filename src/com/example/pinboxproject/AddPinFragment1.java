package com.example.pinboxproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pinboxproject.apputils.MapSetup;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Settings;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class AddPinFragment1 extends Fragment{
	
	private GoogleMap map;
//	private Activity activity;
	
	private EditText editTextTitle;
	private Spinner spinnerCategory;
	private TextView textViewAdditional;
	private Button buttonAddPin;
	private DataHandler dataObj;
	MapSetup mapSetup;
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 view= inflater.inflate(R.layout.fragment_add_pin_1, container, false);
		mapSetup = new MapSetup(getActivity(), R.id.add_pin_map_fragment);
		map = mapSetup.getMap();
		
		init();
		return view;
	}
	private void init()
	{
		editTextTitle=(EditText)view.findViewById(R.id.add_pin_edittext_title);
		spinnerCategory=(Spinner)view.findViewById(R.id.add_pin_spinner_category);
		textViewAdditional=(TextView)view.findViewById(R.id.add_pin_text_additional);
		buttonAddPin=(Button)view.findViewById(R.id.app_pin_button_add);
		
		buttonAddPin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData(1);
			}
		});
		
		textViewAdditional.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData(0);
			}
		});
		ArrayList<String> cats=new ArrayList<String>();
		if(Settings.categories!=null)
		{
			if(Settings.categories.size()>0)
			{
				for(int i=0;i<Settings.categories.size();i++)
				{
					String cat=Settings.categories.get(i).getCatName();
					cats.add(cat);
				}
			}
		}
		ArrayAdapter<String> sortSpinnerAdapter = new ArrayAdapter<String>(getActivity(), 
	    		android.R.layout.simple_spinner_dropdown_item, 
	    		cats);
	    spinnerCategory.setAdapter(sortSpinnerAdapter);
	    
	}
	private void getData(int flag)
	{
		String title=editTextTitle.getText().toString();
		int id=Settings.categories.get(spinnerCategory.getSelectedItemPosition()).getId();
		LatLng ll=mapSetup.getMyLocation();
		
		Geocoder geocoder;
		List<Address> addresses=null;
		geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
		
		try {
			addresses = geocoder.getFromLocation(ll.latitude, ll.longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Address a=addresses.get(0);
		String address=a.getFeatureName();
		String thana=a.getSubLocality();
		String district=a.getSubAdminArea();
		Location loc=new Location(ll.latitude, ll.longitude, district, thana, address);
		if(flag==0)
		{
			dataObj.addData(title, id, loc);
			AddPinActivity.getViewPager().setCurrentItem(1);
		}
		else
		{
			dataObj.sendData(title, id, loc);
		}
		
	}
	
	public interface DataHandler
	{
		public void addData(String title,int catId,Location loc);
		public void sendData(String title,int catId,Location loc);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (activity instanceof DataHandler) {
			dataObj = (DataHandler) activity;
		    } else {
		      throw new ClassCastException(activity.toString()
		          + " must implemenet AddpinFragment1.DataHandler");
		    }
	}
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDetach()
	 */
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		dataObj=null;
	}
	
	

}
