package org.reservation.module.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.reservation.module.model.ClubMemberBeanModel;
import org.reservation.module.model.VehicleBeanModel;
import org.reservation.module.model.VehicleListBeanModel;

import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * This class is the DAO to load information for a system administrator 
 *
 */
public class SearchVehicleDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection; 
	private String category;
	private String type;
	private String brand;
	
	//private JTable table;
	//private JScrollPane scrollPane;
	public static void main(String arg[]) {
		SearchVehicleDAO s= new SearchVehicleDAO();
		VehicleListBeanModel v = new VehicleListBeanModel();
		v = s.search("TRUCK", "SELECT","2014-5-21 12:00","2014-5-22 12:00");
	}
	
	
	public SearchVehicleDAO () {
		
		/* Connect to database */
		try {
			//open a connection
			databaseConnection = new DatabaseConnection();
			connection = databaseConnection.getConnection();
		} catch (Exception e) {
			//DatabaseConnection.rollback(connection);
			//DatabaseConnection.close(connection);
			e.printStackTrace();
		} 
	}
	public VehicleListBeanModel search(String category, String type, String picktime, String droptime) {
		
		System.out.println("You are going to search "+ category + " with phone number"+ type + " in the database");
		ArrayList<VehicleBeanModel> vehlist = new ArrayList<VehicleBeanModel>();
		/* Create SQL query and perform the search */
		try{
			ResultSet rs;
			Statement st = connection.createStatement();
			String query;
			if(type.isEmpty())
			{
				query = "SELECT DISTINCT regNo, category, type, brand "
						+ "FROM Vehicle "
						+ "WHERE category = '"+ category +"' "
						+ "AND regNo NOT IN("
						+ "SELECT V.regNo "//, V.category, V.type, V.brand "
						+ "FROM Vehicle V, MakeReservation M, Reservation R "
						+ "WHERE V.regNo = M.regNo "
						+ "AND M.confirmationNo = R.confirmationNo "
						+ "AND R.status = 0 "
						+ "AND V.category = '"+ category +"' AND V.type = '"+ type +"'"
						+ "AND"
							+ "((dropDate > '"+ droptime+":00' AND pickDate < '"+ droptime+":00')"
								+ "OR "
							+ "(dropDate > '"+ picktime+":00' AND pickDate < '"+ picktime+":00'))"
						+ ")";
			}
			else
			{
				query = "SELECT DISTINCT regNo, category, type, brand "
					+ "FROM Vehicle "
					+ "WHERE category = '"+ category +"' AND type = '"+ type +"' "
					+ "AND regNo NOT IN("
					+ "SELECT V.regNo "//, V.category, V.type, V.brand "
					+ "FROM Vehicle V, MakeReservation M, Reservation R "
					+ "WHERE V.regNo = M.regNo "
					+ "AND M.confirmationNo = R.confirmationNo "
					+ "AND R.status = 0 "
					+ "AND V.category = '"+ category +"' AND V.type = '"+ type +"'"
					+ "AND"
						+ "((dropDate > '"+ droptime+":00' AND pickDate < '"+ droptime+":00')"
							+ "OR "
						+ "(dropDate > '"+ picktime+":00' AND pickDate < '"+ picktime+":00'))"
					+ ")";
			}
			//String query = "SELECT name,email,phoneNumber,type,address FROM User WHERE name='" + name +"'" + " AND phoneNumber='" + phone + "'";
			System.out.println("query is: " + query);
			rs = st.executeQuery(query);
			while(rs.next())
			{
				VehicleBeanModel veh = new VehicleBeanModel();
				veh.setRegNo(rs.getInt("regNo"));
				veh.setCategory(rs.getString("category"));
				veh.setType(rs.getString("type"));
				veh.setBrand(rs.getString("brand"));
				//System.out.print(veh.getRegNo() +"\t"+ veh.getCategory()+"\t"+ veh.getType() +"\t"+ veh.getBrand() +"\n");
				vehlist.add(veh);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			//DatabaseConnection.close(connection);
		}
		VehicleListBeanModel v = new VehicleListBeanModel();
		v.setVehlist(vehlist);
		return v;
	}
/*
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	*/
}