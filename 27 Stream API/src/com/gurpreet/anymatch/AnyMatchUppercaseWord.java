package com.gurpreet.anymatch;

import java.util.List;

public class AnyMatchUppercaseWord {
    public static void main(String[] args) {
        List<String> words = List.of("hello", "JAVA", "python", "Code", "STREAM");

        boolean hasUpper = words.stream().anyMatch(w -> w.equals(w.toUpperCase()));

        System.out.println("Any word entirely in uppercase: " + hasUpper);
    }
}
