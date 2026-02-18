package com.introduction;

public class DataTypes {
	
	// main 
	public static void main (String[] args) {
		
		
		// boolean data type 
		// 1. true 2. false 
		boolean isTrue = true;
		boolean isFalse = false;
		
		System.out.println("the value of isTure: " + isTrue);
		System.out.println("the valse of isFalse: " + isFalse);
		
		// byte 
		// it store the 8 bit signed integer 
		byte counter = 120;
		System.out.println(counter);
		
		// counter = 128; 
		// cannot convert from int to byte
		// actually the range of byte is -128 to 127, so the 128 is out of range either it can store in short or int
		short count = 128;
		System.out.println(count);
		
		counter = (byte) 128;
		System.out.println(counter);
		
		// this will start counting from -128 if it more thsn 127
		
		long worldPopulation = 7800000000L;
        long lightYears = 9460730472580800L;
        System.out.println("World Population: " + worldPopulation);
        System.out.println("Light Years: " + lightYears);
        
        // float
        float piFloat = 3.14F;
        System.out.println(piFloat);
        
        double piDouble = 3.141592653589793;
        System.out.println(piDouble);
        
        // string
        String name = "Gurpreet";
        System.out.println(name);
        
        name = "Gurpreet Singh";
        System.out.println(name);
	}
}
