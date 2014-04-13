package org.reservation.module.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.reservation.module.model.ClubMemberBeanModel;
import org.reservation.module.model.ClubMemberListModel;
import org.reservation.module.model.UserBeanModel;
import org.reservation.module.model.UserListModel;

public class UserDAO {
	private static int baseId = 872201;
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private static java.sql.Statement stmt;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private static UserListModel users = new UserListModel();
	private static Date utilDate;
	
	//intialization
	public UserDAO(){
		//open a connection
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getConnection();
	}
	
	public UserListModel displayUsers(){
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
				c1.setPhoneNumber(rs.getInt(4));
				c1.setDateCreated(rs.getDate(5));
				users.getUsers().add(c1);
			}
		} catch (SQLException e) {
			System.out.println("Exception coming from displayClubMember() of CLubMemberDAO---> " + e.getMessage());
		}
		cm = users;
		return cm;
	}
	
	/*
	 * Add the user record at the time of reservation
	 */
	private boolean addUser(UserBeanModel user1){
		int uid = isUserExist(user1.getEmail());
		if(uid != 0){
			sql = "SELECT name,email,phoneNumber,dateCreated,type,address FROM user where uid=?";
			try {
				preparedStatement.setInt(1, uid);
				preparedStatement.executeUpdate();
				//see the result
				if(rs != null && rs.next()){
	                System.out.println("Generated User Id: "+rs.getInt(1));
	                System.out.println("Generated User Id: "+rs.getString(2));
	                System.out.println("Generated User Id: "+rs.getString(3));
				}
			} catch (SQLException e) {
				System.out.println("Exception coming from addUser(1) of USERDAO---> " + e.getMessage());
			}
			
			return true;
		}else{
		/*
		 * Auto generate User id
		 */
			//java.sql.Date currdate= new java.sql.Date(System.currentTimeMillis());
			
			sql = "INSERT INTO user (name,email,phoneNumber,dateCreated,type,address) VALUES (?,?,?,?,?,?)";
			try {
				preparedStatement = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, user1.getName());
				preparedStatement.setString(2, user1.getEmail());
				preparedStatement.setInt(3, user1.getPhoneNumber());
				preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				preparedStatement.setInt(5, 1);
				preparedStatement.setString(6, user1.getAddress());
				
				preparedStatement.executeUpdate();
				rs = preparedStatement.getGeneratedKeys();
				//see the result
				if(rs != null && rs.next()){
	                System.out.println("Generated User Id: "+rs.getInt(1));
	            }
			} catch (SQLException e) {
				System.out.println("Exception coming from addUser(2) of USERDAO---> " + e.getMessage());
			}
			return true;
		}
	}
	
	/*
	 * check; if the user already has the entry in table
	 */
	
	private int isUserExist(String email){
		sql = "SELECT uid from user where email=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);	
			
			//execute select statement
			rs = preparedStatement.executeQuery();
			if (rs.next()){
				return (rs.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Exception coming from isUserExist() of USERDAO---> " + e.getMessage());
		}
		
		return 0;
	}
	
	
	public static void main(String args[]){
		/*
		 * Demo to display list
		 */
		UserListModel m = new UserListModel();
		UserDAO dao = new UserDAO();
		ArrayList<UserBeanModel> members = new ArrayList<UserBeanModel>();
		m = dao.displayUsers();
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
		UserDAO u = new UserDAO();
		UserBeanModel s = new UserBeanModel();
		s.setAddress("Surrey");
		s.setEmail("parminder@mss.ubc.ca");
		s.setName("Parminder");
		s.setPhoneNumber(12345);
		u.addUser(s);
	}
}
