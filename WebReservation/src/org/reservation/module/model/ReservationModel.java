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
	
	public int getConfirmationNo() {
		return confirmationNo;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setConfirmationNo(int confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	public Timestamp getPickDate() {
		return pickDate;
	}
	public void setPickDate(Timestamp pickDate) {
		this.pickDate = pickDate;
	}
	public Timestamp getDropDate() {
		return dropDate;
	}
	public void setDropDate(Timestamp dropDate) {
		this.dropDate = dropDate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	
}
