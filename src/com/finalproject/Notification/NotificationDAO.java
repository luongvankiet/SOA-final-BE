package com.finalproject.Notification;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.finalproject.Activity.Activity;
import com.finalproject.Semester.Semester;


public class NotificationDAO {
	public Response getAllNotification(){
		List<Notification> noList = new ArrayList<Notification>();
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
			rs = stm.executeQuery("SELECT * FROM notification"
					+ " LEFT JOIN activity on notification.activityID = activity.activityID "
					+ " LEFT JOIN semester on activity.semesterID = semester.semesterID");
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String created_at = df.format(rs.getDate("created_at"));
				Activity activity;
				if(rs.getInt("activityID") == 0) {
					activity = null;
				} else {
					Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
					activity = new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem,
							rs.getInt("score"), rs.getInt("numberOfJoin"), rs.getInt("limitOfJoin"));
				}
				noList.add(new Notification(rs.getInt("notificationID"), rs.getString("title"), rs.getString("content"),
						activity, created_at));
			}
			return Response.status(Status.OK.getStatusCode()).entity(noList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stm != null) stm.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity("Error").build();
	}
	
	public Response getNotification(int id) {
		List<Notification> notify = new ArrayList<Notification>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3306/soa_final";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.prepareStatement("SELECT * FROM notification"
					+ " LEFT JOIN activity on notification.activityID = activity.activityID"
					+ " LEFT JOIN semester on activity.semesterID = semester.semesterID"
					+ " where notification.notificationID = ?");
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String created_at = df.format(rs.getDate("created_at"));
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				Activity ac = new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem,
						rs.getInt("score"), rs.getInt("numberOfJoin"), rs.getInt("limitOfJoin"));
				notify.add(new Notification(rs.getInt("notificationID"), rs.getString("title"), rs.getString("content"),
						ac, created_at));
			}
			return Response.status(Status.OK.getStatusCode()).entity(notify).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stm != null) stm.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode()).build();
	}
}
