package com.example.pinboxproject.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable{
	double latitude,longitude;
	String district,thana,address;
	public Location(double latitude, double longitude, String district,
			String thana, String address) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.district = district;
		this.thana = thana;
		this.address = address;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude
				+ ", district=" + district + ", thana=" + thana + ", address="
				+ address + "]";
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the thana
	 */
	public String getThana() {
		return thana;
	}
	/**
	 * @param thana the thana to set
	 */
	public void setThana(String thana) {
		this.thana = thana;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Location(Parcel in)
	{
		readFromParcel(in);
	}
	
	private void readFromParcel(Parcel in)
	{
		latitude=in.readDouble();
		longitude=in.readDouble();
		district=in.readString();
		thana=in.readString();
		address=in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeDouble(latitude);
		dest.writeDouble(longitude);
		dest.writeString(district);
		dest.writeString(thana);
		dest.writeString(address);
	}
	public static final Parcelable.Creator<Location> CREATOR=new Parcelable.Creator<Location>() {

		@Override
		public Location createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Location(source);
		}
		@Override
		public Location[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Location[size];
		}
	};
	
}	
