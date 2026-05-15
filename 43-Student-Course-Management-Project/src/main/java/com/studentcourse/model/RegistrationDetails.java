package com.studentcourse.model;

import java.time.LocalDate;

public class RegistrationDetails {

	private int registrationId;
	private int studentId;
	private int courseId;
	private LocalDate registrationDate;
	private String status;
	private String studentName;
	private String courseName;

	public RegistrationDetails(int registrationId, int studentId, int courseId, LocalDate registrationDate,
			String status, String studentName, String courseName) {
		this.registrationId = registrationId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.registrationDate = registrationDate;
		this.status = status;
		this.studentName = studentName;
		this.courseName = courseName;
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

	public String getStudentName() {
		return studentName;
	}

	public String getCourseName() {
		return courseName;
	}

}
