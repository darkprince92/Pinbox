package com.example.pinboxproject.entity;

public class Location {
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
	
}	
