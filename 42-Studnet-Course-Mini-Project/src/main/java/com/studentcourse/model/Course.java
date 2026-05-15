package com.studentcourse.model;

public class Course {
	
	private int courseId;
    private String courseName;
	private String duration; 
    private double fees;
    private String trainerName;
    
    // for data from dao
	public Course(int courseId, String courseName, String duration, double fees, String trainerName) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.duration = duration;
		this.fees = fees;
		this.trainerName = trainerName;
	}
	
	// for form
	public Course(String courseName, String duration, double fees, String trainerName) {
		this.courseName = courseName;
		this.duration = duration;
		this.fees = fees;
		this.trainerName = trainerName;
	}


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