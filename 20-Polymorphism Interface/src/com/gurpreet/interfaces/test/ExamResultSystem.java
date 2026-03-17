package com.gurpreet.interfaces.test;

import com.gurpreet.interfaces.interfaces.ExamEvaluator;
import com.gurpreet.interfaces.models.OnlineQuiz;
import com.gurpreet.interfaces.models.PracticalExam;
import com.gurpreet.interfaces.models.TheoryExam;

public class ExamResultSystem {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("          EXAM RESULT EVALUATION SYSTEM         ");
        System.out.println("═══════════════════════════════════════════════\n");

        ExamEvaluator[] exams = new ExamEvaluator[3];

        exams[0] = new TheoryExam("Gurpreet Singh", 84.5, 40);
        exams[1] = new PracticalExam("Priya Sharma", 42.0, 16.5, 25.0);
        exams[2] = new OnlineQuiz("Aarav Patel", 38, 50);

        System.out.printf("%-20s %-12s %-10s %-12s%n",
                "Student", "Exam Type", "Marks", "Grade");
        System.out.println("───────────────────────────────────────────────");

        for (ExamEvaluator exam : exams) {
            String studentName = ((Object) exam instanceof TheoryExam te ? te.getStudentName() :
                                 (Object) exam instanceof PracticalExam pe ? pe.getStudentName() :
                                 ((OnlineQuiz) exam).getStudentName());

            String examType = exam.getClass().getSimpleName();

            double marks = exam.evaluateMarks();
            String grade = exam.calculateGrade();

            System.out.printf("%-20s %-12s %-10.1f %-12s%n",
                    studentName, examType, marks, grade);
        }

        System.out.println("\nResult processing completed.");
    }
}
