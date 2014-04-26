package org.reservation.module.model;

import java.sql.Date;

import org.joda.time.DateTime;

public class ReservationModel{
	int confirmationNo;
	int uid;
	DateTime pickDate;
	DateTime dropDate;
	Date date;
	int regNo;
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
	public DateTime getPickDate() {
		return pickDate;
	}
	public void setPickDate(DateTime pickDate) {
		this.pickDate = pickDate;
	}
	public DateTime getDropDate() {
		return dropDate;
	}
	public void setDropDate(DateTime dropDate) {
		this.dropDate = dropDate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRegNo() {
		return regNo;
	}
	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	
}
