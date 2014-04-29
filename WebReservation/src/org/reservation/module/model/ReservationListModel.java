package org.reservation.module.model;

import java.util.ArrayList;

public class ReservationListModel {
	private static ArrayList<ReservationModel> reservations = new ArrayList<ReservationModel>();
	/**
	 * Getter method
	 * @return ArrayList
	 */
	public static ArrayList<ReservationModel> getReservations() {
		return reservations;
	}

	/**
	 * Setter method
	 * @param reservations
	 */
	public static void setReservations(ArrayList<ReservationModel> reservations) {
		ReservationListModel.reservations = reservations;
	}
	
}
