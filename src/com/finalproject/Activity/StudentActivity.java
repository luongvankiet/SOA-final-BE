package com.finalproject.Activity;

public class StudentActivity {
	private String studentID;
	private int activityID;
	public StudentActivity() {}
	public StudentActivity(String studentID, int activityID) {
		this.studentID = studentID;
		this.activityID = activityID;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
}
