package com.example.pinboxproject.apputils;

import com.example.pinboxproject.entity.Settings;

import android.content.Context;
import android.database.Cursor;

public class PinCursorLoader extends SimpleCursorLoader {

	private MyPrePopulatedDBHelper mHelper;
	private String type;
	
	
	public PinCursorLoader(Context context,MyPrePopulatedDBHelper mHelper,String type) {
		super(context);
		
		this.mHelper=mHelper;
		this.type=type;
		
	}

	@Override
	public Cursor loadInBackground() {
		// TODO Auto-generated method stub
		Cursor c=null;
		
		if(this.type.equals("ByCurrentUser"))
		{
			c=mHelper.getLocationCursor(100, Settings.loggedUser.getId(), null);
		}
		else if(this.type.equals("All"))
		{
			c=mHelper.getLocationCursor(8000, 0, null);
		}
		else if(this.type.equals("Home"))
		{
			c=mHelper.getLocationCursor(100, 0, null);
		}
		
		return c;
	}

}
