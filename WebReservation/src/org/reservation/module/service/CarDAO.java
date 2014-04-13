package org.reservation.module.service;
import org.reservation.module.model.VehicleBeanModel;
import org.reservation.module.model.VehicleListBeanModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class CarDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private static PreparedStatement ps;
	ResultSet rs;
	private static VehicleListBeanModel vehicles = new VehicleListBeanModel();
	
	public static void main(String args[]){
		CarDAO c = new CarDAO();
		c.getFilteredList();
		
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
	
	public CarDAO(){
		//open a connection
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getConnection();
	}
	
	public VehicleListBeanModel getFilteredList(){
		VehicleListBeanModel vehlist = new VehicleListBeanModel();
		try{
			//sql query
			
			String sql = "SELECT regNo, category, type, brand, purchaseDate FROM Vehicle";
			//execute a query
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			
		//Extract data from result set
	    while (rs.next()) {
	    	//Retrieve by column name
	    	VehicleBeanModel vehicle = new VehicleBeanModel();
	    	//-----------------------------------------------
	    	vehicle.setRegNo(rs.getInt("regNo"));
	    	vehicle.setCategory(rs.getString("category"));
	    	vehicle.setType(rs.getString("type"));
	    	vehicle.setBrand(rs.getString("brand"));
	    	//vehicle.setPurchaseDate(rs.getString("purchaseDate"));
	    	vehicles.getVehlist().add(vehicle);
	    	//------------------------------------------------
	        int regNo = rs.getInt("regNo");
	        String category = rs.getString("category");
	        String type = rs.getString("type");
	        String brand = rs.getString("brand");
	        //String purchaseDate = rs.getString("purchaseDate");
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
	        System.out.print("Index " +vehicles.getVehlist().indexOf(vehicle));
	        System.out.print("RegNo: " +vehicle.getRegNo());
	        System.out.print(", Category: " + vehicle.getCategory());
	        System.out.print(", Type: " + vehicle.getType());
	        System.out.println(", Brand: " + vehicle.getBrand());
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
		vehlist = vehicles;
		//return vehicles;
		return vehlist;
}//end method
	
	public VehicleListBeanModel passVehicles(){
		return vehicles;
		
	}
	
	
	
	
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