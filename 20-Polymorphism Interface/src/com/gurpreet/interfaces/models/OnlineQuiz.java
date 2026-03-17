package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.ExamEvaluator;

public class OnlineQuiz implements ExamEvaluator {
    private final String studentName;
    private final int correctAnswers;
    private final int totalQuestions;

    public OnlineQuiz(String studentName, int correct, int total) {
        this.studentName = studentName;
        this.correctAnswers = correct;
        this.totalQuestions = total;
    }

    @Override
    public double evaluateMarks() {
        if (totalQuestions == 0) return 0;
        return (correctAnswers * 100.0) / totalQuestions;
    }

    @Override
    public String calculateGrade() {
        double percentage = evaluateMarks();

        if (percentage >= 85) return "Outstanding";
        if (percentage >= 70) return "Very Good";
        if (percentage >= 55) return "Good";
        if (percentage >= 40) return "Average";
        return "Needs Improvement";
    }

    public String getStudentName() {
        return studentName;
    }
}
