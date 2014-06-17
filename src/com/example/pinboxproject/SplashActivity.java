package com.example.pinboxproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.entity.Settings;

public class SplashActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGTH = 1000;
	MyPrePopulatedDBHelper mdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		init();
		
		//getActionBar().hide();
		
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Main-Activity. */
            	if(Settings.loggedUser==null)
            	{
            		Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
            	}
            	else 
            	{
            		Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
            	}
                
            }
        }, SPLASH_DISPLAY_LENGTH);
	}
	private void init()
	{
		mdb= MyPrePopulatedDBHelper.getInstance(this, "tik");
		Settings.categories=mdb.getAllCats();
		getActionBar().hide();
		
	}
}
