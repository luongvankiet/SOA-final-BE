package com.finalproject.Semester;

public class Semester {
	private int semesterID;
	private String semester;
	private String years;
	public Semester() {}
	public Semester(int semesterID, String semester, String years) {
		this.semesterID = semesterID;
		this.semester = semester;
		this.setYears(years);
	}
	public int getSemesterID() {
		return semesterID;
	}
	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	
}
