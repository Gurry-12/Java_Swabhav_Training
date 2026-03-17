package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.ExamEvaluator;

public class PracticalExam implements ExamEvaluator {
    private final String studentName;
    private final double performanceScore;   // out of 50
    private final double vivaScore;          // out of 20
    private final double recordBook;         // out of 30

    public PracticalExam(String studentName, double performance, double viva, double record) {
        this.studentName = studentName;
        this.performanceScore = performance;
        this.vivaScore = viva;
        this.recordBook = record;
    }

    @Override
    public double evaluateMarks() {
        return performanceScore + vivaScore + recordBook;   // out of 100
    }

    @Override
    public String calculateGrade() {
        double total = evaluateMarks();

        if (total < 0 || total > 100) return "Invalid";

        if (total >= 75) return "Excellent";
        if (total >= 60) return "Good";
        if (total >= 45) return "Satisfactory";
        return "Needs Improvement";
    }

    public String getStudentName() {
        return studentName;
    }
}
