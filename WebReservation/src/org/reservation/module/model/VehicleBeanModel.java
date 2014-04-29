package org.reservation.module.model;

import java.sql.Timestamp;

import org.joda.time.DateTime;

public class VehicleBeanModel {
	String regNo;
	String category, type, brand;
	Timestamp purchaseDate;
	int status;
	
	/**
	 * Getter method
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
	 * @return string
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * Setter method
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * Getter method
	 * @return string-type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Setter method
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Getter method
	 * @return string
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * Setter method
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * Getter method
	 * @return Timestamp
	 */
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * Setter method
	 * @param purchaseDate
	 */
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	/**
	 * Getter method
	 * @return int
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * Setter method
	 * @param sold
	 */
	public void setStatus(int sold) {
		this.status = status;
	}
	
}
