package com.collections.course.exceptions;

public class InvalidParticipantException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidParticipantException(String message) {
        super(message);
    }
}