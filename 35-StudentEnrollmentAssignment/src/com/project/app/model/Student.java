package com.project.app.model;

public class Student {

	private int id;
	private String name;
	private int age;
	private int departmentId;

	// Constructor for creating new student
	public Student(int id, String name, int age, int departmentId) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.departmentId = departmentId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return id + "	 " + name + "	 " + age + "  " + departmentId;
	}

	public int getDepartmentId() {
		return departmentId;
	}
}