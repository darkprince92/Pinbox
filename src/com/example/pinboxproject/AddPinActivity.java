package com.example.pinboxproject;

import java.util.ArrayList;

import com.example.pinboxproject.adapters.PagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class AddPinActivity extends FragmentActivity {
	
	private ViewPager pager;
	private PagerAdapter pagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_pin);
		
		pager = (ViewPager)findViewById(R.id.add_pin_pager);
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new AddPinFragment1(this));
		fragments.add(new AddPinFragment2(this));
		pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, fragments);
		pager.setAdapter(pagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_pin, menu);
		return true;
	}

}
