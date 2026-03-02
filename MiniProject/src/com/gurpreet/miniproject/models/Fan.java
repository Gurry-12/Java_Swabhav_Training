package com.gurpreet.miniproject.models;

import com.gurpreet.miniproject.interfaces.Controllable;

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
