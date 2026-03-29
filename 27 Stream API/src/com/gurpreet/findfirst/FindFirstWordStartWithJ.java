package com.gurpreet.findfirst;

import java.util.List;

public class FindFirstWordStartWithJ {
    public static void main(String[] args) {
        List<String> words = List.of("hello", "java", "python", "javascript", "ruby", "junit");

        String firstJWord = words.stream()
                                 .filter(w -> w.toLowerCase().startsWith("j"))
                                 .findFirst()
                                 .orElse("No word found");

        System.out.println("First word starting with 'J': " + firstJWord);
    }
}