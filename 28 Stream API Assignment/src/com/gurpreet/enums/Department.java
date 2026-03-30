package com.gurpreet.enums;

public enum Department {

    HR("Human Resources"),
    IT("Information Technology"),
    FINANCE("Finance"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    SALES("Sales"),
    ENGINEERING("Engineering"),
    ADMIN("Administration");

    private final String fullName;

    Department(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName ;   
    }
}
