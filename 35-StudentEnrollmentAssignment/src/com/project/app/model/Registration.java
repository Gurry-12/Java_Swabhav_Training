package com.project.app.model;

public class Registration {

    private int regId;        // DB generated
    private int studentId;    // FK
    private int courseId;
    private double feesPaid;

    // Constructor for creating new registration (no regId yet)
    public Registration(int studentId, int courseId, double feesPaid) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.feesPaid = feesPaid;
    }

    // Constructor when fetching from DB
    public Registration(int regId, int studentId, int courseId, double feesPaid) {
        this.regId = regId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.feesPaid = feesPaid;
    }

	public int getRegId() {
		return regId;
	}

	public int getStudentId() {
		return studentId;
	}

	public double getFeesPaid() {
		return feesPaid;
	}
 
	@Override
	public String toString() {
		return regId + " "  + studentId + " " + " " + feesPaid;
	}

	public int getCourseId() {
		return courseId;
	}

}