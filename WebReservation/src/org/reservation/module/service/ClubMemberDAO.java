package org.reservation.module.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.reservation.module.model.ClubMemberBeanModel;
import org.reservation.module.model.ClubMemberListModel;

public class ClubMemberDAO {
	private static Connection connection;
	private static DatabaseConnection databaseConnection;
	private String sql;
	private ResultSet rs;
	private PreparedStatement preparedStatement;
	private static ClubMemberListModel members = new ClubMemberListModel();
	
	public ClubMemberDAO(){
		//open a connection
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getConnection();
	}
	
	public ClubMemberListModel displayClubMembers(){
		ClubMemberListModel cm = new ClubMemberListModel();
		
		sql = "SELECT * FROM ClubMember";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				ClubMemberBeanModel c1 = new ClubMemberBeanModel();
				c1.setUid(rs.getInt("uid"));
				c1.setMembershipNo(rs.getInt(2));
				c1.setPoints(rs.getDouble(3));
				c1.setDatecreated(rs.getDate(4));
				members.getMembers().add(c1);
			}
		} catch (SQLException e) {
			System.out.println("Exception coming from displayClubMember() of CLubMemberDAO---> " + e.getMessage());
		}
		cm = members;
		return cm;
	}
	
	public int viewPoints(int membershipNo){
		
		sql = "SELECT points FROM ClubMember where membershipNo=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, membershipNo);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				return (int)rs.getDouble(1);
			}
			}catch(SQLException e){
				System.out.println("Exception coming from viewPoints() of CLubMemberDAO---> " + e.getMessage());
			}
		return 0;
	}
	
	public static void main(String args[]){
		/*
		 * Demo to display list
		 */
		ClubMemberListModel m = new ClubMemberListModel();
		ClubMemberDAO dao = new ClubMemberDAO();
		ArrayList<ClubMemberBeanModel> members = new ArrayList<ClubMemberBeanModel>();
		m = dao.displayClubMembers();
		//display
		members = m.getMembers();
		for (int i=0; i<members.size(); i++){
			System.out.println("Clubmember "+i+"-->");
			System.out.println(members.get(i).getUid());
			System.out.println(members.get(i).getMembershipNo());
			System.out.println(members.get(i).getPoints());
			System.out.println(members.get(i).getDatecreated());
		}
		
		/*
		 * Demo to view points
		 */
		double pts = dao.viewPoints(456);
		System.out.println("Points="+pts);
	}
}