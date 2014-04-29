package org.reservation.module.model;

import java.util.ArrayList;

public class VehicleTypeListBeanModel {
	ArrayList<VehicleTypeBeanModel> veh= new ArrayList<VehicleTypeBeanModel>();
	/**
	 * Getter method
	 * @return ArrayList
	 */
	public ArrayList<VehicleTypeBeanModel> getVeh() {
		return veh;
	}
	/**
	 * Setter method
	 * @param veh
	 */
	public void setVeh(ArrayList<VehicleTypeBeanModel> veh) {
		this.veh = veh;
	}
}
