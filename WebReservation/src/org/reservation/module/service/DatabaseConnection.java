package org.reservation.module.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {
	
	public static void main(String args[]){
		DatabaseConnection c = new DatabaseConnection();
		try {
			c.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * This getConnection() operation builds the connection with the database.
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception{
		Connection c = null;
		try {
				Context ctx = new InitialContext();		
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydatabase");
				c = ds.getConnection();
				c.setAutoCommit(false);
				return c;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Oops! Unable to make connection with database: "+e.getMessage());
				throw new SQLException(e.getMessage());
	
			}
			catch (NamingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
				throw new NamingException(e.getMessage());
			}
		
		
		
		
		
		/*
		Connection con;
		String url = "jdbc:mysql://superrent.c6stxmjzckce.us-west-2.rds.amazonaws.com:3306/superrent";
		String username = "superrent";
		String password = "justdoit505";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			System.out.print("Success");
			return con;
		} 
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("ClassNotFoundException: raised while loading the class [occured in DatabaseConnection]: "+e.getMessage());
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Oops! Unable to make connection with database: "+e.getMessage());
		}
		System.out.print("Noooo Success");
		return null;
		*/
	}
}