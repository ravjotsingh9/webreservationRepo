package org.reservation.module.model;

import java.util.ArrayList;

public class VehicleListBeanModel {
	private ArrayList<VehicleBeanModel> vehlist = new ArrayList<VehicleBeanModel>();

	public ArrayList<VehicleBeanModel> getVehlist() {
		return vehlist;
	}

	public void setVehlist(ArrayList<VehicleBeanModel> vehlist) {
		this.vehlist = vehlist;
	}
	
	public void clearVehlist()
	{
		vehlist.clear();
	}
}
