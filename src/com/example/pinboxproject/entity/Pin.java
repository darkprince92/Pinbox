package com.example.pinboxproject.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;


public class Pin implements Parcelable{
	Location loc;
	String name,description;
	String username,time;
	Category cat;
	int id,upvote,downvote;
	
	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * @return the cat
	 */
	public Category getCat() {
		return cat;
	}
	/**
	 * @param cat the cat to set
	 */
	public void setCat(Category cat) {
		this.cat = cat;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the upvote
	 */
	public int getUpvote() {
		return upvote;
	}
	/**
	 * @param upvote the upvote to set
	 */
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	/**
	 * @return the downvote
	 */
	public int getDownvote() {
		return downvote;
	}
	/**
	 * @param downvote the downvote to set
	 */
	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}
	public Pin(Location loc, String name, String description, String username,
			String time, Category cat, int upvote, int downvote) {
		super();
		this.loc = loc;
		this.name = name;
		this.description = description;
		this.username = username;
		this.time = time;
		this.cat = cat;
		this.upvote = upvote;
		this.downvote = downvote;
	}
	public Pin(Location loc, String name, String description, String username,
			String time, Category cat, int id, int upvote, int downvote) {
		super();
		this.loc = loc;
		this.name = name;
		this.description = description;
		this.username = username;
		this.time = time;
		this.cat = cat;
		this.id = id;
		this.upvote = upvote;
		this.downvote = downvote;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pin [loc=" + loc + ", name=" + name + ", description="
				+ description + ", username=" + username + ", time=" + time
				+ ", cat=" + cat + ", id=" + id + ", upvote=" + upvote
				+ ", downvote=" + downvote + "]";
	}
	public Pin(Parcel in)
	{
		readFromParcel(in);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeParcelable(loc, PARCELABLE_WRITE_RETURN_VALUE);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(username);
		dest.writeString(time);
		dest.writeParcelable(cat,PARCELABLE_WRITE_RETURN_VALUE);
		dest.writeInt(id);
		dest.writeInt(upvote);
		dest.writeInt(downvote);
		
		
	}
	private void readFromParcel(Parcel in) 
	{
		loc=in.readParcelable(Pin.class.getClassLoader());
		name=in.readString();
		description=in.readString();
		username=in.readString();
		time=in.readString();
		cat=in.readParcelable(Pin.class.getClassLoader());
		id=in.readInt();
		upvote=in.readInt();
		downvote=in.readInt();
	}
	public static final Parcelable.Creator<Pin> CREATOR=new Parcelable.Creator<Pin>() {

		@Override
		public Pin createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Pin(source);
		}
		@Override
		public Pin[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Pin[size];
		}
	};
	
	
	
	
	
}
