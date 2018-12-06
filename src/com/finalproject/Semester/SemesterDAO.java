package com.finalproject.Semester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class SemesterDAO {
	public Response getAllSemester(){
		List<Semester> semList = new ArrayList<Semester>();
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
			rs = stm.executeQuery("SELECT * FROM semester");
			while(rs.next()) {
				semList.add(new Semester(rs.getInt("semesterID"), rs.getString("semester"), rs.getString("years")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(semList).build();
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
