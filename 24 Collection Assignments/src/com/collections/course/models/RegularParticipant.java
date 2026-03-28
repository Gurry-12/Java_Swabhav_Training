package com.collections.course.models;

import com.collections.course.abstracts.Participant;
import com.collections.course.enums.Track;
import com.collections.course.exceptions.InvalidParticipantException;

public class RegularParticipant extends Participant {

    private static long counter = 1000;
    private static final String PREFIX = "RP";

    private String educationLevel;

    public RegularParticipant(String id, String name, Track track, String batchName, String educationLevel)
            throws InvalidParticipantException {

        super(id, name, track, batchName, PREFIX, counter++);

        if (educationLevel == null || educationLevel.trim().isEmpty()) {
            throw new InvalidParticipantException("Education level can't be empty");
        }

        this.educationLevel = educationLevel;
    }

    @Override
    public void printDetails() {
        System.out.println(" Participant ID  : " + getId());
        System.out.println(" Type            : Regular Participant");
        System.out.println(" Name            : " + getName());
        System.out.println(" Track           : " + getTrack());
        System.out.println(" Batch           : " + getBatchName());
        System.out.println(" Education Level : " + educationLevel);
    }
}