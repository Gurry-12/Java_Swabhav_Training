package com.gurpreet.comparator.models;

public class Flight implements Comparable<Flight> {

	private String airline;
	private double fare;
	
	
	public Flight(String airline, double fare) {
		this.airline = airline;
		this.fare = fare;
	}
	public String getAirline() {
		return airline;
	}
	
	public double getFare() {
		return fare;
	}
	@Override
	public int compareTo(Flight flight) {
		
		return Double.compare(flight.fare, this.fare);
	}
	
	
	
}
