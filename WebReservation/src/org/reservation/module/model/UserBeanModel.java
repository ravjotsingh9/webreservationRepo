package org.reservation.module.model;

import java.sql.Date;

public class UserBeanModel {
	int uid;
	String name;
	String email;
	long phoneNumber;
	Date dateCreated;
	int type;
	String address;
	/**
	 * Getter method
	 * @return userid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * Setter method
	 * @param uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * Getter method
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter method
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter method
	 * @return string - email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter method
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter method
	 * @return long
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Setter method
	 * @param phoneNumber
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Getter method
	 * @return Date
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	/**
	 * Setter method
	 * @param dateCreated
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	/**
	 * Getter method
	 * @return int
	 */
	public int getType() {
		return type;
	}
	/**
	 * Setter method
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * Getter method
	 * @return string
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Setter method
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}	
}
