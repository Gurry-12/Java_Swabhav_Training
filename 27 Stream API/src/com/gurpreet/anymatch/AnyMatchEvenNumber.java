package com.gurpreet.anymatch;

import java.util.List;

public class AnyMatchEvenNumber {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(45, 67, 33, 89, 22, 91);

        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);

        System.out.println("Contains any even number: " + hasEven);
    }
}