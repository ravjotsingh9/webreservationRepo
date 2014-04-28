package org.reservation.module.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
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

/**
 * ReservationDAO is used for making reservation and for cancelling reservation
 *  
 */
public class ReservationDAO {
	
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private static ReservationListModel reservations = new ReservationListModel();
	
	/**Connection with database must be established.
	 * @throws Exception 
	 * @invariant !getConnection()  
	 */
	public ReservationDAO() throws Exception{
		//open a connection
		try{
				databaseConnection = new DatabaseConnection();
				connection = databaseConnection.getConnection();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	
/** 
 * @return all the reservations done so far  
 * @throws SQLException 
 */
public ReservationListModel displayReservations() throws SQLException{
	ReservationListModel cm = new ReservationListModel();
	
	sql = "SELECT r.confirmationNo, m.uid, r.pickDate, r.dropDate, m.date, m.regNo, r.charges FROM Reservation r, MakeReservation m WHERE r.confirmationNo = m.confirmationNo";
	try {
		preparedStatement = connection.prepareStatement(sql);
		rs = preparedStatement.executeQuery();
		
		while(rs.next()){
			ReservationModel c1 = new ReservationModel();
			c1.setConfirmationNo(rs.getInt(1));
			c1.setUid(rs.getInt(2));
			c1.setPickDate(rs.getTimestamp("pickDate"));
			c1.setDropDate(rs.getTimestamp("dropDate"));
			c1.setDate(rs.getDate(5));
			c1.setRegNo(rs.getInt(6));
			c1.setCharges(rs.getDouble(7));
			reservations.getReservations().add(c1);
		}
	} catch (SQLTimeoutException e) {
		//connection.rollback();
		System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from displayReservation() of ReservationDAO-> " + e.getMessage());
		throw new SQLTimeoutException(e.getMessage());
	}catch(SQLException e){
		//connection.rollback();
		System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from displayReservation() of ReservationDAO-> " + e.getMessage());
		throw new SQLException(e.getMessage());
	}
	cm = reservations;
	return cm;
}

/**
 * The cancelReservation() operation assumes that confirmation number shouldn't be empty.
 * It updates the Reservation and makeReservation table in superrent database. 
 * @param confirmationNo 
 * @param phoneNumber
 * @param picktime
 * @pre !confNo.isEmpty()
 * @return 0 on error, else positive value
 * @throws ParseException
 * @throws SQLException 
 */
public int cancelReservation(String confirmationNo, String phoneNumber, String picktime) throws ParseException, SQLException
{
	int retval=0;
	try{
	if(!confirmationNo.isEmpty())
	{
		System.out.println(confirmationNo);
		sql="Update Reservation set status=1 where confirmationNo=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.valueOf(confirmationNo));
		retval= preparedStatement.executeUpdate();
		if(retval!=0){
			String sql1="Update MakeReservation set status=3 where confirmationNo=?";
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, Integer.valueOf(confirmationNo));
			retval= preparedStatement.executeUpdate();
		}
	}
	else
	{
		sql="Update Reservation set status=1 where ph=? AND pickDate=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, phoneNumber);
		String source=picktime;              
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
		java.sql.Date d= new java.sql.Date(format.parse(source).getTime());
		preparedStatement.setDate(2, d);
		retval= preparedStatement.executeUpdate();
	}
	} catch (SQLTimeoutException e) {
		//connection.rollback();
		System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from cancelReservation() of ReservationDAO-> " + e.getMessage());
		throw new SQLTimeoutException(e.getMessage());
		
	}catch(SQLException e){
		//connection.rollback();
		System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from cancelReservation() of ReservationDAO-> " + e.getMessage());
		throw new SQLException(e.getMessage());
	}
	return retval;
}

	/**This makeReservation() updates reservation, makeReservation and RequireAdditionalEquip table in the superrent database
	 * where the reservation table contains reservation related information, makeReservation contains reserved vehicle and
	 * reservation relationship information and RequireAdditionalEquip contains the booked equipments along with reservation.
	 * @pre !isReservationExists(regNo, pickDate, dropDate)
	 * @pre addReservation(regNo, pickDate, dropDate) != -1
	 * @pre category = getCategory(regNo)
	 * @pre !addAddtionalEquip(confirmationNo, addEquip, category) 
	 * @param userid
	 * @param pickDate
	 * @param dropDate
	 * @param regNo
	 * @param addEquip
	 * @throws SQLException 
	 */
	public boolean makeReservation(int uid, Timestamp pickDate, Timestamp dropDate, int regNo, String[] addEquip) throws ParseException, SQLException{
		if(!isReservationExists(regNo, pickDate, dropDate)){
			System.out.println("Sorry! Now the requested vehicle is not available.");
			return false;
		}
		int confirmationNo = addReservation(regNo, pickDate, dropDate);
		String regNum = String.valueOf(regNo);
		for(int i=0; i<addEquip.length; i++){
			if(!addEquip[i].equals("nothing")){
				if(!addAdditionalEquip(confirmationNo, addEquip[i], getCategory(regNum))){
					System.out.println("Error: while entering additional equipment");
					//connection.rollback();
					return false;
				}
			}
		}
		/*
		 * Inserted the values into MakeReservation table
		 */
		if(confirmationNo != -1){
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
			connection.commit();
			
			//see the result
			while(rs != null && rs.next()){
				//confirmationNo = rs.getInt(1);
                System.out.println("Confirmation No: "+rs.getInt(1));
                //System.out.println("Generated User Id: "+rs.getTimestamp(2));
            }
			return true;
		}catch (SQLTimeoutException e) {
			connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from makeReservation() of ReservationDAO-> " + e.getMessage());
			throw new SQLTimeoutException(e.getMessage());
		}catch(SQLException e){
			connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLExpection coming from makeReservation() of ReservationDAO-> " + e.getMessage());
			throw new SQLException(e.getMessage());
		}finally{
			connection.close();
			System.out.println("Connection closed!");
		}
		}
		System.out.println("Sorry, Unable to make reservation..Try Again.");
		return false;
	}
	
	/**
	 * The addReservation() operation adds the record to the Reservation table of superrent database.
	 * @param regNo
	 * @param pickDate
	 * @param dropDate
	 * @return 0 if corresponding record doesn't exits else positive integer
	 * @throws ParseException
	 * @throws SQLException 
	 */
	private int addReservation(int regNo, Timestamp pickDate, Timestamp dropDate) throws ParseException, SQLException{
		int confirmationNo = 0;
		//Timestamp picktimeStamp = new Timestamp(pickDate.getMillis());
		//Timestamp droptimeStamp = new Timestamp(dropDate.getMillis());
		double charges = calculateCharges(regNo, pickDate, dropDate);
		/*
		 * Inserted the values into reservation table
		 */
		sql = "INSERT INTO Reservation (pickDate, dropDate, creationDate, charges, status) VALUES (?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
			preparedStatement.setTimestamp(1, pickDate);
			preparedStatement.setTimestamp(2, dropDate);
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
            }
		} catch (SQLTimeoutException e) {
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from addReservation() of ReservationDAO-> " + e.getMessage());
			throw new SQLTimeoutException(e.getMessage());
		}catch(SQLException e){
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from addReservation() of ReservationDAO-> " + e.getMessage());
			throw new SQLException(e.getMessage());
		}
		return confirmationNo;
	}
	
	/**
	 * The calculateCharges() operation calculates the estimated reservation charges
	 * @pre drop.compareTo(pick)<=0
	 * @pre pick.compareTo(currentTime)<=0
	 * @param regNo
	 * @param pick
	 * @param drop
	 * @return double
	 * @throws SQLException 
	 */
	public double calculateCharges(int regNo, Timestamp pick, Timestamp drop) throws SQLException{
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
		} catch (SQLTimeoutException e) {
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from calculateCharges() of ReservationDAO-> " + e.getMessage());
			throw new SQLTimeoutException(e.getMessage());
		}catch(SQLException e){
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from calculateCharges() of ReservationDAO-> " + e.getMessage());
			throw new SQLException(e.getMessage());
		}
		/*
		 * computation
		 */
		java.util.Date date= new java.util.Date();
		Timestamp currentTime = new Timestamp(date.getTime());
		if(pick.compareTo(currentTime)<=0){
			System.out.println("Pickup date can't be before current date...");
			return -1;
		}
		if (drop.compareTo(pick)<=0){
			System.out.println("Conflict in pickup and drop dates...");
			return -1;
		}
		
		if(((d_date - p_date) == 0)&&((d_hour - p_hour)<=24)){ //for same day return
			System.out.println("hourly entry");
				//impose hourly rates
				total = (d_hour - p_hour) * hourlyR;
		}else {	//1
				if(d_month == p_month){	//2 for same month
					if((d_date - p_date) > 5){ //3 
						//impose weekly rates
						System.out.println("3.weekly entry");
						total = ((d_date - p_date)+1) * weeklyR;
						}else if((d_date - p_date) < 5){
							//impose daily rates
							System.out.println("3.daily entry");
							total = ((d_date - p_date)+1) * dailyR;
						}//3
					}//2
					else{ //for different months
						if((d_date + (numDays-p_date)) > 5){
							//impose weekly rates
							System.out.println("L.weekly entry");
							total = (d_date + (numDays-p_date)+1) * weeklyR;
						}else if((d_date + (numDays-p_date)) < 5){
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
	
	/**
	 * This getCategory() operation takes the registration number and returns its category
	 * @param regNo
	 * @return String
	 * @throws SQLException 
	 */
	public String getCategory(String regNo) throws SQLException{
		int regNum = Integer.parseInt(regNo);
		String cat = null;
		sql = "Select category FROM Vehicle WHERE regNo=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, regNum);
			rs = preparedStatement.executeQuery();
			
			while(rs != null && rs.next()){
				cat = rs.getString("category");
			}
		}catch (SQLTimeoutException e) {
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from getCategory() of ReservationDAO-> " + e.getMessage());
			throw new SQLTimeoutException(e.getMessage());
		}catch(SQLException e){
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from getCategory() of ReservationDAO-> " + e.getMessage());
			throw new SQLException(e.getMessage());
		}
		return cat;
	}
	
	/**
	 * This getConfirmationNo() operation takes the registration number and returns its confirmationNo
	 * @param regNo
	 * @return String
	 * @throws SQLException
	 */
	public String getConfirmationNo(String regNo) throws SQLException{
		int regNum = Integer.parseInt(regNo);
		String cat = null;
		sql = "Select confirmationNo FROM makeReservation WHERE regNo=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, regNum);
			rs = preparedStatement.executeQuery();
			
			while(rs != null && rs.next()){
				cat = rs.getString("confirmationNo");
			}
		}catch (SQLTimeoutException e) {
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from getCategory() of ReservationDAO-> " + e.getMessage());
		}catch(SQLException e){
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from getCategory() of ReservationDAO-> " + e.getMessage());
		}
		return cat;
	}
	/**
	 * The addAdditionalEquip() operation updates the requireAdditionalEquip table in superrent database.
	 * @pre !addEquip[i].equals("nothing")
	 * @param confirmationNo
	 * @param addEquip
	 * @param cat
	 * @return true on success else false
	 * @throws SQLException 
	 */
	private boolean addAdditionalEquip(int confirmationNo, String addEquip, String cat) throws SQLException{
		sql = "INSERT INTO RequireAdditionalEquipment values (?, ?, ?, ?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, confirmationNo);
			preparedStatement.setInt(2, 1);
			preparedStatement.setString(3, addEquip);
			preparedStatement.setString(4, cat);
			preparedStatement.setInt(5, 0);
			preparedStatement.executeUpdate();
			
			while(rs != null && rs.next()){
				cat = rs.getString("category");
			}
			return true;
		}catch (SQLTimeoutException e) {
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from addAdditionalEuip() of ReservationDAO-> " + e.getMessage());
		}catch(SQLException e){
			//connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from addAdditionalEuip() of ReservationDAO-> " + e.getMessage());
		}
		return false;
	}
	
	/**
	 * The isReservationExists() operation checks if the requested vehicle is now available
	 * or not while entering the records for the completion of reservation.
	 * @param regNo
	 * @param pickDate
	 * @param dropDate
	 * @return true on success else false
	 * @throws SQLException 
	 */
	private boolean isReservationExists(int regNo, Timestamp pickDate, Timestamp dropDate) throws SQLException{
		sql = "SELECT M.confirmationNo from MakeReservation M WHERE M.regNo = ? AND M.status=0 "
				+ "AND M.confirmationNo IN(SELECT R.confirmationNo from Reservation R WHERE R.confirmationNo=M.confirmationNo AND R.pickDate=? AND R.dropDate=?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, regNo);
			preparedStatement.setTimestamp(2, pickDate);
			preparedStatement.setTimestamp(3, dropDate);
			preparedStatement.executeQuery();
			
			while(rs != null && rs.next()){
				return false;
			}
			return true;
		}catch (SQLTimeoutException e) {
			connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLTimeout Exception coming from isReservationExists() of ReservationDAO-> " + e.getMessage());
		}catch(SQLException e){
			connection.rollback();
			System.out.println("<<ROLLBACK DONE>>SQLConnection Failure coming from isReservationExists() of ReservationDAO-> " + e.getMessage());
		}
		return false;
	}
	
	
	
	
	public static void main(String args[]) throws Exception{
		try {
			ReservationDAO u = new ReservationDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDAO user = null;
		try {
			user = new UserDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DateTime pdt = new DateTime(2014, 04, 25, 11, 0, 0, 0);
		DateTime ddt = new DateTime(2014, 04, 26, 22, 0, 0, 0);
		Timestamp picktimeStamp = new Timestamp(pdt.getMillis());
		Timestamp droptimeStamp = new Timestamp(ddt.getMillis());
		/*
		 * demo of reservation
		 */
		UserBeanModel s = new UserBeanModel();
		s.setAddress("Surrey");
		s.setEmail("brouno@mypet.ca");
		s.setName("Brouno");
		s.setPhoneNumber(998776542);
		try {
			int uid = user.addUser(s);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new  Exception(e1.getMessage());
		}
		String[] add = {"CAR TOW", ""};

		//u.makeReservation(uid, pdt, ddt, 78380, add);
		/*
		 * to display list
		 */
		ReservationListModel m = new ReservationListModel();
		try {
			ReservationDAO dao = new ReservationDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ReservationModel> members = new ArrayList<ReservationModel>();
		//m = dao.displayReservations();
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
