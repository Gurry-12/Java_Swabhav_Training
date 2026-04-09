package com.collections.hospital.models;

public class InvalidPatientException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidPatientException(String message) {
        super(message);
    }
}