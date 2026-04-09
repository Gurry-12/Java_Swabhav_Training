package com.collections.course.models;

import java.util.Comparator;

public class BatchNameComparator implements Comparator<Participant> {

    @Override
    public int compare(Participant p1, Participant p2) {
        int batchResult = p1.getBatchName().compareToIgnoreCase(p2.getBatchName());
        if (batchResult != 0) return batchResult;
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}
