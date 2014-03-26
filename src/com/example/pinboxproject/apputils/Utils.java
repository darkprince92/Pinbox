package com.example.pinboxproject.apputils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.database.Cursor;

import com.example.pinboxproject.entity.Category;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Pin;

public class Utils {
	public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
	
	public static ArrayList<Pin> cursorToPinList(Cursor c)
	{
		Cursor f=c;
		ArrayList<Pin> allPins=new ArrayList<Pin>();
		for(int i=0;i<c.getCount();i++)
		{
			Location loc;
			Category cat;
			Pin pin; 
			
			int id=f.getInt(f.getColumnIndex("LOCATION_ID"));
			double lat=f.getDouble(f.getColumnIndex("LATITUDE"));
			double lng=f.getDouble(f.getColumnIndex("LONGITUDE"));
			
			
			loc=new Location(lat,lng,"","","");
			
			
//			cat=getCat(catId);
//			loc=getLocation(id);
			
			String name=f.getString(f.getColumnIndex("LOCATION_NAME"));
			String desc=f.getString(f.getColumnIndex("DESCRIPTION"));
			String pinTime=f.getString(f.getColumnIndex("PINNING_TIME"));
			
			String catName=f.getString(f.getColumnIndex("CATEGORY_NAME"));
			int catId=f.getInt(f.getColumnIndex("CATEGORY_ID"));
			cat=new Category(catId, catName);
			
			
			pin=new Pin(loc, name, desc, "admin", pinTime, cat, id, 0, 0);
			allPins.add(pin);
			f.moveToNext();
			
		}
		return allPins;
	}

}
