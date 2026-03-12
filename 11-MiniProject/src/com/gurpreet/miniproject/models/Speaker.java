package com.gurpreet.miniproject.models;

import com.gurpreet.miniproject.interfaces.Controllable;

public class Speaker implements Controllable {

	@Override
	public void turnOn() {
		System.out.println("Speaker turn ON. ....");

	}

	@Override
	public void turnOff() {
		System.out.println("Speaker turn OFF. ....");

	}

	@Override
	public void setMode(String mode) {
		System.out.println("Speaker mode set to: " + mode);

	}

}
