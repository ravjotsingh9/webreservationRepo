package org.reservation.module.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.reservation.module.model.ClubMemberBeanModel;
import org.reservation.module.model.ClubMemberListModel;
import org.reservation.module.model.UserBeanModel;
import org.reservation.module.model.UserListModel;

public class UserDAO {
	//private static int baseId = 872201;
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private static UserListModel users = new UserListModel();
	private static Date utilDate;
	private UserBeanModel user = new UserBeanModel();
	
	//intialization
	public UserDAO() throws Exception{
		//open a connection
		databaseConnection = new DatabaseConnection();
		try {
			connection = databaseConnection.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * This displayUsers() operation displays the list of all the customers.
	 * @return UserListModel
	 * @throws Exception
	 */
	public UserListModel displayUsers() throws Exception{
		UserListModel cm = new UserListModel();
		
		sql = "SELECT * FROM user";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				UserBeanModel c1 = new UserBeanModel();
				c1.setUid(rs.getInt("uid"));
				c1.setName(rs.getString(2));
				c1.setEmail(rs.getString(3));
				c1.setPhoneNumber(rs.getLong(4));
				c1.setDateCreated(rs.getDate(5));
				users.getUsers().add(c1);
			}
		} catch (SQLException e) {
			System.out.println("Exception coming from displayClubMember() of CLubMemberDAO---> " + e.getMessage());
			throw new  SQLException(e.getMessage());
		}
		cm = users;
		return cm;
	}
	
	/** This addUser() operation adds the user record at the time of reservation but if it already exists 
	 * then it will return his userid without updating superrent database.
	 * @pre isUserExist(user1.getEmail())
	 * @pre uid != 0
	 * @param user1
	 * @return userid
	 * @throws ParseException
	 * @throws Exception
	 */
	public int addUser(UserBeanModel user1) throws ParseException, Exception{
		int uid = isUserExist(user1.getEmail());
		if(uid != 0){	
			return uid;
		}else{
		/*
		 * Auto generate User id
		 */
			
			sql = "INSERT INTO User (name,email,phoneNumber,dateCreated,type,address) VALUES (?,?,?,?,?,?)";
			System.out.println("Recently added");
			try {
				preparedStatement = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, user1.getName());
				preparedStatement.setString(2, user1.getEmail());
				preparedStatement.setLong(3, user1.getPhoneNumber());
				java.util.Date curr_date = new java.util.Date();
		       /* SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		        String source= format.format(curr_date);
				java.sql.Date d= new java.sql.Date(format.parse(source).getTime());
				*/
				java.sql.Timestamp d = new java.sql.Timestamp(curr_date.getTime());
				preparedStatement.setTimestamp(4, d);
				preparedStatement.setInt(5, 0);
				preparedStatement.setString(6, user1.getAddress());
				
				preparedStatement.executeUpdate();
				rs = preparedStatement.getGeneratedKeys();
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Exception coming from addUser(2) of USERDAO---> " + e.getMessage());
				throw new  SQLException(e.getMessage());
			}
			int newUid = isUserExist(user1.getEmail());
			return newUid;
		}
	}
	
	/*
	 * check; if the user already has the entry in table
	 */
	
	private int isUserExist(String email) throws Exception{
		sql = "SELECT * from User where email=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);	
			
			//execute select statement
			rs = preparedStatement.executeQuery();
			/*if (!rs.next()){
				return 0;
			}*/
			if(rs.next()){
				user.setUid(rs.getInt("uid"));
				//return user.getUid();
			}else{
				return 0;
			}
		} catch (SQLException e) {
			System.out.println("Exception coming from isUserExist() of USERDAO---> " + e.getMessage());
			throw new  SQLException(e.getMessage());
		}
		int uid = user.getUid();
		return uid;
	}
	
	
	public static void main(String args[]) throws ParseException{
		/*
		 * Demo to display list
		 */
		UserListModel m = new UserListModel();
		UserDAO dao = null;
		try {
			dao = new UserDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<UserBeanModel> members = new ArrayList<UserBeanModel>();
		try {
			m = dao.displayUsers();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//display
		members = m.getUsers();
		for (int i=0; i<members.size(); i++){
			System.out.println("User "+i+"-->");
			System.out.println(members.get(i).getUid());
			System.out.println(members.get(i).getName());
			System.out.println(members.get(i).getEmail());
			System.out.println(members.get(i).getDateCreated());
		}
		/*
		 * Demo to add user
		 */
		UserDAO u = null;
		try {
			u = new UserDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserBeanModel s = new UserBeanModel();
		s.setAddress("Surrey");
		s.setEmail("jenny@mypet.ca");
		s.setName("Jenny");
		s.setPhoneNumber(89382);
		int res = 0;
		try {
			res = u.addUser(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result = "+res);
	}
}
