package org.reservation.module.model;

import java.sql.Date;

public class ClubMemberBeanModel {
	int uid;
	int membershipNo;
	double points;
	Date datecreated;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(int membershipNo) {
		this.membershipNo = membershipNo;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public Date getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}
}
