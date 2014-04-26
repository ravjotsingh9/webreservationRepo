package org.reservation.module.model;

import org.joda.time.DateTime;

public class VehicleBeanModel {
	int regNo;
	String category, type, brand;
	DateTime purchaseDate;
	int status;

	public int getRegNo() {
		return regNo;
	}
	public void setRegNo(int regNo) {
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
	public DateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(DateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int sold) {
		this.status = status;
	}
	
}
