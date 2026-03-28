package com.consumer.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class PrintListConsumer {

    public static void main(String[] args) {

        Consumer<List<String>> printList = list -> list.forEach(System.out::println);

        List<String> fruits = Arrays.asList("Apple", "Banana", "Mango", "Orange", "Grapes");

        printList.accept(fruits);
    }
}