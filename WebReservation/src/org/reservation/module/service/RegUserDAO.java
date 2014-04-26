package org.reservation.module.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.reservation.module.model.RegUserBeanModel;
import org.reservation.module.model.RegUserListModel;

public class RegUserDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private static RegUserListModel regusers = new RegUserListModel();
	
	public RegUserDAO(){
		//open a connection
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getConnection();
	}
	
	public RegUserListModel displayRegUsers(){
		RegUserListModel ru = new RegUserListModel();
		sql = "SELECT * FROM RegUser";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				RegUserBeanModel c1 = new RegUserBeanModel();
				c1.setUid(rs.getInt("uid"));
				c1.setUsername(rs.getString(2));
				c1.setPassword(rs.getString(3));
				c1.setDatecreated(rs.getDate(4));
				regusers.getRegusers().add(c1);
			}
		} catch (SQLException e) {
			System.out.println("Exception coming from displayRegUsers() of RegUserDAO---> " + e.getMessage());
		}
		ru = regusers;
		return ru;
	}
	
	public boolean isLogin(String username, String password){
		sql = "SELECT count(*) FROM RegUser WHERE username=? AND password=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				if(rs.getInt(1)>0)
					return true;
			}
		} catch (SQLException e) {
			System.out.println("Exception coming from isLogin() of RegUserDAO---> " + e.getMessage());
		}
		return false;
	}
	

	public static void main(String args[]){
		/*
		 * Demo to display list
		 */
		RegUserListModel m = new RegUserListModel();
		RegUserDAO dao = new RegUserDAO();
		ArrayList<RegUserBeanModel> members = new ArrayList<RegUserBeanModel>();
		m = dao.displayRegUsers();
		//display
		members = m.getRegusers();
		for (int i=0; i<members.size(); i++){
			System.out.println("RegUSer "+i+"-->");
			System.out.println(members.get(i).getUid());
			System.out.println(members.get(i).getUsername());
			System.out.println(members.get(i).getPassword());
			System.out.println(members.get(i).getDatecreated());
		}
		
		/*
		 * Demo to view points
		 */
		boolean pts = dao.isLogin("clubmember", "newpasswo");
		System.out.println("Can Login? ="+pts);
	}
}
