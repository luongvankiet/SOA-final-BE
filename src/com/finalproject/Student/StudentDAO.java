package com.finalproject.Student;

import java.sql.*;
import java.text.*;
import java.util.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
public class StudentDAO {
	public Response getAllStudents() {
		List<Student> studentList = new ArrayList<Student>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3306/soa_final";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM student");
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dob = df.format(rs.getDate("dateofbirth"));
				studentList.add(new Student(rs.getInt("studentID"), rs.getString("studentName"), rs.getString("email"),
						dob, rs.getString("gender"), rs.getString("classID")));
			}
			return Response.status(Status.OK.getStatusCode()).entity(studentList).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.NOT_FOUND.getStatusCode()).build();
	}
	
	public Student getStudentByEmail(String email) {
		Student student = new Student();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3306/soa_final";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.prepareStatement("SELECT * FROM student where email = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			rs.next();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String dob = df.format(rs.getDate("dateofbirth"));
			student.setStudentID(rs.getInt("studentID"));
			student.setStudentName(rs.getString("studentName"));
			student.setEmail(rs.getString("email"));
			student.setGender(rs.getString("gender"));
			student.setClassID(rs.getString("classID"));
			student.setDateofbirth(dob);
			return student;
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return null;
	}
}
