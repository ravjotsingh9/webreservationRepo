package org.reservation.module.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;
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
import org.reservation.module.model.UserBeanModel;

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

public int cancelReservation(String confNo, String ph, String ptime) throws ParseException
	{
		int retval=0;
		try{
		if(!confNo.isEmpty())
		{
			System.out.println(confNo);
			sql="Update Reservation set status=1 where confirmationNo=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.valueOf(confNo));
		}
		else
		{
			sql="Update Reservation set status=1 where ph=? AND pickDate=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ph);
			String source=ptime;              
	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
			java.sql.Date d= new java.sql.Date(format.parse(source).getTime());
			preparedStatement.setDate(2, d);
		}
			
			retval= preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Exception coming from CancelReservation() of ReservationDAO" + e.getMessage());
			return 0;
		}
		return retval;
	}
	



	
	/*
	 * make reservation by updating reservation and makeReservation table
	 */
	
	public boolean makeReservation(int uid, DateTime pickDate, DateTime dropDate, int regNo) throws ParseException{
		int confirmationNo = addReservation(regNo, pickDate, dropDate);
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
                System.out.println("Confirmation No: "+rs.getInt(1));
                //System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
			return true;
		} catch (SQLException e) {
			System.out.println("Exception coming from makeReservation() of ReservationDAO" + e.getMessage());
		}
		
		return false;
	}
	
	public int addReservation(int regNo, DateTime pickDate, DateTime dropDate) throws ParseException{
		int confirmationNo = 0;
		Timestamp picktimeStamp = new Timestamp(pickDate.getMillis());
		Timestamp droptimeStamp = new Timestamp(dropDate.getMillis());
		double charges = calculateCharges(regNo, picktimeStamp, droptimeStamp);
		/*
		 * Inserted the values into reservation table
		 */
		sql = "INSERT INTO Reservation (pickDate, dropDate, creationDate, charges, status) VALUES (?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
			preparedStatement.setTimestamp(1, picktimeStamp);
			preparedStatement.setTimestamp(2, droptimeStamp);
			java.util.Date curr_date = new java.util.Date();
			java.sql.Timestamp d = new java.sql.Timestamp(curr_date.getTime());
			preparedStatement.setTimestamp(3, d);
			preparedStatement.setDouble(4, charges);
			preparedStatement.setInt(5, 0);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			
			//see the result
			while(rs != null && rs.next()){
				confirmationNo = rs.getInt(1);
                System.out.println("Generated User Id: "+confirmationNo);
                //System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
		} catch (SQLException e) {
			System.out.println("Exception coming from addReservation() of ReservationDAO" + e.getMessage());
		}
		return confirmationNo;
	}
	
	/*
	 * to calculate estimated reservation charges
	 */
	
	private double calculateCharges(int regNo, Timestamp pick, Timestamp drop){
		int d_month = drop.getMonth(), p_month = pick.getMonth(); //return 0 to 11
		int d_date = drop.getDate(), p_date = pick.getDate();//return 1 to 31
		int d_year = drop.getYear()+1900, p_year = pick.getYear()+1900;
		int d_hour = drop.getHours(), p_hour = pick.getHours();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, p_year);
		calendar.set(Calendar.MONTH, p_month);
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		double total = 0.0;
		double dailyR =0.0, weeklyR=0.0, hourlyR=0.0;
		/*
		 * retrieve the rental rates from database
		 */
		sql = "SELECT dailyRate, weeklyRate, hourlyRate FROM SuperRentRentalRate "
				+ "WHERE category IN(Select category FROM Vehicle WHERE regNo=?)"
				+ " AND type IN(Select type FROM Vehicle WHERE regNo=?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, regNo);
			preparedStatement.setInt(2, regNo);
			rs = preparedStatement.executeQuery();
		
			//put the raates in local variable
			while(rs != null && rs.next()){
				dailyR = rs.getDouble("dailyRate");
				weeklyR = rs.getDouble("weeklyRate");
				hourlyR = rs.getDouble("hourlyRate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * computation
		 */
		if (drop.compareTo(pick)<=0){
			System.out.println("Conflict in pickup and drop dates");
			return -1;
		}
		
		if(((d_date - p_date) == 0)&&((d_hour - p_hour)<=24)){ //for same day return
			System.out.println("hourly entry");
				//impose hourly rates
				total = (d_hour - p_hour) * hourlyR;
		}else {	//1
				if(d_month == p_month){	//2 for same month
					if((d_date - p_date) > 7){ //3 
						//impose weekly rates
						System.out.println("3.weekly entry");
						total = ((d_date - p_date)+1) * weeklyR;
						}else if((d_date - p_date) < 7){
							//impose daily rates
							System.out.println("3.daily entry");
							total = ((d_date - p_date)+1) * dailyR;
						}//3
					}//2
					else{ //for different months
						if((d_date + (numDays-p_date)) > 7){
							//impose weekly rates
							System.out.println("L.weekly entry");
							total = (d_date + (numDays-p_date)+1) * weeklyR;
						}else if((d_date + (numDays-p_date)) < 7){
							//impose daily rates
							System.out.println("L.daily entry");
							total = (d_date + (numDays-p_date)+1) * dailyR;
						}
					}
				}//1
		System.out.println("d_date|month|year:"+d_date+"/"+d_month+"/"+d_year);
		System.out.println("p_date|month|year:"+p_date+"/"+p_month+"/"+p_year);
		System.out.println("rates_daily|weekly|hourly:"+dailyR+"/"+weeklyR+"/"+hourlyR);
		System.out.println("regno="+regNo);
		System.out.println("total="+total);
		return total;
	}
	
	/*
	 * alert for duplicate reservation????????????
	 */
	public boolean isReservationExists(int regNo, DateTime pickDate, DateTime dropDate){
		/*
		 * incomplete-------------need to decide whether to implement or not
		 */
		
		sql = "SELECT * FROM Vehicle WHERE regNo IN("
				+ "SELECT V.regNo FROM Vehicle V, MakeReservation M, Reservation R "
				+ "WHERE V.regNo = M.regNo AND M.confirmationNo = R.confirmationNo "
				+ "AND R.status = 0 "
				+ "AND((dropDate > ? AND pickDate < ?) OR(dropDate > ? AND pickDate < ?)))";
		return false;
	}
	
	
	public static void main(String args[]) throws ParseException{
		ReservationDAO u = new ReservationDAO();
		UserDAO user = new UserDAO();
		DateTime pdt = new DateTime(2014, 04, 25, 11, 0, 0, 0);
		DateTime ddt = new DateTime(2014, 04, 26, 22, 0, 0, 0);
		
		/*
		 * demo of reservation
		 */
		UserBeanModel s = new UserBeanModel();
		s.setAddress("Surrey");
		s.setEmail("brouno@mypet.ca");
		s.setName("Brouno");
		s.setPhoneNumber(998776542);
		int uid = user.addUser(s);
		u.makeReservation(uid, pdt, ddt, 78380);
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
