package com.gurpreet.findfirst;

import java.util.List;

public class FindFirstEvenNumber {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(45, 67, 34, 89, 22, 56, 91);

        Integer firstEven = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .findFirst()
                                   .orElse(-1);

        System.out.println("First Even Number: " + firstEven);
    }
}