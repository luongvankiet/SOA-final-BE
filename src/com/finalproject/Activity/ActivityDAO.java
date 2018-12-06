package com.finalproject.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM activity"
					+ " LEFT JOIN semester on activity.\"SEMESTERID\" = semester.\"SEMESTERID\"");
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyyy");
				String date = df.format(rs.getTimestamp("date"));
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				acList.add(new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem, rs.getInt("score"), rs.getInt("numberOfJoin"), 
						rs.getInt("limitOfJoin"), date, rs.getString("status"), rs.getString("place")));
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
	
	public Response getActivitiesBySemesterID(int semesterID){
		List<Activity> acList = new ArrayList<Activity>();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM activity"
					+ " LEFT JOIN semester on activity.\"SEMESTERID\" = semester.\"SEMESTERID\""
					+ " WHERE activity.\"SEMESTERID\" = "+semesterID);
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyyy");
				String date = df.format(rs.getTimestamp("date"));
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				acList.add(new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem, rs.getInt("score"), rs.getInt("numberOfJoin"), 
						rs.getInt("limitOfJoin"), date, rs.getString("status"), rs.getString("place")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(acList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stm != null) stm.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		String error = "Error";
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(error).build();
	}
	
	public Response getActivitiesByStudentID(String studentID){
		List<Activity> acList = new ArrayList<Activity>();
		Connection conn = null;
		Statement stm = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.createStatement();
			stmt = conn.prepareStatement("SELECT * FROM student_activity sa"
					+ " LEFT JOIN (select * from activity left join semester on activity.\"SEMESTERID\" = semester.\"SEMESTERID\") ac"
					+ " on sa.\"ACTIVITYID\" = ac.\"ACTIVITYID\""
					+ " WHERE sa.\"STUDENTID\" = ?");
			stmt.setString(1, studentID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyyy");
				String date = df.format(rs.getTimestamp("date"));
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				acList.add(new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem, rs.getInt("score"), rs.getInt("numberOfJoin"), 
						rs.getInt("limitOfJoin"), date, rs.getString("status"), rs.getString("place")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(acList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stm != null) stm.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		String error = "Error";
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(error).build();
	}

	public Response onParticipateInActivity(String studentID, Activity activity){
		List<Activity> acList = new ArrayList<Activity>();
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			
			stmt1 = conn.prepareStatement("INSERT INTO student_activity values(?,?)");
			stmt1.setString(1, studentID);
			stmt1.setInt(2, activity.getActivityID());
			stmt1.executeUpdate();
			
			stmt2 = conn.prepareStatement("UPDATE activity SET \"NUMBEROFJOIN\"=? WHERE \"ACTIVITYID\" = ?");
			stmt2.setInt(1, activity.getNumberOfJoin());
			stmt2.setInt(2, activity.getActivityID());
			stmt2.executeUpdate();

			stmt3 = conn.prepareStatement("SELECT * FROM student_activity sa"
					+ " LEFT JOIN (select * from activity left join semester on activity.\"SEMESTERID\" = semester.\"SEMESTERID\") ac"
					+ " on sa.\"ACTIVITYID\" = ac.\"ACTIVITYID\""
					+ " WHERE sa.\"STUDENTID\" = ?");
			stmt3.setString(1, studentID);
			rs = stmt3.executeQuery();
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyyy");
				String date = df.format(rs.getTimestamp("date"));
				Semester sem = new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years"));
				acList.add(new Activity(rs.getInt("activityID"), rs.getString("activityContent"), sem, rs.getInt("score"), rs.getInt("numberOfJoin"), 
						rs.getInt("limitOfJoin"), date, rs.getString("status"), rs.getString("place")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(acList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt1 != null) stmt1.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt2 != null) stmt2.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt3 != null) stmt3.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity("Error").build();
	}
	public Response showActivities(){
		List<StudentActivity> acList = new ArrayList<StudentActivity>();
		Connection conn = null;
		Statement stm = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stm = conn.createStatement();
			stmt = conn.prepareStatement("SELECT * FROM student_activity");
			rs = stmt.executeQuery();
			while(rs.next()) {
				acList.add(new StudentActivity(rs.getString("studentID"), rs.getInt("activityID")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(acList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stm != null) stm.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		String error = "Error";
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(error).build();
	}
}
