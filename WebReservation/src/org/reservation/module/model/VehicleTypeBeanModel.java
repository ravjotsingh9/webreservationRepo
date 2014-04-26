package org.reservation.module.model;

public class VehicleTypeBeanModel {
	private int branchID;
	private String category;
	private String type;
	private double dailyRate;
	private double weeklyRate;
	private double hourlyRate;
	private double perKMRate;
	private double MileageLimit;
	private double dailyIRate;
	private double weeklyIRate;
	private double hourlyIRate;
	
	public int getBranchID() {
		return branchID;
	}
	public void setBranchID(int branchID) {
		this.branchID = branchID;
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
	public double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	public double getWeeklyRate() {
		return weeklyRate;
	}
	public void setWeeklyRate(double weeklyRate) {
		this.weeklyRate = weeklyRate;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public double getPerKMRate() {
		return perKMRate;
	}
	public void setPerKMRate(double perKMRate) {
		this.perKMRate = perKMRate;
	}
	public double getMileageLimit() {
		return MileageLimit;
	}
	public void setMileageLimit(double mileageLimit) {
		MileageLimit = mileageLimit;
	}
	public double getDailyIRate() {
		return dailyIRate;
	}
	public void setDailyIRate(double dailyIRate) {
		this.dailyIRate = dailyIRate;
	}
	public double getWeeklyIRate() {
		return weeklyIRate;
	}
	public void setWeeklyIRate(double weeklyIRate) {
		this.weeklyIRate = weeklyIRate;
	}
	public double getHourlyIRate() {
		return hourlyIRate;
	}
	public void setHourlyIRate(double hourlyIRate) {
		this.hourlyIRate = hourlyIRate;
	}
}
