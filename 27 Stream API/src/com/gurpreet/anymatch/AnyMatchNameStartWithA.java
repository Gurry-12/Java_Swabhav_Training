package com.gurpreet.anymatch;

import java.util.List;

public class AnyMatchNameStartWithA {
    public static void main(String[] args) {
        List<String> names = List.of("Rahul", "Priya", "Amit", "Suresh", "Anjali");

        boolean hasA = names.stream().anyMatch(name -> name.startsWith("A"));

        System.out.println("Any name starts with 'A': " + hasA);
    }
}