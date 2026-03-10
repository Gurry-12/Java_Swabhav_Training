package com.gurpreet.miniproject.test;

import com.gurpreet.miniproject.interfaces.Controllable;
import com.gurpreet.miniproject.models.Fan;
import com.gurpreet.miniproject.models.Light;
import com.gurpreet.miniproject.models.Speaker;
import com.gurpreet.miniproject.models.TV;

public class ControlTest {

	public static void main(String[] args) {
		Controllable[] devices = new Controllable[4];
        devices[0] = new Light();
        devices[1] = new Fan();
        devices[2] = new TV();
        devices[3] = new Speaker();

        String[] modes = {"Dim", "High", "HD", "Bass"};

       
        for (int i = 0; i < devices.length; i++) {
            devices[i].turnOn();
            devices[i].setMode(modes[i]);
            devices[i].turnOff();
            System.out.println("----------------------------");
        }

	}

}
