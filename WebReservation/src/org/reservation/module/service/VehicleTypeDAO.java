
package org.reservation.module.service;
import org.reservation.module.model.VehicleListBeanModel;
import org.reservation.module.model.VehicleTypeBeanModel;
import org.reservation.module.model.VehicleTypeListBeanModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class VehicleTypeDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private static PreparedStatement ps;
	ResultSet rs;
	private static VehicleListBeanModel vehicles = new VehicleListBeanModel();
	
	public static void main(String args[]){
		VehicleTypeDAO c = new VehicleTypeDAO();
		c.getFilteredList("");
		
		//update the column
		int regNo; 
		String cat, type, brand, date;
		//int flag;
		Scanner scan = new Scanner(System.in);
		regNo = scan.nextInt();
		//cat = scan.next();
		//type = scan.next();
		//brand = scan.next();
		//date = scan.next();
		//flag = scan.nextInt();
		
		//c.updateVehicleList(regNo, cat, type, brand, date);
		
	}
	
	public VehicleTypeDAO(){
		//open a connection
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getConnection();
	}
	
	public VehicleTypeListBeanModel getFilteredList(String Category){
		
		VehicleTypeListBeanModel veh = new VehicleTypeListBeanModel();
		ArrayList<VehicleTypeBeanModel> vehlist= new ArrayList<VehicleTypeBeanModel>();
		try{
			//sql query
			String sql;
			if(Category.isEmpty())
			{
				sql = "SELECT SR.category, SR.type, SR.dailyRate as dr, SR.weeklyRate as wr, SR.hourlyRate as hr, SR.perKMRate,"
						+ " SR.MileageLimit , SI.dailyRate as dir, SI.weeklyRate wir, SI.hourlyRate hir"
						+ " FROM SuperRentRentalRate SR, SuperRentInsuranceRate SI "
						+ " where SR.branchID=SI.branchID "
						+ " AND SR.category= SI.category "
						+ " AND SR.type= SI.type ";
				ps = connection.prepareStatement(sql);
			}
			else
			{
				sql = "SELECT SR.category, SR.type, SR.dailyRate as dr, SR.weeklyRate as wr, SR.hourlyRate as hr, SR.perKMRate,"
						+ " SR.MileageLimit , SI.dailyRate as dir, SI.weeklyRate wir, SI.hourlyRate hir"
						+ " FROM SuperRentRentalRate SR, SuperRentInsuranceRate SI "
						+ " where SR.branchID=SI.branchID "
						+ " AND SR.category= SI.category "
						+ " AND SR.type= SI.type "
						+ " AND SR.category=?";
				ps = connection.prepareStatement(sql);
				ps.setString(1, Category);
			}
			
			rs = ps.executeQuery();
			
		//Extract data from result set
	    while (rs.next()) {
	    	//Retrieve by column name
	    	VehicleTypeBeanModel vehicle = new VehicleTypeBeanModel();
	    	//-----------------------------------------------
	    	vehicle.setType(rs.getString("type"));
	    	vehicle.setCategory(rs.getString("category"));
	    	vehicle.setDailyRate(rs.getDouble("dr"));
	    	vehicle.setWeeklyRate(rs.getDouble("wr"));
	    	vehicle.setHourlyRate(rs.getDouble("hr"));
	    	vehicle.setPerKMRate(rs.getDouble("perKMRate"));
	    	vehicle.setMileageLimit(rs.getDouble("MileageLimit"));
	    	vehicle.setDailyIRate(rs.getDouble("dir"));
	    	vehicle.setWeeklyIRate(rs.getDouble("wir"));
	    	vehicle.setHourlyIRate(rs.getDouble("hir"));
	    	//vehicle.setPurchaseDate(rs.getString("purchaseDate"));
	    	vehlist.add(vehicle);
	    	//------------------------------------------------
	    	/*
	        int regNo = rs.getInt("regNo");
	        String category = rs.getString("category");
	        String type = rs.getString("type");
	        String brand = rs.getString("brand");
	        String purchaseDate = rs.getString("purchaseDate");
	        */
	        //int flag = rs.getInt("sold");
	        
	        //Display values
	        /*
	        System.out.print("RegNo: " + regNo);
	        System.out.print(", Category: " + category);
	        System.out.print(", Type: " + type);
	        System.out.print(", Brand: " + brand);
	        System.out.print(", Purchase Date: " + purchaseDate);
	        */
	        //System.out.print(", Sold?: " + flag);    
	        
	        
	        //Display values
	    	
	        //System.out.print("Index " +vehicles.getVehlist().indexOf(vehicle));
	        System.out.print(vehicle.getType()+"\t");
	        System.out.print(vehicle.getCategory()+"\t");
	        System.out.print(vehicle.getDailyRate()+"\t");
	        System.out.print(vehicle.getWeeklyRate()+"\t");
	        System.out.print(vehicle.getHourlyRate()+"\t");
	        System.out.print(vehicle.getDailyIRate()+"\t");
	        System.out.print(vehicle.getWeeklyIRate()+"\t");
	        System.out.print(vehicle.getHourlyIRate()+"\t");
	        System.out.print(vehicle.getMileageLimit()+"\t");
	        System.out.print(vehicle.getPerKMRate()+"\n");
	        
	        //System.out.print(", Purchase Date: " + vehicle.getPurchaseDate());
	        
	    }//while
	    
	}//try
	catch (SQLException e){
		e.printStackTrace();
	}
	finally{
	      //finally block used to close resources
		if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
        if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	   System.out.println("\nGoodbye!");
	}
		veh.setVeh(vehlist);
		//return vehicles;
		return veh;
}//end method
		
	
	
	public void updateVehicleList(int regNo, String cat, String type, String brand, String date){
		String sql = "INSERT INTO Vehicle"
				+ "(regNo, category, type, brand, purchasedate, sold) VALUES"
				+ "(?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, regNo);
			ps.setString(2, cat);
			ps.setString(3, type);
			ps.setString(4, brand);
			ps.setString(5, date);
			//ps.setInt(6, flag);
			
			ps.executeUpdate();
			
			System.out.println("Record inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
