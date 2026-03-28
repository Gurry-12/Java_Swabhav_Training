package com.collections.course.models;

import com.collections.course.abstracts.Participant;
import com.collections.course.enums.Track;
import com.collections.course.exceptions.InvalidParticipantException;

public class CorporateParticipant extends Participant {

    private static long counter = 5000;
    private static final String PREFIX = "CP";

    private String companyName;

    public CorporateParticipant(String id, String name, Track track, String batchName, String companyName)
            throws InvalidParticipantException {

        super(id, name, track, batchName, PREFIX, counter++);

        if (companyName == null || companyName.trim().isEmpty()) {
            throw new InvalidParticipantException("Company name can't be empty");
        }

        this.companyName = companyName;
    }

    @Override
    public void printDetails() {
        System.out.println(" Participant ID  : " + getId());
        System.out.println(" Type            : Corporate Participant");
        System.out.println(" Name            : " + getName());
        System.out.println(" Track           : " + getTrack());
        System.out.println(" Batch           : " + getBatchName());
        System.out.println(" Company         : " + companyName);
    }
}
