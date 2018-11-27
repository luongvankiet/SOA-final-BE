package com.finalproject.Student;

import com.finalproject.Account.Account;

public class Student extends Account{
	private int studentID;
	private String studentName;
	private String email;
	private String dateofbirth;
	private String gender;
	private String classID;
	public Student() {}
	public Student(int studentID, String studentName, String email, String dateofbirth, String gender, String classID) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.email = email;
		this.gender = gender;
		this.classID = classID;
		this.dateofbirth = dateofbirth;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
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
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
}
