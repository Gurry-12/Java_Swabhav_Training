package com.practice.main;

import com.practice.functionalinterface.Greeting;

public class GreetingMain {
public static void main(String[] args) {
	Greeting greet1 = name -> "Hello! " + name;
	
	System.out.println(greet1.greet("Gurii"));
	
}
}
