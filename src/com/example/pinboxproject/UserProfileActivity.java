package com.example.pinboxproject;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.pinboxproject.adapters.PagerAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;


public class UserProfileActivity extends NavigationActivity {
	
	private Button buttonSelectPins;
	private Button buttonSelectAlbums;
	private Button buttonEditProfile;
	private Button buttonAddAlbum;
	private ViewPager pager;
	private PagerAdapter pagerAdapter;
	MyPrePopulatedDBHelper mdh;
	ArrayList<Pin> userPins;
	
	private static final int SELF_USER_MODE = 0;
	private static final int GUEST_USER_MODE = 1;
	
	private int userMode;

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user_profile);
		
		init();
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}
	
	private void init(){
		
		userMode = SELF_USER_MODE;
		
		pullData();
		pager = (ViewPager)findViewById(R.id.profile_pager);
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new ProfilePinsFragment(userPins));
		fragments.add(new ProfileAlbumsFragment(this));
		pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, fragments);
		pager.setAdapter(pagerAdapter);
		
		buttonSelectPins = (Button)findViewById(R.id.profile_button_pin);
		buttonSelectAlbums = (Button)findViewById(R.id.profile_button_album);
		buttonEditProfile = (Button)findViewById(R.id.profile_button_edit_profile);
		buttonAddAlbum = (Button)findViewById(R.id.profile_button_add_album);
		
		if(userMode == SELF_USER_MODE){
			View view = (View)findViewById(R.id.profile_view_self);
			view.setVisibility(View.VISIBLE);
		}
		
		buttonSelectPins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectFragment(0);
			}
		});
		
		buttonSelectAlbums.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectFragment(1);
			}
		});
		
		buttonEditProfile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UserProfileActivity.this, EditProfileActivity.class);				
				UserProfileActivity.this.startActivity(intent);
			}
		});
		
		buttonAddAlbum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UserProfileActivity.this, CreateAlbumActivity.class);				
				UserProfileActivity.this.startActivity(intent);
				
			}
		});
				
	}
	
	private void pullData()
	{
		mdh=new MyPrePopulatedDBHelper(getApplicationContext(), "tik");
		userPins=mdh.getUserPins(Settings.loggedUser.getId());
	}
	private void selectFragment(int pos){
		pager.setCurrentItem(pos);
	}

}
