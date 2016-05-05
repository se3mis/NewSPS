package com.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class App implements Serializable{
	String one;
	String two;
	String three;
	
	
	public App(String one, String two, String three) {
		super();
		this.one = one;
		this.two = two;
		this.three = three;
	}
	/**
	 * @return the one
	 */
	public String getOne() {
		return one;
	}
	/**
	 * @param one the one to set
	 */
	public void setOne(String one) {
		this.one = one;
	}
	/**
	 * @return the two
	 */
	public String getTwo() {
		return two;
	}
	/**
	 * @param two the two to set
	 */
	public void setTwo(String two) {
		this.two = two;
	}
	/**
	 * @return the three
	 */
	public String getThree() {
		return three;
	}
	/**
	 * @param three the three to set
	 */
	public void setThree(String three) {
		this.three = three;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "App [one=" + one + ", two=" + two + ", three=" + three + "]";
	}
	

}
