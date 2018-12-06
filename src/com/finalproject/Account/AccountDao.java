package com.finalproject.Account;

import java.sql.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.finalproject.Student.StudentDAO;

public class AccountDao {
	StudentDAO studentDao =  new StudentDAO();
	
	public Response login(Account acc) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM account");
			while(rs.next()) {
				if(acc.getUsername().equals(rs.getString("username")) && acc.getPassword().equals(rs.getString("password"))) {
					if(rs.getString("role").equals("student")) {
						return Response.status(Status.OK.getStatusCode()).entity(studentDao.getStudentByEmail(rs.getString("email"))).build();
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized").build();
	}
	
	public Response register(Account acc) {
		Connection conn = null;
		PreparedStatement ps = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			String connectionUrl = "jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com/d6u2br8eoqak53?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			String connectionUser = "ycgrutozfhhllk";
			String connectionPassword = "e828c983daf61d1f8fe2a3f972db44f037605c0841e64242721fef24d94f2c60";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			sm = conn.createStatement();
			rs = sm.executeQuery("select * from account");
			while(rs.next()) {
				if(acc.getEmail().equals(rs.getString("email")) && acc.getUsername().equals(rs.getString("username"))) {
					return Response.status(Status.BAD_REQUEST.getStatusCode()).entity("Email or username is already exist").build();
				}
			}
			ps = conn.prepareStatement("insert into account value(?,?,?,?)");
			ps.setString(1, acc.getUsername());
			ps.setString(2, acc.getPassword());
			ps.setString(3, acc.getEmail());
			ps.setString(4, acc.getRole());
			ps.executeUpdate();
			return Response.status(Status.OK.getStatusCode()).entity(studentDao.getStudentByEmail(acc.getEmail())).build();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace();}
			try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity("Create failed").build();
	}
}
