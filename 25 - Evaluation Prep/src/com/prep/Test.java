package com.prep;

interface I {
	public void printA();
}

class A implements I{
	private int x;
	
	public A(int x) {
		this.x = x;
	}
	
	public int getVal() {
		return x;
	}
	
//	public void setVal(int a) {
//		x = a;
//	}
	public void printA() {
		System.out.println("A class");
	}
}

//class B extends A {
//	
//	public void printA() {
//		//super.printA();
//		System.out.println("B class");
//	}
//	
//	public void printB() {
//		System.out.println("B class");
//	}
//	
//}

public class Test {
	
	public static void main(String[] args) {
		A a = new A(10);  // up casting 
	//	B b = (B) a;
		System.out.println(a.getVal());
		a.printA(); 
		
		//a.setVal(20);
		System.out.println(a.getVal());

	}
}
