package com.collections.student.models;

import java.util.Comparator;

public class TotalMarksComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        // Descending: higher marks ranked first
        return Integer.compare(s2.getTotalMarks(), s1.getTotalMarks());
    }
}