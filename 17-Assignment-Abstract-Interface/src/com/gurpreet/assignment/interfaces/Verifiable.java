package com.gurpreet.assignment.interfaces;

import com.gurpreet.assignment.exceptions.InvalidOrderException;

public interface Verifiable {
    boolean verifyOrder() throws InvalidOrderException;
}