package org.reservation.module.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;

public class ReservationDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	
	/*
	 * make reservation by updating reservation and makeReservation table
	 */
	
	public boolean makeReservation(int uid, DateTime pickDate, DateTime dropDate, int regNo){
		int confirmationNo = addReservation(pickDate, dropDate);
		/*
		 * Inserted the values into MakeReservation table
		 */
		sql = "INSERT INTO MakeReservation (uid, confirmationNo, date, regNo) VALUES (?,?,NOW(),?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, confirmationNo);
			preparedStatement.setInt(4, regNo);
			preparedStatement.executeUpdate();
			
			//see the result
			while(rs != null && rs.next()){
				confirmationNo = rs.getInt(1);
                System.out.println("Generated User Id: "+rs.getInt(1));
                System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
			return true;
		} catch (SQLException e) {
			System.out.println("Exception coming from makeReservation() of USERDAO" + e.getMessage());
		}
		
		return false;
	}
	
	public int addReservation(DateTime pickDate, DateTime dropDate){
		int confirmationNo = 0;
		Timestamp picktimeStamp = new Timestamp(pickDate.getMillis());
		Timestamp droptimeStamp = new Timestamp(dropDate.getMillis());
		/*
		 * Inserted the values into reservation table
		 */
		sql = "INSERT INTO Reservation (pickDate, dropDate, date) VALUES (?,?,NOW())";
		try {
			preparedStatement = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
			preparedStatement.setTimestamp(1, picktimeStamp);
			preparedStatement.setTimestamp(2, droptimeStamp);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			
			//see the result
			while(rs != null && rs.next()){
				confirmationNo = rs.getInt(1);
                System.out.println("Generated User Id: "+rs.getInt(1));
                System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
		} catch (SQLException e) {
			System.out.println("Exception coming from addUser() of USERDAO" + e.getMessage());
		}
		return confirmationNo;
	}
	
	public static void main(String args[]){
		ReservationDAO u = new ReservationDAO();
		DateTime pdt = new DateTime(2004, 12, 25, 12, 0, 0, 0);
		DateTime ddt = new DateTime(2004, 12, 26, 12, 4, 0, 0);
		u.makeReservation(123, pdt, ddt, 301);
	}
}
