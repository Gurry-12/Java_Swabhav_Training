package com.gurpreet.miniproject.models;

public class Fan implements Controllable {

	@Override
	public void turnOn() {
		System.out.println("Fan turn On. ....");

	}

	@Override
	public void turnOff() {
		System.out.println("Fan turn Off . ....");

	}

	@Override
	public void setMode(String mode) {
		System.out.println("Fan mode set to: " + mode);

	}

}
