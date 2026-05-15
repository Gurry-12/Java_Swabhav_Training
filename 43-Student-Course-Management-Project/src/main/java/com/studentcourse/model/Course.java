package com.studentcourse.model;

public class Course {

	private int courseId;
	private String courseName;
	private String duration;
	private double fees;
	private String trainerName;

	public Course() {
	}

	public Course(int courseId, String courseName, String duration, double fees, String trainerName) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.duration = duration;
		this.fees = fees;
		this.trainerName = trainerName;
	}

	public Course(String courseName, String duration, double fees, String trainerName) {
		this.courseName = courseName;
		this.duration = duration;
		this.fees = fees;
		this.trainerName = trainerName;
	}

	// Getters
	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDuration() {
		return duration;
	}

	public double getFees() {
		return fees;
	}

	public String getTrainerName() {
		return trainerName;
	}
}