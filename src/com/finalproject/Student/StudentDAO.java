package com.finalproject.Student;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
public class StudentDAO {
	public Response getAllStudents() {
		List<Student> studentList = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.prepareStatement("SELECT * FROM student LEFT JOIN account ON student.\"EMAIL\" = account.\"EMAIL\"");
			rs = stmt.executeQuery();
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dob = df.format(rs.getDate("birthday"));
				studentList.add(new Student(rs.getString("studentID"), rs.getString("studentName"), rs.getString("email"),
						dob, rs.getString("gender"), rs.getString("class"), rs.getString("department"), rs.getString("role")));
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
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.prepareStatement("SELECT * FROM student "
					+ "LEFT JOIN account ON student.\"EMAIL\" = account.\"EMAIL\""
					+ "where student.\"EMAIL\" = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String bd = df.format(rs.getDate("birthday"));
				student.setStudentID(rs.getString("studentID"));
				student.setStudentName(rs.getString("studentName"));
				student.setEmail(rs.getString("email"));
				student.setGender(rs.getString("gender"));
				student.setClassID(rs.getString("class"));
				student.setDepartment(rs.getString("department"));
				student.setBirthday(bd);
				student.setRole(rs.getString("role"));
				return student;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return null;
	}
	
	public Response createNewStudent(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = df.parse(student.getBirthday());
			java.sql.Date bd = new java.sql.Date(parsed.getTime());
			stmt.setString(1, student.getStudentID());
			stmt.setString(2, student.getStudentName());
			stmt.setString(3, student.getEmail());
			stmt.setDate(4, bd);
			stmt.setString(5, student.getGender());
			stmt.setString(6, student.getClassID());
			stmt.setString(7, student.getDepartment());
			stmt.executeUpdate();
			String email = student.getEmail();
			return Response.status(Status.OK.getStatusCode()).entity(getStudentByEmail(email)).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity("Create failed!").build();
	}
}
