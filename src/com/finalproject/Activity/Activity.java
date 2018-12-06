package com.finalproject.Activity;

import com.finalproject.Semester.Semester;

public class Activity {
	private int activityID;
	private String activityContent;
	private Semester semester;
	private int score;
	private int numberOfJoin;
	private int limitOfJoin;
	private String date;
	private String status;
	private String place;
	public Activity() {}
	public Activity(int activityID, String activityContent, Semester semester, int score, int numberOfJoin,
			int limitOfJoin, String date, String status, String place) {
		this.activityID = activityID;
		this.activityContent = activityContent;
		this.setSemester(semester);
		this.score = score;
		this.numberOfJoin = numberOfJoin;
		this.limitOfJoin = limitOfJoin;
		this.date = date;
		this.status = status;
		this.place = place;
		
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getNumberOfJoin() {
		return numberOfJoin;
	}
	public void setNumberOfJoin(int numberOfJoin) {
		this.numberOfJoin = numberOfJoin;
	}
	public int getLimitOfJoin() {
		return limitOfJoin;
	}
	public void setLimitOfJoin(int limitOfJoin) {
		this.limitOfJoin = limitOfJoin;
	}
	public Semester getSemester() {
		return semester;
	}
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
}
