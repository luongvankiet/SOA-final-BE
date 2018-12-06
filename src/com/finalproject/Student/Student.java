package com.finalproject.Student;

import com.finalproject.Account.Account;

public class Student extends Account{
	private String studentID;
	private String studentName;
	private String email;
	private String birthday;
	private String gender;
	private String classID;
	private String department;
	private String role;
	public Student() {}
	public Student(String studentID, String studentName, String email, String birthday, String gender, String classID, String department, String role) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.email = email;
		this.gender = gender;
		this.classID = classID;
		this.setBirthday(birthday);
		this.department = department;
		this.role = role;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
