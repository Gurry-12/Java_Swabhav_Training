package com.collections.course.models;

public class InvalidParticipantException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidParticipantException(String message) {
        super(message);
    }
}