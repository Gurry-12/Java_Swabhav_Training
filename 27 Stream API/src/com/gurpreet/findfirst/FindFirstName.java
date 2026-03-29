package com.gurpreet.findfirst;

import java.util.List;

public class FindFirstName {
    public static void main(String[] args) {
        List<String> names = List.of("Amit", "Rahul", "Priya", "Suresh", "Neha");

        String firstName = names.stream().findFirst().orElse("List is empty");

        System.out.println("First Name: " + firstName);
    }
}