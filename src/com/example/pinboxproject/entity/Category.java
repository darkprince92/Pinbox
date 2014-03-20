package com.example.pinboxproject.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
	int id;
	String catName;
	public Category(int id, String catName) {
		super();
		this.id = id;
		this.catName = catName;
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
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Category(Parcel in)
	{
		readFromParcel(in);
	}
	private void readFromParcel(Parcel in)
	{
		id=in.readInt();
		catName=in.readString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(catName);
	}
	public static final Parcelable.Creator<Category> CREATOR=new Parcelable.Creator<Category>() {

		@Override
		public Category createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Category(source);
		}
		@Override
		public Category[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Category[size];
		}
	};
	
	
}
