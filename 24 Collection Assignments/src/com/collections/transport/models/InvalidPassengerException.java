package com.collections.transport.models;

public class InvalidPassengerException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidPassengerException(String message) {
        super(message);
    }
}