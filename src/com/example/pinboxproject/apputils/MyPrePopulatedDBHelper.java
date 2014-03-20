package com.example.pinboxproject.apputils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.example.pinboxproject.entity.Category;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Pin;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyPrePopulatedDBHelper extends SQLiteOpenHelper{
	public static String DB_PATH;

    //Database file name
   public static String DB_NAME;
   public SQLiteDatabase database;
   public final Context cntext;

    public SQLiteDatabase getDb() {
       return database;
   }
	public MyPrePopulatedDBHelper(Context context,String name) {
		super(context, name, null, 1);
		this.cntext=context;
		DB_NAME=name;
		String packageName = context.getPackageName();
	    DB_PATH = String.format("/data/data/%s/databases/", packageName);
	     
	    openDataBase();
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
	            this.getReadableDatabase();
	            try {
//	            	this.getReadableDatabase();
	                copyDataBase();
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
	    }
	    public ArrayList<Category> getAllCats()
	    {
	    	ArrayList<Category> cats=new ArrayList<Category>();
	    	if(this.database==null)
	    	{
	    		openDataBase();
	    	}
	    	SQLiteDatabase db=this.database;
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
	    	this.database.close();
	    	return cats;
	    			
	    }
	    private Category getCat(int id)
	    {
	    	Category cat=null;
	    	if(this.database==null)
	    	{
	    		openDataBase();
	    	}
	    	SQLiteDatabase db=this.database;
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
//	    	this.database.close();
	    	return cat;
	    }
	    private Location getLocation(int id)
	    {
	    	Location loc=null;
	    	
	    	if(this.database==null)
	    	{
	    		openDataBase();
	    	}
	    	SQLiteDatabase db=this.database;
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
//	    	this.database.close();
	    	return loc;
	    	
	    }
	    public ArrayList<Pin> getAllLocations()
	    {
	    	ArrayList<Pin> pins=new ArrayList<Pin>();
	    	ArrayList<Integer> catIds=new ArrayList<Integer>();
	    	if(this.database==null)
	    	{
	    		openDataBase();
	    	}
	    	SQLiteDatabase db=this.database;
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
	    	this.database.close();
			
	    	return pins;
	    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
