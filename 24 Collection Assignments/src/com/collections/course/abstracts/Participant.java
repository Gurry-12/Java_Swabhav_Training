package com.collections.course.abstracts;

import java.util.Objects;

import com.collections.course.enums.Track;
import com.collections.course.exceptions.InvalidParticipantException;

public abstract class Participant implements Comparable<Participant> {

    private String id;
    private String name;
    private Track track;
    private String batchName;

    public Participant(String id, String name, Track track, String batchName, String prefix, long counter)
            throws InvalidParticipantException {
    	if (id == null || id.trim().isEmpty()) {
            throw new InvalidParticipantException("Participant Id can't be empty");
        }
    	
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidParticipantException("Participant name can't be empty");
        }
        if (track == null) {
            throw new InvalidParticipantException("Track can't be null");
        }
        if (batchName == null || batchName.trim().isEmpty()) {
            throw new InvalidParticipantException("Batch name can't be empty");
        }

        this.id = id;
        this.name = name;
        this.track = track;
        this.batchName = batchName;
    }

    public String getId()        { return id; }
    public String getName()      { return name; }
    public String getTrack()     { return track.toString(); }
    public String getBatchName() { return batchName; }

    // Natural ordering: alphabetical by name
    @Override
    public int compareTo(Participant other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Duplicate: id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Participant)) return false;
        Participant other = (Participant) obj;
        return other.id.equalsIgnoreCase(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public abstract void printDetails();
}
