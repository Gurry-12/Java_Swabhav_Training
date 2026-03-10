package com.basics.objectcalisthenics;

public class Student {
	 private String studentName;
	 private int marks;
	 
	 Student(String studentName, int marks){
		 if(studentName == null || studentName.isEmpty()) {
			 System.out.println("Please Enter a Valid Name");
			 return;
		 }
		 
		 if(marks < 0) {
			 System.out.println("Marks cannot be negative. ");
			 return;
		 }
		 
		 this.setStudentName(studentName);
		 this.setMarks(marks);
	 }

	 public String getStudentName() {
		return studentName;
	 }

	 public void setStudentName(String studentName) {
		this.studentName = studentName;
	 }

	 public int getMarks() {
		return marks;
	 }

	 public void setMarks(int marks) {
		this.marks = marks;
	 }
}
