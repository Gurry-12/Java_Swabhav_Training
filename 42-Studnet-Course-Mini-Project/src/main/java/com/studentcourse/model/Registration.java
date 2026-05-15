package com.studentcourse.model;

import java.time.LocalDate;

public class Registration {

	private int registrationId;
	private int studentId;
	private int courseId;
	private LocalDate registrationDate;
	private String status;

	// for form
	public Registration(int studentId, int courseId, LocalDate registrationDate, String status) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.registrationDate = registrationDate;
		this.status = status;
	}

	// for data from dao
	public Registration(int registrationId, int studentId, int courseId, LocalDate registrationDate, String status) {
		this.registrationId = registrationId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.registrationDate = registrationDate;
		this.status = status;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public String getStatus() {
		return status;
	}

}