package org.reservation.module.model;

import java.util.ArrayList;

public class UserListModel {
	private static ArrayList<UserBeanModel> users = new ArrayList<UserBeanModel>();
	/**
	 * Getter method
	 * @return ArrayList
	 */
	public static ArrayList<UserBeanModel> getUsers() {
		return users;
	}

	/**
	 * Setter method
	 * @param users
	 */
	public static void setUsers(ArrayList<UserBeanModel> users) {
		UserListModel.users = users;
	}
	
}
