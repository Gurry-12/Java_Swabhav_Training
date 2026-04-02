package com.gurpreet.miniproject.models;

public class TV implements Controllable {

	@Override
	public void turnOn() {
		System.out.println("TV turn ON. ....");

	}

	@Override
	public void turnOff() {
		System.out.println("TV turn OFF. ....");

	}

	@Override
	public void setMode(String mode) {
		System.out.println("TV mode set to: " + mode);

	}

}
