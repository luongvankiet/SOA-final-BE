package com.finalproject.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.finalproject.Semester.Semester;

public class ActivityDAO {
	public Response getAllActivities(){
		List<Activity> acList = new ArrayList<Activity>();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3306/soa_final";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM activity"
					+ " LEFT JOIN semester on activity.semesterID = semester.semesterID");
			while(rs.next()) {
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				acList.add(new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem,
						rs.getInt("score"), rs.getInt("numberOfJoin"), rs.getInt("limitOfJoin")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(acList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stm != null) stm.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity("Error").build();
	}
}
