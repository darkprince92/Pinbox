package com.example.pinboxproject.entity;

public class Location {
	double latitude,longitude;
	String name,address,district,thana,description;
	String catName,username,time;
	int id,upvote,downvote;
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
	public Location(double latitude, double longitude, String name,
			String address, String district, String thana, String description,
			String catName, String username, String time, int upvote,
			int downvote) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.address = address;
		this.district = district;
		this.thana = thana;
		this.description = description;
		this.catName = catName;
		this.username = username;
		this.time = time;
		this.upvote = upvote;
		this.downvote = downvote;
	}
	public Location(double latitude, double longitude, String name,
			String address, String district, String thana, String description,
			String catName, String username, String time, int id, int upvote,
			int downvote) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.address = address;
		this.district = district;
		this.thana = thana;
		this.description = description;
		this.catName = catName;
		this.username = username;
		this.time = time;
		this.id = id;
		this.upvote = upvote;
		this.downvote = downvote;
	}
	
}
