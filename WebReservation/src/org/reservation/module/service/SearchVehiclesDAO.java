package org.reservation.module.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.reservation.module.model.VehicleBeanModel;
import org.reservation.module.model.VehicleListBeanModel;

public class SearchVehiclesDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private VehicleListBeanModel vehicles = new VehicleListBeanModel();
	
	/**Connection with database must be established.
	 * @invariant !getConnection()  
	 */
	public SearchVehiclesDAO(){
		//open a connection
				databaseConnection = new DatabaseConnection();
				connection = databaseConnection.getConnection();
	}
	
	public VehicleListBeanModel search(String category, String type, String picktime, String droptime) {
		System.out.println("You are going to search "+ category + " with phone number"+ type + " in the database");
		ArrayList<VehicleBeanModel> vehlist = new ArrayList<VehicleBeanModel>();
		Timestamp pick = Timestamp.valueOf(picktime);
		Timestamp drop = Timestamp.valueOf(droptime);
		
		sql="(SELECT VV.regNo, VV.category, VV.type, VV.brand, VV.purchaseDate, VV.status FROM Vehicle VV WHERE VV.category=? AND VV.type=? AND (VV.status=0) AND "
				+ "VV.regNo NOT IN (SELECT V.regNo FROM Vehicle V,  MakeReservation M WHERE V.regNo=M.regNo AND (M.status=0) AND "
				+ "M.confirmationNo IN (SELECT R.confirmationNo FROM Reservation R WHERE R.pickDate=? AND R.dropDate=?)) "
				+ "GROUP BY VV.category, VV.type, VV.brand)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
			preparedStatement.setString(2, type);
			preparedStatement.setTimestamp(3, pick);
			preparedStatement.setTimestamp(4, drop);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				int i=1;
				System.out.println("V"+i+" Regno="+rs.getString("regNo"));
				VehicleBeanModel aVehicle = new VehicleBeanModel();
				aVehicle.setRegNo(rs.getString("regNo"));
				aVehicle.setCategory(rs.getString("category"));
				aVehicle.setType(rs.getString("type"));
				aVehicle.setBrand(rs.getString("brand"));
				aVehicle.setPurchaseDate(rs.getTimestamp("purchaseDate"));
				aVehicle.setStatus(rs.getInt("status"));
				vehlist.add(aVehicle);
			}
			
			VehicleListBeanModel temp = new VehicleListBeanModel();
			vehicles.setVehlist(vehlist);
			temp = vehicles;
			return temp;
			
		}catch (SQLTimeoutException e) {
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from search() of SearchVehiclesDAO-> " + e.getMessage());
		}catch(SQLException e){
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLException coming from search() of SearchVehiclesDAO-> " + e.getMessage());
		}
		return null;
	}
	

	public static void main(String arg[]) {
		SearchVehiclesDAO s= new SearchVehiclesDAO();
		VehicleListBeanModel v = new VehicleListBeanModel();
		ArrayList<VehicleBeanModel> vehlist = new ArrayList<VehicleBeanModel>();
		v = s.search("TRUCK", "24-FOOT","2014-5-21 12:00:00","2014-5-22 12:00:00");
		vehlist = v.getVehlist();
		
		System.out.println("***************Avaliable Vehicles***********");
		for (int i=0; i<vehlist.size(); i++){
			System.out.print("Vehicle "+i+"-->");
			System.out.println("RegNo="+vehlist.get(i).getRegNo()+
					" Category="+vehlist.get(i).getCategory()+
					" Type="+vehlist.get(i).getType()+
					" Brand="+vehlist.get(i).getBrand()+
					" Status="+vehlist.get(i).getStatus());
		}
	}
}
