package org.reservation.module.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static void main(String args[]){
		DatabaseConnection c = new DatabaseConnection();
		c.getConnection();
		
	}

	public Connection getConnection() {
		Connection con;
		String url = "jdbc:mysql://superrent.c6stxmjzckce.us-west-2.rds.amazonaws.com:3306/superrent";
		String username = "superrent";
		String password = "justdoit505";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.print("Success");
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("Noooo Success");
		return null;
	}
}