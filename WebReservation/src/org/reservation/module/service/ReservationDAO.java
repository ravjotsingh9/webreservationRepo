package org.reservation.module.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.reservation.module.model.ClubMemberBeanModel;
import org.reservation.module.model.ClubMemberListModel;
import org.reservation.module.model.ReservationListModel;
import org.reservation.module.model.ReservationModel;

public class ReservationDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private static ReservationListModel reservations = new ReservationListModel();
	
	public ReservationDAO(){
		//open a connection
				databaseConnection = new DatabaseConnection();
				connection = databaseConnection.getConnection();
	}
	/*
	 * display the reservation
	 */
public ReservationListModel displayReservations(){
	
	ReservationListModel cm = new ReservationListModel();
	sql = "SELECT r.confirmationNo, m.uid, r.pickDate, r.dropDate, m.date, m.regNo, r.charges FROM Reservation r, MakeReservation m WHERE r.confirmationNo = m.confirmationNo";
	try {
		preparedStatement = connection.prepareStatement(sql);
		rs = preparedStatement.executeQuery();
		
		while(rs.next()){
			ReservationModel c1 = new ReservationModel();
			c1.setConfirmationNo(rs.getInt(1));
			c1.setUid(rs.getInt(2));
			c1.setPickDate(new DateTime(rs.getTimestamp("pickDate").getTime()));
			c1.setDropDate(new DateTime(rs.getTimestamp("dropDate").getTime()));
			c1.setDate(rs.getDate(5));
			c1.setRegNo(rs.getInt(6));
			c1.setCharges(rs.getDouble(7));
			reservations.getReservations().add(c1);
		}
	} catch (SQLException e) {
		System.out.println("Exception coming from displayReservation() of ReservationDAO---> " + e.getMessage());
	}
	cm = reservations;
	return cm;
}
	
	/*
	 * make reservation by updating reservation and makeReservation table
	 */
	
	public boolean makeReservation(int uid, DateTime pickDate, DateTime dropDate, int regNo) throws ParseException{
		int confirmationNo = addReservation(pickDate, dropDate);
		/*
		 * Inserted the values into MakeReservation table
		 */
		sql = "INSERT INTO MakeReservation (uid, confirmationNo, date, regNo) VALUES (?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, confirmationNo);
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			preparedStatement.setTimestamp(3, timestamp);
			preparedStatement.setInt(4, regNo);
			preparedStatement.executeUpdate();
			
			//see the result
			while(rs != null && rs.next()){
				//confirmationNo = rs.getInt(1);
                System.out.println("Generated User Id: "+rs.getInt(1));
                System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
			return true;
		} catch (SQLException e) {
			System.out.println("Exception coming from makeReservation() of ReservationDAO" + e.getMessage());
		}
		
		return false;
	}
	
	public int addReservation(DateTime pickDate, DateTime dropDate) throws ParseException{
		int confirmationNo = 0;
		Timestamp picktimeStamp = new Timestamp(pickDate.getMillis());
		Timestamp droptimeStamp = new Timestamp(dropDate.getMillis());
		/*
		 * Inserted the values into reservation table
		 */
		sql = "INSERT INTO Reservation (pickDate, dropDate, date, charges) VALUES (?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
			preparedStatement.setTimestamp(1, picktimeStamp);
			preparedStatement.setTimestamp(2, droptimeStamp);
			String source="2008-4-5";              
	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
			java.sql.Date d= new java.sql.Date(format.parse(source).getTime());
			preparedStatement.setDate(3, d);
			
			/*
			 * calculateCharges method needed ????Missing????
			 */
			preparedStatement.setDouble(4, 10.0);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			
			//see the result
			while(rs != null && rs.next()){
				confirmationNo = rs.getInt(1);
                System.out.println("Generated User Id: "+confirmationNo);
                System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
		} catch (SQLException e) {
			System.out.println("Exception coming from addReservation() of ReservationDAO" + e.getMessage());
		}
		return confirmationNo;
	}
	public boolean cancelReservation(String confNo, String ph, String ptime) throws ParseException
	{
		try{
		if(!confNo.isEmpty())
		{
			sql="Update Reservation set status=1 where confirmationNo=?";
			preparedStatement.setString(1, confNo);
		}
		else
		{
			sql="Update Reservation set status=1 where ph=? AND pickDate=?";
			preparedStatement.setString(1, ph);
			String source=ptime;              
	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
			java.sql.Date d= new java.sql.Date(format.parse(source).getTime());
			preparedStatement.setDate(2, d);
		}
		
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception coming from addReservation() of ReservationDAO" + e.getMessage());
		}
		return true;
	}
	
	
	
	
	public static void main(String args[]) throws ParseException{
		ReservationDAO u = new ReservationDAO();
		DateTime pdt = new DateTime(2004, 12, 25, 12, 0, 0, 0);
		DateTime ddt = new DateTime(2004, 12, 26, 12, 4, 0, 0);
		u.makeReservation(5, pdt, ddt, 301);
		/*
		 * to display list
		 */
		ReservationListModel m = new ReservationListModel();
		ReservationDAO dao = new ReservationDAO();
		ArrayList<ReservationModel> members = new ArrayList<ReservationModel>();
		m = dao.displayReservations();
		//display
		members = m.getReservations();
		for (int i=0; i<members.size(); i++){
			System.out.println("Reservation "+i+"-->");
			System.out.println(members.get(i).getConfirmationNo());
			System.out.println(members.get(i).getUid());
			System.out.println(members.get(i).getRegNo());
			System.out.println(members.get(i).getPickDate());
			System.out.println(members.get(i).getDropDate());
			System.out.println(members.get(i).getDate());
			System.out.println(members.get(i).getCharges());
		}
		
	}
}