package com.basics.introduction;


// class naming convention 
class Employee {
	// class variables 
	public String employeeName;
}

// Interface naming convention 
interface EmployeeList {}


public class NamingConvention {
	
	// method naming convention 
	public void namingConventionMethod() {}
	
	
	public static void main(String[] args) {
		
		//  Java heavily use the two type of naming conventions 
		/* 
		 * 1. Camel case = for - method name , variables, etc 
		 * 2. Title case = for - classes and interfaces 
		 */
		
		// package naming 
		/* 
		 * follow the com.packagename - structure
		 * 
		 *  ex: = package com.google.search.common;
		 */
		
		// variable naming convention
		int age = 20;
		String name = "Gurpreet";
		
		//Constant 
		final int ADULT_AGE = 18;
		
		System.out.println(age);
		System.out.println(name);
		System.out.println(ADULT_AGE);
		
		}
}
