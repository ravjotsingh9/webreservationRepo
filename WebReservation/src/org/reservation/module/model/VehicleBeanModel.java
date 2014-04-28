package org.reservation.module.model;

import java.sql.Timestamp;

import org.joda.time.DateTime;

public class VehicleBeanModel {
	String regNo;
	String category, type, brand;
	Timestamp purchaseDate;
	int status;

	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int sold) {
		this.status = status;
	}
	
}
