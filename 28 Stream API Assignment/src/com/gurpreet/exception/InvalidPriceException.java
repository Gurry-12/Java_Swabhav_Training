package com.gurpreet.exception;

public class InvalidPriceException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidPriceException(String message) {
        super(message);
    }
}