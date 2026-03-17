package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.ExamEvaluator;

public class TheoryExam implements ExamEvaluator {
    private final String studentName;
    private final double marksObtained;     // out of 100
    private final int passingMarks;

    public TheoryExam(String studentName, double marksObtained, int passingMarks) {
        this.studentName = studentName;
        this.marksObtained = marksObtained;
        this.passingMarks = passingMarks;
    }

    @Override
    public double evaluateMarks() {
        return marksObtained;
    }

    @Override
    public String calculateGrade() {
        if (marksObtained < 0 || marksObtained > 100) {
            return "Invalid";
        }
        if (marksObtained < passingMarks) {
            return "Fail";
        }

        if (marksObtained >= 90) return "A+";
        if (marksObtained >= 80) return "A";
        if (marksObtained >= 70) return "B+";
        if (marksObtained >= 60) return "B";
        if (marksObtained >= 50) return "C";
        return "Pass";
    }

    public String getStudentName() {
        return studentName;
    }
}