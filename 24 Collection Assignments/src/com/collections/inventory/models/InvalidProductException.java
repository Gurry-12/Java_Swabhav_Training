package com.collections.inventory.models;

public class InvalidProductException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidProductException(String message) {
        super(message);
    }
}