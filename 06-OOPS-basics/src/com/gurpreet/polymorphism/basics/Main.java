package com.gurpreet.polymorphism.basics;

class Animal {
    void eat() {
        System.out.println("Animal eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog a = new Dog();
        a.eat();
        a.bark();
    }
}