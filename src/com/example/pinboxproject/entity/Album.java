package com.example.pinboxproject.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Album implements Parcelable{
	
	private int user_id;
	private String title,desc;
	String location_ids;
	public Album(int user_id, String title, String desc, String location_ids) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.desc = desc;
		this.location_ids = location_ids;
	}
	public Album(Parcel in)
	{
		readFromParcel(in);
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the location_ids
	 */
	public String getLocation_ids() {
		return location_ids;
	}
	/**
	 * @param location_ids the location_ids to set
	 */
	public void setLocation_ids(String location_ids) {
		this.location_ids = location_ids;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Album [user_id=" + user_id + ", title=" + title + ", desc="
				+ desc + ", location_ids=" + location_ids + "]";
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(user_id);
		dest.writeString(title);
		dest.writeString(desc);
		dest.writeString(location_ids);
	}
	
	private void readFromParcel(Parcel in) 
	{
		user_id=in.readInt();
		title=in.readString();
		desc=in.readString();
		location_ids=in.readString();
		
		
	}
	
	public static final Parcelable.Creator<Album> CREATOR=new Parcelable.Creator<Album>() {

		@Override
		public Album createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Album(source);
		}
		@Override
		public Album[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Album[size];
		}
	};
	
	
}
