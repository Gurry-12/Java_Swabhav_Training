package com.studentcourse.model;

public class Admin {

	private int adminId;
	private String username;
	private String password;

	// constructor for the dao
	public Admin(int adminId, String username, String password) {
		this.adminId = adminId;
		this.username = username;
		this.password = password;
	}

	// Getters
	public int getAdminId() {
		return adminId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
