package org.reservation.module.model;

import java.util.ArrayList;

public class RegUserListModel {
	private static ArrayList<RegUserBeanModel> regusers = new ArrayList<RegUserBeanModel>();

	public static ArrayList<RegUserBeanModel> getRegusers() {
		return regusers;
	}

	public static void setRegusers(ArrayList<RegUserBeanModel> regusers) {
		RegUserListModel.regusers = regusers;
	}
	
}
