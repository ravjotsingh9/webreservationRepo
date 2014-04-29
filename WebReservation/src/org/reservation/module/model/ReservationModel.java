package org.reservation.module.model;

import java.sql.Date;
import java.sql.Timestamp;

import org.joda.time.DateTime;

public class ReservationModel{
	int confirmationNo;
	int uid;
	Timestamp pickDate;
	Timestamp dropDate;
	Date date;
	String regNo;
	double charges;
	/**
	 * Getter method
	 * @return int - confirmation number
	 */
	public int getConfirmationNo() {
		return confirmationNo;
	}
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
	 * Setter method
	 * @param confirmationNo
	 */
	public void setConfirmationNo(int confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	/**
	 * Getter method
	 * @return Timestamp
	 */
	public Timestamp getPickDate() {
		return pickDate;
	}
	/**
	 * Setter method
	 * @param pickDate
	 */
	public void setPickDate(Timestamp pickDate) {
		this.pickDate = pickDate;
	}
	/**
	 * Getter method
	 * @return Timestamp
	 */
	public Timestamp getDropDate() {
		return dropDate;
	}
	/**
	 * Setter method
	 * @param dropDate
	 */
	public void setDropDate(Timestamp dropDate) {
		this.dropDate = dropDate;
	}
	/**
	 * Setter method
	 * @return Date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Setter method
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Setter method
	 * @return string
	 */
	public String getRegNo() {
		return regNo;
	}
	/**
	 * Setter method
	 * @param regNo
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	/**
	 * Getter method
	 * @return charges
	 */
	public double getCharges() {
		return charges;
	}
	/**
	 * Setter method
	 * @param charges
	 */
	public void setCharges(double charges) {
		this.charges = charges;
	}
	
}
