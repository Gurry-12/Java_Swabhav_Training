package com.basics.introduction;


public class Operators {

	public static void main(String[] args) {

		System.out.println("Arithmetic:");
		System.out.println("4 + 3 :" + (4 + 3));
		System.out.println("4 - 3 :" + (4 - 3));
		System.out.println("4 * 3 :" + (4 * 3));
		System.out.println("4 / 3 :" + (4 / 3));
		System.out.println("4 % 3 :" + (4 % 3));

		System.out.println("Relational: ");
		System.out.println("4 == 4 :" + (4 == 4));
		System.out.println("4 != 4 :" + (4 != 4));
		System.out.println("4 > 4 :" + (4 > 4));
		System.out.println("4 < 4 :" + (4 < 4));
		System.out.println("4 >= 4 :" + (4 >= 4));
		System.out.println("4 <= 4 :" + (4 <= 4));

		System.out.println("Logical : ");
		System.out.println("4 > 3 && 3  < 5 :" + (4 > 3 && 3 < 5));
		System.out.println("4 > 5 || 3 < 5 :" + (4 > 5 || 3 < 5));
		System.out.println("! 4 > 4 :" + !(4 > 4));

		System.out.println("Assignment : ");
		int x = 10;
		System.out.println("x : " + x);
		System.out.println(" x += 10 : " + (x += 10));
		System.out.println(" x -= 10 : " + (x -= 10));
		System.out.println(" x *= 10 : " + (x *= 10));
		System.out.println(" x /= 10 : " + (x /= 10));

		System.out.println("Increment : ");
		int a = 10;
		System.out.println("Before increment " + a);
		System.out.println("During post increment : " + a++);
		System.out.println("After post increment : " + a);
		System.out.println("During pre increment : " + ++a);
		System.out.println("After pre increment : " + a);
		
		System.out.println("Decrement : ");
		a = 10;
		System.out.println("Before decrement " + a);
		System.out.println("During post decrement : " + a--);
		System.out.println("After post decrement : " + a);
		System.out.println("During pre decrement : " + --a);
		System.out.println("After pre decrement : " + a);
	}

}
