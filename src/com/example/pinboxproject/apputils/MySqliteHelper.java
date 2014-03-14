package com.example.pinboxproject.apputils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {
	private static String DB_NAME="pinbox";
	private static int version=1;

	public MySqliteHelper(Context context) {
		super(context, DB_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="CREATE TABLE test_table (id INTEGER PRIMARY KEY, name TEXT, email TEXT, address TEXT) ";
		System.out.println(sql);
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	// 
	public long insertPerson(Person p)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		
		values.put("name", p.getName());
		values.put("email", p.getEmail());
		values.put("address", p.getAddress());
		long l=db.insert("test_table", null, values);
		db.close();
		return l;
	}
	public ArrayList<Person> getAllPersons()
	{
		ArrayList<Person> allPersons=new ArrayList<Person>();
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.query("test_table", null, null, null, null, null, null);
		if(c!=null && c.getCount()>0)
		{
			c.moveToFirst();
			for(int i=0;i<c.getCount();i++)
			{
				int id=c.getInt(c.getColumnIndex("id"));
				String name=c.getString(c.getColumnIndex("name"));
				String email=c.getString(c.getColumnIndex("email"));
				String address=c.getString(c.getColumnIndex("address"));
				Person p=new Person(id, name, email,address);
				allPersons.add(p);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return allPersons;
	}

}
