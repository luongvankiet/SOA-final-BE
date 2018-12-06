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
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.prepareStatement("SELECT * FROM notification"
					+ " LEFT JOIN activity on notification.\"ACTIVITYID\" = activity.\"ACTIVITYID\""
					+ " LEFT JOIN semester on activity.\"SEMESTERID\" = semester.\"SEMESTERID\"");
			rs = stm.executeQuery();
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String created_at = df.format(rs.getDate("created_at"));
				Activity activity;
				if(rs.getInt("activityID") == 0) {
					activity = null;
				} else {
					String date = null;
					if(rs.getDate("date") != null) {
						date = df.format(rs.getDate("date"));
					}
					Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
					activity = new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem, rs.getInt("score"), rs.getInt("numberOfJoin"), 
							rs.getInt("limitOfJoin"), date, rs.getString("status"), rs.getString("place"));
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
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.prepareStatement("SELECT * FROM notification"
					+ " LEFT JOIN activity on notification.\"ACTIVITYID\" = activity.\"ACTIVITYID\""
					+ " LEFT JOIN semester on activity.\"SEMESTERID\" = semester.\"SEMESTERID\""
					+ " where notification.\"NOTIFICATIONID\" = ?");
			stm.setInt(1, id);
			rs = stm.executeQuery();	
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String created_at = df.format(rs.getDate("created_at"));
				String date = df.format(rs.getDate("date"));
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				Activity ac = new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem, rs.getInt("score"), rs.getInt("numberOfJoin"), 
							rs.getInt("limitOfJoin"), date, rs.getString("status"), rs.getString("place"));
					
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
