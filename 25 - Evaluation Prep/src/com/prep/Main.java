package com.prep;
//
//interface Speakable {
//	public void speak();
//}
//
//abstract class Animal implements Speakable {
//	
//	
//}
//
//class Dog extends Animal {
//	
//	@Override
//	void speak() {
//		System.out.println("Bark");
//	}
//	
//}
//
//public class Main {
//
//	public static void main(String[] args) {
//		Animal dog = new Dog();
//		dog.speak();
//
//	}
//
//}

class Dog {
	
	protected static int a = 10;
	int b = 20;
			
}

class Math {
	
	void printInt(int a){
		System.out.println(a);
	}

	void printInt(Integer a){
		System.out.println(a);
	}

}

public class Main {

	public static void main(String[] args) {
		Dog dog = new Dog();
		System.out.println(Dog.a);
		System.out.println(dog.b);
	}

}
