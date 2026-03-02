package com.gurpreet.arrayobject.models;

public class Course {
	
	private static int idCreater = 0;
	private String courseId;
	private String courseName;
	private double baseFee;
	private static int totalCourses = 0;
	protected String courseType;
	
	public Course(String courseName, double baseFee) {
		
		if(courseName == null || courseName.trim().isEmpty() || courseName.isBlank()) {
			System.out.println("Course name can not be empty.");
			return;
		}
		
		if(baseFee < 0) {
			System.out.println("Base fee can not be negative.");
		}
		
		this.courseId = "C00" + Integer.toString(++idCreater);
		this.courseName = courseName;
		this.baseFee = baseFee;
		totalCourses++;
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public double getBaseFee() {
		return baseFee;
	}
	
	public static int getTotalCourses() {
		return totalCourses;
	}
	
	public double getTotalFee() {
		return baseFee;
	}
	

	public void calculateTotalFee(double additionalCharges, double labCharges) {
		
	}

	public void calculateTotalFee(double techCharges) {
		
	}

	public String getCourseType() {
		return courseType;
	}
	
	
}
