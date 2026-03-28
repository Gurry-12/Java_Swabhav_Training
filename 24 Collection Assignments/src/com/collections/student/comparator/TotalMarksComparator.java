package com.collections.student.comparator;

import java.util.Comparator;

import com.collections.student.abstracts.Student;

public class TotalMarksComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        // Descending: higher marks ranked first
        return Integer.compare(s2.getTotalMarks(), s1.getTotalMarks());
    }
}