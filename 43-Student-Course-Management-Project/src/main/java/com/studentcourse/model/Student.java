package com.studentcourse.model;

public class Student {

	private int studentId;
	private String studentName;
	private String email;
	private String phone;
	private int age;
	private String city;

	// Default constructor
	public Student() {
	}

	public Student(int studentId, String studentName, String email, String phone, int age, String city) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.city = city;
	}

	public Student(String name, String email, String phone, int age, String city) {
		this.studentName = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.city = city;
	}

	// Getters
	public int getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public int getAge() {
		return age;
	}

	public String getCity() {
		return city;
	}

}