package com.gurpreet.exception.test;

public class ArrayExceptions {

	public static void main(String[] args) {
		
		String[] array = {"Java Batch", "Gurpreet"};
		
		try {
			System.out.println("The fifth element is " + array[4]);
			
			String s = null;
			System.out.println("Print null string length  "+ s.length());
					
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Array out of bound. " + e);
		}
		catch (NullPointerException e) {
			System.out.println("Error: Array element should not be null. " + e);
		}
		
		

	}

}
