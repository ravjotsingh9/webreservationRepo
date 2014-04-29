package org.reservation.module.model;

import java.sql.Date;

public class ClubMemberBeanModel {
	int uid;
	int membershipNo;
	double points;
	Date datecreated;
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
	 * @return membership number
	 */
	public int getMembershipNo() {
		return membershipNo;
	}
	/**
	 * Setter method
	 * @param membershipNo
	 */
	public void setMembershipNo(int membershipNo) {
		this.membershipNo = membershipNo;
	}
	/**
	 * Getter method
	 * @return double
	 */
	public double getPoints() {
		return points;
	}
	/**
	 * Setter method
	 * @param points
	 */
	public void setPoints(double points) {
		this.points = points;
	}
	/**
	 * Getter method
	 * @return Date
	 */
	public Date getDatecreated() {
		return datecreated;
	}
	/**
	 * Setter method
	 * @param datecreated
	 */
	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}
}
