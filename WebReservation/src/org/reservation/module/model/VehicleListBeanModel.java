package org.reservation.module.model;

import java.util.ArrayList;

public class VehicleListBeanModel {
	private ArrayList<VehicleBeanModel> vehlist = new ArrayList<VehicleBeanModel>();
	/**
	 * Getter method
	 * @return ArrayList
	 */
	public ArrayList<VehicleBeanModel> getVehlist() {
		return vehlist;
	}
	
	/**
	 * Setter method
	 * @param vehlist
	 */
	public void setVehlist(ArrayList<VehicleBeanModel> vehlist) {
		this.vehlist = vehlist;
	}
	
	/**
	 * clears the list of vehicles
	 */
	public void clearVehlist()
	{
		vehlist.clear();
	}
}
