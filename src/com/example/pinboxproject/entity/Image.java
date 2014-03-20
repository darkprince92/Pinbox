package com.example.pinboxproject.entity;

public class Image {
	String path,thumb_path;

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the thumb_path
	 */
	public String getThumb_path() {
		return thumb_path;
	}

	/**
	 * @param thumb_path the thumb_path to set
	 */
	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}

	public Image(String path, String thumb_path) {
		super();
		this.path = path;
		this.thumb_path = thumb_path;
	}
	
}
