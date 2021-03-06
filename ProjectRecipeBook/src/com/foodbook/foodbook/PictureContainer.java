package com.foodbook.foodbook;

import java.util.ArrayList;

/**
 * This class is used to transfer ArrayList<String> between activities that have "SingleInstance" mode. (It's not possible to return result or intent for these activities)
 * 
 */

public class PictureContainer {

	// attributes
	private static PictureContainer picCon = new PictureContainer();
	private ArrayList<String> pics;

	/**
	 * This is the constructor of the class object. It can only be accessed through getInstance().
	 * 
	 */

	private PictureContainer() {
		this.pics = null;
	}
	
	/**
	 * 
	 * getter method for pictures
	 * 
	 * @return
	 */

	public ArrayList<String> getPics() {
		return picCon.pics;
	}

	/**
	 * 
	 * setter methods for pictures
	 * 
	 * @param pics
	 */
	
	public void setPics(ArrayList<String> pics) {
		picCon.pics = pics;
	}
	
	/**
	 * 
	 * resets contents of pictures
	 * 
	 */

	public void reset() {
		picCon.pics = null;
	}
	
	/**
	 * 
	 * Singleton instnace access method
	 * 
	 * @return
	 */

	public static PictureContainer getInstance() {
		return picCon;
	}

}