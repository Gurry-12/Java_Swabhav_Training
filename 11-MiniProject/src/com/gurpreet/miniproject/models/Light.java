package com.gurpreet.miniproject.models;

import com.gurpreet.miniproject.interfaces.Controllable;

public class Light implements Controllable {

	@Override
	public void turnOn() {
		System.out.println("Light turn ON. ....");

	}

	@Override
	public void turnOff() {
		System.out.println("Light turn OFF. ....");

	}

	@Override
	public void setMode(String mode) {
		System.out.println("Light mode set to: " + mode);

	}

}
