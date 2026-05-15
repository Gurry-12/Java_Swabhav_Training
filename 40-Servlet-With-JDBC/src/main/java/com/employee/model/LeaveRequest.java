package com.employee.model;

public class LeaveRequest {
	private String name;
	private String id;
	private String department;
	private String type;
	private int days;
	private String reason;
	private String status;

	// Default constructor (required for JavaBeans)
	public LeaveRequest() {
	}

	// Parameterized constructor for DAO use
	public LeaveRequest(String name, String id, String department, String type, int days, String reason,
			String status) {
		this.name = name;
		this.id = id;
		this.department = department;
		this.type = type;
		this.days = days;
		this.reason = reason;
		this.status = status;
	}

	// Getters and Setters (Essential for ${leave.name} syntax in JSP)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}