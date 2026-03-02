package com.oops.introduction.inheritance;

class Animal {
	public void sound() {
		System.out.println("Animal Sound....");
	}
}

// inheritance
// child class 
class Dog extends Animal {
	public void sound() {
		System.out.println("Dog bark ....");
	}
}

// child 
class Cow extends Animal {
	public void sound() {
		System.out.println("Cow moo .....");
	}
}

public class InheritanceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Animal animal;
		animal = new Animal();

		animal.sound();

		animal = new Dog();
		animal.sound();

		animal = new Cow();
		animal.sound();

	}

}
