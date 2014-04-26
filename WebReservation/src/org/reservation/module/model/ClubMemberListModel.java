package org.reservation.module.model;

import java.util.ArrayList;

public class ClubMemberListModel {
	private static ArrayList<ClubMemberBeanModel> members = new ArrayList<ClubMemberBeanModel>();

	public static ArrayList<ClubMemberBeanModel> getMembers() {
		return members;
	}

	public static void setMembers(ArrayList<ClubMemberBeanModel> members) {
		ClubMemberListModel.members = members;
	}
	
}
