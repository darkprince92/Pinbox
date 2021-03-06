package com.example.pinboxproject.apputils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.R.raw;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;

import com.example.pinboxproject.entity.Category;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;

public class MyPrePopulatedDBHelper extends SQLiteOpenHelper{
	
	private static MyPrePopulatedDBHelper helperInstance;
	public static String DB_PATH;

    //Database file name
   public static String DB_NAME;
   public  SQLiteDatabase database;
   public final Context cntext;

    public SQLiteDatabase getDb() {
       return database;
   }
	private MyPrePopulatedDBHelper(Context context,String name) {
		super(context, name, null, 1);
		this.cntext=context;
		DB_NAME=name;
		String packageName = context.getPackageName();
	    DB_PATH = String.format("/data/data/%s/databases/", packageName);
	     
	    openDataBase();
	}
	
	public  static MyPrePopulatedDBHelper getInstance(Context context,String name)
	{
		if(helperInstance==null)
		{
			helperInstance=new MyPrePopulatedDBHelper(context, name);
		}
		return helperInstance;
	}
	private void copyDataBase() throws IOException {
        //Open a stream for reading from our ready-made database
        //The stream source is located in the assets
        InputStream externalDbStream = cntext.getAssets().open(DB_NAME+".sqlite");

         //Path to the created empty database on your Android device
        String outFileName = DB_PATH + DB_NAME;
        System.out.println(outFileName);
         //Now create a stream for writing the database byte by byte
        OutputStream localDbStream = new FileOutputStream(outFileName);

         //Copying the database
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = externalDbStream.read(buffer)) > 0) {
            localDbStream.write(buffer, 0, bytesRead);
        }
        //Don�t forget to close the streams
        localDbStream.close();
        externalDbStream.close();
    }
	public SQLiteDatabase openDataBase() throws SQLException {
	        String path = DB_PATH + DB_NAME;
	        if (database == null) {
	        	
	            createDataBase();
	            database = SQLiteDatabase.openDatabase(path, null,
	                SQLiteDatabase.OPEN_READWRITE);
	        }
	        return database;
	    }
	 public void createDataBase() {
	        boolean dbExist = checkDataBase();
	        System.out.println("Is Exists "+dbExist);
	        if (!dbExist) {
//	            this.getReadableDatabase();
	            try {
	            	SQLiteDatabase db=this.getReadableDatabase();
	                copyDataBase();
	                db.close();
	            } catch (IOException e) {
	                Log.e(this.getClass().toString(), "Copying error");
	                throw new Error("Error copying database!");
	            }
	        } else {
	            Log.i(this.getClass().toString(), "Database already exists");
	        }
	    }
	//Performing a database existence check
    private boolean checkDataBase() {
    	String path=DB_PATH+DB_NAME;
    	File file=new File(path);
    	if(file.exists())
    	{
    		return true;
    	}
        return false;
    }
	    
    public synchronized void close()
    {
    	if(database!=null)
    	{
    		database.close();
    	}
    	
    	System.out.println("Database Closed");
    }
    public ArrayList<Category> getAllCats()
    {
    	ArrayList<Category> cats=new ArrayList<Category>();
    	
    	System.out.println("In catagories pull");
    	SQLiteDatabase db=this.getReadableDatabase();
    	String col[]={"CATEGORY_ID","CATEGORY_NAME"};
    	Cursor c=db.query("pin_category", col, null, null, null, null,null);
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		for(int i=0;i<c.getCount();i++)
			{
    			String name=c.getString(c.getColumnIndex("CATEGORY_NAME"));
    			int id=c.getInt(c.getColumnIndex("CATEGORY_ID"));
    			Category cat=new Category(id, name);
    			cats.add(cat);
    			c.moveToNext();
    			
			}
    		
		
		}
    	c.close();
    	db.close();
//	    	this.database.close();
    	return cats;
    			
    }
    private Category getCat(int id)
    {
    	Category cat=null;
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String col[]={"CATEGORY_ID","CATEGORY_NAME"};
    	String whereArgs[]={id+""};
    	Cursor c=db.query("pin_category", col, "CATEGORY_ID=?", whereArgs, null, null,null);
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		int catId=id;
    		String catName=c.getString(c.getColumnIndex("CATEGORY_NAME"));
    		cat=new Category(catId, catName);
		}
    	c.close();
    	db.close();
//	    	this.database.close();
    	return cat;
    }
    private Location getLocation(int id)
    {
    	Location loc=null;
    	
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String col[]={"LATITUDE","LONGITUDE","district","thana","location_address"};
    	Cursor c=db.query("pin_location", col, "LOCATION_ID="+id, null, null, null,null);
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		double lat,lng;
    		String district, thana, address;
    		lat=c.getDouble(c.getColumnIndex("LATITUDE"));
    		lng=c.getDouble(c.getColumnIndex("LONGITUDE"));
    		district=c.getString(c.getColumnIndex("district"));
    		thana=c.getString(c.getColumnIndex("thana"));
    		address=c.getString(c.getColumnIndex("location_address"));
    		loc=new Location(lat, lng, district, thana, address);
		
		}
    	c.close();
    	db.close();
//	    	this.database.close();
    	return loc;
    	
    }
    
    public ArrayList<Pin> getAllLocations()
    {
    	ArrayList<Pin> pins=new ArrayList<Pin>();
    	ArrayList<Integer> catIds=new ArrayList<Integer>();
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String col[]={"CATEGORY_ID","LOCATION_NAME","LOCATION_ID","DESCRIPTION","up_vote","down_vote","PINNING_TIME","USER_ID"};
    	Cursor c=db.query("pin_location", col, null, null, null, null,"PINNING_TIME DESC","0,100");
    	
    	
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		for(int i=0;i<c.getCount();i++)
			{
//	    			Location loc;
//	    			Category cat;
    			Pin pin; 
    			int catId=c.getInt(c.getColumnIndex("CATEGORY_ID"));
    			int id=c.getInt(c.getColumnIndex("LOCATION_ID"));
    			catIds.add(catId);
//	    			cat=getCat(catId);
//	    			loc=getLocation(id);
    			
    			String name=c.getString(c.getColumnIndex("LOCATION_NAME"));
    			
    			String desc=c.getString(c.getColumnIndex("DESCRIPTION"));
    			String pin_time=c.getString(c.getColumnIndex("PINNING_TIME"));
    			int upVote=c.getInt(c.getColumnIndex("up_vote"));
    			int downVote=c.getInt(c.getColumnIndex("down_vote"));
    			
    			pin=new Pin(null, name, desc, "admin", pin_time, null, id, upVote, downVote);
    			pins.add(pin);
    			c.moveToNext();
    			
			}
    		
		
		}
    	c.close();
    	
    	for(int i=0;i<pins.size();i++)
    	{
    		Pin p=pins.get(i);
    		Location loc=getLocation(p.getId());
    		Category cat=getCat(catIds.get(i));
    		pins.get(i).setLoc(loc);
    		pins.get(i).setCat(cat);
    	}
    	db.close();
    	
    	
//	    	this.database.close();
		
    	return pins;
    }
    
    public ArrayList<Pin> getUserPins(int userId){
    	
    	ArrayList<Pin> pins=new ArrayList<Pin>();
    	ArrayList<Integer> catIds=new ArrayList<Integer>();
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String col[]={"CATEGORY_ID","LOCATION_NAME","LOCATION_ID","DESCRIPTION","up_vote","down_vote","PINNING_TIME","USER_ID"};
    	Cursor c=db.query("pin_location", col, "USER_ID=" + userId, null, null, null,"PINNING_TIME DESC","0,20");
    	
    	
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		for(int i=0;i<c.getCount();i++)
			{
//	    			Location loc;
//	    			Category cat;
    			Pin pin; 
    			int catId=c.getInt(c.getColumnIndex("CATEGORY_ID"));
    			int id=c.getInt(c.getColumnIndex("LOCATION_ID"));
    			catIds.add(catId);
//	    			cat=getCat(catId);
//	    			loc=getLocation(id);
    			
    			String name=c.getString(c.getColumnIndex("LOCATION_NAME"));
    			
    			String desc=c.getString(c.getColumnIndex("DESCRIPTION"));
    			String pin_time=c.getString(c.getColumnIndex("PINNING_TIME"));
    			int upVote=c.getInt(c.getColumnIndex("up_vote"));
    			int downVote=c.getInt(c.getColumnIndex("down_vote"));
    			
    			pin=new Pin(null, name, desc, "admin", pin_time, null, id, upVote, downVote);
    			pins.add(pin);
    			c.moveToNext();	    			
			}			
		}
    	c.close();
    	
    	for(int i=0;i<pins.size();i++)
    	{
    		Pin p=pins.get(i);
    		Location loc=getLocation(p.getId());
    		Category cat=getCat(catIds.get(i));
    		pins.get(i).setLoc(loc);
    		pins.get(i).setCat(cat);
    	}
    	db.close();
//	    	this.database.close();
		
    	return pins;
    	//return null;
    }

    public ArrayList<Pin> getAllLocations(int limit)
    {
    	ArrayList<Pin> pins=new ArrayList<Pin>();
    	ArrayList<Integer> catIds=new ArrayList<Integer>();
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String limitStr="0,"+limit;
    	String col[]={"LOCATION_NAME","LOCATION_ID","LATITUDE","LONGITUDE"};
    	Cursor c=db.query("pin_location", null, null, null, null, null,"PINNING_TIME DESC",limitStr);
    	
    	
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		for(int i=0;i<c.getCount();i++)
			{
    			Location loc;
//	    			Category cat;
    			Pin pin; 
    			
    			int id=c.getInt(c.getColumnIndex("LOCATION_ID"));
    			double lat=c.getDouble(c.getColumnIndex("LATITUDE"));
    			double lng=c.getDouble(c.getColumnIndex("LONGITUDE"));
    			loc=new Location(lat,lng,"","","");
    			    			
    			String name=c.getString(c.getColumnIndex("LOCATION_NAME"));	    			
    			
    			pin=new Pin(loc, name, null, "admin", null, null, id, 0, 0);
    			pins.add(pin);
    			c.moveToNext();
    			
			}
    				
		}
    	c.close();
    	db.close();
//	    	this.database.close();
		
    	return pins;
    }
    
	    
    public Cursor getLocationCursor(int limit,int userId,String where)
    {
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String cols="pin_location.LOCATION_ID,USER_NAME,pin_location.CATEGORY_ID,LOCATION_NAME,LATITUDE,LONGITUDE,CATEGORY_NAME,DESCRIPTION,PINNING_TIME,up_vote,down_vote";
    	String rawQuery="SELECT "+cols+" FROM pin_location,pin_user INNER JOIN pin_category on pin_location.CATEGORY_ID=pin_category.CATEGORY_ID WHERE pin_location.USER_ID=pin_user.USER_ID ";
    	if(userId!=0)
    	{
    		rawQuery+=" AND pin_location.USER_ID="+userId;
    		if(where!=null)
    		{
    			rawQuery+=" AND "+where;
    		}	
    	}
    	else
    	{
    		if(where!=null)
    		{
    			rawQuery+=" AND "+where;
    		}
    	}
    	
    	rawQuery+=" ORDER BY PINNING_TIME DESC LIMIT 0,"+limit;
    	System.out.println(rawQuery);
    	Cursor c=db.rawQuery(rawQuery, null);
    	c.moveToFirst();
    	db.close();
    	return c;
    }
    
    public Cursor searchLocationCursor(String tag)
    {
    	SQLiteDatabase db=this.getReadableDatabase();
    	String rawQuery="SELECT * FROM pin_location,pin_user INNER JOIN pin_category on pin_location.CATEGORY_ID=pin_category.CATEGORY_ID  where pin_location.USER_ID=pin_user.USER_ID AND ";
    	rawQuery+="LOCATION_NAME LIKE '%"+tag+"%' ";
    	rawQuery+=" OR ";
    	rawQuery+="location_address LIKE '%"+tag+"%' ";
    	rawQuery+=" OR ";
    	rawQuery+="district LIKE '%"+tag+"%' ";
    	rawQuery+=" OR ";
    	rawQuery+="thana LIKE '%"+tag+"%' ";
    	rawQuery+=" OR ";
    	rawQuery+="CATEGORY_NAME LIKE '%"+tag+"%' ";
    	rawQuery+=" ORDER BY PINNING_TIME DESC LIMIT 0,100";
	    System.out.println(rawQuery);
    			
    	Cursor c=db.rawQuery(rawQuery, null);
    	c.moveToFirst(); 
//	    	db.close();
    	return c;
    }
    
    public Cursor searchAlbumCursor(String tag)
    {
    	SQLiteDatabase db=this.getReadableDatabase();
    	
    	Cursor c=db.query("pin_album", null, "ALBUM_NAME LIKE '%"+tag+"%'", null, null, null, null);
    	c.moveToFirst();
//	    	db.close();
    	return c;
    }
    
	    public Cursor getUserAlbums(int userId)
	    {
	    	Cursor c;
	    	SQLiteDatabase db=this.getReadableDatabase();
	    	
	    	c=db.query("pin_album", null, "USER_ID="+userId, null, null, null, null);
	    	c.moveToFirst();
	    	db.close();
	    	return c;
	    }
	    public Cursor searchUserCursor(String tag)
	    {
	    	SQLiteDatabase db=this.getReadableDatabase();
	    	Cursor c=db.query("pin_user", null, "USER_NAME LIKE '%"+tag+"%'", null, null, null, null);
	    	c.moveToFirst();
//	    	db.close();
	    	return c;
	    }
	    public boolean insertPin(Pin p)
	    {
	    	SQLiteDatabase db=this.getWritableDatabase();
	    	ContentValues values = new ContentValues();
	    	values.put("DESCRIPTION", p.getDescription()); 
	    	values.put("LOCATION_NAME", p.getName());
	    	values.put("USER_ID", Settings.loggedUser.getId());
	    	values.put("CATEGORY_ID", p.getCat().getId());
	    	values.put("LONGITUDE", p.getLoc().getLongitude());
	    	values.put("LATITUDE", p.getLoc().getLatitude());
	    	values.put("PINNING_TIME", p.getTime());
	    	values.put("location_address", p.getLoc().getAddress());
	    	values.put("district", p.getLoc().getDistrict());
	    	values.put("thana", p.getLoc().getThana());
	    	values.put("up_vote",0);
	    	values.put("down_vote", 0);
	    	
	    	long id=db.insert("pin_location", null, values);
	    	System.out.println("last inserted id :"+id);
	    	db.close();
	    	return true;
	    }
	    public boolean insertAlbum(String title,String desc,String locs)
	    {
	    	SQLiteDatabase db=this.getWritableDatabase();
	    	ContentValues values = new ContentValues();
	    	values.put("ALBUM_NAME", title);
	    	values.put("USER_ID", Settings.loggedUser.getId());
	    	values.put("ALBUM_DESC", desc);
	    	values.put("LOCATION_ID", locs);
	    	long id=db.insert("pin_album", null, values);
	    	System.out.println("last inserted id :"+id);
	    	db.close();
	    	return true;
	    }
	    @Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Pin> pinSearch(String str){
		
		ArrayList<Pin> pins=new ArrayList<Pin>();
    	ArrayList<Integer> catIds=new ArrayList<Integer>();
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	String col[]={"CATEGORY_ID","LOCATION_NAME","LOCATION_ID"};
    	String s = "LOCATION_NAME LIKE" + "'%" + str + "%'";
    	s += " OR location_address" + "'%" + str + "%'";
    	s += " OR district" + "'%" + str + "%'";
    	s += " OR thana" + "'%" + str + "%'";
    	Cursor c=db.query("pin_location", null,  s , null, null, null,"PINNING_TIME DESC","0,50");
    	
    	
    	if(c!=null && c.getCount()>0)
		{
    		c.moveToFirst();
    		for(int i=0;i<c.getCount();i++)
			{
//    			Location loc;
//    			Category cat;
    			Pin pin; 
    			int catId=c.getInt(c.getColumnIndex("CATEGORY_ID"));
    			int id=c.getInt(c.getColumnIndex("LOCATION_ID"));
    			catIds.add(catId);
//    			cat=getCat(catId);
//    			loc=getLocation(id);
    			
    			String name=c.getString(c.getColumnIndex("LOCATION_NAME"));
    			
    			String desc=c.getString(c.getColumnIndex("DESCRIPTION"));
    			String pin_time=c.getString(c.getColumnIndex("PINNING_TIME"));
    			int upVote=c.getInt(c.getColumnIndex("up_vote"));
    			int downVote=c.getInt(c.getColumnIndex("down_vote"));
    			
    			pin=new Pin(null, name, desc, "admin", pin_time, null, id, upVote, downVote);
    			pins.add(pin);
    			c.moveToNext();	    			
			}			
		}
    	c.close();
    	
    	for(int i=0;i<pins.size();i++)
    	{
    		Pin p=pins.get(i);
    		Location loc=getLocation(p.getId());
    		Category cat=getCat(catIds.get(i));
    		pins.get(i).setLoc(loc);
    		pins.get(i).setCat(cat);
    	}
    	//this.database.close();
		db.close();
    	return pins;
		
	}

}
