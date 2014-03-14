package com.example.pinboxproject.apputils;

public class Person {
	int id;
	String name,email,address;
	public Person(String name, String email, String address) {
		
		this.name = name;
		this.email = email;
		this.address = address;
	}
	public Person(int id, String name, String email, String address) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email
				+ ", address=" + address + "]";
	}
	
	
	
	
	
}
