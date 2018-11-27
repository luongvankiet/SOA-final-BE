package com.finalproject.Notification;

import com.finalproject.Activity.Activity;

public class Notification {
	private int notificationID;
	private String title;
	private String content;
	private Activity activity;
	private String created_at;
	public Notification() {}
	public Notification(int notificationID, String title, String content, Activity activity, String created_at) {
		this.notificationID = notificationID;
		this.title = title;
		this.content = content;
		this.activity = activity;
		this.created_at = created_at;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
}
