package com.example.pinboxproject.apputils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;

import com.example.pinboxproject.entity.Album;
import com.example.pinboxproject.entity.Category;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.User;

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
//		c.close();
		
		int l=f.getCount();
		f.moveToFirst();
//		System.out.println("Pin Count: "+c.getCount());
		ArrayList<Pin> allPins=new ArrayList<Pin>();
		for(int i=0;i<l;i++)
		{
			Location loc;
			Category cat;
			Pin pin; 
			
//			System.out.println(i);
			int id=f.getInt(f.getColumnIndex("LOCATION_ID"));
			double lat=f.getDouble(f.getColumnIndex("LATITUDE"));
			double lng=f.getDouble(f.getColumnIndex("LONGITUDE"));
			
			
			loc=new Location(lat,lng,"","","");
			
			
//			cat=getCat(catId);
//			loc=getLocation(id);
			
			String name=f.getString(f.getColumnIndex("LOCATION_NAME"));
			String desc=f.getString(f.getColumnIndex("DESCRIPTION"));
			String pinTime=f.getString(f.getColumnIndex("PINNING_TIME"));
			
			String uName=f.getString(f.getColumnIndex("USER_NAME"));
			
			String catName=f.getString(f.getColumnIndex("CATEGORY_NAME"));
			int catId=f.getInt(f.getColumnIndex("CATEGORY_ID"));
			cat=new Category(catId, catName);
			
			
			pin=new Pin(loc, name, desc, uName, pinTime, cat, id, 0, 0);
			allPins.add(pin);
			f.moveToNext();
			
		}
//		f.close();
		return allPins;
	}
	
	public static ArrayList<Album> cursorToAlbums(Cursor c)
	{
		ArrayList<Album> albums=new ArrayList<Album>();
		Cursor f=c;
		
		System.out.println("Row count "+c.getCount()+" Column Count: "+c.getColumnCount());
		f.moveToFirst();
		for(int i=0;i<f.getCount();i++)
		{
			Album a;
			String title=f.getString(f.getColumnIndex("ALBUM_NAME"));
			String desc=f.getString(f.getColumnIndex("ALBUM_DESC"));
			String locs=f.getString(f.getColumnIndex("LOCATION_ID"));
			int userId=f.getInt(f.getColumnIndex("USER_ID"));
			
			a=new Album(userId, title, desc, locs);
			albums.add(a);
			f.moveToNext();
		}
		f.close();
		return albums;
	}
	
	public static ArrayList<User> cursorToUsers(Cursor c)
	{
		ArrayList<User> users=new ArrayList<User>();
		Cursor f=c;
		f.moveToFirst();
		for(int i=0;i<f.getCount();i++)
		{
			String userName=f.getString(f.getColumnIndex("USER_NAME"));
			int userId=f.getInt(f.getColumnIndex("USER_ID"));
			String email=f.getString(f.getColumnIndex("EMAIL"));
			User u=new User(userId, userName, email);
			users.add(u);
			f.moveToNext();
		}
		f.close();
		return users;
	}
}
